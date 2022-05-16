package com.mendel.challenge.transaction.service.impl;

import com.mendel.challenge.transaction.exception.BusinessException;
import com.mendel.challenge.transaction.model.Transaction;
import com.mendel.challenge.transaction.repository.TransactionRepository;
import com.mendel.challenge.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    @Transactional
    public void save(Transaction transaction) throws BusinessException {
        //valido que no este registrado el transaction id
        if(transactionRepository.existsById(transaction.getTransactionId())){
            throw new BusinessException("transaction_id","Ya existe transaction_id ya esta registrado");
        }
        //valido que exista el id de la transaccion padre a la que quiero agregar
        if(null != transaction.getParentId() && !transactionRepository.existsById(transaction.getParentId())){
            throw new BusinessException("parent_id","No existe una transaccion con el parent_id al que se intenta asociar");
        }
        transactionRepository.save(transaction);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Long> getTransactionIdByType(String type) {
        return transactionRepository.findByType(type).stream().map(t -> t.getTransactionId()).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Double getTransactionSum(Long transactionId) throws BusinessException {

        //valida que exista el id.
        Optional<Transaction> transactionOptional  = transactionRepository.findById(transactionId);
        if(!transactionOptional.isPresent()){
            throw new BusinessException("transaction_id","No existe transaction_id.");
        }
        return searchParentIdAndSumAmount(transactionOptional.get());
    }

    /**
     * Realiza una busqued recursiva buscando todos los hijos asociados a una transaccion y sumando sus montos
     *
     * @param transaction
     * @return
     */
    private Double searchParentIdAndSumAmount(Transaction transaction) {
        Double sum = transaction.getAmount();
        List<Transaction> transactions = transactionRepository.findByParentId(transaction.getTransactionId());
        for(Transaction t : transactions){
            sum += searchParentIdAndSumAmount(t);
         }
        return sum;
    }

}