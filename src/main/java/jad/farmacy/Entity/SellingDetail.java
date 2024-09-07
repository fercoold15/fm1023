package jad.farmacy.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "SELLING_DETAILS")
public class SellingDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SELLING_DETAIL_ID", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "SELLING_ID", referencedColumnName = "SELLING_ID", nullable = false)
    private Selling selling;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID", nullable = false)
    private Product product;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "TOTAL")
    private double total;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Selling getSelling() {
        return selling;
    }

    public void setSelling(Selling selling) {
        this.selling = selling;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
}
