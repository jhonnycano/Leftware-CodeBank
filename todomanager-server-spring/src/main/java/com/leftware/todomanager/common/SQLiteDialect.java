package com.leftware.todomanager.common;

import org.hibernate.dialect.DatabaseVersion;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.identity.IdentityColumnSupport;
import org.hibernate.dialect.identity.IdentityColumnSupportImpl;
import org.hibernate.dialect.pagination.LimitHandler;
import org.hibernate.dialect.pagination.LimitOffsetLimitHandler;

public class SQLiteDialect extends Dialect {

    public SQLiteDialect() {
        super(DatabaseVersion.make(3, 36));  // SQLite version (change if needed)
    }

    @Override
    public int getDefaultStatementBatchSize() {
        return 20;
    }

    @Override
    public IdentityColumnSupport getIdentityColumnSupport() {
        return new IdentityColumnSupportImpl() {
            @Override
            public boolean supportsIdentityColumns() {
                return true;
            }

            @Override
            public String getIdentityColumnString(int type) {
                return "integer";  // Auto-increment column type in SQLite
            }

            @Override
            public String getIdentitySelectString(String table, String column, int type) {
                return "select last_insert_rowid()";  // Get last inserted ID
            }
        };
    }

    @Override
    public LimitHandler getLimitHandler() {
        // LIMIT/OFFSET support
        return LimitOffsetLimitHandler.INSTANCE;
    }

    // @Override
    // public void initializeFunctionRegistry(SqmFunctionRegistry functionRegistry) {
    //     super.initializeFunctionRegistry(functionRegistry);
    //     // Register SQLite-specific functions like CONCAT
    //     functionRegistry.register("concat", 
    //         new VarArgsSqlFunction(SqlTypes.STRING, "", "||", ""));
    // }

    // @Override
    // protected void registerJdbcTypeCodes() {
    //     super.registerJdbcTypeCodes();
    //     // Register SQLite column types
    //     getJdbcTypeRegistry().addDescriptor(SqlTypes.INTEGER, IntegerJdbcType.INSTANCE);
    //     // You can add more types if necessary.
    // }
}