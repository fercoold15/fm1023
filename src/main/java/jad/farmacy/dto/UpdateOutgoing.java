package jad.farmacy.dto;

import java.time.LocalDate;

public class UpdateOutgoing {
    private long outgoingID;
    private String description;
    private String amount;
    private String value;
    private LocalDate billDate;
    private long userID;

    public long getOutgoingID() {
        return outgoingID;
    }

    public void setOutgoingID(long outgoingID) {
        this.outgoingID = outgoingID;
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

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }
}
