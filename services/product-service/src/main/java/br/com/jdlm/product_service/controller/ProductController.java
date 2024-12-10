package br.com.jdlm.product_service.controller;

import br.com.jdlm.product_service.domain.entity.product.Product;
import br.com.jdlm.product_service.domain.entity.product.ProductDTO;
import br.com.jdlm.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/save")
    public ResponseEntity<Product> createProduct(@RequestBody ProductDTO receivedProduct) {
        return new ResponseEntity<>(productService.createProduct(receivedProduct), HttpStatus.CREATED);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>("Produto deletado com sucesso", HttpStatus.GONE);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable UUID id) {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Page<Product>> getProducts(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size) {
        return new ResponseEntity<>(productService.getProducts(page, size), HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Product> editProduct(@RequestBody ProductDTO receivedProduct, @PathVariable UUID id) {
        return new ResponseEntity<>(productService.updateProduct(id, receivedProduct), HttpStatus.ACCEPTED);
    }

}
