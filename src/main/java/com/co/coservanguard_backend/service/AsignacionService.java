package com.co.coservanguard_backend.service;

import com.co.coservanguard_backend.dto.AsignacionDTO;

import com.co.coservanguard_backend.entity.*;
import com.co.coservanguard_backend.interfaces.services.AsignacionServiceInterface;
import com.co.coservanguard_backend.repository.AsignacionRepository;
import com.co.coservanguard_backend.repository.PuestoRepository;
import com.co.coservanguard_backend.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AsignacionService implements AsignacionServiceInterface {

    private AsignacionRepository asignacionRepository;

    private PuestoRepository puestoRepository;

    private UsuarioRepository usuarioRepository;

    public AsignacionService(AsignacionRepository asignacionRepository, PuestoRepository puestoRepository, UsuarioRepository usuarioRepository) {
        this.asignacionRepository = asignacionRepository;
        this.puestoRepository = puestoRepository;
        this.usuarioRepository = usuarioRepository;
    }



    @Override
    public AsignacionDTO crearAsignacion(AsignacionDTO asignacionDTO) {
        Asignacion asignacion = new Asignacion();
        Puesto puesto = puestoRepository.findById(asignacionDTO.getId_puesto())
                .orElseThrow(() -> new NoSuchElementException("Puesto no encontrado"));
        asignacion.setId_puesto(puesto);
        Usuario usuario = usuarioRepository.findById(asignacionDTO.getId_user())
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));
        asignacion.setId_user(usuario);
        Asignacion asignacion1 = asignacionRepository.save(asignacion);

        return new AsignacionDTO(asignacion1.getId_user().getId_user(),asignacion1.getId_puesto().getId_puesto());
    }

    @Override
    public List<AsignacionDTO> listarAsignacion() {
        List<Asignacion> asignacion = asignacionRepository.findAll();
        List<AsignacionDTO> asignacionDTOS = new ArrayList<>();
        for (Asignacion asignacion1 : asignacion) {
            asignacionDTOS.add(new AsignacionDTO(asignacion1.getId_asignacion(),asignacion1.getId_user().getId_user(),asignacion1.getId_puesto().getId_puesto()));
        }
        return asignacionDTOS;
    }

    public Asignacion findByUsuarioIdUserAndPuestoIdPuesto(Integer usuarioId, Integer puestoId){
        Asignacion asignacion = asignacionRepository.findByUsuarioAndPuesto(usuarioId,puestoId);
        return asignacion;
    }
}
