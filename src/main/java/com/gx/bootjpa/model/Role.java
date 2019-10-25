package com.gx.bootjpa.model;

import javax.persistence.*;
import java.util.List;

/**
 * @author gx
 * @ClassName: Role
 * @Description: java类作用描述
 * @date 2019/10/25 21:52
 * @Version: 1.0
 * @since
 */
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "user_authority",joinColumns = @JoinColumn(name ="role_permission"),
            inverseJoinColumns = @JoinColumn(name ="permission_id"))
    private List<Permission> permissions;

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

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
