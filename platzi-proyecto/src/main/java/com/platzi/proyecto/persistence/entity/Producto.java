package com.platzi.proyecto.persistence.entity;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Entity // esta etiquete mapea una tabla a la bd
@Table(name = "PRODUCTOS")//nombre de la tabla de bd
@Getter//metodos getter
@Setter//metodos setter
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //consecutivo automatico
    @Column(name = "ID_PRODUCTO")
    private Integer idProducto;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "ID_CATEGORIA")
    private Integer idCategoria;
    @Column(name = "CODIGO_BARRAS")
    private String codigoBarras;
    @Column(name = "PRECIO_VENTA")
    private Double precioVenta;
    @Column(name = "CANTIDAD_STOCK")
    private Integer cantidadStock;
    @Column(name = "ESTADO")
    private String estado;
    //--------------------------------------------Cardinalidad--------------------------------------
    //A traves de esta relacion no se puede borrar, actualizar ni insertar una categoria, para lograrlo se hace directamente
    // desde la entity Categoria y esta relacion nos permite consultar a que categoria pertenece un producto
    @ManyToOne
    @JoinColumn(name = "ID_CATEGORIA", insertable = false, updatable = false)// insertable y update = false significa el comentario de la linea 29
    private Categoria categoria;
    //----------------------------------------------------------------------------------------------
    // ESTA RELACION ME SERVI PARA CONSULTAR TODAS COMPRAS QUE LLEVARON UN PRODUCTO EN ESPECIFICO (NO ES UNA RELACION QUE GENERA MUCHO VALOR)
    @OneToMany(mappedBy = "compra")
    private  List<ComprasProducto> compras;

    public Integer getIdProducto() { return idProducto;}
    public void setIdProducto(Integer idProducto) {this.idProducto = idProducto;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public Integer getIdCategoria() {return idCategoria;}
    public void setIdCategoria(Integer idCategoria) {this.idCategoria = idCategoria;}
    public String getCodigoBarras() {return codigoBarras;}
    public void setCodigoBarras(String codigoBarras) {this.codigoBarras = codigoBarras;}
    public Double getPrecioVenta() {return precioVenta;}
    public void setPrecioVenta(Double precioVenta) {this.precioVenta = precioVenta;}
    public Integer getCantidadStock() {return cantidadStock;}
    public void setCantidadStock(Integer cantidadStock) {this.cantidadStock = cantidadStock;}
    public String getEstado() {return estado;}
    public void setEstado(String estado) {this.estado = estado;}
    public Categoria getCategoria() {return categoria;}
    public void setCategoria(Categoria categoria) {this.categoria = categoria;}
    public List<ComprasProducto> getCompras() {return compras;}
    public void setCompras(List<ComprasProducto> compras) {this.compras = compras;}
}
