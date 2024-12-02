package br.com.jdlm.product_service.service;

import br.com.jdlm.product_service.domain.entity.category.Category;
import br.com.jdlm.product_service.domain.entity.product.Product;
import br.com.jdlm.product_service.domain.entity.product.ProductDTO;
import br.com.jdlm.product_service.domain.entity.product.StockDTO;
import br.com.jdlm.product_service.domain.repository.ProductRepository;
import br.com.jdlm.product_service.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final RestTemplate restTemplate;

    public Product createProduct(ProductDTO receivedProduct) {
        Product createdProduct = new Product();
        Category category = null;
        if (receivedProduct.category() != null) {
            category = categoryService.getCategoryById(receivedProduct.category().getId());
        }
        BeanUtils.copyProperties(receivedProduct, createdProduct);
        createdProduct.setCategory(category);
        createdProduct = productRepository.save(createdProduct);

        StockDTO postStock = new StockDTO(createdProduct.getId(), receivedProduct.actualStock());
        restTemplate.postForObject("http://localhost:8082/stocks/first-entry", postStock, String.class);

        return createdProduct;
    }

    public void deleteProduct(UUID id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new CustomException("Product Not Found", HttpStatus.NOT_FOUND));
        product.setIsActive(false);
        productRepository.save(product);
    }

    public Product getProductById(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new CustomException("Product Not Found", HttpStatus.NOT_FOUND));

        product.setActualStock(getActualStockFromItsService(id));
        return product;
    }

    public Page<Product> getProducts(int page, int size) {
        Page<Product> productsPage = productRepository.findAll(PageRequest.of(page, size));
        productsPage.forEach(this::updateProductStock);
        return productsPage;
    }

    private void updateProductStock(Product product) {
        Integer actualStock = getActualStockFromItsService(product.getId());
        product.setActualStock(actualStock);
    }

    private Integer getActualStockFromItsService(UUID productId) {
        String stockServiceUrl = "http://localhost:8082/stocks/" + productId;
        StockDTO stockDTO = restTemplate.getForObject(stockServiceUrl, StockDTO.class);
        return (stockDTO != null) ? stockDTO.actualStock() : 0;
    }

    public Product updateProduct(UUID id, ProductDTO receivedProduct) {
        Product editedProduct = productRepository.findById(id).orElseThrow(() -> new CustomException("Product Not Found", HttpStatus.NOT_FOUND));

        if (receivedProduct.name() != null) {
            editedProduct.setName(receivedProduct.name());
        }

        if (receivedProduct.description() != null) {
            editedProduct.setDescription(receivedProduct.description());
        }

        if (receivedProduct.price() != null) {
            editedProduct.setPrice(receivedProduct.price());
        }

        if (receivedProduct.category() != null) {
            Category category = categoryService.getCategoryById(receivedProduct.category().getId());
            editedProduct.setCategory(category);
        }

//        if (receivedProduct.images() != null) {
//            editedProduct.setImages(receivedProduct.images());
//        }

        if (receivedProduct.reviews() != null) {
            editedProduct.setReviews(receivedProduct.reviews());
        }

        return productRepository.save(editedProduct);
    }

}
