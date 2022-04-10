package com.platzi.proyecto.persistence.entity;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity // esta etiquete mapea una tabla a la bd
@Table(name = "COMPRAS")//nombre de la tabla de bd
@Getter//metodos getter
@Setter//metodos setter
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_COMPRA")
    private Integer idCompra;
    @Column(name = "ID_CLIENTE")
    private Integer idCliente;
    @Column(name = "FECHA")
    private LocalDateTime fecha;
    @Column(name = "MEDIO_PAGO")
    private String medioPago;
    @Column(name = "COMENTARIO")
    private String comentario;
    @Column(name = "ESTADO")
    private String estado;
    @ManyToOne//A traves de esta relacion no se puede borrar, actualizar ni insertar un cliente, para lograrlo se hace directamente desde la entity Cliente y esta relacion nos permite consultar la compra realizada por un cliente
    @JoinColumn(name = "ID_CLIENTE", insertable = false, updatable = false)
    private Cliente cliente;
    //la propiedad cascade quiere decir que todos los procesos que se hagan contra la bd de una compra van a incluir
    //en cacada sus productos
    @OneToMany(mappedBy = "compra", cascade = {CascadeType.ALL})//ESTA RELACION ME SIRVE PARA CONSULTAR LOS PRODUCTOS DE UNA COMPRA, ES DECIR DESDE COMPRA PUEDO ACCEDER A TODOS LOS PRODUCTOS QUE PERTENECEN A ESA COMPRA
    private List<ComprasProducto> productos;

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(String medioPago) {
        this.medioPago = medioPago;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ComprasProducto> getProductos() {
        return productos;
    }

    public void setProductos(List<ComprasProducto> productos) {
        this.productos = productos;
    }
}
