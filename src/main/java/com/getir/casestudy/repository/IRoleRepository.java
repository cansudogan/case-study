package com.getir.casestudy.repository;

import com.getir.casestudy.domain.Role;
import com.getir.casestudy.model.enums.ERole;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IRoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByRole(ERole role);
}
