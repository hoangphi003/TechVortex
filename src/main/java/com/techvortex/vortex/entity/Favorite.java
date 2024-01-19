package com.techvortex.vortex.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "Favorite")
public class Favorite implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer FavoriteId;
    
    @ManyToOne @JoinColumn(name = "UserName")
    Account account;

    @ManyToOne @JoinColumn(name = "ProductId")
    Product product;

    @Temporal(TemporalType.DATE)
    private Date DateFavorite = new Date();
}
