
public class C206_CaseStudy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public void start() {
		String jdbcURL = "jdbc:mysql://localhost/demodb/crt_product";
		String dbUsername = "root";
		String dbPassword = "";

		DBUtil.init(jdbcURL, dbUsername, dbPassword);
	}

}
