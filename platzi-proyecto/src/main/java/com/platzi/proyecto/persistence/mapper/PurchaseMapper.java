package com.platzi.proyecto.persistence.mapper;

import com.platzi.proyecto.domain.Purchase;
import com.platzi.proyecto.persistence.entity.Compra;
import org.apache.catalina.LifecycleState;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

//EN USES LE DEFINIMOS QUE VAMOS A USAR EL MAPEADOR LLAMADO (PurchaseItemMapper) YA QUE INTERNAMENTE DENTRO DE LA COMPRA
//SE VAN A MAPEAR TODOS SUS PRODUCTOS
@Mapper(componentModel = "spring", uses = {PurchaseItemMapper.class})
public interface PurchaseMapper {

    //Conversion de Purchase a Compras
    @Mappings({
            @Mapping(source = "idCompra", target = "idPurchase"),
            @Mapping(source = "idCliente", target = "idClient"),
            @Mapping(source = "fecha", target = "date"),
            @Mapping(source = "medioPago", target = "paymentMethod"),
            @Mapping(source = "comentario", target = "comment"),
            @Mapping(source = "estado", target = "status"),
            @Mapping(source = "productos",target = "items")

    })
    Purchase toPurchase(Compra compra);

    //Conversion (Lista Purchase) a (Lista Compras)
    //No necesitamos hacer el mapeo para esta conversacion ya que ya se hizo en la conversion anterior Purchase toPurchase(Compra compra)
    //Es decir esta conversion ( List<Purchase> toListPurchase(List<Compra> listCompra)) adquiere automaticamente adquiere
    //toda la configuracion del mapeo (Purchase toPurchase(Compra compra)) singular
    List<Purchase> toListPurchase(List<Compra> compras);

    //Conversion de Purchase a Compra

    @InheritInverseConfiguration
    @Mapping(target = "cliente", ignore = true)//ignoramos "cliente" ya que en Purchase no esta por lo tanto en Compra no se mapea
    Compra toCompra(Purchase purchase);










}
