package rabbitmq.two.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class InvoiceCancelledMessage {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate cancelledDate;
    private String invoiceNumber;
    private String reason;

    public InvoiceCancelledMessage(LocalDate cancelledDate, String invoiceNumber, String reason) {
        this.cancelledDate = cancelledDate;
        this.invoiceNumber = invoiceNumber;
        this.reason = reason;
    }

    public InvoiceCancelledMessage() {
    }

    @Override
    public String toString() {
        return "InvoiceCancelledMessage{" +
                "cancelledDate=" + cancelledDate +
                ", invoiceNumber='" + invoiceNumber + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }

    public LocalDate getCancelledDate() {
        return cancelledDate;
    }

    public void setCancelledDate(LocalDate cancelledDate) {
        this.cancelledDate = cancelledDate;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
