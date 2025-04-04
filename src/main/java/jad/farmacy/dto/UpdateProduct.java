package jad.farmacy.dto;

public class UpdateProduct {
    long productId;

    String productName;

    String comercialName;

    Double sellPrice;

    Double buyPrice;

    String presentation;

    String qrCode;

    String expirationDate;

    String lote;

    int quantity;

    String description;

    long storeID;

    int blistersPerBox;
    int billsPerBlister;
    String brand;

    double pricePerPill;
    double pricePerBlister;

    private boolean sellByPill;

    private boolean sellByBlister;

    private boolean sellByBox;

    private boolean isPillType;

    private String Shelf;
    private String wholeSalePrice;

    public boolean isSellByPill() {
        return sellByPill;
    }

    private String type;

    private long supplierID;

    public long getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(long supplierID) {
        this.supplierID = supplierID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public double getPricePerPill() {
        return pricePerPill;
    }

    public void setPricePerPill(double pricePerPill) {
        this.pricePerPill = pricePerPill;
    }

    public double getPricePerBlister() {
        return pricePerBlister;
    }

    public void setPricePerBlister(double pricePerBlister) {
        this.pricePerBlister = pricePerBlister;
    }

    public int getBlistersPerBox() {
        return blistersPerBox;
    }

    public void setBlistersPerBox(int blistersPerBox) {
        this.blistersPerBox = blistersPerBox;
    }

    public int getBillsPerBlister() {
        return billsPerBlister;
    }

    public void setBillsPerBlister(int billsPerBlister) {
        this.billsPerBlister = billsPerBlister;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
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

    public long getStoreID() {
        return storeID;
    }

    public void setStoreID(long storeID) {
        this.storeID = storeID;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getShelf() {
        return Shelf;
    }

    public void setShelf(String shelf) {
        Shelf = shelf;
    }

    public String getWholeSalePrice() {
        return wholeSalePrice;
    }

    public void setWholeSalePrice(String wholeSalePrice) {
        this.wholeSalePrice = wholeSalePrice;
    }
}
