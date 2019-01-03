package com.example.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Catalog extends BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;

    private String name;

    @OneToMany(mappedBy = "catalog")
    private Set<Product> products = new HashSet<>();

    public void addToProducts(Product product) {
        product.setCatalog(this);
        this.products.add(product);

    }
}
