package DB;

import java.sql.*;

public class DBHelper {

    private final String connectionString;

    public DBHelper(String connectionString) {
        this.connectionString = connectionString;
    }

    public String getSingleValue(String query) throws SQLException {
        try (Connection connection = DriverManager.getConnection(connectionString);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {

            if (resultSet.next()) {
                return resultSet.getString(1); // Получаем значение первого столбца
            } else {
                return null; // Или бросить исключение, если значение обязательно должно быть
            }
        }
    }

    public String getSingleValue(String query, Object... params) throws SQLException {
        // Подготовленный запрос с параметрами.
        try (Connection connection = DriverManager.getConnection(connectionString);
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString(1);
                } else {
                    return null;
                }
            }
        }
    }

    // Другие методы для выполнения запросов (SELECT с несколькими
    // столбцами/строками, INSERT, UPDATE, DELETE, etc.)
    // Очень желательно использовать PreparedStatement.
}
