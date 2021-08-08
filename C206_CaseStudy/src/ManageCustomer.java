public class ManageCustomer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

private void registerCustomer() {
	String name = Helper.readString("Enter name > ");
	int mobileNumber = Helper.readInt("Enter mobile number > ");

	//TODO: - Write the code to add a new participant.
	String insertsql= "INSERT INTO customer (name,mobile_number) VALUES('"+ name + "' + mobileNumber + ")";
	int rowsAdded = DBUtil.execSQL(insertsql);
	
	if (rowsAdded == 1) {
		System.out.println("Customer added!"); 
		
	}else {
		System.out.println("Customer not added!");
	}
	
}

//view
	private Customer findCustomer(String mobileNumber) {

		String sql = "SELECT name, mobile_number FROM Customer ";
		Customer p = null;

		ResultSet rs = DBUtil.getTable(sql);

		try {
			if (rs.next()) {
				p = new Customer(rs.getString("name"), rs.getInt("mobile_number"));
			}
		} catch (SQLException e) {
			System.out.println("SQL Error: " + e.getMessage());
		}
		return p;
	}

//TODO: - Write the code to delete a customer
	{
		char confirmRemove = Helper.readChar("Are you sure you wish to remove " + customer.getName() + " (Y/N) > ");
		if (confirmRemove == 'Y' | confirmRemove == 'y') {
			String deleteSQL = "DELETE FROM customer WHERE mobile_number='" + mobileNumber + "'";
			int rowsDeleted = DBUtil.execSQL(deleteSQL);
			if (rowsDeleted == 1) {
				System.out.println("Customer removed!");
			} else {
				System.out.println("Removal failed!");
			}

		}
	}
}