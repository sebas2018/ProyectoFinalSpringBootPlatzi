package com.platzi.proyecto.persistence.entity;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity // esta etiquete mapea una tabla a la bd
@Table(name = "COMPRAS_PRODUCTOS")//nombre de la tabla de bd
@Getter//metodos getter
@Setter//metodos setter
public class ComprasProducto { //Cuando compras producto se guarde en cascada va saber a que pk pertenece cada uno de los
                               // productos que estan en una compra

    @EmbeddedId//ESTA ETIQUETA SE UTILIZA CUANDO LA LLAVE PRIMARIA DE LA TABLA ES COMPUESTA
    private ComprasProductoPK idComprasProducto;
    @Column(name = "CANTIDAD")
    private Integer cantidad;
    @Column(name = "TOTAL")
    private Integer total;
    @Column(name = "ESTADO")
    private String estado;
    @ManyToOne
    @MapsId("idCompra")//como parametro el nombre de la pk de la entity (Compra) que se requiere que se enlace
    @JoinColumn(name = "ID_COMPRA", insertable = false, updatable = false)
    private Compra compra;
    @ManyToOne
    @JoinColumn(name = "ID_PRODUCTO", insertable = false, updatable = false)
    private Producto producto;

    public ComprasProductoPK getIdComprasProducto() {return idComprasProducto;}
    public void setIdComprasProducto(ComprasProductoPK idComprasProducto) {this.idComprasProducto = idComprasProducto;}
    public Integer getCantidad() {return cantidad;}
    public void setCantidad(Integer cantidad) {this.cantidad = cantidad;}
    public Integer getTotal() {return total;}
    public void setTotal(Integer total) {this.total = total;}
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public Compra getCompra() {
        return compra;
    }
    public void setCompra(Compra compra) {
        this.compra = compra;
    }
    public Producto getProducto() {
        return producto;
    }
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
