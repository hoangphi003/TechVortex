package com.techvortex.vortex.entity;

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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Table(name = "Product")
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ProductId;

    @Nationalized
    @NotBlank(message = "Không bỏ trống tên sản phẩm")
    private String ProductName;

    @Nationalized
    private String Description;
    
   
    private String Image;
    
    @NotNull(message = "Không bỏ trống giá")
	// @Min(value = 1, message = "Giá lớn hơn 0")
    private Float Price;

    @Nationalized
    @NotBlank(message = "Không bỏ trống nơi xuất xứ")
    private String Origin;

    @ManyToOne
    @JoinColumn(name = "CategoryId")
    Category category;

    @OneToMany(mappedBy = "product")
    List<Favorite> favorites;

    @OneToMany(mappedBy = "product")
    List<ProductDetail> productDetails;

    @OneToMany(mappedBy = "product")
    List<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "product")
    List<Review> reviews;
}
