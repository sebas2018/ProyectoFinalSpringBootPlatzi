package com.platzi.proyecto.persistence;
import com.platzi.proyecto.domain.Purchase;
import com.platzi.proyecto.domain.repository.IPurchaseRepository;
import com.platzi.proyecto.persistence.crud.IClienteRepositoy;
import com.platzi.proyecto.persistence.crud.ICompraRepository;
import com.platzi.proyecto.persistence.entity.Cliente;
import com.platzi.proyecto.persistence.entity.Compra;
import com.platzi.proyecto.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements IPurchaseRepository {

    @Autowired
    private ICompraRepository compraJpaRepository;

    @Autowired
    private PurchaseMapper purchaseMapper;

    @Autowired
    private IClienteRepositoy iClienteRepositoy;

    @Override
    public List<Purchase> findAllPurchase() {
        List<Compra> compras = compraJpaRepository.findAll();
        return purchaseMapper.toListPurchase(compras);
    }

    @Override
    public Optional<List<Purchase>> findAllPurchaseByIdClient(Integer idClient) throws Exception {
        Optional<Cliente> cliente =iClienteRepositoy.findById(idClient);
        if (!cliente.isPresent())
            throw new Exception("The client does not exist");
        Optional<List<Compra>> compras = compraJpaRepository.findByIdCliente(idClient);
        return compras.map(list -> purchaseMapper.toListPurchase(list));
    }

    @Override
    public Purchase savePurchase(Purchase purchase) {
        Compra compra = purchaseMapper.toCompra(purchase);
        //garantizamos que la informacion se guarde en cascada por lo cual compras debe
        //conocer sus productos y los productos deben conocer a que compra pertenece
        //la siguiente linea nos indica que compra ya conoce sus productos y producto ya sabe a que compra pertenece
        //para confirmar lo anterior debemos de ir al entity de (Compra) e indicarle que en el campo (productos) se
        //guardara en cascada
        compra.getProductos().forEach(producto -> producto.setCompra(compra));//a cada producto le asignamos la compra
        return purchaseMapper.toPurchase(compraJpaRepository.save(compra));
    }
}
