package com.kawahedukasi.model;

import com.kawahedukasi.utils.AuditModel;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product extends AuditModel {

    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "count")
    private long count;

    @Column(name = "price")
    private float price;

    @Column(name = "type")
    private String type;

    @Column(name = "desciption")
    private String description;

    public Product() {
    }

    public Product(String name, long count, float price, String type, String description) {
        this.name = name;
        this.count = count;
        this.price = price;
        this.type = type;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
