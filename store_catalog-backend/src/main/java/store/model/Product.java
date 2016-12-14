package store.model;

public class Product {

	private String name;
	private float price;
	private int amount;

	public Product() {
	};

	public Product(String name, float price, int amount) {
		this.setName(name);
		this.setPrice(price);
		this.setAmount(amount);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
