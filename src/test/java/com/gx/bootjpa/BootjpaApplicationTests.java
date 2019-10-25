package com.gx.bootjpa;

import com.gx.bootjpa.model.Permission;
import com.gx.bootjpa.model.Role;
import com.gx.bootjpa.repository.PermissionRepository;
import com.gx.bootjpa.repository.RoleRepository;
import com.gx.bootjpa.service.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootjpaApplicationTests {
@Autowired
private RoleRepository roleRepository;
@Autowired
private PermissionRepository permissionRepository;
@Autowired
private RoleService roleService;
    @Test
    public void saveRole() {
        Role role=new Role();
        role.setName("gx");
        roleRepository.save(role);
    }

    @Test
    public void savePermission() {
        Permission p=new Permission();
        p.setName("3");
        permissionRepository.save(p);
    }

    @Test
    public void saveRolePermissions() {
        roleService.saveRolePermissions(1L, Arrays.asList(1L));
    }

}
