package com.co.coservanguard_backend.service;

import com.co.coservanguard_backend.dto.MarcacionQRDTO;
import com.co.coservanguard_backend.entity.*;
import com.co.coservanguard_backend.interfaces.services.MarcacionQRServiceInterface;
import com.co.coservanguard_backend.repository.AsignacionRepository;
import com.co.coservanguard_backend.repository.CodigoQRRepository;
import com.co.coservanguard_backend.repository.MarcacionQRRepository;
import com.co.coservanguard_backend.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MarcacionQRService implements MarcacionQRServiceInterface {

    private MarcacionQRRepository marcacionQRRepository;
    private CodigoQRRepository codigoQRRepository;
    private AsignacionRepository asignacionRepository;
    private UsuarioRepository usuarioRepository;


    public MarcacionQRService(MarcacionQRRepository marcacionQRRepository,
                              CodigoQRRepository codigoQRRepository, AsignacionRepository asignacionRepository,
                              UsuarioRepository usuarioRepository) {
       this.marcacionQRRepository = marcacionQRRepository;
       this.codigoQRRepository = codigoQRRepository;
       this.asignacionRepository = asignacionRepository;

    }

    @Override
    public MarcacionQRDTO crearMarcacion(MarcacionQRDTO marcacionQRDTO) {
        MarcacionQR marcacionQR = new MarcacionQR();
        Asignacion asignacion = asignacionRepository.findById(marcacionQRDTO.getId_asignacion())
                .orElseThrow(() -> new NoSuchElementException("Asignacion no encontrado"));

        marcacionQR.setId_asignacion(asignacion);

        CodigoQR codigoQR = codigoQRRepository.findById(marcacionQRDTO.getId_codigo())
                .orElseThrow(() -> new NoSuchElementException("Codigo no encontrado"));

        marcacionQR.setId_codigo(codigoQR);

        marcacionQR.setFecha(marcacionQRDTO.getFecha());
        marcacionQR.setLatitude(marcacionQRDTO.getLatitude());
        marcacionQR.setLongitude(marcacionQRDTO.getLongitude());
        marcacionQR.setDistanciaM(marcacionQRDTO.getDistanciaM());
        marcacionQR.setEs_cercano(marcacionQRDTO.getEs_cercano());

        MarcacionQR m = marcacionQRRepository.save(marcacionQR);
        return new MarcacionQRDTO(m.getId_asignacion().getId_asignacion(), m.getId_codigo().getId_codigo(),m.getFecha(),m.getLatitude(),m.getLongitude(),m.getDistanciaM(),m.getEs_cercano());
    }

    @Override
    public List<MarcacionQRDTO> listarMarcacion() {
        List<MarcacionQR> marcacionQRS = marcacionQRRepository.findAll();
        List<MarcacionQRDTO> marcacionQRDTOS = new ArrayList<>();
        for (MarcacionQR m : marcacionQRS) {
            marcacionQRDTOS.add(new MarcacionQRDTO(m.getId_logs(),m.getId_asignacion().getId_asignacion(), m.getId_codigo().getId_codigo(),m.getFecha(),m.getLatitude(),m.getLongitude(),m.getDistanciaM(),m.getEs_cercano()));
        }
        return marcacionQRDTOS;
    }


}
