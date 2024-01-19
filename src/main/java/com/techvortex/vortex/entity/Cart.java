package com.techvortex.vortex.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "Cart")
@Entity
public class Cart implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer CartId;

    @ManyToOne
    @JoinColumn(name = "ProductDetailId")
    ProductDetail productDetails;

    @ManyToOne
    @JoinColumn(name = "Account")
    Account account;

    private Integer Quantity;

}
