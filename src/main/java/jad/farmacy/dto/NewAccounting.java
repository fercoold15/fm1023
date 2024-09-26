package jad.farmacy.dto;

import jakarta.persistence.Column;

import java.time.LocalDate;

public class NewAccounting {

    private double totalOutgoings;

    private double totalSellings;

    private double totalNet;

    private String comparisonDate;

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

    public String getComparisonDate() {
        return comparisonDate;
    }

    public void setComparisonDate(String comparisonDate) {
        this.comparisonDate = comparisonDate;
    }
}
