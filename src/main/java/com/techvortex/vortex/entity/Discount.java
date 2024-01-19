package com.techvortex.vortex.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import org.hibernate.annotations.Nationalized;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Table(name = "Discount")
@Entity
public class Discount implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer DiscountId;

    @Nationalized
    private String DiscountName;

    @Nationalized
    private String Description;

    private Float Percents;

    @Temporal(TemporalType.DATE)
    private Date StartDay;

    @Temporal(TemporalType.DATE)
    private Date EndDay;

    private Integer Quantity;

    private String DiscountCode;

    @OneToMany(mappedBy = "discount")
    List<OrderDiscount> OrderDiscounts;

}