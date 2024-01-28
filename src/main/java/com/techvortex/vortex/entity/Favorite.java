package com.techvortex.vortex.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
