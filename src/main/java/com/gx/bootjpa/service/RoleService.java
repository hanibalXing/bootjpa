package com.gx.bootjpa.service;

import java.util.List;

/**
 * @author gx
 * @ClassName: RoleService
 * @Description: java类作用描述
 * @date 2019/10/25 22:49
 * @Version: 1.0
 * @since
 */
public interface RoleService {
    void saveRolePermissions(Long roleId, List<Long> permissionIds);
}
