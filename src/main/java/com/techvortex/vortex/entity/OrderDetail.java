package com.techvortex.vortex.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
