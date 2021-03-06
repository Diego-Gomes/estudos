package infra.config;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {

	private DataSource dataSource;

	public ConnectionFactory(Properties config) {
		this.dataSource = getDataSource(config);

	}

	public Connection getConnection() throws SQLException {
		return this.dataSource.getConnection();
	}

	private DataSource getDataSource(Properties config) {

		ComboPooledDataSource dataSource = new ComboPooledDataSource();

		dataSource.setJdbcUrl(config.getProperty("url"));
		dataSource.setUser(config.getProperty("user"));
		dataSource.setPassword(config.getProperty("password"));
		dataSource.setMaxPoolSize(Integer.parseInt((config.getProperty("maxPoolSize"))));

		return dataSource;
	}

}
