package com.gx.bootjpa.repository;

import com.gx.bootjpa.model.ProductStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author gx
 * @ClassName: StoreRepository
 * @Description: java类作用描述
 * @date 2019/8/9 5:22
 * @Version: 1.0
 * @since
 */
@Repository
public interface StoreRepository extends JpaRepository<ProductStore, Integer> {
}
