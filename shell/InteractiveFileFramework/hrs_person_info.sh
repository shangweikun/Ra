#!/bin/sh

startParseFileSql (){
  echo "$(date +'%Y-%m-%d %H:%M:%S'): File start to parse [title: $TASK_TITLE, date: $DATE]" >> $PATH_LOG_FILE
  sqlplus -s "$DB_USERID" <<EOF
set echo off feedback off heading off underline off;
merge INTO EBDCNEW.DB_SHELL_TASK_INFO A
using (
    select nvl((select task_id
                from EBDCNEW.DB_SHELL_TASK_INFO B
                where B.TASK_TITLE = to_char('$TASK_TITLE')
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
    values (SEQ_DSTI_TASK_ID.nextval, to_char('$TASK_TITLE'), to_char('$DATE'),
            sysdate, '00', 'task start;')
/
commit
/
EOF
}

getTaskId(){
  id=$(sqlplus -s "$DB_USERID" <<EOF
set head off
set linesize 20000
set echo off
set feedback off
set pagesize 0
set termout off
set trimout on
set trimspool on
select task_id from DB_SHELL_TASK_INFO where TASK_TITLE='$TASK_TITLE' and TASK_DATE='$DATE'
/
exit
/
EOF
)
  echo "$(date +'%Y-%m-%d %H:%M:%S'): taskId is $id" >> $PATH_LOG_FILE
  return $id
}

getSwitch (){
  switch=$(sqlplus -s "$DB_USERID" <<EOF
set head off
set linesize 20000
set echo off
set feedback off
set pagesize 0
set termout off
set trimout on
set trimspool on
select PARAMVALUE from FBAS_SYSPM where PARAMNAME='HRS_OK_FILE_CHECK_SWITCH'
/
exit
/
EOF
  )
  return $switch
}

getFileSuccessSql (){
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
}

scriptParseFileSuccessSql(){
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
}

scriptParseFileFailSql(){
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
}

checkIsTheTargetFileIsOk (){
  
  echo "$(date +'%Y-%m-%d %H:%M:%S'): checkIsTheTargetFileIsOk begin fileName is[$1] " >> $PATH_LOG_FILE
  while true 
  do
    echo "$(date +'%Y-%m-%d %H:%M:%S'): check fileName is[$1] " >> $PATH_LOG_FILE
    find $PATH_DATA_DIR -name "$1" >> $PATH_LOG_FILE
    if [ 0 -lt $( find $PATH_DATA_DIR -name "$1" | wc -l ) ] ; then {
      echo "$(date +'%Y-%m-%d %H:%M:%S'): file [$1] is found , ift send success " >> $PATH_LOG_FILE
      break
    }
    fi

    if [ $(date +'%Y%m%d%H%M') -gt $SHELL_LOOP_END_TIME ] ; then {
      echo "$(date +'%Y-%m-%d %H:%M:%S'): loop out time, break loop " >> $PATH_LOG_FILE
      break
    }
    fi

    getSwitch
    switch=$?
    if [ $switch -eq "0" ] ; then {
      echo "$(date +'%Y-%m-%d %H:%M:%S'): switch is [$switch] , break loop" >> $PATH_LOG_FILE
      break
    }
    fi
    sleep 10
  done
}

findFileName(){

  if  [ ! 1 -eq $(find $PATH_IFT -ctime 0 -name "$1" | wc -l ) ] ; then
      echo "$1 file has find nor or multiple files, then exit" >> $PATH_LOG_FILE
      exit 0
  fi
  FILE_NAME=$(find $PATH_IFT -ctime 0 -name "$1")
}

backupAndClearHistoryFile(){

  echo "$(date +'%Y-%m-%d %H:%M:%S'): history file clear" >> $PATH_LOG_FILE

  #unzip files
  if [ 0 -lt $( find $PATH_DATA_DIR -type d -maxdepth 1 -name "[0-9]*" -ctime 7 | wc -l ) ] ; then {
      echo "$(date +'%Y-%m-%d %H:%M:%S'): rm unzip files" >> $PATH_LOG_FILE
      find $PATH_DATA_DIR -type d -maxdepth 1 -name "[0-9]*" -ctime 7 -exec rm -r {} \; >> $PATH_LOG_FILE
  }
  fi

  #tar file
  if [ 0 -lt $( find $PATH_DATA_DIR -maxdepth 1 -name "$FILE_NAME_PREFIX[0-9]*$FILE_NAME_SUFFIX.tar" | wc -l ) ] ; then {
      echo "$(date +'%Y-%m-%d %H:%M:%S'): rm -rf $PATH_DATA_DIR/$FILE_NAME_PREFIX[0-9]*$FILE_NAME_SUFFIX.tar" >> $PATH_LOG_FILE
      find $PATH_DATA_DIR -maxdepth 1 -name "$FILE_NAME_PREFIX[0-9]*$FILE_NAME_SUFFIX.tar" -ctime 7 -exec rm {} \; >> $PATH_LOG_FILE
  }
  fi

  #log
  if [ 0 -lt $( find $PATH_LOG_DIR -maxdepth 1 -name "hrs_person_[0-9]*.log" | wc -l ) ] ; then {
      echo "$(date +'%Y-%m-%d %H:%M:%S'): rm -rf $PATH_LOG_DIR/hrs_person_[0-9]*.log" >> $PATH_LOG_FILE
      find $PATH_LOG_DIR -maxdepth 1 -name "hrs_person_[0-9]*.log" -ctime 7 -exec rm -r {} \;
  }
  fi

  #sqlloader log
  if [ 0 -lt $( find $PATH_LOG_DIR -maxdepth 1 -name "hrs_person_sql_loader_[0-9]*.log" -ctime 7  | wc -l ) ] ; then {
      echo "$(date +'%Y-%m-%d %H:%M:%S'): rm -rf $PATH_LOG_DIR/hrs_person_sql_loader_[0-9]*.log" >> $PATH_LOG_FILE
      find $PATH_LOG_DIR -maxdepth 1 -name "hrs_person_sql_loader_[0-9]*.log" -ctime 7 rm -r {} \; >> $PATH_LOG_FILE
  }
  fi

  #sqlloader bad file
  if [ 0 -lt $( find $PATH_LOG_DIR -maxdepth 1 -name "hrs_person_back_up_[0-9]*.dat" -ctime 7 | wc -l ) ] ; then {
      echo "$(date +'%Y-%m-%d %H:%M:%S'): rm -rf $PATH_LOG_DIR/hrs_person_back_up_[0-9]*.dat" >> $PATH_LOG_FILE
      find $PATH_LOG_DIR -maxdepth 1 -name "hrs_person_back_up_[0-9]*.dat" -ctime 7 -exec rm -r {} \; >> $PATH_LOG_FILE
  }
  fi
}

main(){

  startParseFileSql

  getTaskId

  taskId=$?

  checkIsTheTargetFileIsOk "$FILE_NAME_PREFIX$DATE$FILE_NAME_SUFFIX.xml" 

  findFileName "$FILE_NAME_PREFIX$DATE$FILE_NAME_SUFFIX.tar"

  if [ ! -d $FILE_DIR ] ; then
      mkdir $FILE_DIR
      echo "$FILE_DIR create success" >> $PATH_LOG_FILE
  else
      rm -rf $FILE_DIR/*.txt
      rm -rf $FILE_DIR/*.jpg
      echo "$FILE_DIR all files clear success" >> $PATH_LOG_FILE
  fi

  #backup the file
  tar xvf $FILE_NAME --directory=$FILE_DIR >> $PATH_LOG_FILE

  findFileName "$FILE_NAME_PREFIX$DATE.txt"

  getFileSuccessSql

  echo "$(date +'%Y-%m-%d %H:%M:%S'): DataBase SqlLoader Start load data" >> $PATH_LOG_FILE
  sqlldr userid="$DB_USERID" control="$DB_CONTROL_FILE" data="$FILE_NAME" log="$DB_SQLLDR_LOG_FILE" bad="$DB_SQLLDR_BAD_FILE" >> $PATH_LOG_FILE

  if [ $? -eq 0 ] ; then
    scriptParseFileSuccessSql
  else
    scriptParseFileFailSql
  fi

  backupAndClearHistoryFile
}

#DATE
DATE=$(date +'%Y%m%d')
PRE_DATE=$(date -d "-7day" +'%Y%m%d')

#LOOP-TIME
SHELL_LOOP_END_TIME=$(date -d "1hour" +'%Y%m%d%H%M') 

ARGS=`getopt -a -o a:b:c:d:e:f:g:h: --long FunctionPath:,ShellPath:,DataPath:,ControlFile:,SqlLoaderPreffix:,FileNamePreffix:,FileNameSuffix:,TaskTitle: -- "$@"`

echo "$@"

eval set -- "$ARGS"
while true ; do
        case "$1" in
                -a|--FunctionPath) echo "Option FunctionPath $2";PATH_FUNCTION_DIR="$2";shift ;;
                -b|--ShellPath) echo "Option ShellPath $2";PATH_SHELL="$2";shift ;;
                -c|--DataPath) echo "Option DataPath $2";PATH_DATA_DIR="$2";shift ;;
                -d|--ControlFile) echo "Option ControlFile $2";CONTROL_FILE="$2";shift ;;
                -e|--SqlLoaderPreffix) echo "Option SqlLoaderPreffix $2";SQLLOADER_PREFFIX="$2";shift ;;
                -f|--FileNamePreffix) echo "Option FileNamePreffix $2";FILE_NAME_PREFIX="$2";shift ;;
                -g|--FileNameSuffix) echo "Option FileNameSuffix $2";FILE_NAME_SUFFIX="$2";shift ;;
                -h|--TaskTitle) echo "Option TaskTitle $2";TASK_TITLE="$2";shift ;;
                --) shift ; break ;;
        esac
shift
done
echo "Parase arguments"

#LOG
PATH_LOG_DIR="$PATH_FUNCTION_DIR/log"
PATH_LOG_FILE="$PATH_LOG_DIR/$FILE_NAME_PREFIX$DATE.log"

#DATABASE
DB_USERID="$DBUSER/$DBPASSWD"
DB_CONTROL_FILE="$PATH_SHELL/$CONTROL_FILE"
DB_SQLLDR_LOG_FILE="$PATH_LOG_DIR/$SQLLOADER_PREFFIX$DATE.log"
DB_SQLLDR_BAD_FILE="$PATH_LOG_DIR/$SQLLOADER_PREFFIX$DATE.dat"

#DATA
FILE_DIR=$PATH_DATA_DIR/$DATE

main
echo 0