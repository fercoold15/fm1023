package jad.farmacy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FelCertificationRequest {
    @JsonProperty("nit_emisor")
    private String nitEmisor;

    @JsonProperty("correo_copia")
    private String correoCopia;

    // Debe ser el XML del DTE en Base64
    @JsonProperty("xml_dte")
    private String xmlDte;

    // Opcional: si se envía, lo usaremos para el header identificador
    private String identificador;

    public FelCertificationRequest() {}

    public String getNitEmisor() {
        return nitEmisor;
    }

    public void setNitEmisor(String nitEmisor) {
        this.nitEmisor = nitEmisor;
    }

    public String getCorreoCopia() {
        return correoCopia;
    }

    public void setCorreoCopia(String correoCopia) {
        this.correoCopia = correoCopia;
    }

    public String getXmlDte() {
        return xmlDte;
    }

    public void setXmlDte(String xmlDte) {
        this.xmlDte = xmlDte;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
}

