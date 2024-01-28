package com.techvortex.vortex.entity;

import java.io.Serializable;
import java.util.List;

import org.hibernate.annotations.Nationalized;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Data
@Table(name = "Color")
@Entity
public class Color implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ColorId;

    @Nationalized
    private String ColorName;

    @OneToMany(mappedBy = "color")
    List<ProductDetail> productDetails;
}
