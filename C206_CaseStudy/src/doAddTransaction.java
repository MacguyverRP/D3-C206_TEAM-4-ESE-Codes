import java.sql.Date;  
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * I declare that this code was written by me.
 * I will not copy or allow others to copy my code.
 * I understand that copying code is considered as plagiarism.
 *
 * 20020705, 8 Aug 2021 4:32:55 pm
 */

public class doAddTransaction {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String jdbcURL = "jdbc:mysql://localhost/demodb";
		String dbUsername = "root";
		String dbPassword = "";

		DBUtil.init(jdbcURL, dbUsername, dbPassword);
		
		int transaction_id = Helper.readInt("Enter transaction_id > ");
		String name = Helper.readString("Enter customer name > ");
		String date = Helper.readString("Enter transaction date > ");
		int contact_no = Helper.readInt("Enter customer mobile number > ");
		String compensation = Helper.readString("Enter compensation type (refund/exchange) > ");

		String sql = "INSERT INTO transaction(transaction_id, customer_name, transaction_date, gpa, compensation_type) " 
					+ "VALUES ('" + transaction_id + "' + '" + name + "', '" + date + "', " + contact_no + ", '" + compensation + "')";
		int rowsAffected = DBUtil.execSQL(sql);

		if (rowsAffected == 1) {
			System.out.println("Transaction added!");
		} else {
			System.out.println("Insert failed!");
		}

		DBUtil.close();

	}

}
