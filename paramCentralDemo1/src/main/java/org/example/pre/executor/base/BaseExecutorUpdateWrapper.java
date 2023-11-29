package org.example.pre.executor.base;

import org.apache.ibatis.executor.BaseExecutor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Invocation;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

import static org.example.constant.SdkConstant.*;
import static org.example.constant.SqlConstant.*;

public class BaseExecutorUpdateWrapper {

    public static final String methodName = "update";

    public static Object update(Invocation invocation)
            throws InvocationTargetException, IllegalAccessException {

        Object[] args = invocation.getArgs();
        BaseExecutor baseExecutor = (BaseExecutor) invocation.getTarget();
        MappedStatement statement = (MappedStatement) args[0];

        //获取关键表信息
        String id = statement.getId();
        String tableName = statementId2TableName.get(id);

        if (tableName == null || tableName.isEmpty()) {
            throw new RuntimeException(
                    String.format("statementId %s do not set tableName", id)
            );
        }

        int currentMainVersion = getCurrentMainVersion(baseExecutor);
        lockMainVersionTable(baseExecutor, currentMainVersion);

        int currentTableVersion = getTableVersion(baseExecutor, tableName);
        updateTableVersionTable(baseExecutor, tableName, currentTableVersion);

        Object result = invocation.proceed();

        insertM2TRelation(baseExecutor, tableName,
                currentMainVersion + 1, currentTableVersion + 1);

        return result;

    }

    private static void insertM2TRelation(BaseExecutor baseExecutor, String tableName,
                                          int aftMainVersion, int aftTableVersion) {
        try {
            Connection connection = baseExecutor.getTransaction().getConnection();

            if (connection.getAutoCommit()) {
                throw new RuntimeException("参数维护扩展功能异常，当前连接为自动提交 5");
            }

            PreparedStatement preparedStatement = connection.prepareStatement(M2T_RELATION_TABLE_INSERT);
            preparedStatement.setInt(1, aftMainVersion);
            preparedStatement.setInt(2, aftTableVersion);
            preparedStatement.setString(3, tableName);
            int i = preparedStatement.executeUpdate();
            if (i != 1) {
                throw new RuntimeException(
                        String.format("参数维护交易报错，主版本与参数表【%s】版本号关联异常 1", tableName)
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(
                    String.format("参数维护交易报错，主版本与参数表【%s】版本号关联异常 2", tableName), e);
        }

    }

    private static void updateTableVersionTable(BaseExecutor baseExecutor,
                                                String tableName, int currentTableVersion) {

        try {
            Connection connection = baseExecutor.getTransaction().getConnection();

            if (connection.getAutoCommit()) {
                throw new RuntimeException("参数维护扩展功能异常，当前连接为自动提交 4");
            }

            PreparedStatement preparedStatement = connection.prepareStatement(TABLE_VERSION_TABLE_INSERT);
            preparedStatement.setInt(1, currentTableVersion + 1);
            preparedStatement.setString(2, tableName);
            int i = preparedStatement.executeUpdate();
            if (i != 1) {
                throw new RuntimeException(
                        String.format("参数维护交易报错，参数表【%s】版本号加锁异常 1", tableName)
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(
                    String.format("参数维护交易报错，参数表【%s】版本号加锁异常 2", tableName), e);
        }
    }

    private static int getTableVersion(BaseExecutor baseExecutor, String tableName) {

        int currentTableVersion;
        try {
            Connection connection = baseExecutor.getTransaction().getConnection();

            if (connection.getAutoCommit()) {
                throw new RuntimeException("参数维护扩展功能异常，当前连接为自动提交 3");
            }

            PreparedStatement sqlStatement = connection.prepareStatement(TABLE_VERSION_TABLE_SELECT);
            sqlStatement.setString(1, tableName);
            ResultSet resultSet = sqlStatement.executeQuery();
            currentTableVersion = resultSet.getInt(TABLE_VERSION_COLUMN_NAME);
        } catch (SQLException e) {
            throw new RuntimeException(
                    String.format("获取参数表【%s】版本号报错:", tableName), e);
        }
        return currentTableVersion;

    }

    private static void lockMainVersionTable(BaseExecutor baseExecutor, int currentMainVersion) {
        try {
            Connection connection = baseExecutor.getTransaction().getConnection();

            if (connection.getAutoCommit()) {
                throw new RuntimeException("参数维护扩展功能异常，当前连接为自动提交 2");
            }

            PreparedStatement preparedStatement = connection.prepareStatement(MAIN_VERSION_TABLE_INSERT);
            preparedStatement.setInt(1, currentMainVersion + 1);
            int i = preparedStatement.executeUpdate();
            if (i != 1) {
                throw new RuntimeException("参数维护交易报错，主版本号加锁异常 1");
            }
        } catch (SQLException e) {
            throw new RuntimeException("参数维护交易报错，主版本号加锁异常 2 :", e);
        }
    }

    private static int getCurrentMainVersion(BaseExecutor baseExecutor) {
        int currentMainVersion;
        try {
            Connection connection = baseExecutor.getTransaction().getConnection();

            if (connection.getAutoCommit()) {
                throw new RuntimeException("参数维护扩展功能异常，当前连接为自动提交 1");
            }

            Statement sqlStatement = connection.createStatement();
            sqlStatement.executeQuery(MAIN_VERSION_TABLE_SELECT);
            ResultSet resultSet = sqlStatement.getResultSet();
            currentMainVersion = resultSet.getInt(MAIN_VERSION_COLUMN_NAME);
        } catch (SQLException e) {
            throw new RuntimeException("获取参数主版本号报错:", e);
        }
        return currentMainVersion;
    }

}
