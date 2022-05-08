package rabbitmq.two.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class InvoiceCreatedMessage {

    private Double amount;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;
    private String currency;
    private String invoiceNumber;

    public InvoiceCreatedMessage() {
    }

    public InvoiceCreatedMessage(Double amount, LocalDate createdAt, String currency, String invoiceNumber) {
        this.amount = amount;
        this.createdAt = createdAt;
        this.currency = currency;
        this.invoiceNumber = invoiceNumber;
    }

    @Override
    public String toString() {
        return "InvoiceCreatedMessage{" +
                "amount=" + amount +
                ", createdAt=" + createdAt +
                ", currency='" + currency + '\'' +
                ", invoiceNumber='" + invoiceNumber + '\'' +
                '}';
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }
}
