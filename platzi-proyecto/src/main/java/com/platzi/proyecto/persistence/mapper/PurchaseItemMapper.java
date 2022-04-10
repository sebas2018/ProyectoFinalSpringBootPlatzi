package com.platzi.proyecto.persistence.mapper;

import com.platzi.proyecto.domain.PurchaseItem;
import com.platzi.proyecto.persistence.entity.ComprasProducto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})//ESTO PERMITE INYECTAR ESTE COMPONENTE DESDE OTROS LUGARES
public interface PurchaseItemMapper { //internamente PurchaseMapper esta utilizando ProductMapper para mapear "producto asi sea para ignorarlo"
    @Mappings({
            @Mapping(source = "idComprasProducto.idProducto", target = "idProduct"),
            @Mapping(source = "cantidad", target = "quantity"),
            //Como el source y el target tienen el nombre del atributo igual se puede omitir es decir quitar
            //@Mapping(source = "total", target = "total"),
            @Mapping(source = "estado", target = "status")

    })
    //Conversio de ComprasProducto a PurchaseItem
    PurchaseItem toPurchaseItem(ComprasProducto producto);

    //Conversion de PurchaseItem a ComprasProducto
    @InheritInverseConfiguration//Esto sirve para en esta conversion se pueda utilizar la anterior conversion(ComprasProducto a PurchaseItem)
    //c
    @Mappings({
            @Mapping(target = "compra", ignore = true),
            @Mapping(target = "producto", ignore = true),//como estamos utilizando producto tenemos que ponerlo en uses de la linea 12
            @Mapping(target = "idComprasProducto.idCompra", ignore = true)

    })


    ComprasProducto toComprasProducto(PurchaseItem item);

}
