package com.techvortex.vortex.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Nationalized;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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