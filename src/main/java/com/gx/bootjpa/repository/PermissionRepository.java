package com.gx.bootjpa.repository;

import com.gx.bootjpa.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author gx
 * @ClassName: PermissionRepository
 * @Description: java类作用描述
 * @date 2019/10/25 22:40
 * @Version: 1.0
 * @since
 */
@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
