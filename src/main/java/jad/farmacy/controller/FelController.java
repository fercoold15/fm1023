package jad.farmacy.controller;

import jad.farmacy.Service.FelCertificationService;
import jad.farmacy.configurations.GlobalResponse;
import jad.farmacy.dto.FelCertificationRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/fel")
public class FelController {
    private final FelCertificationService felCertificationService;

    public FelController(FelCertificationService felCertificationService) {
        this.felCertificationService = felCertificationService;
    }

    @PostMapping("/certificar")
    public ResponseEntity<GlobalResponse> certificar(
            @RequestHeader("usuario") String usuario,
            @RequestHeader("llave") String llave,
            @RequestHeader(value = "identificador", required = false) String identificador,
            @RequestBody FelCertificationRequest request) {
        Map<String, Object> result = felCertificationService.certificarDte(request, usuario, llave, identificador);

        int status = (int) result.getOrDefault("status", 500);
        Object body = result.get("body");

        GlobalResponse apiResponse = new GlobalResponse(status,
                status >= 200 && status < 300 ? "OK" : "ERROR",
                status >= 200 && status < 300 ? "Documento procesado" : "Error al procesar documento",
                body);
        return new ResponseEntity<>(apiResponse, HttpStatus.valueOf(status));
    }

    @PostMapping("/anular")
    public ResponseEntity<GlobalResponse> anular(
            @RequestHeader("usuario") String usuario,
            @RequestHeader("llave") String llave,
            @RequestHeader(value = "identificador", required = false) String identificador,
            @RequestBody FelCertificationRequest request) {
        Map<String, Object> result = felCertificationService.anularDte(request, usuario, llave, identificador);

        int status = (int) result.getOrDefault("status", 500);
        Object body = result.get("body");

        GlobalResponse apiResponse = new GlobalResponse(status,
                status >= 200 && status < 300 ? "OK" : "ERROR",
                status >= 200 && status < 300 ? "Anulación procesada" : "Error al procesar anulación",
                body);
        return new ResponseEntity<>(apiResponse, HttpStatus.valueOf(status));
    }

    // ===== Consultas por CUI =====
    @PostMapping("/consultas/login")
    public ResponseEntity<GlobalResponse> consultasLogin(
            @RequestHeader("prefijo") String prefijo,
            @RequestHeader("llave") String llave
    ) {
        Map<String, Object> result = felCertificationService.consultasLogin(prefijo, llave);

        int status = (int) result.getOrDefault("status", 500);
        Object body = result.get("body");

        GlobalResponse apiResponse = new GlobalResponse(status,
                status >= 200 && status < 300 ? "OK" : "ERROR",
                status >= 200 && status < 300 ? "Login exitoso" : "Error en login de consultas",
                body);
        return new ResponseEntity<>(apiResponse, HttpStatus.valueOf(status));
    }

    @PostMapping("/consultas/cui")
    public ResponseEntity<GlobalResponse> consultarCui(
            @RequestHeader("Authorization") String authorization,
            @RequestParam("cui") String cui
    ) {
        String token = authorization.replace("Bearer ", "");
        Map<String, Object> result = felCertificationService.consultarPorCui(token, cui);

        int status = (int) result.getOrDefault("status", 500);
        Object body = result.get("body");

        GlobalResponse apiResponse = new GlobalResponse(status,
                status >= 200 && status < 300 ? "OK" : "ERROR",
                status >= 200 && status < 300 ? "Consulta realizada" : "Error en consulta CUI",
                body);
        return new ResponseEntity<>(apiResponse, HttpStatus.valueOf(status));
    }
}
