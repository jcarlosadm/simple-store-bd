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

		} catch (Exception e) {
			System.out.println("Error to connect. exiting...");
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}
}
