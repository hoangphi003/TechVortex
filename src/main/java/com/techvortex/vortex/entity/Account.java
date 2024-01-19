package com.techvortex.vortex.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Nationalized;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Table(name = "Account")
@Entity
public class Account implements Serializable {
    @Id
    @Nationalized
    private String UserName;

    @Nationalized
    private String FullName;

    private String Password;

    private Integer Phone;

    private String Email;

    private Boolean Active;

    @Temporal(TemporalType.DATE)
    private Date Birth = new Date();

    private Boolean Gender;

    private String Avatar;

    @OneToMany(mappedBy = "account")
    List<Cart> carts;

    @OneToMany(mappedBy = "account")
    List<Authority> authorities;

    @OneToOne(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Review review;

    @OneToMany(mappedBy = "account")
    List<Order> orders;
}
