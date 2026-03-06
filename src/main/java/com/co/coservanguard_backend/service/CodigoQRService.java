package com.co.coservanguard_backend.service;

import com.co.coservanguard_backend.dto.CodigoQRDTO;

import com.co.coservanguard_backend.entity.CodigoQR;
import com.co.coservanguard_backend.entity.Puesto;


import com.co.coservanguard_backend.interfaces.services.CodigoQRServiceInterface;
import com.co.coservanguard_backend.repository.CodigoQRRepository;
import com.co.coservanguard_backend.repository.PuestoRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CodigoQRService implements CodigoQRServiceInterface {

    private CodigoQRRepository codigoQRRepository;
    private PuestoRepository puestoRepository;

    public CodigoQRService(CodigoQRRepository codigoQRRepository, PuestoRepository puestoRepository) {
        this.codigoQRRepository = codigoQRRepository;
        this.puestoRepository = puestoRepository;
    }

    public String generateQrCode(String text, int width, int height) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);

        byte[] pngData = outputStream.toByteArray();
        return Base64.getEncoder().encodeToString(pngData);
    }


    @Override
    public CodigoQRDTO crearCodigos(CodigoQRDTO codigoQRDTO) {
        Puesto puesto = puestoRepository.findById(codigoQRDTO.getId_puesto())
                .orElseThrow(() -> new NoSuchElementException("Codigo no encontrado"));
        CodigoQR codigoQR = new CodigoQR();
        codigoQR.setQr(codigoQRDTO.getQr());
        codigoQR.setLatitude(codigoQRDTO.getLatitude());
        codigoQR.setLongitude(codigoQRDTO.getLongitude());
        codigoQR.setId_puesto(puesto);


        CodigoQR save = codigoQRRepository.save(codigoQR);

        return new CodigoQRDTO(codigoQR.getQr(),codigoQR.getLatitude(),codigoQR.getLongitude(),codigoQR.getId_puesto().getId_puesto());

    }

    @Override
    public List<CodigoQRDTO> listarCodigo() {
        List<CodigoQR> codigoQRList = codigoQRRepository.findAll();
        List<CodigoQRDTO> codigoQRDTOList = new ArrayList<>();
        for (CodigoQR codigoQR : codigoQRList) {
            codigoQRDTOList.add(new CodigoQRDTO(codigoQR.getId_codigo(), codigoQR.getQr(),codigoQR.getLatitude(),codigoQR.getLongitude(),codigoQR.getId_puesto().getId_puesto()));
        }
        return codigoQRDTOList;

    }

    @Override
    public Boolean actualizarLatLongCodigo(Integer codigo, Double latitude, Double longitude) {
        CodigoQR codigoQRAct= codigoQRRepository.findById(codigo).orElseThrow(() -> new NoSuchElementException("Codigo no encontrado"));
        System.out.print("Latiude campos"+latitude);
        System.out.print("Longitde"+longitude);
        codigoQRAct.setLatitude(latitude);
        codigoQRAct.setLongitude(longitude);
        System.out.println("Funcion de actualizar: ");
        System.out.println("Latitud: "+codigoQRAct.getLatitude());
        System.out.println("Longitud: "+codigoQRAct.getLongitude());
        System.out.println("Informacion de codigo: "+codigoQRAct.getQr());

        try {
            codigoQRRepository.save(codigoQRAct);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public CodigoQR findByCodigo(String codigo){
        CodigoQR codigoQR = codigoQRRepository.findByQr(codigo);
        return codigoQR;
    }

}
