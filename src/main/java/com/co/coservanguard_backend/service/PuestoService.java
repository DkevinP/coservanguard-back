package com.co.coservanguard_backend.service;

import com.co.coservanguard_backend.dto.PuestoDTO;

import com.co.coservanguard_backend.entity.Puesto;
import com.co.coservanguard_backend.entity.SedeCliente;
import com.co.coservanguard_backend.interfaces.services.PuestoServiceInterface;
import com.co.coservanguard_backend.repository.PuestoRepository;
import com.co.coservanguard_backend.repository.SedeClienteRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PuestoService implements PuestoServiceInterface {

    private final PuestoRepository puestoRepository;
    private final SedeClienteRepository sedeClienteRepository;

    public PuestoService(PuestoRepository repository, SedeClienteRepository sedeClienteRepository) {
        this.puestoRepository = repository;
        this.sedeClienteRepository = sedeClienteRepository;
    }

    @Override
    public PuestoDTO crearPuesto(PuestoDTO puestoDTO) {
        SedeCliente sedeCliente = sedeClienteRepository.findById(puestoDTO.getId_sede())
                .orElseThrow(() -> new NoSuchElementException("Sede del Cliente no encontrado"));

        Puesto puesto = new Puesto();

        puesto.setPuesto(puestoDTO.getPuesto());
        puesto.setId_sede(sedeCliente);

        Puesto p = puestoRepository.save(puesto);
        return new PuestoDTO(p.getPuesto(), p.getId_sede().getId_sede());
    }

    @Override
    public List<PuestoDTO> listarPuestos() {
        List<Puesto> puestos = puestoRepository.findAll();
        List<PuestoDTO> puestoDTOs = new ArrayList<>();
        for (Puesto p : puestos) {
            puestoDTOs.add(new PuestoDTO(p.getId_puesto(),p.getPuesto(),p.getId_sede().getId_sede()));
        }

        return puestoDTOs;
    }
}
