package com.platzi.proyecto.domain.service;
import com.platzi.proyecto.domain.Product;
import com.platzi.proyecto.domain.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
//la anotacion @Service se puede remplazar por la anotacion @Component
//Este servicio inyecta a la interfaz de (IProductRepository)
public class ProductService {
    @Autowired
    private IProductRepository iProductRepository;

    public List<Product> findAllProducts(){
       return iProductRepository.findAllProducts();
    }
    public List<Product>findAllProductsByIdCategory(Integer idCategory){
        return iProductRepository.findAllProductsByIdCategory(idCategory);
    }
    public List<Product> findAllScarceProducts(Integer cantidadStock, String staus){
        return iProductRepository.findAllScarceProducts(cantidadStock,staus);
    }

    public List<Product> findByProductsWithStockQuantityGreaterThanStockQuantity(Integer stockQuantity, String status){
        return iProductRepository.findByProductsWithStockQuantityGreaterThanStockQuantity(stockQuantity,status);

    }

    public Optional<?> sumOfStockQuantitiesOfAllProductsWithStatusAc(){
        return iProductRepository.sumOfStockQuantitiesOfAllProductsWithStatusAc();
    }


    public Optional<Product> findProductById(Integer idProducto) throws Exception {
        return iProductRepository.findProductById(idProducto);
    }
    public Product saveProduct(Product product){
        return iProductRepository.saveProduct(product);
    }

    public Product updateProduct(Product product, Integer idProducto) throws Exception {
        return iProductRepository.updateProduct(product, idProducto);

    }

    public boolean deleteProduct(Integer idProduct) throws Exception {
       /* if (findProductById(idProduct).isPresent()){
            iProductRepository.deleteProduct(idProduct);
            return true;
        }else{
            return false;
        }*/
        return findProductById(idProduct).map(product -> {
            iProductRepository.deleteProduct(idProduct);
                    return true;
        }).orElse(false);

    }
}
