#!/bin/sh

#日期
DATE=$(date +'%Y%m%d')

PATH_ROOT="/appshare/attachment"
PATH_FUNCTION_DIR="$PATH_ROOT/person"

#SHELL
PATH_SHELL="/dbshare/shell/file_batch"

#IFT
PATH_IFT="$PATH_ROOT/ift_data"
PATH_DATA_DIR="$PATH_IFT/receive/person"

#BACKUP
PATH_BACK_DIR="$PATH_FUNCTION_DIR/backup"

#LOG
PATH_LOG_DIR="$PATH_FUNCTION_DIR/log"
PATH_LOG_FILE="$PATH_LOG_DIR/hrs_person_$DATE.log"

#数据库
DB_USERID="$DBUSER/$DBPASSWD"
DB_CONTROL_FILE="$PATH_SHELL/hrs_person_info.ctl"
DB_SQLLDR_LOG_FILE="$PATH_LOG_DIR/hrs_person_sql_loader_$DATE.log"
DB_SQLLDR_BAD_FILE="$PATH_LOG_DIR/hrs_person_back_up_$DATE.dat"

#DATA
HRS_PERSON_FILE_DIR=$PATH_DATA_DIR/$DATE
HRS_PERSON_FILE_NAME_PREFIX="HRS_EBDC_"
HRS_PERSON_FILE_NAME_SUFFIX="_001"
HRS_PERSON_TASK_TITLE="HRS person info"

#env
#dev
#PATH_IFT="/d/tmp/shellWorkspace"
#HRS_PERSON_FILE_DIR=$PATH_IFT/$DATE
#test=dev
#PATH_IFT="/share_file/shangweikun/hrs_person_test"
#HRS_PERSON_FILE_DIR=$PATH_IFT/$DATE
#DB_CONTROL_FILE="$PATH_IFT/hrs_person_info.ctl"
#DB_SQLLDR_LOG_FILE="$PATH_IFT/hrs_person_sql_loader.log"
#DB_SQLLDR_BAD_FILE="$PATH_IFT/hrs_person_back_up.txt"

echo "$(date +'%Y-%m-%d %H:%M:%S'): File start to parse [title: $HRS_PERSON_TASK_TITLE, date: $DATE]" >> $PATH_LOG_FILE

sqlplus -s "$DB_USERID" <<EOF
set echo off feedback off heading off underline off;
merge INTO EBDCNEW.DB_SHELL_TASK_INFO A
using (
    select nvl((select task_id
                from EBDCNEW.DB_SHELL_TASK_INFO B
                where B.TASK_TITLE = to_char('$HRS_PERSON_TASK_TITLE')
                  and B.TASK_DATE = to_char('$DATE')
               ), 0) as TASK_ID
    from dual
) T
on (T.TASK_ID = A.TASK_ID)
when not matched then
    INSERT (TASK_ID,
            TASK_TITLE,
            TASK_DATE,
            TASK_BEGIN_TIME,
            STATUS,
            STATUS_TXT)
    values (SEQ_DSTI_TASK_ID.nextval, to_char('$HRS_PERSON_TASK_TITLE'), to_char('$DATE'),
            sysdate, '00', 'task start;')
/
commit
/
EOF

taskId=$(sqlplus -s "$DB_USERID" <<EOF
set echo off feedback off heading off underline off;
select task_id from DB_SHELL_TASK_INFO where TASK_TITLE='$HRS_PERSON_TASK_TITLE' and TASK_DATE='$DATE'
/
exit
/
EOF
)

echo "$(date +'%Y-%m-%d %H:%M:%S'): taskId is $taskId" >> $PATH_LOG_FILE

if  ! find $PATH_DATA_DIR -ctime 0 -name "$HRS_PERSON_FILE_NAME_PREFIX$DATE$HRS_PERSON_FILE_NAME_SUFFIX.tar"  ; then
    echo "$HRS_PERSON_FILE_NAME_PREFIX$DATE$HRS_PERSON_FILE_NAME_SUFFIX.tar file do not find, then exit" >> $PATH_LOG_FILE
    exit 0
fi

if  [ ! 1 -eq $(find $PATH_DATA_DIR -ctime 0 -name "$HRS_PERSON_FILE_NAME_PREFIX$DATE$HRS_PERSON_FILE_NAME_SUFFIX.tar" | wc -l ) ] ; then
    echo "$HRS_PERSON_FILE_NAME_PREFIX$DATE$HRS_PERSON_FILE_NAME_SUFFIX.tar file has find multiple files, then exit" >> $PATH_LOG_FILE
    exit 0
fi

FILE_NAME=$(find $PATH_DATA_DIR -ctime 0 -name "$HRS_PERSON_FILE_NAME_PREFIX$DATE$HRS_PERSON_FILE_NAME_SUFFIX.tar")

#判断文件夹并清理数据
if [ ! -d $HRS_PERSON_FILE_DIR ] ; then
    mkdir -p $HRS_PERSON_FILE_DIR
    echo "$HRS_PERSON_FILE_DIR create success" >> $PATH_LOG_FILE
else
    cd $HRS_PERSON_FILE_DIR || exit 0
    rm -rf ./*.txt
    rm -rf ./*.jpg
    echo "$HRS_PERSON_FILE_DIR all files clear success" >> $PATH_LOG_FILE
fi

#backup the file
tar xvf $FILE_NAME --directory=$HRS_PERSON_FILE_DIR >> $PATH_LOG_FILE

if  ! find $HRS_PERSON_FILE_DIR -ctime 0 -name "$HRS_PERSON_FILE_NAME_PREFIX$DATE.txt"  ; then
    echo "$HRS_PERSON_FILE_NAME_PREFIX$DATE.txt file do not find, then exit" >> $PATH_LOG_FILE
    exit 0
fi

if  [ ! 1 -eq $(find $HRS_PERSON_FILE_DIR -ctime 0 -name "$HRS_PERSON_FILE_NAME_PREFIX$DATE.txt" | wc -l ) ] ; then
    echo "$HRS_PERSON_FILE_NAME_PREFIX$DATE.txt file has find multiple files, then exit" >> $PATH_LOG_FILE
    exit 0
fi

FILE_NAME=$(find $HRS_PERSON_FILE_DIR -ctime 0 -name "$HRS_PERSON_FILE_NAME_PREFIX$DATE.txt")

echo "$(date +'%Y-%m-%d %H:%M:%S'): $FILE_NAME unzip success" >> $PATH_LOG_FILE

echo "$(date +'%Y-%m-%d %H:%M:%S'): File get $FILE_NAME success" >> $PATH_LOG_FILE
sqlplus -s "$DB_USERID" <<EOF
        set echo off feedback off heading off underline off;
        update DB_SHELL_TASK_INFO set status='01',
                                status_txt='get file success;'
        where TASK_ID='$taskId'
        /
        commit
        /
EOF

echo "$(date +'%Y-%m-%d %H:%M:%S'): DataBase SqlLoader Start load data" >> $PATH_LOG_FILE
sqlldr userid="$DB_USERID" control="$DB_CONTROL_FILE" data="$FILE_NAME" log="$DB_SQLLDR_LOG_FILE" bad="$DB_SQLLDR_BAD_FILE" >> $PATH_LOG_FILE

if [ $? -eq 0 ] ; then
  sqlplus -s "$DB_USERID" <<EOF
        set echo off feedback off heading off underline off;
        update DB_SHELL_TASK_INFO set status='02',
                                status_txt='script:file load success;',
                                SCRIPT_DEAL_END_TIME=sysdate,
                                SCRIPT_DEAL_COST=to_char(sysdate-SCRIPT_DEAL_BEGIN_TIME)
        where TASK_ID=$taskId
        /
        commit
        /
EOF
  echo "$(date +'%Y-%m-%d %H:%M:%S'): DataBase SqlLoader has loader data success" >> $PATH_LOG_FILE
else
  sqlplus -s "$DB_USERID" <<EOF
        set echo off feedback off heading off underline off;
        update DB_SHELL_TASK_INFO set status='92',
                                status_txt='script:file load fail;',
                                SCRIPT_DEAL_END_TIME=sysdate,
                                SCRIPT_DEAL_COST=to_char(sysdate-SCRIPT_DEAL_BEGIN_TIME)
        where TASK_ID=$taskId
        /
        commit
        /
EOF
  echo "$(date +'%Y-%m-%d %H:%M:%S'): DataBase SqlLoader has loader data fail" >> $PATH_LOG_FILE
fi


mv $PATH_DATA_DIR/*.tar $PATH_BACK_DIR/.
