package com.metehan.springbootfundamentals.two_orm;

import java.util.List;

public interface SpecialRepository {
    List<Supplier> findSupplierTotalDebitMin(double totalDebitMin);
}
