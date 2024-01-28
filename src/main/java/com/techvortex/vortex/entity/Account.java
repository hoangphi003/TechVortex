package com.techvortex.vortex.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Nationalized;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "Account")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Account implements Serializable {
    @Id
    @Nationalized
    private String UserName;

    @Nationalized
    private String FullName;

    private String Password;

    // Dùng kiểu String để lưu được số 0
    private String Phone;

    private String Email;

    private Boolean Active;

    @Temporal(TemporalType.DATE)
    private Date Birth;

    private Boolean Gender;

    private String Avatar;

    @OneToMany(mappedBy = "account")
    List<Cart> carts;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    List<Authority> authorities;

    @OneToOne(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Review review;

    @OneToMany(mappedBy = "account")
    List<Order> orders;
}
