package com.primer_parcial.SyK.controllers;

import com.primer_parcial.SyK.models.CategoryModel;
import com.primer_parcial.SyK.services.CategoryService;
import com.primer_parcial.SyK.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(maxAge = 3600)
@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/category")
    public ResponseEntity<?> createCategory(@RequestBody CategoryModel category, @RequestHeader(value = "Authorization") String token){

        try {
            if (jwtUtil.getKey(token) == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token error");
            }
            return categoryService.createCategory(category);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token error "+e.getMessage());
        }



    }
    @PutMapping("/category/{id}")
    public ResponseEntity<?> editCategory(@PathVariable Long id , @RequestBody CategoryModel category, @RequestHeader(value = "Authorization") String token){
        try {
            if (jwtUtil.getKey(token) == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token error");
            }
            return categoryService.editCategory(id, category);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token error "+e.getMessage());
        }

    }
    @DeleteMapping("category/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id, @RequestHeader(value = "Authorization") String token){
        try {
            if (jwtUtil.getKey(token) == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token error");
            }
            return categoryService.deleteCategory(id);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token error "+e.getMessage());
        }
}
    @GetMapping("/categories")
    public ResponseEntity<?> listCategory(@RequestHeader(value = "Authorization") String token){
        //if (jwtUtil.getKey(token)==null){
        //  return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        //}
        //return articuloService.getAllArticles();
        try {
            if (jwtUtil.getKey(token) == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token error");
            }
            return categoryService.getAllCategories();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token error "+e.getMessage());
        }

    }
    @GetMapping(value = "/category/id/{id}")
    public ResponseEntity<?> getCategory(@PathVariable Long id, @RequestHeader(value = "Authorization") String token) {
        if (jwtUtil.getKey(token)==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token error");
        }
        return categoryService.getCategoryById(id);

    }
}
