package jad.farmacy.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "SELLINGS")
public class Selling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SELLING_ID",nullable = false)
    private Long id;

    @Column(name = "SELLING_DATE")
    private LocalDateTime sellingDate;

    @Column(name = "SELLING_TOTAL")
    private double sellingTotal;

    @Column(name = "XML")
    private String XML;

    public String getXML() {
        return XML;
    }

    public void setXML(String XML) {
        this.XML = XML;
    }

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "USER_ID", referencedColumnName = "id", nullable = false)
    private User user;

    @OneToMany(fetch = FetchType.EAGER,mappedBy="selling")
    private List<SellingDetail> sellingDetailList;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    public List<SellingDetail> getSellingDetailList() {
        return sellingDetailList;
    }

    public void setSellingDetailList(List<SellingDetail> sellingDetailList) {
        this.sellingDetailList = sellingDetailList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getSellingDate() {
        return sellingDate;
    }

    public void setSellingDate(LocalDateTime sellingDate) {
        this.sellingDate = sellingDate;
    }

    public double getSellingTotal() {
        return sellingTotal;
    }

    public void setSellingTotal(double sellingTotal) {
        this.sellingTotal = sellingTotal;
    }
}
