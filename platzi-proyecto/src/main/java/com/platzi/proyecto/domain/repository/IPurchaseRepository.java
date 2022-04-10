package com.platzi.proyecto.domain.repository;

import com.platzi.proyecto.domain.Purchase;

import java.util.List;
import java.util.Optional;

public interface IPurchaseRepository {

    List<Purchase> findAllPurchase();
    //Como es posible que se consulte un cliente que no realizo compras por lo tanto tenemos que incluir el Optional en la lista
   // List<Purchase> finAllPurchaseByIdClient(Integer idClient);
    Optional<List<Purchase>> findAllPurchaseByIdClient(Integer idClient)throws Exception;
    Purchase savePurchase(Purchase purchase);
}
