package jad.farmacy.dto;

import java.time.LocalDate;
import java.util.List;

public class NewSelling {
    private LocalDate sellingDate;
    private int userId;
    private double sellingTotal;
    private List<NewSellingDetail> details;
    private String XML;




    public NewSelling(LocalDate sellingDate, int userId, double sellingTotal, List<NewSellingDetail> details, String xml) {
        this.sellingDate = sellingDate;
        this.userId = userId;
        this.sellingTotal = sellingTotal;
        this.details = details;
        XML = xml;
    }

    // Getters y Setters
    public LocalDate getSellingDate() {
        return sellingDate;
    }

    public void setSellingDate(LocalDate sellingDate) {
        this.sellingDate = sellingDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getSellingTotal() {
        return sellingTotal;
    }

    public void setSellingTotal(double sellingTotal) {
        this.sellingTotal = sellingTotal;
    }

    public List<NewSellingDetail> getDetails() {
        return details;
    }

    public void setDetails(List<NewSellingDetail> details) {
        this.details = details;
    }

    public String getXML() {
        return XML;
    }

    public void setXML(String XML) {
        this.XML = XML;
    }

    // Clase interna para los detalles de la venta
    public static class NewSellingDetail {
        private Long productId;
        private int quantity;
        private double total;

        private String sellingUnit;

        // Constructor
        public NewSellingDetail() {}



        // Getters y Setters
        public Long getProductId() {
            return productId;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }

        public String getSellingUnit() {
            return sellingUnit;
        }

        public void setSellingUnit(String sellingUnit) {
            this.sellingUnit = sellingUnit;
        }
    }
}

