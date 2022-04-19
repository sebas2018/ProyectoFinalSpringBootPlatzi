package com.platzi.proyecto.persistence.crud;

import com.platzi.proyecto.persistence.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IProductoRepository extends JpaRepository<Producto,Integer> {

    //FORMA NATIVA(Opcional)
    //@Query(value = "SELECT * FROM PRODUCTO WHERE ID_CATEGORIA = ?", nativeQuery = true)
    //List<Producto> consultarProductosPorIdCategoria(int idCategoria);

    //METODO QUE CONSULTA TODOS LOS PRODUCTOS DE UNA CATEGORIA Y LOS ORDENA POR NOMBRE DE FORMA ASCENDENTE
    //QUERY METHODS (Buena Practica)
    List<Producto> findByidCategoriaOrderByNombreAsc(int idCategoria);

    //OPERADORES OPCIONALES DE JAVA
    //METODO QUE CONSULTA LOS PRODUCTOS CON CANTIDAD DE SROCK MENOR A ALGO Y QUE TENGAN ESATADO AC
    List<Producto> findByCantidadStockLessThanAndEstado(int cantidadStock, String estado);

    //METODO QUE TRAE LOS PRODUCTOS QUE TIENEN UN CANTIDADSTOCK MAYOR O IGUAL (CANTIDADSTOCK Y QUE TIENEN ESTADO AC)
    @Query(value = "select * from productos where cantidad_stock >= :stockQuantity AND estado = :status", nativeQuery = true)
    List<Producto> findByProductsWithStockQuantityGreaterThanStockQuantity(@Param("stockQuantity") Integer stockQuantity, @Param("status") String status);

    //METODO QUE CALCULA LA SUMATORIA DE TODOS LOS (cantidad_stock) DE LA TABLA PRODUCTOS
    @Query(value = "select sum(cantidad_stock) as Total from productos where estado = 'AC'", nativeQuery = true)
    Optional<?> sumOfStockQuantitiesOfAllProductsWithStatusAc();




}
