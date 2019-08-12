package com.gx.bootjpa.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author gx
 * @ClassName: ProductStore
 * @Description: java类作用描述
 * @date 2019/8/9 5:41
 * @Version: 1.0
 * @since
 */


@Entity
@Table(name = "store")
public class ProductStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToMany(mappedBy = "id.t", cascade = CascadeType.ALL)
    private Set<Goods> goods=new HashSet<>();

    @Column(name = "name")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Goods> getGoods() {
        return goods;
    }

    public void setGoods(Set<Goods> goods) {
        this.goods = goods;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

