package com.platzi.proyecto.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

//ESTA CLASE NO MAPEA NINGUNA TABLA DE LA BASE DE DATOS
//ESTA CLASE CONTIENE LAS LLAVE COMPUESTA DE LA TABLA COMPRAS_PRODUCTO
@Embeddable
@Getter
@Setter
public class ComprasProductoPK implements Serializable {

    @Column(name = "ID_COMPRA")
    private Integer idCompra;
    @Column(name = "ID_PRODUCTO")
    private Integer idProducto;

    public Integer getIdCompra() {
        return idCompra;
    }
    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }
    public Integer getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }
}
