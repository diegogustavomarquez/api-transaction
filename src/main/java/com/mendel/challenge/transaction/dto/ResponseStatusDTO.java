package com.mendel.challenge.transaction.dto;

import java.io.Serializable;

public class ResponseStatusDTO implements Serializable {

    private String status;

    public ResponseStatusDTO(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
