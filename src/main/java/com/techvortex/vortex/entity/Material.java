package com.techvortex.vortex.entity;

import java.io.Serializable;
import java.util.List;

import org.hibernate.annotations.Nationalized;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "Material")
@Entity
public class Material implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer MaterialId;

    @Nationalized
    private String MaterialName;

    @OneToMany(mappedBy = "material")
    List<ProductDetail> productDetails;

}
