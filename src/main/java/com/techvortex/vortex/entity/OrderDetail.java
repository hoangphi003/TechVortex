package com.techvortex.vortex.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "OrderDetail")
@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer OrderDetailId;

    @ManyToOne
    @JoinColumn(name = "OrderId")
    Order order;

    @ManyToOne
    @JoinColumn(name = "ProductId")
    Product product;

    private Float Price;

    private Integer Quantity;

    private Float Total;

}
