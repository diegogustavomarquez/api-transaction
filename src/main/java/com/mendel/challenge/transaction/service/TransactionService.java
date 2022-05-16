package com.mendel.challenge.transaction.service;

import com.mendel.challenge.transaction.exception.BusinessException;
import com.mendel.challenge.transaction.model.Transaction;

import java.util.List;

/**
 * @author diegogustavomarquez
 */
public interface TransactionService {

    /**
     * Persiste la transaccion
     *
     * @param transaction
     * @throws BusinessException
     */
    public Transaction save(Transaction transaction) throws BusinessException;

    /**
     * Devuelve la lista de id de transacciones asociados al type dado
     *
     * @param type
     * @return
     */
    public List<Long> getTransactionIdByType(String type);

    /**
     * Devuelve la suma de todas las transacciones que estan transitivamente conectadas por su parentId a transactionId.
     *
     * @param transactionId
     * @return
     * @throws BusinessException
     */
    Double getTransactionSum(Long transactionId) throws BusinessException;

}
