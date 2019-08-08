package com.gx.bootjpa.service.impl;

import com.gx.bootjpa.model.ProductStore;
import com.gx.bootjpa.repository.StoreRepository;
import com.gx.bootjpa.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gx
 * @ClassName: StoreServiceImpl
 * @Description: java类作用描述
 * @date 2019/8/9 5:24
 * @Version: 1.0
 * @since
 */
@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    StoreRepository storeRepository;

    @Override
    public ProductStore addStore(ProductStore s) {
       return storeRepository.saveAndFlush(s);
    }

    @Override
    public void deleteStore(Integer id) {
        storeRepository.deleteById(id);
    }

    @Override
    public ProductStore findStore(Integer id) {
        return storeRepository.findById(id).orElse(null);
    }
}
