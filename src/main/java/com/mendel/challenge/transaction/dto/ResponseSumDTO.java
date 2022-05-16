package com.mendel.challenge.transaction.dto;

import java.io.Serializable;

public class ResponseSumDTO implements Serializable {
    private Double sum;

    public ResponseSumDTO(Double sum) {
        this.sum = sum;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }
}
