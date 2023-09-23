package com.metehan.springbootfundamentals.two_orm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SupplierController {
    @Autowired
    private SupplierRepository supplierRepository;

    @GetMapping("/supplier/generate")
    @ResponseBody
    public String generateSuppliers() {

        for (int i = 0; i < 100; i++) {
            Supplier supplier = new Supplier(0, "Satımcı " + (i + 1), 500 + 25 * i);
            supplierRepository.save(supplier);
        }
        return "Satımcı kuşaldı: " + 100;
    }

    @GetMapping("/supplier/findall")
    @ResponseBody
    public String findAllSuppliers() {

        Iterable<Supplier> suppliers = supplierRepository.findAll();
        int count = 0;
        double grandDebit = 0;
        for (Supplier supplier : suppliers) {
            grandDebit += supplier.getTotalDebit();
            count++;
        }
        return "Satımcılar bulundu: " + count + " toplam alacakları " + grandDebit;
    }

    @GetMapping("/supplier/findbyname/{name}")
    @ResponseBody
    public String findSuppliersByName(@PathVariable(name = "name") String supplierName) {

        Iterable<Supplier> suppliers = supplierRepository.findSuppliersByName(supplierName);
        int count = 0;
        double grandDebit = 0;
        for (Supplier supplier : suppliers) {
            grandDebit += supplier.getTotalDebit();
            count++;
        }
        return "Satımcılar bulundu: " + count + " toplam alacakları " + grandDebit;
    }

    @GetMapping("/supplier/findbymindebit/{debit}"
    )
    @ResponseBody
    public String findSuppliersByTotalDebitMin(@PathVariable(name = "debit") double totalDebitMin) {

        Iterable<Supplier> suppliers = supplierRepository.findSupplierTotalDebitMin(totalDebitMin);
        int count = 0;
        double grandDebit = 0;
        for (Supplier supplier : suppliers) {
            System.out.println(supplier.getSupplierName());
            grandDebit += supplier.getTotalDebit();
            count++;
        }
        return "Çok alacaklı satımcılar: " + count + " toplam alacakları " + grandDebit;
    }

    @GetMapping(
            "/supplier/findbynamedebit/{name}/{debit}")
    @ResponseBody
    public String findSuppliersByNameLikeAndDebitMin(@PathVariable(name = "name") String supplierNameLike,
                                                     @PathVariable(name = "debit") double totalDebitMin) {

        Iterable<Supplier> suppliers = supplierRepository.findSuppliersByNameLikeAndDebitMin(supplierNameLike, totalDebitMin);
        int count = 0;
        double grandDebit = 0;
        for (Supplier supplier : suppliers) {
            System.out.println(supplier.getSupplierName());
            grandDebit += supplier.getTotalDebit();
            count++;
        }
        return "Adı gibi, çok alacaklı satımcılar: " + count + " toplam alacakları " + grandDebit;
    }

    @GetMapping("/supplier/countall")
    @ResponseBody
    public String countAllSuppliers() {

        long count = supplierRepository.count();
        return "Satımcıların sayısı: " + count;
    }

    @GetMapping("/supplier/countbynamedebit/{name}/{debit}")
    @ResponseBody
    public String countSuppliersByTotalDebitMin(@PathVariable(name = "name") String supplierNameLike,
                                                @PathVariable(name = "debit") double totalDebitMin) {

        long count = supplierRepository.countSuppliersByNameLikeAndDebitMin(supplierNameLike, totalDebitMin);
        return "Adı gibi, çok alacaklı satımcıların sayısı: " + count;
    }
}
