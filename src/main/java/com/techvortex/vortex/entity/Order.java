package com.techvortex.vortex.entity;

import java.io.Serializable;
import java.util.List;

import org.hibernate.annotations.Nationalized;

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
@Table(name = "Orders")
@Entity
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer OrderId;

    @ManyToOne
    @JoinColumn(name = "UserName")
    Account account;

    @Nationalized
    private String Address;

    @Nationalized
    private String OrderStatus;

    @OneToMany(mappedBy = "order")
    List<OrderDiscount> orderDiscounts;

    @OneToMany(mappedBy = "order")
    List<OrderDetail> orderDetails;
}