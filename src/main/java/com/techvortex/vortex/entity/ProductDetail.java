package com.techvortex.vortex.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Table(name = "ProductDetail")
@Entity
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ProductDetailId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ProductId")
    Product product;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ColorId")
    Color color;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "Material")
    Material material;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "Brand")
    Brand brand;

    private Integer InventoryQuantity;

    @JsonIgnore
    @OneToMany(mappedBy = "productDetails")
    List<Cart> carts;
}
