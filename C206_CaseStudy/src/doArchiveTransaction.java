package transaction;

/**
 * I declare that this code was written by me.
 * I will not copy or allow others to copy my code.
 * I understand that copying code is considered as plagiarism.
 *
 * 20020705, 8 Aug 2021 10:36:55 pm
 */

public class doArchiveTransaction {

	public static void main(String[] args) {
		String jdbcURL = "jdbc:mysql://localhost/demodb";
		String dbUsername = "root";
		String dbPassword = "";

		DBUtil.init(jdbcURL, dbUsername, dbPassword);

		String name = Helper.readString("Enter name > ");

		if (DBUtil.execSQL("SELECT days_since_transaction FROM transaction") > 90) {
			String deleteSQL = "DELETE FROM student WHERE name='" + name + "'";
			int rowsAffected = DBUtil.execSQL(deleteSQL);
			if (rowsAffected == 1) {
				System.out.println("Transaction Archived!");
			} else {
				System.out.println("Delete failed!");
			}
		}
		
		else {
			System.out.println("Error: Transaction is not older than 30 days Old!");
		}

		

		DBUtil.close();
		

	}

}
