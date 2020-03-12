package infra.repositories;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.entities.Product;
import domain.repositories.IProductDAO;

public class ProductDAO implements IProductDAO{

	private Connection connection;

	public ProductDAO(Connection connection) {
		
		this.connection = connection;
	}
	
	public Product insert(Product product) {

		Product result = null;
		
		String sql = "INSERT INTO produto (nome, descricao) VALUES(?, ?)";
		
		try(PreparedStatement prepareStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
		
			prepareStatement.setString(1, product.getName());
			prepareStatement.setString(2, product.getDescription());
			
			prepareStatement.execute();
			
			try(ResultSet generatedKeys = prepareStatement.getGeneratedKeys()){
				if(generatedKeys.next()) {
					result = new Product(generatedKeys.getInt(1), product.getName(), product.getDescription());
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public List<Product> list() {
		
		List<Product> products = new  ArrayList<Product>();

		try (Statement createStatement = connection.createStatement()){
			
			String sql = "SELECT * FROM produto";
			createStatement.execute(sql );
			
			try(ResultSet resultSet = createStatement.getResultSet()){
			
				while(resultSet.next()) {
					
					int id 				= resultSet.getInt("id");
					String name	 		= resultSet.getString("nome");
					String description 	= resultSet.getString("descricao");
				
					products.add(new Product(id, name, description));
					
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return products;
	}
	
	
	public void delete(int id) {
		
		String sql = "DELETE FROM produto WHERE id > ?";
		
		try(PreparedStatement prepareStatement = connection.prepareStatement(sql)){
	
			prepareStatement.setInt(1, id);
			
			prepareStatement.execute();

			System.out.println(String.format("Amount deleted records: %s", prepareStatement.getUpdateCount()));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
