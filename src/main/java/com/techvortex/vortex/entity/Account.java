package com.techvortex.vortex.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Nationalized;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    @DateTimeFormat(pattern = "dd-MM-yyyy")
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

    public void addAttribute(String string, List<Account> findAll) {
    }

    public Account orElseThrow(Object object) {
        return null;
    }
}
