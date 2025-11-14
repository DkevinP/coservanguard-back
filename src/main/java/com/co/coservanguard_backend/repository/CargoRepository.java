package com.co.coservanguard_backend.repository;

import com.co.coservanguard_backend.entity.Cargo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Integer> {
}
