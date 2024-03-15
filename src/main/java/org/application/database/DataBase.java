package org.application.database;

import java.sql.Array;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataBase implements Source {

    public DataBase() {
        try (var connection = DriverManager.getConnection(
                DataBaseConfig.URL, DataBaseConfig.USERNAME, DataBaseConfig.PASSWORD
        )) {

            var statement = connection.createStatement();
            var DDL = DDLRequests.CREATE_FIB_TABLE;

            statement.execute(DDL);

        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public void saveData(int number, List<Integer> data) {
        try (var connection = DriverManager.getConnection(
                DataBaseConfig.URL, DataBaseConfig.USERNAME, DataBaseConfig.PASSWORD
        )) {
            var DDL = DDLRequests.INSERT_DATA_INTO_FIB_TABLE;
            var statement = connection.prepareStatement(DDL);

            statement.setInt(1, number);
            statement.setArray(2, connection.createArrayOf("INTEGER", data.toArray()));

            int checkout = statement.executeUpdate();
            System.out.println(checkout == 0 ? "Ошибка сохранения данных" : "Данные успешно сохранены");
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public List<Integer> getData(int number) {
        try (var connection = DriverManager.getConnection(
                DataBaseConfig.URL, DataBaseConfig.USERNAME, DataBaseConfig.PASSWORD
        )) {
            var DDL = String.format(DDLRequests.GET_DATA_FROM_FIB_TABLE, number);
            var statement = connection.createStatement();
            var resultSet = statement.executeQuery(DDL);

            return resultSetToList(resultSet);

        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return null;
    }

    private List<Integer> resultSetToList(ResultSet resultSet) throws SQLException {
        Array array = null;

        if (resultSet.next()) {
            array = resultSet.getArray("result");
        }
        List<Integer> answer = new ArrayList<>();

        if (array != null) {
            Integer[] result = (Integer[]) array.getArray();

            answer = new ArrayList<>(Arrays.asList(result));
        }

        return answer;
    }
}
