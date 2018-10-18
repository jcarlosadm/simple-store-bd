package main;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;

public class ConnectRethinkDB {

	private static final String HOSTNAME = "localhost";
	private static final int PORT = 32769;

	private static Connection conn = null;

	private ConnectRethinkDB() {
	}

	public static Connection getConnection() throws Exception {
		if (conn == null)
			conn = RethinkDB.r.connection().hostname(HOSTNAME).port(PORT).connect();
		return conn;
	}
}
