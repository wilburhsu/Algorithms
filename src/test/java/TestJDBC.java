import java.sql.*;

public class TestJDBC {
    public static void main(String[] args) throws SQLException,ClassNotFoundException {
        Class.forName("");
        Connection connection = DriverManager.getConnection("");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("");
    }

}
