package com.gx.bootjpa.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author gx
 * @ClassName: User
 * @Description: java类作用描述
 * @date 2019/8/9 4:43
 * @Version: 1.0
 * @since
 */
@Entity
@Table(name = "goods")
public class Goods implements Serializable {
    @EmbeddedId
    private GoodsKey<ProductStore> id;
    @Column(name = "price")
    private Integer price;

    public GoodsKey getId() {
        return id;
    }

    public void setId(GoodsKey id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

}
