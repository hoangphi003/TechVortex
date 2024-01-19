package com.techvortex.vortex.entity;

import java.io.Serializable;
import java.util.List;

import org.hibernate.annotations.Nationalized;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "Role")
@Entity
public class Role implements Serializable {
    @Id
    private Integer RoleId;

    @Nationalized
    private String RoleName;

    @OneToMany(mappedBy = "role")
    List<Authority> authorities;
}
