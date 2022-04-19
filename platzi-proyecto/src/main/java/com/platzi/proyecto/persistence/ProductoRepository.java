package com.platzi.proyecto.persistence;
import com.platzi.proyecto.domain.Product;
import com.platzi.proyecto.domain.repository.IProductRepository;
import com.platzi.proyecto.persistence.crud.IProductoRepository;
import com.platzi.proyecto.persistence.entity.Producto;
import com.platzi.proyecto.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository //permite a esta clase interactuar con la BD.
//@Component // esta equita tambien se puede utilizar en ves de @Repository, es opcional
public class ProductoRepository implements IProductRepository {

    @Autowired
    private IProductoRepository productoJpaRepository;
    @Autowired
    private ProductMapper productMapper;

   /* //Metodo que lista todos los productos de la base de datos
    public List<Producto> listarProductos(){
        return productoJpaRepository.findAll();
    }

    //Metodo que retorna una lista de todos los productos de una categoria
    public List<Producto> consultarProductosPorIdCategoria(int idCategoria){
        return productoJpaRepository.findByidCategoriaOrderByNombreAsc(idCategoria);
    }

    //METODO QUE CONSULTA LOS PRODUCTOS ESCASOS ES DECIR QUE TENGAN UNA CANTIDAD DE STOCK MENOR A ALGO Y ESTADO AC
    public List<Producto> consultarProductosEscasos(int cantidadStock, String estado){
        return productoJpaRepository.findByCantidadStockLessThanAndEstado(cantidadStock,estado);
    }

    //METODO QUE CONSULTA UN PRODUCTO POR ID
    public Optional<Producto> consultarProductoPorId(int idProducto){
        return productoJpaRepository.findById(idProducto);
    }

    //METODO QUE GUARDA UN PRODUCTO
    public Producto guardarProducto(Producto producto){
        return productoJpaRepository.save(producto);
    }

    //METODO QUE ELIMINA UN PRODUCTO
    public void eliminarProducto(int idProducto){
        productoJpaRepository.deleteById(idProducto);
    }*/
    @Override
    public List<Product> findAllProducts() {
        List<Producto> productos = productoJpaRepository.findAll();
        return productMapper.toListProduct(productos);
    }
    @Override
    public List<Product> findAllProductsByIdCategory(Integer idCategory) {
        List<Producto> productos = productoJpaRepository.findByidCategoriaOrderByNombreAsc(idCategory);
        return productMapper.toListProduct(productos);
    }
    @Override
    public List<Product> findAllScarceProducts(Integer cantidadStock, String status) {
        List<Producto>productos = productoJpaRepository.findByCantidadStockLessThanAndEstado(cantidadStock,status);
        return productMapper.toListProduct(productos);
    }

    @Override
    public List<Product> findByProductsWithStockQuantityGreaterThanStockQuantity(Integer stockQuantity, String status) {
        List<Producto> productos = productoJpaRepository.findByProductsWithStockQuantityGreaterThanStockQuantity(stockQuantity, status);
        return  productMapper.toListProduct(productos);
    }


    @Override
    public Optional<Product> findProductById(Integer idProducto) throws Exception {
        Optional<Producto> prod = productoJpaRepository.findById(idProducto);
        if (!prod.isPresent())
            throw new Exception("The product does not exist in the database");
        return productoJpaRepository.findById(idProducto).map(producto -> productMapper.toProduct(producto));
    }
    @Override
    public Product saveProduct(Product product) {
       Producto producto = productMapper.toProducto(product);
       // return productMapper.toProduct(productoJpaRepository.save(productMapper.toProducto(product)));
        return productMapper.toProduct(productoJpaRepository.save(producto));

    }

    @Override
    public Product updateProduct(Product product, Integer idProducto)throws Exception {
        Product product1 = findProductById(idProducto).get();
        if (product1.getIdProduct() == null){
            System.out.println("---> NO EXISTE EL PRODUCTO  A ACTUALIZAR EN LA BASE DE DATOS");
            return null;
        } else{
            product1.setName(product.getName());
            product1.setIdCategory(product.getIdCategory());
            product1.setSalePrice(product.getSalePrice());
            product1.setStockQuantity(product.getStockQuantity());
            product1.setStatus(product.getStatus());
            return productMapper.toProduct(productoJpaRepository.save(productMapper.toProducto(product1)));
        }

    }

    @Override
    public void deleteProduct(Integer idProduct) {
       productoJpaRepository.deleteById(idProduct);
    }

    @Override
    public Optional<?> sumOfStockQuantitiesOfAllProductsWithStatusAc() {
        Optional<?> total = productoJpaRepository.sumOfStockQuantitiesOfAllProductsWithStatusAc();
        return total;
    }
}
