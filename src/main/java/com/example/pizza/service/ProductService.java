package com.example.pizza.service;

import com.example.pizza.entity.ComboItem;
import com.example.pizza.entity.CrustPrice;
import com.example.pizza.entity.Product;
import com.example.pizza.entity.ProductPrice;
import com.example.pizza.enums.Crust;
import com.example.pizza.enums.Size;
import com.example.pizza.enums.ProductType;
import com.example.pizza.model.CartItem;
import com.example.pizza.model.ProductDTO;
import com.example.pizza.repository.ComboItemRepository;
import com.example.pizza.repository.CrustPriceRepository;
import com.example.pizza.repository.ProductPriceRepository;
import com.example.pizza.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductPriceRepository productPriceRepository;

    @Autowired
    private CrustPriceRepository crustPriceRepository;

    public List<ProductPrice> getPriceListByProduct(int productId) {
        return productPriceRepository.findByProductId(productId);
    }

    public List<CrustPrice> getCrustPriceListBySize(Size size) {
        return crustPriceRepository.findBySize(size);
    }

    public List<Crust> getCrustBySize(Size size) {
        return crustPriceRepository.findCrustsBySize(size);
    }

    public ProductDTO ToProductDTO(int productId, Size size, Crust crust) {
        Product product = productRepository.findById(productId).orElse(null);

        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getImage(),
                product.getType(),
                crust,
                size);
    }

    public ProductDTO ToProductDTOById(int productId) {
        Product product = productRepository.findById(productId).orElse(null);

        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getImage(),
                product.getType());
    }

    // Lấy sản phẩm theo id
    public Product getProductById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> getProductsByType(ProductType type) {
        return productRepository.findByType(type);
    }

    // Thêm sản phẩm mới
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    // Cập nhật sản phẩm
    public Product updateProduct(Product product) {
        if (productRepository.existsById(product.getId())) {
            return productRepository.save(product);
        }
        return null; // Nếu không tìm thấy sản phẩm, trả về null
    }

    // Xóa sản phẩm
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

}