package com.techvortex.vortex.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
