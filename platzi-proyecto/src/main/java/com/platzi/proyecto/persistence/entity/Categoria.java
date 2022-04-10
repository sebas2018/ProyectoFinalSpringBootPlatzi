package com.platzi.proyecto.persistence.entity;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Entity // esta etiquete mapea una tabla a la bd
@Table(name = "CATEGORIAS")//nombre de la tabla de bd
@Getter//metodos getter
@Setter//metodos setter
public class Categoria {
    @Id //ESTA ETIQUETA SE UTILIZA CUANDO LA LLAVE PRIMARIA DE LA TABLA ES SENCILLA ES DECIR NO ES COMPUESTA
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CATEGORIA")
    private Integer idCategoria;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "ESTADO")
    private String estado;
    @OneToMany(mappedBy = "categoria") //¿Que relacion respalda este nuevo atributo (productos)? R/: el atributo categoria de la tabla producto
    private List<Producto> productos;

   public Integer getIdCategoria() {
        return idCategoria;
    }
    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public List<Producto> getProductos() {
        return productos;
    }
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
