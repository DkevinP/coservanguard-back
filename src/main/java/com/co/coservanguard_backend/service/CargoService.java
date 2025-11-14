package com.co.coservanguard_backend.service;

import com.co.coservanguard_backend.dto.CargoDTO;
import com.co.coservanguard_backend.entity.Cargo;
import com.co.coservanguard_backend.interfaces.services.CargoServiceInterface;
import com.co.coservanguard_backend.repository.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CargoService implements CargoServiceInterface {

    private final CargoRepository cargoRepository;

    public CargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }
    @Override
    public CargoDTO crearCargo(CargoDTO cargoDTO) {
        Cargo cargo = new Cargo();
        cargo.setNombre_cargo(cargoDTO.getNombre_cargo());
        Cargo cargo1 = cargoRepository.save(cargo);
        return new CargoDTO(cargo1.getNombre_cargo());
    }

    @Override
    public List<CargoDTO> listarCargo() {

        List<Cargo> cargos = cargoRepository.findAll();
        List<CargoDTO> cargoDTOs = new ArrayList<>();
        for (Cargo cargo : cargos) {
            cargoDTOs.add(new CargoDTO(cargo.getId_cargo(),cargo.getNombre_cargo()));
        }

        return cargoDTOs;
    }
}
