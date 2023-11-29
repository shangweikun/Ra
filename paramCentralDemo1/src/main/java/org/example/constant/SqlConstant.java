package org.example.constant;

public class SqlConstant {

    public static final String MAIN_VERSION_TABLE_SELECT
            = "select max(main_version) from main_version_table";

    public static final String MAIN_VERSION_TABLE_INSERT
            = "insert main_version_table (main_version) values (:1)";

    public static final String TABLE_VERSION_TABLE_SELECT
            = "select max(table_version) from table_version_table where table_name = :1";

    public static final String TABLE_VERSION_TABLE_INSERT
            = "insert main_version_table (table_version,table_name) values (:1,:2)";

    public static final String M2T_RELATION_TABLE_INSERT
            = "insert m2t_relation_table (main_version, table_version, table_name) values (:1, :2, :3)";


}
