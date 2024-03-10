package com.techvortex.vortex.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Nationalized;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Table(name = "Discount")
@Entity
public class Discount implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer DiscountId;
    @NotBlank(message = "Giảm giá không được bỏ trống!")
    @Nationalized
    private String DiscountName;
    @NotBlank(message = "Mô tả không được bỏ trống!")
    @Nationalized
    private String Description;
    @NotNull(message = "Percents must not be null")
    private Float Percents;
    @NotNull(message = "Chọn ngày bắt đầu")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date StartDay;
    @NotNull(message = "Chọn ngày kết thúc")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date EndDay;
    @NotBlank(message = "Số lượng không được bỏ trống!")
    private Integer Quantity;

    private String DiscountCode;

    @OneToMany(mappedBy = "discount")
    List<OrderDiscount> OrderDiscounts;

}