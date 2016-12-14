package store.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Product {

	@SequenceGenerator(name = "gen_product", sequenceName = "seq_product", allocationSize = 1)
	@Id
	@GeneratedValue(generator = "gen_product")
	private long id;
	private String name;
	private BigDecimal price;
	private int amount;

	public Product() {
	};

	public Product(String name, BigDecimal price, int amount) {
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
