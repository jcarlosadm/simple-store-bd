package main;

import com.rethinkdb.net.Connection;

public class Main {
	public static void main(String[] args) {

		Connection conn = null;
		try {
			// open connection
			conn = ConnectRethinkDB.getConnection();
			
			// create a query object
			RethinkDBProductQuery rQuery = new RethinkDBProductQuery(conn);
			
			// get all products
			for (Product product : rQuery.getProducts()) {
				System.out.println(product);
			}
			
			System.out.println("===================");
			System.out.println(rQuery.getProductById("034bd25b-fabe-4d34-ab76-c2ddc617708b"));
			System.out.println(rQuery.getProductById("034bd25b-fabe-4d34-ab76-c2ddc617708"));
			
			

		} catch (Exception e) {
			System.out.println("Error to connect. exiting...");
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}
}
