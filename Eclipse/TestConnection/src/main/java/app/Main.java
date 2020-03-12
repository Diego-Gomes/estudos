package app;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.function.Consumer;

import domain.entities.Product;
import infra.config.ConnectionFactory;
import infra.config.PropertiesFactory;
import infra.repositories.ProductDAO;

public class Main {

	public static void main(String[] args) throws SQLException, InterruptedException, IOException {

		Properties config = new PropertiesFactory().getProperties();

		try (Connection connection = new ConnectionFactory(config).getConnection()) {

			ProductDAO productDAO = new ProductDAO(connection);

			String name = "TV";
			String description = "TV LG 49 POLEGADAS";
			Product p = productDAO.insert(new Product(name, description));

			System.out.println(String.format("Product inserted: %s %s %s", p.getId(), p.getName(), p.getDescription()));
			System.out.println("");

			List<Product> products = productDAO.list();

			Consumer<Product> lambdaExpression = product -> System.out
					.println(String.format("%s %s %s", product.getId(), product.getName(), product.getDescription()));

			products.forEach(lambdaExpression);

			System.out.println("");

			productDAO.delete(2);

		}
	}
}
