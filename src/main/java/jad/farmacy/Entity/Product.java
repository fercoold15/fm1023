package jad.farmacy.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Table(name = "PRODUCTS")
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID",nullable = false)
    private Long id;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "COMERCIAL_NAME")
    private String comercialName;

    @Column(name = "SELL_PRICE")
    private Double sellPrice;

    @Column(name = "BUY_PRICE")
    private Double buyPrice;

    @Column(name = "PRESENTATION")
    private String presentation;

    @Column(name = "QR_CODE")
    private String qrCode;

    @Column(name = "QUANTITY")
    private int quantity;
    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "EXPIRATION_DATE")
    private LocalDate expirationDate;

    @Column(name = "LOTE")
    private String lote;
    @Column(name = "BLISTERS_PER_BOX")
    private int blisterPerBox;
    @Column(name = "BILLS_PER_BLISTER")
    private int billsPerBlister;
    @Column(name = "BRAND")
    private String brand;

    @Column(name = "PRICE_PER_BLISTER")
    private double pricePerBlister;

    @Column(name = "PRICE_PER_PILL")
    private double pricePerPill;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "STORE_ID", referencedColumnName = "STORE_ID", nullable = false)
    private Store store;
    @Column(name = "SELL_BY_PILL")
    private boolean sellByPill;

    @Column(name = "SELL_BY_BLISTER")
    private boolean sellByBlister;

    @Column(name = "SELL_BY_BOX")
    private boolean sellByBox;

    @Column(name = "IS_PILL_TYPE")
    private boolean isPillType;

    @Column(name = "TYPE")
    private String type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SUPPLIER_ID", referencedColumnName = "SUPPLIER_ID")
    private Supplier supplier;

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isSellByPill() {
        return sellByPill;
    }

    public void setSellByPill(boolean sellByPill) {
        this.sellByPill = sellByPill;
    }

    public boolean isSellByBlister() {
        return sellByBlister;
    }

    public void setSellByBlister(boolean sellByBlister) {
        this.sellByBlister = sellByBlister;
    }

    public boolean isSellByBox() {
        return sellByBox;
    }

    public void setSellByBox(boolean sellByBox) {
        this.sellByBox = sellByBox;
    }

    public boolean isPillType() {
        return isPillType;
    }

    public void setPillType(boolean pillType) {
        isPillType = pillType;
    }

    public double getPricePerBlister() {
        return pricePerBlister;
    }

    public void setPricePerBlister(double pricePerBlister) {
        this.pricePerBlister = pricePerBlister;
    }

    public double getPricePerPill() {
        return pricePerPill;
    }

    public void setPricePerPill(double pricePerPill) {
        this.pricePerPill = pricePerPill;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getBlisterPerBox() {
        return blisterPerBox;
    }

    public void setBlisterPerBox(int blisterPerBox) {
        this.blisterPerBox = blisterPerBox;
    }

    public int getBillsPerBlister() {
        return billsPerBlister;
    }

    public void setBillsPerBlister(int billsPerBlister) {
        this.billsPerBlister = billsPerBlister;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getComercialName() {
        return comercialName;
    }

    public void setComercialName(String comercialName) {
        this.comercialName = comercialName;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }
}
