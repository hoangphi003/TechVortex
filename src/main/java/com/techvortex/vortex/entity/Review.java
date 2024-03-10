package com.techvortex.vortex.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Nationalized;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    private Date ReviewDate;
    // private Date ReviewDate = new Date();

    @OneToMany(mappedBy = "review")
    List<ReviewImg> reviewImgs;
}
