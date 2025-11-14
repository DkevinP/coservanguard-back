package com.co.coservanguard_backend.controller;

import com.co.coservanguard_backend.dto.MarcacionQRDTO;

import com.co.coservanguard_backend.dto.MarcacionRequest;
import com.co.coservanguard_backend.entity.Asignacion;
import com.co.coservanguard_backend.entity.CodigoQR;
import com.co.coservanguard_backend.entity.Usuario;
import com.co.coservanguard_backend.service.AsignacionService;
import com.co.coservanguard_backend.service.CodigoQRService;
import com.co.coservanguard_backend.service.MarcacionQRService;
import com.co.coservanguard_backend.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/marcacionqr")
@CrossOrigin(origins = "http://localhost:4200")
public class MarcacionQRController {

    private  MarcacionQRService marcacionQRService;
    private UsuarioService usuarioService;
    private CodigoQRService codigoQRService;
    private AsignacionService asignacionService;

    public MarcacionQRController(MarcacionQRService marcacionQRService, UsuarioService usuarioService, CodigoQRService codigoQRService, AsignacionService asignacionService) {
        this.marcacionQRService = marcacionQRService;
        this.usuarioService = usuarioService;
        this.codigoQRService = codigoQRService;
        this.asignacionService = asignacionService;
    }


    @PostMapping("/crear-marcacion")
    public ResponseEntity<MarcacionQRDTO> crearMarcacion(@Valid @RequestBody MarcacionQRDTO marcacionQRDTO) {
        LocalDateTime localDateTime = LocalDateTime.now();
        marcacionQRDTO.setFecha(localDateTime);
        MarcacionQRDTO marcacionQRDTO1 = marcacionQRService.crearMarcacion(marcacionQRDTO);


        return ResponseEntity.status(HttpStatus.CREATED).body(marcacionQRDTO1);
    }

    @PostMapping("/crear-marcacion-app")
    public ResponseEntity<MarcacionQRDTO> crearMarcacion(@RequestBody MarcacionRequest marcacionRequest) {

        Usuario usuario = usuarioService.findByCedula(marcacionRequest.getCedula());


        // Extraer id_puesto del texto QR (por ejemplo "COSERVANGUARD|5")
        String[] partes = marcacionRequest.getQr().split("\\@");
        int idPuesto = Integer.parseInt(partes[1]);

        CodigoQR codigoQR = codigoQRService.findByCodigo(marcacionRequest.getQr());

        if((codigoQR.getLatitude() == null || codigoQR.getLatitude() == 0) && (codigoQR.getLongitude() == null || codigoQR.getLongitude() == 0)) {
            if(codigoQRService.actualizarLatLongCodigo(codigoQR.getId_codigo(), marcacionRequest.getLatitude(), marcacionRequest.getLongitude())) {
                System.out.println("Codigo Qr de puesto sin ubicacion, se actualzia en la marcacion hecha");
            }else{
                System.out.println("Error en la actualizacion de la ubicacion");
            }
        }

        Asignacion asignacion = asignacionService.findByUsuarioIdUserAndPuestoIdPuesto(usuario.getId_user(), idPuesto);

        Integer distancia =(int)calcularDistanciaHaversine(codigoQR.getLatitude(), codigoQR.getLongitude(), marcacionRequest.getLatitude(), marcacionRequest.getLongitude());

        System.out.println("Distancia de la ubicacion es: "+distancia);

        MarcacionQRDTO marcacionQRDTO = new MarcacionQRDTO();

        marcacionQRDTO.setId_asignacion(asignacion.getId_asignacion());
        marcacionQRDTO.setId_codigo(codigoQR.getId_codigo());
        marcacionQRDTO.setLatitude(marcacionRequest.getLatitude());
        marcacionQRDTO.setLongitude(marcacionRequest.getLongitude());
        marcacionQRDTO.setFecha(LocalDateTime.now());
        marcacionQRDTO.setDistanciaM(distancia);
        marcacionQRDTO.setEs_cercano(distancia <= 100 ? Boolean.TRUE : Boolean.FALSE);

        MarcacionQRDTO marcacionQRDTO1 = marcacionQRService.crearMarcacion(marcacionQRDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(marcacionQRDTO1);
    }

    @GetMapping("/list-marcacion")
    public ResponseEntity<List<MarcacionQRDTO>> listarMarcacion() {
        List<MarcacionQRDTO>marcacionQR= marcacionQRService.listarMarcacion();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(marcacionQR);
    }

    public static double calcularDistanciaHaversine(double latCodigo, double lonCodigo, double latMarcacion, double lonMarcacion) {

        final int R = 6371000;

        double latDistance = Math.toRadians(latMarcacion - latCodigo);
        double lonDistance = Math.toRadians(lonMarcacion - lonCodigo);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(latCodigo)) * Math.cos(Math.toRadians(latMarcacion))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }

}
