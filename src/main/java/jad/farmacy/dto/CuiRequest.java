package jad.farmacy.dto;

public class CuiRequest {
    private String cui;

    public CuiRequest() {}

    public CuiRequest(String cui) {
        this.cui = cui;
    }

    public String getCui() {
        return cui;
    }

    public void setCui(String cui) {
        this.cui = cui;
    }
}

