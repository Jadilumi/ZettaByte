package br.com.jdlm.product_service.service;

import br.com.jdlm.product_service.domain.entity.category.Category;
import br.com.jdlm.product_service.domain.entity.category.CategoryDTO;
import br.com.jdlm.product_service.domain.repository.CategoryRepository;
import br.com.jdlm.product_service.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category createCategory(CategoryDTO receivedCategory) {
        Category createdCategory = new Category();
        BeanUtils.copyProperties(receivedCategory, createdCategory);
        createdCategory.setIsActive(true);
        return categoryRepository.save(createdCategory);
    }

    public Category updateCategory(UUID id, CategoryDTO receivedCategory) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CustomException("Category Not Found", HttpStatus.NOT_FOUND));

        if (receivedCategory.isActive() != null) {
            category.setIsActive(receivedCategory.isActive());
        }

        if (receivedCategory.name() != null) {
            category.setName(receivedCategory.name());
        }

        if (receivedCategory.description() != null) {
            category.setDescription(receivedCategory.description());
        }

        return categoryRepository.save(category);
    }

    public Category getCategoryById(UUID id) {
        return categoryRepository.findById(id).orElseThrow(() -> new CustomException("Category Not Found", HttpStatus.NOT_FOUND));
    }

    public Page<Category> getCategories(int page, int size) {
        return categoryRepository.findAll(PageRequest.of(page, size));
    }

    public void deleteCategory(UUID id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CustomException("Category Not Found", HttpStatus.NOT_FOUND));
        category.setIsActive(false);
        categoryRepository.save(category);
    }
}
