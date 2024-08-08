package jad.farmacy.dto;

public class UpdateStore {
    long storeId;
    String storeName;
    String storeDirection;

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreDirection() {
        return storeDirection;
    }

    public void setStoreDirection(String storeDirection) {
        this.storeDirection = storeDirection;
    }
}
