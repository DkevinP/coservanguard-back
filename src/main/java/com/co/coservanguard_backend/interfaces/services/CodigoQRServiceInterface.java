package com.co.coservanguard_backend.interfaces.services;

import com.co.coservanguard_backend.dto.CodigoQRDTO;
import com.co.coservanguard_backend.entity.CodigoQR;


import java.util.List;

public interface CodigoQRServiceInterface {

    CodigoQRDTO crearCodigos(CodigoQRDTO codigoQRDTO);

    List<CodigoQRDTO> listarCodigo();

    Boolean actualizarLatLongCodigo(Integer codigo, Double latitude, Double longitude);
}
