package com.techvortex.vortex.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Nationalized;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Table(name = "Review")
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ReviewId;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "UserName", nullable = false)
    Account account;

    @Nationalized
    private String Comment;

    private Integer Rating;

    @ManyToOne
    @JoinColumn(name = "ProductId")
    Product product;

    @Temporal(TemporalType.DATE)
    private Date ReviewDate = new Date();

    @OneToMany(mappedBy = "review")
    List<ReviewImg> reviewImgs;
}
