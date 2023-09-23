package com.ps.jac16.controller;

import com.ps.jac16.controller.model.ApiResponseMessage;
import com.ps.jac16.controller.model.HttpStatusMessages;
import com.ps.jac16.model.Cuenta;
import com.ps.jac16.services.CuentaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    CuentaServices cuentaService;


    @GetMapping
    public ResponseEntity<?> findByAll() {

        try {

            return ResponseEntity.ok().body(ApiResponseMessage.builder()
                    .status(HttpStatusMessages.OK.getStatusCode())
                    .message(HttpStatusMessages.OK.getStatusMessage())
                    .data(cuentaService.findByAll()).build());


        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponseMessage.builder()
                    .status(HttpStatusMessages.BAD_REQUEST.getStatusCode())
                    .message(HttpStatusMessages.BAD_REQUEST.getStatusMessage()+":"+e.getMessage())
                    .data(new ArrayList<Cuenta>()).build());
        }

    }

    @GetMapping("/{numeroCuenta}")
    public ResponseEntity<?> obtenerCuentaPorId(@PathVariable String numeroCuenta) throws Exception {
        try {
            return ResponseEntity.ok().body(ApiResponseMessage.builder()
                    .status(HttpStatusMessages.OK.getStatusCode())
                    .message(HttpStatusMessages.OK.getStatusMessage())
                    .data(cuentaService.findByNumberCount(numeroCuenta)).build());


        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponseMessage.builder()
                    .status(HttpStatusMessages.BAD_REQUEST.getStatusCode())
                    .message(HttpStatusMessages.BAD_REQUEST.getStatusMessage()+":"+e.getMessage())
                    .data(new ArrayList<Cuenta>()).build());
        }
    }

    @PostMapping
    public ResponseEntity<?> crearCuenta(@RequestBody Cuenta cuenta) {
        try {
            return ResponseEntity.ok().body(ApiResponseMessage.builder()
                    .status(HttpStatusMessages.OK.getStatusCode())
                    .message(HttpStatusMessages.OK.getStatusMessage())
                    .data(cuentaService.save(cuenta)).build());


        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponseMessage.builder()
                    .status(HttpStatusMessages.BAD_REQUEST.getStatusCode())
                    .message(HttpStatusMessages.BAD_REQUEST.getStatusMessage()+":"+e.getMessage())
                    .data(new ArrayList<Cuenta>()).build());
        }

    }

    @PutMapping
    public ResponseEntity<?> actualizarCuenta(@RequestBody Cuenta cuenta) {
        try {
            return ResponseEntity.ok().body(ApiResponseMessage.builder()
                    .status(HttpStatusMessages.OK.getStatusCode())
                    .message(HttpStatusMessages.OK.getStatusMessage())
                    .data(cuentaService.update(cuenta)).build());


        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponseMessage.builder()
                    .status(HttpStatusMessages.BAD_REQUEST.getStatusCode())
                    .message(HttpStatusMessages.BAD_REQUEST.getStatusMessage()+":"+e.getMessage())
                    .data(new ArrayList<Cuenta>()).build());
        }
    }

    @DeleteMapping("/{numeroDeCuenta}")
    public ResponseEntity<?> eliminarCuenta(@PathVariable String numeroDeCuenta) {
        try {
            cuentaService.delete(numeroDeCuenta);
            return ResponseEntity.ok().body(ApiResponseMessage.builder()
                    .status(HttpStatusMessages.OK.getStatusCode())
                    .message(HttpStatusMessages.OK.getStatusMessage())
                    .data(null).build());

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponseMessage.builder()
                    .status(HttpStatusMessages.BAD_REQUEST.getStatusCode())
                    .message(HttpStatusMessages.BAD_REQUEST.getStatusMessage()+":"+e.getMessage())
                    .data(null).build());
        }
    }

}
