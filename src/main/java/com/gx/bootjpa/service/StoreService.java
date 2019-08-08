package com.gx.bootjpa.service;

import com.gx.bootjpa.model.ProductStore;

/**
 * @author gx
 * @ClassName: StoreService
 * @Description: java类作用描述
 * @date 2019/8/9 5:23
 * @Version: 1.0
 * @since
 */
public interface StoreService {
    ProductStore addStore(ProductStore s);

    void deleteStore(Integer id);

    ProductStore findStore(Integer id);
}
