package com.mendel.challenge.transaction.repository;


import com.mendel.challenge.transaction.model.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author diegogustavomarquez
 */
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    /**
     * Devuelve la lista de transacciones asociados al type dado
     * @param type
     * @return
     */
    List<Transaction> findByType(String type);

    /**
     * Devuelve la lista de transacciones asociados al parent_id dado
     *
     * @param parentId
     * @return
     */
    List<Transaction> findByParentId(Long parentId);
}
