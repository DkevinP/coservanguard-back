package com.co.coservanguard_backend.interfaces.services;


import com.co.coservanguard_backend.dto.PuestoDTO;

import java.util.List;

public interface PuestoServiceInterface {

    PuestoDTO crearPuesto(PuestoDTO puestoDTO);

    List<PuestoDTO> listarPuestos();

}
