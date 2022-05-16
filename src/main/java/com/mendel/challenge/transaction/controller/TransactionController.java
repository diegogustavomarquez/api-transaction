package com.mendel.challenge.transaction.controller;

import com.mendel.challenge.transaction.dto.ResponseStatusDTO;
import com.mendel.challenge.transaction.dto.ResponseSumDTO;
import com.mendel.challenge.transaction.dto.TransactionDTO;
import com.mendel.challenge.transaction.exception.BusinessException;
import com.mendel.challenge.transaction.model.Transaction;
import com.mendel.challenge.transaction.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author diegogustavomarquez
 */
@RestController
@RequestMapping("/transactions")
public class TransactionController extends CommonController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/{transactionId}")
    public ResponseEntity<?> postTransaction(@Valid @RequestBody TransactionDTO transaction, BindingResult result, @PathVariable Long transactionId) {
        LOGGER.info("init postTransaction");
        try {
            if (result.hasErrors()) {
                return this.validate(result);
            }
            if (transactionId <= 0) {
                throw new BusinessException("transaction_id", "No puede ser menor o igual a cero.");
            }
            if (null != transaction.getParentId() && transaction.getParentId() <= 0) {
                throw new BusinessException("transaction_id", "No puede ser menor o igual a cero.");
            }
            if (null != transaction.getParentId() && (transactionId.equals(transaction.getParentId()))) {
                throw new BusinessException("parent_id", "No puede ser igual a transaction_id");
            }
            transactionService.save(new Transaction(transactionId, transaction));
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStatusDTO("ok"));
        } catch (BusinessException e) {
            return this.validate(e.getField(), e.getMessage());
        } finally {
            LOGGER.info("finish postTransaction");
        }
    }

    @GetMapping("/types/{type}")
    public ResponseEntity<?> getTransactionIdByType(@PathVariable String type) {
        LOGGER.info("init getTransactionIdByType");
        try {
            if (null == type || type.isBlank()) {
                throw new BusinessException("type", "No puede nulo o vacio");
            }
            List<Long> result = transactionService.getTransactionIdByType(type);
            if(result == null || result.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (BusinessException e) {
            return this.validate(e.getField(), e.getMessage());
        } finally {
            LOGGER.info("finish getTransactionIdByType");
        }
    }

    @GetMapping("/sum/{transactionId}")
    public ResponseEntity<?> getTransactionSum(@PathVariable Long transactionId) {
        LOGGER.info("init getTransactionSum");
        try {
            if (transactionId <= 0) {
                throw new BusinessException("transaction_id", "No puede ser menor o igual a cero.");
            }
            Double sum = transactionService.getTransactionSum(transactionId);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseSumDTO(sum));
        } catch (BusinessException e) {
            return this.validate(e.getField(), e.getMessage());
        } finally {
            LOGGER.info("finish getTransactionSum");
        }
    }

}