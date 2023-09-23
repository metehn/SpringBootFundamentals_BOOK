package com.metehan.springbootfundamentals.two_orm;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SupplierRepository extends CrudRepository<Supplier, Long>, SpecialRepository {
    @Query("select s from Supplier s where s.supplierName = :supplierName ")
    List<Supplier> findSuppliersByName(@Param("supplierName") String supplierName);

    @Query("select s from Supplier s where s.supplierName like %?1%  and s.totalDebit > ?2 ")
    List<Supplier> findSuppliersByNameLikeAndDebitMin(String supplierNameLike, double totalDebitMin);

    @Query("select count(s.supplierId) from Supplier s where s.supplierName like %?1% and s.totalDebit>?2")
    long countSuppliersByNameLikeAndDebitMin(String supplierNameLike, double totalDebitMin);

}
