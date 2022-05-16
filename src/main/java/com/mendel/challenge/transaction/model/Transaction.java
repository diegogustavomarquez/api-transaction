package com.mendel.challenge.transaction.model;

import com.mendel.challenge.transaction.dto.TransactionDTO;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "transactions")
public class Transaction implements Serializable {
    @Id
    @Column(name = "transaction_id")
    private Long transactionId;

    @Column(name = "count")
    @NotNull
    private Double amount;

    @Column(name = "type")
    @NotEmpty
    private String type;

    @Column(name = "parent_id")
    private Long parentId;

    public Transaction() {
    }

    public Transaction(Long transactionId, TransactionDTO transactioDTO) {
        this.transactionId = transactionId;
        this.amount = transactioDTO.getAmount();
        type=transactioDTO.getType();
        parentId = transactioDTO.getParentId();
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
