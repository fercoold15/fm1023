package jad.farmacy.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jad.farmacy.dto.FelCertificationRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class FelCertificationService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${fel.base-url:https://certificador.feel.com.gt/fel/certificacion/v2/dte/}")
    private String baseUrl;

    @Value("${fel.annul-url:https://certificador.feel.com.gt/fel/anulacion/dte/}")
    private String annulUrl;

    // Consultas por CUI
    @Value("${fel.consultas.login-url:https://certificador.feel.com.gt/api/v2/servicios/externos/login}")
    private String consultasLoginUrl;

    @Value("${fel.consultas.cui-url:https://certificador.feel.com.gt/api/v2/servicios/externos/cui}")
    private String consultasCuiUrl;

    public FelCertificationService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public Map<String, Object> certificarDte(
            FelCertificationRequest request,
            String usuario,
            String llave,
            String identificadorHeader
    ) {
        return postFelRequest(baseUrl, request, usuario, llave, identificadorHeader);
    }

    public Map<String, Object> anularDte(
            FelCertificationRequest request,
            String usuario,
            String llave,
            String identificadorHeader
    ) {
        // El consumo es igual al de certificación, solo cambia la URL
        return postFelRequest(annulUrl, request, usuario, llave, identificadorHeader);
    }

    private Map<String, Object> postFelRequest(
            String url,
            FelCertificationRequest request,
            String usuario,
            String llave,
            String identificadorHeader
    ) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("usuario", usuario);
        headers.add("llave", llave);
        headers.add("identificador", identificadorHeader != null && !identificadorHeader.isBlank()
                ? identificadorHeader
                : (request.getIdentificador() != null && !request.getIdentificador().isBlank()
                ? request.getIdentificador()
                : UUID.randomUUID().toString()));

        Map<String, Object> body = new HashMap<>();
        body.put("nit_emisor", request.getNitEmisor());
        body.put("correo_copia", request.getCorreoCopia());
        body.put("xml_dte", request.getXmlDte());

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
            Map<String, Object> result = new HashMap<>();
            result.put("status", response.getStatusCodeValue());
            result.put("body", parseJsonSafely(response.getBody()));
            return result;
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            Map<String, Object> error = new HashMap<>();
            error.put("status", ex.getStatusCode().value());
            error.put("body", parseJsonSafely(ex.getResponseBodyAsString()));
            return error;
        }
    }

    private JsonNode parseJsonSafely(String json) {
        try {
            if (json == null || json.isBlank()) return objectMapper.nullNode();
            return objectMapper.readTree(json);
        } catch (Exception e) {
            // Devuelve como texto si no es JSON válido
            return objectMapper.getNodeFactory().textNode(json);
        }
    }

    // ======== API Consultas por CUI ========
    public Map<String, Object> consultasLogin(String prefijo, String llave) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("prefijo", prefijo);
        form.add("llave", llave);

        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(form, headers);
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(consultasLoginUrl, entity, String.class);
            Map<String, Object> result = new HashMap<>();
            result.put("status", response.getStatusCodeValue());
            result.put("body", parseJsonSafely(response.getBody()));
            return result;
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            Map<String, Object> error = new HashMap<>();
            error.put("status", ex.getStatusCode().value());
            error.put("body", parseJsonSafely(ex.getResponseBodyAsString()));
            return error;
        }
    }

    public Map<String, Object> consultarPorCui(String bearerToken, String cui) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("Authorization", "Bearer " + bearerToken);

        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("cui", cui);

        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(form, headers);
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(consultasCuiUrl, entity, String.class);
            Map<String, Object> result = new HashMap<>();
            result.put("status", response.getStatusCodeValue());
            result.put("body", parseJsonSafely(response.getBody()));
            return result;
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            Map<String, Object> error = new HashMap<>();
            error.put("status", ex.getStatusCode().value());
            error.put("body", parseJsonSafely(ex.getResponseBodyAsString()));
            return error;
        }
    }
}
