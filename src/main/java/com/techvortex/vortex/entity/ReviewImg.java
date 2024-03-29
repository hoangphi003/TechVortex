package com.techvortex.vortex.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
