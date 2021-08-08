package transaction;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * I declare that this code was written by me.
 * I will not copy or allow others to copy my code.
 * I understand that copying code is considered as plagiarism.
 *
 * 20020705, 8 Aug 2021 10:12:24 pm
 */

public class doViewTransaction {

	public static void main(String[] args) {
		try {
			
			String jdbcURL = "jdbc:mysql://localhost/demodb";
			String dbUsername = "root";
			String dbPassword = "";

			DBUtil.init(jdbcURL, dbUsername, dbPassword);

			String output = String.format("%-5s %-23s %-20s %-25s %13s %-25s\n", "ID", "DATE OF TRANSACTION", "CUSTOMER NAME", "CUSTOMER MOBILE NUMBER",
					"COMPENSATION", "DAYS SINCE TRANSACTION");

			String sql = "SELECT transaction_id, transaction_date, customer_name, customer_mobile_number, compensation_type, days_since_transaction FROM transaction";
			ResultSet rs = DBUtil.getTable(sql);

			while (rs.next()) {

				int id = rs.getInt("transaction_id");
				Date date = rs.getDate("transaction_date");
				String name = rs.getString("customer_name");
				int no = rs.getInt("customer_mobile_number");
				String type = rs.getString("compensation_type");
				int days = rs.getInt("days_since_transaction");

				output += String.format("%-5s %-23s %-20s %-25s %13s %-25s\n", id, date, name, no, type, days);
			}
			System.out.println(output);

		} catch (SQLException se) {
			se.printStackTrace();
		}

	}

}
