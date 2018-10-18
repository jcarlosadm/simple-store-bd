package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;
import com.rethinkdb.net.Cursor;

public class RethinkDBProductQuery {

	private static final String DB_NAME = "store";
	private static final String TABLE_NAME = "products";

	private static final RethinkDB r = RethinkDB.r;

	private Connection conn = null;

	public RethinkDBProductQuery(Connection conn) {
		this.conn = conn;
	}

	public List<Product> getProducts() {
		List<Product> products = new ArrayList<>();

		Cursor<HashMap<String, ?>> cursor = r.db(DB_NAME).table(TABLE_NAME).run(conn);

		for (HashMap<String, ?> data : cursor) {
			Product product = productData(data);
			products.add(product);
		}

		return products;
	}

	private Product productData(HashMap<String, ?> data) {
		Product product = new Product((String) data.get("id"), (String) data.get("name"), (long) data.get("quant"),
				(double) data.get("price"));
		return product;
	}
}
