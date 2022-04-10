package com.platzi.proyecto.domain.service;

import com.platzi.proyecto.domain.Purchase;
import com.platzi.proyecto.domain.repository.IPurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    @Autowired
    private IPurchaseRepository iPurchaseRepository;

    public List<Purchase> findAllPurchase(){
        return iPurchaseRepository.findAllPurchase();
    }

    public Optional<List<Purchase>> findAllPurchaseByIdClient(Integer idClient) throws Exception {

        return  iPurchaseRepository.findAllPurchaseByIdClient(idClient);
    }

    public Purchase savePurchase(Purchase purchase){
        return iPurchaseRepository.savePurchase(purchase);
    }
}
