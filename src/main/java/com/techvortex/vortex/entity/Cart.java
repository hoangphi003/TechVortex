package com.techvortex.vortex.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Table(name = "Cart")
@Entity
public class Cart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer CartId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ProductDetailId")
    ProductDetail productDetails;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "Account")
    Account account;

    private Integer Quantity;

}
