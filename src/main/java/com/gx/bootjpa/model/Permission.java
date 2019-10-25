package com.gx.bootjpa.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * @author gx
 * @ClassName: Permission
 * @Description: java类作用描述
 * @date 2019/10/25 21:55
 * @Version: 1.0
 * @since
 */
@Entity
@Table(name = "permission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @ManyToMany(mappedBy ="permissions")
    private Set<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
