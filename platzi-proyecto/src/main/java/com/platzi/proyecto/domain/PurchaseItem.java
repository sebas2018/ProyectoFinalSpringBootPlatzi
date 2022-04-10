package com.platzi.proyecto.domain;

//OBJETO DE DOMINIO LISTO PARA SER UTILIZADO
public class PurchaseItem {

    private Integer idProduct;
    private Integer quantity;
    private Double total;
    private String status;

    public Integer getIdProduct() {
        return idProduct;
    }
    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Double getTotal() {
        return total;
    }
    public void setTotal(Double total) {
        this.total = total;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
