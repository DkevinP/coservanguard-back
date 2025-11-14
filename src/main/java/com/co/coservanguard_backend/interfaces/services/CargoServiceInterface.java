package com.co.coservanguard_backend.interfaces.services;

import com.co.coservanguard_backend.dto.CargoDTO;


import java.util.List;

public interface CargoServiceInterface{

    CargoDTO crearCargo(CargoDTO cargoDTO);

    List<CargoDTO> listarCargo();

}
