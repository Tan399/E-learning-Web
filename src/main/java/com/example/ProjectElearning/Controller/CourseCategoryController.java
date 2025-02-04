package com.example.ProjectElearning.Controller;




import com.example.ProjectElearning.Model.CourseCategory;
import com.example.ProjectElearning.Model.CourseCategoryDTO;
import com.example.ProjectElearning.Model.UserType;
import com.example.ProjectElearning.Service.CourseCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coursecategory")
public class CourseCategoryController {

    @Autowired
    private  CourseCategoryService courseCategoryService;




    @GetMapping
    public ResponseEntity<List<CourseCategory>> getAllCategories() {
        List<CourseCategory> categories = courseCategoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
    @GetMapping("/titles")
    public ResponseEntity<List<CourseCategory>> getAllCategoriesTitle() {
        List<CourseCategory> categories = courseCategoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CourseCategory> getCategoryById(@PathVariable Long id) {
        CourseCategory category = courseCategoryService.getCategoryById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<CourseCategory> createCategory(@RequestBody CourseCategoryDTO courseCategoryDTO) {


        CourseCategory courseCategory=new CourseCategory();
        courseCategory.setTitle(courseCategoryDTO.getTitle());
        return new  ResponseEntity<CourseCategory>(courseCategoryService.createCategory(courseCategory),HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CourseCategory> updateCategory(@PathVariable Long id, @RequestBody CourseCategory updatedCategory) {
        CourseCategory category = courseCategoryService.updateCategory(id, updatedCategory);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        courseCategoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

