package com.co.coservanguard_backend.repository;

import com.co.coservanguard_backend.entity.SedeCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SedeClienteRepository extends JpaRepository<SedeCliente, Integer> {


}
