package com.techvortex.vortex.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Table(name = "Authority")
@Entity
public class Authority implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer AuthorityID;

    @ManyToOne
    @JoinColumn(name = "UserName")
    Account account;

    @ManyToOne
    @JoinColumn(name = "RoleId")
    Role role;
}
