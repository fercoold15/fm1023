package jad.farmacy.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "SELLINGS")
public class Selling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SELLING_ID",nullable = false)
    private Long id;

    @Column(name = "SELLING_DATE")
    private LocalDate sellingDate;

    @Column(name = "SELLING_TOTAL")
    private double sellingTotal;

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "id", nullable = false)
    private User user;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getSellingDate() {
        return sellingDate;
    }

    public void setSellingDate(LocalDate sellingDate) {
        this.sellingDate = sellingDate;
    }

    public double getSellingTotal() {
        return sellingTotal;
    }

    public void setSellingTotal(double sellingTotal) {
        this.sellingTotal = sellingTotal;
    }
}
