package com.platzi.proyecto.domain.repository;

import com.platzi.proyecto.domain.Product;
import com.platzi.proyecto.persistence.entity.Producto;

import java.util.List;
import java.util.Optional;

//ESTO SE HACE PARA QUE SE HABLE EN TERMINOS DEL DOMINIO ES DECIR DE PRODUCT Y NO DE PPRODUCTO QUE ES LA ENTITY
//QUE HACE REFERENCIA A LA BD
public interface IProductRepository {

    List<Product> findAllProducts();
    List<Product>findAllProductsByIdCategory(Integer idCategory);
    List<Product> findAllScarceProducts(Integer cantidadStock, String staus);
    List<Product> findByProductsWithStockQuantityGreaterThanStockQuantity(Integer stockQuantity, String status);
    Optional<Product> findProductById(Integer idProducto)throws Exception;
    Product saveProduct(Product product);
    Product updateProduct(Product product, Integer idProducto)throws Exception;
    void deleteProduct(Integer idProduct);
    Optional<?> sumOfStockQuantitiesOfAllProductsWithStatusAc();

}


