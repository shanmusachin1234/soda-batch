package com.soda.nse.batch.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


@Document(
        collection = "nseBulkDeal"
)
public class NSEBulkDeal {
    @Id
    private Long id;
    @Indexed(name = "dateIndex", background = true)
    private LocalDate date;
    @Indexed(name = "symbolIndex", background = true)
    private String symbol;
    private String securityName;
    @Indexed(name = "traderIndex", background = true)
    private String clientName;
    private String transactionType;
    private String quantityTraded;
    private String tradePrice;
    private String remark;

    public NSEBulkDeal(Long id, LocalDate date, String symbol, String securityName,
                       String clientName, String transactionType,
                       String quantityTraded, String tradePrice, String remark) {
        this.id = id;
        this.date = date;
        this.symbol = symbol;
        this.securityName = securityName;
        this.clientName = clientName;
        this.transactionType = transactionType;
        this.quantityTraded = quantityTraded;
        this.tradePrice = tradePrice;
        this.remark = remark;
    }

    public NSEBulkDeal() {

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSecurityName() {
        return securityName;
    }

    public void setSecurityName(String securityName) {
        this.securityName = securityName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getQuantityTraded() {
        return quantityTraded;
    }

    public void setQuantityTraded(String quantityTraded) {
        this.quantityTraded = quantityTraded;
    }

    public String getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(String tradePrice) {
        this.tradePrice = tradePrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "NSEBulkDeal{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", symbol='" + symbol + '\'' +
                ", securityName='" + securityName + '\'' +
                ", clientName='" + clientName + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", quantityTraded='" + quantityTraded + '\'' +
                ", tradePrice='" + tradePrice + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
