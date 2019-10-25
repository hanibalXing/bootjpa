package com.gx.bootjpa.repository;

import com.gx.bootjpa.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author gx
 * @ClassName: RoleRepository
 * @Description: java类作用描述
 * @date 2019/10/25 22:39
 * @Version: 1.0
 * @since
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
