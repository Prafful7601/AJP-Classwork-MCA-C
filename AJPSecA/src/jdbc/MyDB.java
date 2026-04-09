package jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

public class MyDB {

	private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
	private static final Path[] CONFIG_LOCATIONS = {
		Path.of("jdbc.properties"),
		Path.of("AJPSecA", "jdbc.properties")
	};

	public static void main(String[] args) {
		Properties config = loadConfig();
		String host = config.getProperty("db.host", "localhost");
		String port = config.getProperty("db.port", "3306");
		String databaseName = config.getProperty("db.name", "ajp_jdbc_demo");
		String username = config.getProperty("db.user", "root");
		String password = config.getProperty("db.password", "");
		String connectionOptions = "useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
		String adminUrl = String.format("jdbc:mysql://%s:%s/?%s", host, port, connectionOptions);
		String databaseUrl = String.format("jdbc:mysql://%s:%s/%s?%s", host, port, databaseName, connectionOptions);
		String createDatabaseSql = "CREATE DATABASE IF NOT EXISTS " + databaseName;
		String createTableSql = """
				CREATE TABLE IF NOT EXISTS employees (
				    id INT PRIMARY KEY AUTO_INCREMENT,
				    name VARCHAR(100) NOT NULL,
				    age INT NOT NULL,
				    salary DECIMAL(10,2) NOT NULL,
				    department VARCHAR(50) NOT NULL
				)
				""";
		List<Employee> sampleEmployees = List.of(
			new Employee("Prafful", 23, 28000.00, "MCA"),
			new Employee("Aditi", 22, 30000.00, "Development"),
			new Employee("Rohit", 24, 32000.00, "Testing")
		);

		try {
			Class.forName(DRIVER_CLASS);

			try (Connection adminConnection = DriverManager.getConnection(adminUrl, username, password);
					Statement adminStatement = adminConnection.createStatement()) {
				adminStatement.executeUpdate(createDatabaseSql);
				System.out.println("Database ready: " + databaseName);
			}

			try (Connection connection = DriverManager.getConnection(databaseUrl, username, password)) {
				System.out.println("Connected to MySQL.");
				createTable(connection, createTableSql);
				insertSampleEmployees(connection, sampleEmployees);
				printEmployees(connection);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("MySQL JDBC driver not found. Check AJPSecA/lib for the connector JAR.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Database connection failed. Update AJPSecA/jdbc.properties with your MySQL username/password if needed.");
			e.printStackTrace();
		}
	}

	private static Properties loadConfig() {
		Properties properties = new Properties();

		for (Path configPath : CONFIG_LOCATIONS) {
			if (Files.exists(configPath)) {
				try (InputStream inputStream = Files.newInputStream(configPath)) {
					properties.load(inputStream);
					System.out.println("Loaded config from " + configPath.toAbsolutePath());
					return properties;
				} catch (IOException e) {
					System.out.println("Could not read config file: " + configPath.toAbsolutePath());
					e.printStackTrace();
				}
			}
		}

		System.out.println("jdbc.properties not found. Using built-in defaults.");
		return properties;
	}

	private static void createTable(Connection connection, String createTableSql) throws SQLException {
		try (Statement statement = connection.createStatement()) {
			statement.executeUpdate(createTableSql);
			System.out.println("Table ready: employees");
		}
	}

	private static void insertSampleEmployees(Connection connection, List<Employee> employees) throws SQLException {
		String checkSql = "SELECT COUNT(*) FROM employees WHERE name = ?";
		String insertSql = "INSERT INTO employees (name, age, salary, department) VALUES (?, ?, ?, ?)";
		int insertedRows = 0;

		try (PreparedStatement checkStatement = connection.prepareStatement(checkSql);
				PreparedStatement insertStatement = connection.prepareStatement(insertSql)) {
			for (Employee employee : employees) {
				checkStatement.setString(1, employee.name());

				try (ResultSet resultSet = checkStatement.executeQuery()) {
					resultSet.next();
					if (resultSet.getInt(1) > 0) {
						continue;
					}
				}

				insertStatement.setString(1, employee.name());
				insertStatement.setInt(2, employee.age());
				insertStatement.setDouble(3, employee.salary());
				insertStatement.setString(4, employee.department());
				insertedRows += insertStatement.executeUpdate();
			}
		}

		System.out.println("Inserted " + insertedRows + " new record(s).");
	}

	private static void printEmployees(Connection connection) throws SQLException {
		String selectSql = "SELECT id, name, age, salary, department FROM employees ORDER BY id";

		try (Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(selectSql)) {
			System.out.println("Current employees:");

			while (resultSet.next()) {
				System.out.printf(
					"[%d] %s | age=%d | salary=%.2f | department=%s%n",
					resultSet.getInt("id"),
					resultSet.getString("name"),
					resultSet.getInt("age"),
					resultSet.getDouble("salary"),
					resultSet.getString("department")
				);
			}
		}
	}

	private record Employee(String name, int age, double salary, String department) {
	}
}
