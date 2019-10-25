package com.gx.bootjpa.service.impl;

import com.gx.bootjpa.model.Permission;
import com.gx.bootjpa.model.Role;
import com.gx.bootjpa.repository.PermissionRepository;
import com.gx.bootjpa.repository.RoleRepository;
import com.gx.bootjpa.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gx
 * @ClassName: RoleServiceImpl
 * @Description: java类作用描述
 * @date 2019/10/25 22:50
 * @Version: 1.0
 * @since
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    @Transactional
    @Override
    public void saveRolePermissions(Long roleId, List<Long> permissionIds) {
        List<Permission> pList=new ArrayList<>();
        for (long id:permissionIds){
            Permission p = permissionRepository.getOne(id);
            pList.add(p);
        }
        Role role = roleRepository.getOne(1L);
        role.setPermissions(pList);
        roleRepository.saveAndFlush(role);
    }
}
