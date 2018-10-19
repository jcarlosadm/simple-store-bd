package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.gen.ast.Javascript;
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

	public List<Product> getProducts(Javascript filterFunction) {
		List<Product> products = new ArrayList<>();

		try {
			Cursor<HashMap<String, ?>> cursor = null;
			if (filterFunction != null)
				cursor = r.db(DB_NAME).table(TABLE_NAME).filter(filterFunction).run(conn);
			else
				cursor = r.db(DB_NAME).table(TABLE_NAME).run(conn);

			for (HashMap<String, ?> data : cursor) {
				Product product = productData(data);
				products.add(product);
			}
		} catch (Exception e) {
			System.out.println("query error");
		}

		return products;
	}
	
	/**
	 * get Product by id
	 * @param id
	 * @return Product object, or null if id not exists
	 */
	public Product getProductById(String id) {
		HashMap<String, ?> data = r.db(DB_NAME).table(TABLE_NAME).get(id).run(conn);
		return data == null? null : productData(data);
	}

	private Product productData(HashMap<String, ?> data) {
		Product product = new Product((String) data.get("id"), (String) data.get("name"), (long) data.get("quant"),
				(double) data.get("price"));
		return product;
	}
}
