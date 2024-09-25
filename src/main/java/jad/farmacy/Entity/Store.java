package jad.farmacy.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Table(name = "STORES")
@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STORE_ID",nullable = false)
    private Long id;

    @Column(name = "STORE_NAME")
    private String storeName;

    @Column(name = "STORE_DIRECTION")
    private String storeDirection;

    @OneToMany(fetch = FetchType.EAGER,mappedBy="product")
    @JsonIgnore
    private List<Product> productList;

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
