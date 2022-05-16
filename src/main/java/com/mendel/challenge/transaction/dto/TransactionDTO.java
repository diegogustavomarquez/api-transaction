package com.mendel.challenge.transaction.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 *
 */
public class TransactionDTO implements Serializable {

    @JsonProperty(value = "amount",required = true)
    @NotNull
    private Double amount;

    @JsonProperty(value = "type",required = true)
    @NotEmpty
    @Size(min = 4, max = 50)
    private String type;

    @JsonProperty("parent_id")
    private Long parentId;

    public TransactionDTO(Double amount, String type, Long parentId) {
        this.amount = amount;
        this.type = type;
        this.parentId = parentId;
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
