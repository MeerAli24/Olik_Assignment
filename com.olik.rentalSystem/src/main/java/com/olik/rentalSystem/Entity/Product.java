package com.olik.rentalSystem.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType
    .IDENTITY)
    private Long id;
    
    private String name;
    private String image;
    private double cost;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public Product(Long id, String name, String image, double cost) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.cost = cost;
	}
	public Product() {
		super();
	}

    public int getDurationInDays() {
        return (int) startDate.until(endDate).getDays();
    }
}
