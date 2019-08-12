package com.gx.bootjpa.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author gx
 * @ClassName: GoodsKey
 * @Description: java类作用描述
 * @date 2019/8/12 21:58
 * @Version: 1.0
 * @since
 */
@Embeddable
public class GoodsKey implements Serializable {
    @ManyToOne
    @JoinColumn(name = "store_id")
    private ProductStore productStore;
    @Column(name = "name")
    private String name;

    public ProductStore getProductStore() {
        return productStore;
    }

    public void setProductStore(ProductStore productStore) {
        this.productStore = productStore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoodsKey goodsKey = (GoodsKey) o;
        return Objects.equals(productStore, goodsKey.productStore) &&
                Objects.equals(name, goodsKey.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productStore, name);
    }


}
