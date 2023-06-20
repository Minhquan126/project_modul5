package com.example.spring_security_demotiepchonho.ra.service;

import com.example.spring_security_demotiepchonho.ra.model.RoleName;
import com.example.spring_security_demotiepchonho.ra.model.Roles;

import java.util.Optional;

public interface IRoleService extends IGenericService<Roles,Long>{
   Roles findByRoleName(RoleName roleName);
}
