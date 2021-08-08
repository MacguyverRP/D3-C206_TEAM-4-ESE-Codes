import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class ManageProduct {

	private int account_status; // 0 is for admin and 1 is for retailers
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ManageProduct product = new ManageProduct();
		product.start();
	}

	private void start() {

		String jdbcURL = "jdbc:mysql://localhost/demodb/crt_product";
		String dbUsername = "root";
		String dbPassword = "";

		DBUtil.init(jdbcURL, dbUsername, dbPassword);

		int option = -1;

		while (option != 5) {
			menu();
			option = Helper.readInt("Enter choice > ");

			if (account_status == 0) {
				if (option == 1) {
					addProduct();
				} else if (option == 2) {
					viewProduct();
				} else if (option == 3) {
				} else if (option == 4) {
					DBUtil.close();
					System.out.println("Good bye!");
				}
			} else if (account_status == 1) {
				if (option == 1) {
					viewProduct();
				} else if (option == 2) {
					DBUtil.close();
					System.out.println("Good bye!");
				}
			}
		}
	}

	private void menu() {
		if (account_status == 0) {
			System.out.println("1. Add Product");
			System.out.println("2. View Product");
			System.out.println("3. Update Product");
			System.out.println("4. Quit");
		} else if (account_status == 1) {
			System.out.println("1. View Product");
			System.out.println("2. Quit");
		}
	}

	private void addProduct() {
		int id = Helper.readInt("Enter id > ");
		String name = Helper.readString("Enter name > ");
		float price = Helper.readFloat("Enter price > ");
		String category = Helper.readString("Enter category > ");
		String vendor = Helper.readString("Enter vendor > ");
		boolean inStock = Helper.readBoolean("Enter stock availability > ");
		String returnPolicy = Helper.readString("Enter conditions for return policies > ");
		String sql = "INSERT INTO crt_product(id, name, price, category, vendor, in_stock, return_policy) "
				+ "VALUES ('" + id + "', '" + name + "', " + price + "', '" + category + "', '" + vendor + "', '"
				+ inStock + "', '" + returnPolicy + ")";
		int rowsAffected = DBUtil.execSQL(sql);

		if (rowsAffected == 1) {
			System.out.println("Product added!");
		} else {
			System.out.println("Insert failed!");
		}
		DBUtil.close();
	}
	
	private void viewProduct() {
		int idToSearch = Helper.readInt("Enter Product ID > ");
		String sql = "SELECT * FROM crt_product";
		String output = String.format("%-10d %-20s %-10.2f %-10s %-20s %-10b %-10s", "id", "name", "price", "category", "vendor", "in_stock", "return_policy");

		ResultSet rs = DBUtil.getTable(sql);
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				if(id == idToSearch) {
					String name = rs.getString("id");
					float price = rs.getFloat("price");
					String category = rs.getString("category");
					String vendor = rs.getString("vendor");
					boolean inStock = rs.getBoolean("in_stock");
					String returnPolicy = rs.getString("return_policy");
					output += String.format("%-10d %-20s %-10.2f %-10s %-20s %-10b %-10s", id, name, price, category, vendor, inStock, returnPolicy);
				}
			}
			
			rs.last();
		} catch (SQLException e) {
			System.out.println("SQL Error: " + e.getMessage());
		}
	}

	private void updateProduct() throws SQLException {
		String sql = "SELECT * FROM crt_product";
		ResultSet rs = DBUtil.getTable(sql);
		boolean loop = true;
		while (loop == true) {
			int idToSearch = Helper.readInt("Enter Product ID > ");

			try {
				int id = rs.getInt("id");
				if (id == idToSearch) {
					String name = rs.getString("id");
					float price = rs.getFloat("price");
					String category = rs.getString("category");
					String vendor = rs.getString("vendor");
					boolean inStock = rs.getBoolean("in_stock");
					String returnPolicy = rs.getString("return_policy");
					String output = String.format("%-10d %-20s %-10.2f %-10s %-20s %-10b %-10s", "id", "name", "price",
							"category", "vendor", "in_stock", "return_policy");
					output += String.format("%-10d %-20s %-10.2f %-10s %-20s %-10b %-10s", id, name, price, category,
							vendor, inStock, returnPolicy);
					System.out.println(output);
				}
				while (loop = true) {
					boolean inStock = rs.getBoolean("in_stock");
					String updateSQL = "UPDATE crt_product SET in_stock" + "VALUES ('" + inStock + ")";
					int rowsAffected = DBUtil.execSQL(updateSQL);
					if (rowsAffected == 1) {
						System.out.println("Product updated!");
						loop = false;
						break;
					} else {
						System.out.println("Update failed!");
					}
					DBUtil.close();
				}
			} catch (NullPointerException n) {
			}
		}
	}
}
