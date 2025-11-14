package com.co.coservanguard_backend.interfaces.services;

import com.co.coservanguard_backend.dto.CodigoQRDTO;
import com.co.coservanguard_backend.dto.MarcacionQRDTO;

import java.util.List;

public interface MarcacionQRServiceInterface {

    MarcacionQRDTO crearMarcacion(MarcacionQRDTO marcacionQRDTO);

    List<MarcacionQRDTO> listarMarcacion();

}
