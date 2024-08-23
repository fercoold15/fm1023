package jad.farmacy.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Table(name = "OUTGOINGS")
@Entity
public class Outgoing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OUTGOING_ID",nullable = false)
    private Long id;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "AMOUNT")
    private String amount;
    @Column(name = "VALUE")
    private String value;

    @Column(name = "BILL_DATE")
    private LocalDate billDate;

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", nullable = false)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public LocalDate getBillDate() {
        return billDate;
    }

    public void setBillDate(LocalDate billDate) {
        this.billDate = billDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
