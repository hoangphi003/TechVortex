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
@Table(name = "OrderDiscount")
@Entity
public class OrderDiscount {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer OrderDiscountId;
 
    
    @ManyToOne 
    @JoinColumn(name = "OrderId")
    Order order;

    @ManyToOne 
    @JoinColumn(name = "DiscountId")
    Discount discount;

}
