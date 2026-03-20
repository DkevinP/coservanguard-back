package com.co.coservanguard_backend.controller;

import com.co.coservanguard_backend.dto.CodigoQRDTO;

import com.co.coservanguard_backend.service.CodigoQRService;

import com.google.zxing.WriterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/codigoqr")
@CrossOrigin(origins = "http://coservanguard.eastus.cloudapp.azure.com")
public class CodigoQRController {

    private CodigoQRService codigoQRService;

    public CodigoQRController(CodigoQRService codigoQRService) {
        this.codigoQRService = codigoQRService;
    }

    @PostMapping ("/crear-codigoqr")
    public ResponseEntity<CodigoQRDTO> crearCodigo(@RequestParam  Integer id_puesto) {
        try{
            CodigoQRDTO codigoQRDTO = new CodigoQRDTO(id_puesto);
            LocalDateTime fechaActual = LocalDateTime.now();

            String qrData= fechaActual +"@"+ codigoQRDTO.getId_puesto();

            codigoQRDTO.setQr(qrData);

            CodigoQRDTO codigoQRDTO1 =  codigoQRService.crearCodigos(codigoQRDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(codigoQRDTO1);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage() );
        }

    }



    @GetMapping("/listar-codigo")
    public ResponseEntity<List<CodigoQRDTO>> listarCodigo() {
        List<CodigoQRDTO> codigoQRDTOS = codigoQRService.listarCodigo();
        return ResponseEntity.ok().body(codigoQRDTOS);

    }

    @GetMapping("/listar-codigo-img")
    public ResponseEntity<List<CodigoQRDTO>> listarCodigoImg() throws IOException, WriterException {
        List<CodigoQRDTO> codigoQRDTOS = codigoQRService.listarCodigo();
        List<CodigoQRDTO> codigoQRDTOS1 = new ArrayList<>();
        String qrBase64="";
        for (CodigoQRDTO codigoQRDTO : codigoQRDTOS) {
             qrBase64 = codigoQRService.generateQrCode(codigoQRDTO.getQr(), 300, 300);
             codigoQRDTO.setQr(qrBase64);
             codigoQRDTOS1.add(codigoQRDTO);
        }

        return ResponseEntity.ok().body(codigoQRDTOS1);

    }
}
