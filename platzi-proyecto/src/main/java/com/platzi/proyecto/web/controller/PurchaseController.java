package com.platzi.proyecto.web.controller;
import com.platzi.proyecto.domain.Purchase;
import com.platzi.proyecto.domain.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/findAllPurchases")
    public ResponseEntity<List<Purchase>> findAllPurchase(){
        try {
            return new ResponseEntity<>(purchaseService.findAllPurchase(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e,HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/findAllPurchaseByIdClient/{idClient}")
    public ResponseEntity<Optional<List<Purchase>>> findAllPurchaseByIdClient(@PathVariable("idClient") Integer idClient){
        try {
            return new ResponseEntity<>(purchaseService.findAllPurchaseByIdClient(idClient),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/savePurchase")
    public ResponseEntity<Purchase> savePurchase(@RequestBody Purchase purchase){
        try {
            return new ResponseEntity<>(purchaseService.savePurchase(purchase),HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e, HttpStatus.CONFLICT);
        }
    }
}
