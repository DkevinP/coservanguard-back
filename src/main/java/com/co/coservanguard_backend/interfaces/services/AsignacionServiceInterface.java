package com.co.coservanguard_backend.interfaces.services;

import com.co.coservanguard_backend.dto.AsignacionDTO;
import com.co.coservanguard_backend.dto.CargoDTO;

import java.util.List;

public interface AsignacionServiceInterface {

    AsignacionDTO crearAsignacion(AsignacionDTO asignacionDTO);

    List<AsignacionDTO> listarAsignacion();
}
