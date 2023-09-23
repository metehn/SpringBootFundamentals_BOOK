package com.metehan.springbootfundamentals.two_orm;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SpecialRepositoryImpl implements SpecialRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Supplier> findSupplierTotalDebitMin(double totalDebitMin) {
        String jpql = "select s from Supplier s where s.totalDebit >= : totalDebitMin";
        TypedQuery<Supplier> query = entityManager.createQuery(jpql, Supplier.class);
        query.setParameter("totalDebitMin", totalDebitMin);
        return query.getResultList();
    }

}
