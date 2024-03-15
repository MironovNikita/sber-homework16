package org.application.database;

public class DDLRequests {
    public static final String CREATE_FIB_TABLE = """
            CREATE TABLE IF NOT EXISTS fibonacci (
            id SERIAL PRIMARY KEY,
            number INTEGER,
            result INTEGER[]
            );
            """;

    public static final String INSERT_DATA_INTO_FIB_TABLE = """
            INSERT INTO fibonacci
            (number, result)
            VALUES (?, ?);
            """;

    public static final String GET_DATA_FROM_FIB_TABLE = """
            SELECT number, result
            FROM fibonacci
            WHERE number = %d;
            """;
}
