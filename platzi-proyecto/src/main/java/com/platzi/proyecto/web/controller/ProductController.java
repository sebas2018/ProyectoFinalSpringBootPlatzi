package com.platzi.proyecto.web.controller;
import com.platzi.proyecto.domain.Product;
import com.platzi.proyecto.domain.service.ProductService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController//Esta etiqueta le indica a spring que esta clase es un controlador de una Api Rest
@RequestMapping("/products")//como parametro se le pasa el path mediante el cual acepatar las peteciones entrantes
public class ProductController {

    @Autowired
    private ProductService productService;//inyectamos el servicio

    @GetMapping("/findAllProducts")
    @ApiOperation("Get all products")
    @ApiResponses({@ApiResponse(code = 200, message = "OK"), @ApiResponse(code =409, message = "Conflict")})
    public ResponseEntity<List<Product>>  findAllProducts(){
       // return productService.findAllProducts();
        try{
            return new ResponseEntity<>(productService.findAllProducts(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e, HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/findAllProductsByIdCategory/{idCategory}")
    @ApiOperation("Get all products of a category")
    @ApiParam(value = "idCategory")
    @ApiResponses({@ApiResponse(code =200, message = "OK"), @ApiResponse(code =409, message = "Conflict")})
    public ResponseEntity<List<Product>>findAllProductsByIdCategory(@PathVariable("idCategory") Integer idCategory){
        try {
            return new ResponseEntity<>(productService.findAllProductsByIdCategory(idCategory),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/findAllScarceProducts/{cantidadStock}/{status}")
    @ApiOperation("Get all scarce products less than quantity value and have active status")
    @ApiResponses({@ApiResponse(code =200, message = "OK"), @ApiResponse(code =409, message = "Conflict")})
    public ResponseEntity<List<Product>> findAllScarceProductsByCategory(@ApiParam(value = "cantidadStock",required = true )@PathVariable("cantidadStock") Integer cantidadStock, @ApiParam(value = "status", required = true) @PathVariable("status") String status){
        try {
            return new ResponseEntity<>(productService.findAllScarceProducts(cantidadStock,status), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e, HttpStatus.CONFLICT);
        }

    }

    @GetMapping("/findByProductsWithStockQuantityGreaterThanStockQuantity/{cantidadStock}/{status}")
    @ApiOperation("Get all products with stock quantity greater than stock quantity and have active status")
    @ApiResponses({@ApiResponse(code =200, message = "OK"), @ApiResponse(code =409, message = "Conflict")})
    public ResponseEntity<List<Product>> findByProductsWithStockQuantityGreaterThanStockQuantity(@PathVariable("cantidadStock")Integer cantidadStock, @PathVariable("status") String status){
        try {
           return new ResponseEntity<List<Product>>(productService.findByProductsWithStockQuantityGreaterThanStockQuantity(cantidadStock,status),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e, HttpStatus.CONFLICT);

        }
    }

    @GetMapping("/findProductById/{idProducto}")
    @ApiOperation("Get product by idProducto")
    @ApiParam(value = "idProducto", required = true)
    @ApiResponses({@ApiResponse(code =200, message = "OK"), @ApiResponse(code =409, message = "Conflict")})
    public ResponseEntity<Optional<Product>> findProductById(@PathVariable("idProducto") Integer idProducto){
        try {
            return new ResponseEntity<>(productService.findProductById(idProducto),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e, HttpStatus.CONFLICT);
        }

        //ESTA ES OYTA FORMA DE HACER EL CONTROLADOR DE BUSCAR PRODUCTO POR ID
        /*public ResponseEntity<Product> findProductById(@PathVariable("idProducto") Integer idProducto){
        return productService.findProductById(idProducto)
                .map(product -> new ResponseEntity<>(product,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));*/
    }

    @PostMapping("/saveProduct")
    @ApiOperation("Save product")
    @ApiResponses({@ApiResponse(code =201, message = "CREATED"), @ApiResponse(code =409, message = "Conflict")})
    public ResponseEntity<Product> saveProduct(@RequestBody Product product){
        try {
            return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e, HttpStatus.CONFLICT);
        }

    }

    @PutMapping("/actualizarProduct/{idProducto}")
    @ApiOperation("Update product")
    @ApiResponses({@ApiResponse(code =200, message = "OK"), @ApiResponse(code =409, message = "Conflict")})
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable("idProducto") Integer idProducto){
        try {
            return new ResponseEntity<>(productService.updateProduct(product, idProducto), HttpStatus.OK) ;
        }catch (Exception e){
            return new ResponseEntity(e, HttpStatus.CONFLICT);
        }

    }

    @DeleteMapping("/eliminarProducto/{idProducto}")
    @ApiOperation("Delete product")
    @ApiParam(value = "idProducto", required = true)
    @ApiResponses({@ApiResponse(code =200, message = "OK"), @ApiResponse(code =404, message = "NOT FOUND")})
    public ResponseEntity deleteProduct(@PathVariable("idProducto") Integer idProduct){
     try {
         return new ResponseEntity<>(productService.deleteProduct(idProduct),HttpStatus.OK);
     }catch (Exception e){
         return new ResponseEntity(HttpStatus.NOT_FOUND);
     }

    }

}
