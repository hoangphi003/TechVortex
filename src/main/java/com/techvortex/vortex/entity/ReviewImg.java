package com.techvortex.vortex.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "ReviewImg")
@Entity
public class ReviewImg {

    @Id
    private Integer ReviewsImgId;

    @ManyToOne
    @JoinColumn(name = "ReviewId")
    Review review;

    private String Image;

}
