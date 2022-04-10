package com.platzi.proyecto.persistence.mapper;
import com.platzi.proyecto.domain.Product;
import com.platzi.proyecto.persistence.entity.Producto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {
    //----------------Convertimos un producto a product------------------------------------------------------------
    @Mappings({
            @Mapping(source = "idProducto", target = "idProduct"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "idCategoria", target = "idCategory"),
            @Mapping(source = "precioVenta", target = "salePrice"),
            @Mapping(source = "cantidadStock", target = "stockQuantity"),
            @Mapping(source = "estado", target = "status"),
            //como category ya tiene su mapper propio entonces dentro de la etiqueta @Mapper de la linea 9 definimos
            //el parametro (uses = {CategoryMapper.class}) de esta manera ya sabe que cuando vaya a convertir categoria a category
            //tiene que usar (CategoryMapper.class)
            @Mapping(source = "categoria", target = "category")
    })

    Product toProduct(Producto producto);
    //------------------------------------------------------------------------------------------------------
    //--------------Convertimos una lista de productos a una lista de products---------------------------------
    //No se tiene que volver a definir el @Mappings por que mapstruct ya sabe que  la conversion se debe de
    //compartar como la anterior ya realizada
    List<Product> toListProduct(List<Producto> productos);
    //---------------------------------------------------------------------------------------------------------
    //--------------Convertimos un producto a product------------------------- ---------------------------------
    @InheritInverseConfiguration
    @Mapping(target = "compras", ignore = true)
    @Mapping(target = "codigoBarras", ignore = true)
    Producto toProducto(Product product);
    //-----------------------------------------------------------------------------------------------------------
}


