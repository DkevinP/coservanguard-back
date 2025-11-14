package com.co.coservanguard_backend.repository;

import com.co.coservanguard_backend.entity.MarcacionQR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcacionQRRepository extends JpaRepository<MarcacionQR, Integer> {
}
