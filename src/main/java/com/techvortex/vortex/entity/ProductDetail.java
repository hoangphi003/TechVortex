package com.techvortex.vortex.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "ProductDetail")
@Entity
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ProductDetailId;

    @ManyToOne
    @JoinColumn(name = "ProductId")
    Product product;

    @ManyToOne
    @JoinColumn(name = "ColorId")
    Color color;

    @ManyToOne
    @JoinColumn(name = "Material")
    Material material;

    @ManyToOne
    @JoinColumn(name = "Brand")
    Brand brand;

    private Integer InventoryQuantity;

    @OneToMany(mappedBy = "productDetails")
    List<Cart> carts;
}
