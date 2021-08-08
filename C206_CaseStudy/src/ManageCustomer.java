
import java.sql.ResultSet;
import java.sql.SQLException;


public class ManageCustomer {


public static void main(String[] args) {
	// TODO Auto-generated method stub

}
private void registerCustomer() {
	String name = Helper.readString("Enter name > ");
	String nric = Helper.readString("Enter NRIC > ");
	int mobileNumber = Helper.readInt("Enter mobile number > ");

	//TODO: - Write the code to add a new participant.
	String insertsql= "INSERT INTO customer (name, nric, mobile_number) VALUES('"+ name + "','"+ nric + "'," + mobileNumber + ")";
	int rowsAdded = DBUtil.execSQL(insertsql);
	
	if (rowsAdded == 1) {
		System.out.println("Customer added!"); 
		
	}else {
		System.out.println("Customer not added!");
	}
	
}

//view
private void findCustomer() {

	String sql = "SELECT name, mobile_number FROM Customer WHERE nric = '" + nric + "'";
	Customer p = null;

	ResultSet rs = DBUtil.getTable(sql);

	try {
		if (rs.next()) {
			p = new Customer(nric, rs.getString("name"), rs.getInt("mobile_number"));
		} else {
			char confirmRemove = Helper.readChar("Are you sure you wish to remove " + customer.getName() + " (Y/N) > ");
			if (confirmRemove == 'Y' | confirmRemove == 'y') {
				String deleteSQL = "DELETE FROM customer WHERE nric='" + nric + "'";
				int rowsDeleted = DBUtil.execSQL(deleteSQL);
				if (rowsDeleted == 1) {
					System.out.println("Customer removed!");
				} else {
					System.out.println("Removal failed!");
				}

			}

		}
	} catch (SQLException e) {
		System.out.println("SQL Error: " + e.getMessage());
	}
	return p;

}
}