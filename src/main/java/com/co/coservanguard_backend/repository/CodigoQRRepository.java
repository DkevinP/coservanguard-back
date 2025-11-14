package com.co.coservanguard_backend.repository;

import com.co.coservanguard_backend.entity.CodigoQR;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodigoQRRepository extends JpaRepository<CodigoQR, Integer> {
    CodigoQR findByQr(String qr);

}
