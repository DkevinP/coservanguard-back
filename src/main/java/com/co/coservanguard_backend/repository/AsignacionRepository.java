package com.co.coservanguard_backend.repository;

import com.co.coservanguard_backend.entity.Asignacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AsignacionRepository extends JpaRepository<Asignacion, Integer> {

    @Query("SELECT a FROM Asignacion a WHERE a.id_user.id_user = :id_user AND a.id_puesto.id_puesto = :id_puesto")
    Asignacion findByUsuarioAndPuesto(@Param("id_user") Integer id_user,
                                      @Param("id_puesto") Integer id_puesto);

}
