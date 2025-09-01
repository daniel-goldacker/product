package org.acme.service;

import java.util.ArrayList;
import java.util.List;

import org.acme.dto.ProductDTO;
import org.acme.entity.ProductEntity;
import org.acme.repository.ProductRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository productRepository;

    public List<ProductDTO> getAllProducts(){
        List<ProductDTO> products = new ArrayList<>();
        productRepository.findAll().stream().forEach(item -> {
            products.add(mapProductEntityToDTO(item));

        });

        return products;
    }

    public void createProduct(ProductDTO productDTO){
        productRepository.persist(mapProductDTOToEntity(productDTO));
    } 

    public void changeProduct(Long id, ProductDTO productDTO){
        ProductEntity productEntity = productRepository.findById(id);

        productEntity.setName(productDTO.getName());
        productEntity.setCategory(productDTO.getCategory());
        productEntity.setDescription(productDTO.getDescription());
        productEntity.setModel(productDTO.getModel());
        productEntity.setPrice(productDTO.getPrice());

        productRepository.persist(productEntity);
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    private ProductDTO mapProductEntityToDTO(ProductEntity productEntity){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setCategory(productEntity.getCategory());
        productDTO.setDescription(productEntity.getDescription());
        productDTO.setModel(productEntity.getModel());
        productDTO.setName(productEntity.getName());
        productDTO.setPrice(productEntity.getPrice());

        return productDTO;
    }

        private ProductEntity mapProductDTOToEntity(ProductDTO productDTO){
        ProductEntity productEntity = new ProductEntity();
        productEntity.setCategory(productDTO.getCategory());
        productEntity.setDescription(productDTO.getDescription());
        productEntity.setModel(productDTO.getModel());
        productEntity.setName(productDTO.getName());
        productEntity.setPrice(productDTO.getPrice());

        return productEntity;
    }


}
