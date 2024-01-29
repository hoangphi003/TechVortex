package com.techvortex.vortex.entity;

import java.io.Serializable;
import java.util.List;

import org.hibernate.annotations.Nationalized;

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