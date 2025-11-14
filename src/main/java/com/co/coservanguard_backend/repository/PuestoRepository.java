package com.co.coservanguard_backend.repository;

import com.co.coservanguard_backend.entity.Puesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PuestoRepository extends JpaRepository<Puesto, Integer> {
}
