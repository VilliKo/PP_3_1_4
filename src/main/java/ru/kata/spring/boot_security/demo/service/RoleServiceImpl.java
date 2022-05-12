package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import java.util.HashSet;
import java.util.Set;

@Transactional
@Service
public class RoleServiceImpl implements RoleService{


    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void addRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void removeRole(long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Set<Role> getAllRoles() {
        return new HashSet<>(roleRepository.findAll());
    }

    @Override
    public Role getRoleById(long id) {
        return roleRepository.findRoleById(id);
    }

    @Override
    public Role getRoleByRoleName(String roleName) {
        return roleRepository.findRoleByRoleName(roleName);
    }
}
