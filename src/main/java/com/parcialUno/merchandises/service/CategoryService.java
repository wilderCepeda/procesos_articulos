package com.parcialUno.merchandises.service;

import com.parcialUno.merchandises.model.CategoryModel;
import com.parcialUno.merchandises.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryModel createCategory(CategoryModel categoryReq){
        return categoryRepository.save(categoryReq);
    }

    public CategoryModel getCategoryById(Long id){return categoryRepository.findById(id).get();
    }


    public boolean deleteCategory(Long id){
        Optional<CategoryModel> categoryDB = categoryRepository.findById(id);
        if(categoryDB.isEmpty()){
            return false;
        }
        categoryRepository.delete(categoryDB.get());
        return true;
    }

    public List<CategoryModel> getCategoryAll(){
        return (List<CategoryModel>) categoryRepository.findAll();
    }

}
