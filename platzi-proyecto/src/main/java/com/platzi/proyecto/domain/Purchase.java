package com.platzi.proyecto.domain;

import java.time.LocalDateTime;
import java.util.List;

//OBJETO DE DOMINIO LISTO PARA SER UTILIZADO
public class Purchase {

    private Integer idPurchase;
    private Integer idClient;
    private LocalDateTime date;
    private String paymentMethod;
    private String comment;
    private String status;
    private List<PurchaseItem> items;

    public Integer getIdPurchase() {
        return idPurchase;
    }
    public void setIdPurchase(Integer idPurchase) {
        this.idPurchase = idPurchase;
    }
    public Integer getIdClient() {
        return idClient;
    }
    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    public String getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public List<PurchaseItem> getItems() {
        return items;
    }
    public void setItems(List<PurchaseItem> items) {
        this.items = items;
    }
}
