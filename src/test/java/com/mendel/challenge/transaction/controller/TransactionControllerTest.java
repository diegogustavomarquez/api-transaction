package com.mendel.challenge.transaction.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mendel.challenge.transaction.model.Transaction;
import com.mendel.challenge.transaction.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @Test
    public void saveOk() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(10l);
        transaction.setType("shopping");
        transaction.setAmount(20000.0);
        transaction.setParentId(null);

        when(transactionService.save(any(Transaction.class))).thenReturn(transaction);

        mockMvc.perform(MockMvcRequestBuilders.post("/transactions/10").content(new ObjectMapper().writeValueAsString(transaction))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andExpect(MockMvcResultMatchers.jsonPath("$.status").exists());
    }

    @Test
    public void saveTransactionIdNegativeNOk() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(10l);
        transaction.setType("shopping");
        transaction.setAmount(20000.0);
        transaction.setParentId(null);

        when(transactionService.save(any(Transaction.class))).thenReturn(transaction);

        mockMvc.perform(MockMvcRequestBuilders.post("/transactions/-1").content(new ObjectMapper().writeValueAsString(transaction))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void saveTransactionIAndParentIdEqualsNOk() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(10l);
        transaction.setType("shopping");
        transaction.setAmount(20000.0);
        transaction.setParentId(10l);

        when(transactionService.save(any(Transaction.class))).thenReturn(transaction);

        mockMvc.perform(MockMvcRequestBuilders.post("/transactions/10").content(new ObjectMapper().writeValueAsString(transaction))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void saveParentIdNegativeNOk() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(10l);
        transaction.setType("shopping");
        transaction.setAmount(20000.0);
        transaction.setParentId(10l);

        when(transactionService.save(any(Transaction.class))).thenReturn(transaction);

        mockMvc.perform(MockMvcRequestBuilders.post("/transactions/10").content(new ObjectMapper().writeValueAsString(transaction))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void getTransactionIdByTypeOk() throws Exception {
        List<Long> results = Arrays.asList(1L, 2L);
        when(transactionService.getTransactionIdByType(any(String.class))).thenReturn(results);

        mockMvc.perform(MockMvcRequestBuilders.get("/transactions/types/shopping"))
                .andExpect(status().isOk());
    }

    @Test
    public void getTransactionIdByTypeReturnArrayEmptyNOk() throws Exception {
        List<Long> results =new ArrayList<>();
        when(transactionService.getTransactionIdByType(any(String.class))).thenReturn(results);

        mockMvc.perform(MockMvcRequestBuilders.get("/transactions/types/shopping"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getTransactionSumOk() throws Exception {
        Double sum = 20000.0;
        when(transactionService.getTransactionSum(any(Long.class))).thenReturn(sum);

        mockMvc.perform(MockMvcRequestBuilders.get("/transactions/sum/10"))
                .andExpect(status().isOk());
    }

    @Test
    public void getTransactionSumTransactionNegativeNOk() throws Exception {
        Double sum = 20000.0;
        when(transactionService.getTransactionSum(any(Long.class))).thenReturn(sum);

        mockMvc.perform(MockMvcRequestBuilders.get("/transactions/sum/-10"))
                .andExpect(status().isBadRequest());
    }

}
