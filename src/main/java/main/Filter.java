package main;

public class Filter {
	
	private String expr = "(function(product) { return";
	
	public Filter compare(String field, String op, String value) {
		this.expr += " product." + field + " " + op + " " + value;
		return this;
	}
	
	public Filter and() {
		this.expr += " &&";
		return this;
	}
	
	public Filter or() {
		this.expr += " ||";
		return this;
	}
	
	public Filter not() {
		this.expr += "!";
		return this;
	}
	
	public String getExpression() {
		return this.expr + " })";
	}
}
