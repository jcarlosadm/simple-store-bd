package main;

public class Product {

	private String name = null;
	private long quant = 0;
	private double price = 0.0;
	private String id = null;
	
	public Product(String id, String name, long quant, double price) {
		this.setId(id);
		this.setName(name);
		this.setPrice(price);
		this.setQuant(quant);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name.length() > 0)
			this.name = name;
	}

	public long getQuant() {
		return quant;
	}

	public void setQuant(long quant) {
		if(quant >= 0)
			this.quant = quant;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		if (price >= 0.0)
			this.price = price;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		if (id.length() > 0)
			this.id = id;
	}
	
	@Override
	public String toString() {
		return "id: " + id + ", name: " + name + ", price: " + price + ", quant: " + quant;
	}
}
