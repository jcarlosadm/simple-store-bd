package example;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;
import com.rethinkdb.net.Cursor;

public class Main {

	private static final String HOSTNAME = "localhost";
	private static final int PORT = 32769;
	private static final String TABLE_NAME = "authors";
	
	public final static RethinkDB r = RethinkDB.r;

	public static void main(String[] args) {
		Connection conn = r.connection().hostname(HOSTNAME).port(PORT).connect();

		//createAndFill(conn);
		
		// All docs
		Cursor<Object> cursor = r.table(TABLE_NAME).run(conn);
		for (Object doc : cursor)
			System.out.println(doc);
		
		// search for doc with name equals as William Adama
		cursor = r.table("authors").filter(row -> row.g("name").eq("William Adama")).run(conn);
		for(Object doc : cursor)
			System.out.println(doc);

	}

	@SuppressWarnings("unused")
	private static void createAndFill(Connection conn) {
		r.db("test").tableCreate(TABLE_NAME).run(conn);

		r.table("authors").insert(r.array(
				r.hashMap("name", "William Adama").with("tv_show", "Battlestar Galactica").with("posts", r.array(
						r.hashMap("title", "Decommissioning speech").with("content", "The Cylon War is long over..."),
						r.hashMap("title", "We are at war").with("content", "Moments ago, this ship received..."),
						r.hashMap("title", "The new Earth").with("content",
								"The discoveries of the past few days..."))),
				r.hashMap("name", "Laura Roslin").with("tv_show", "Battlestar Galactica").with("posts",
						r.array(r.hashMap("title", "The oath of office").with("content", "I, Laura Roslin, ..."),
								r.hashMap("title", "They look like us").with("content",
										"The Cylons have the ability..."))),
				r.hashMap("name", "Jean-Luc Picard").with("tv_show", "Star Trek TNG").with("posts",
						r.array(r.hashMap("title", "Civil rights").with("content",
								"There are some words I've known since...")))))
				.run(conn);
	}
}
