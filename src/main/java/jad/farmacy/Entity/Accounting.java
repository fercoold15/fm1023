package jad.farmacy.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Table(name = "ACCOUNTING")
@Entity
public class Accounting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNTING_ID",nullable = false)
    private Long accountingID;

    @Column(name = "TOTAL_OUTGOINGS")
    private double totalOutgoings;

    @Column(name = "TOTAL_SELLINGS")
    private double totalSellings;

    @Column(name = "TOTAL_NET")
    private double totalNet;

    @Column(name = "COMPARISON_DATE")
    private LocalDate comparisonDate;

    public Long getAccountingID() {
        return accountingID;
    }

    public void setAccountingID(Long accountingID) {
        this.accountingID = accountingID;
    }

    public double getTotalOutgoings() {
        return totalOutgoings;
    }

    public void setTotalOutgoings(double totalOutgoings) {
        this.totalOutgoings = totalOutgoings;
    }

    public double getTotalSellings() {
        return totalSellings;
    }

    public void setTotalSellings(double totalSellings) {
        this.totalSellings = totalSellings;
    }

    public double getTotalNet() {
        return totalNet;
    }

    public void setTotalNet(double totalNet) {
        this.totalNet = totalNet;
    }

    public LocalDate getComparisonDate() {
        return comparisonDate;
    }

    public void setComparisonDate(LocalDate comparisonDate) {
        this.comparisonDate = comparisonDate;
    }
}
