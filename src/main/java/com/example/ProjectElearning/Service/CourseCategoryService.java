package com.example.ProjectElearning.Service;



import com.example.ProjectElearning.Model.CourseCategory;
import com.example.ProjectElearning.Repository.CourseCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseCategoryService {


    @Autowired
    private final CourseCategoryRepository courseCategoryRepository;

    public CourseCategoryService(CourseCategoryRepository courseCategoryRepository) {
        this.courseCategoryRepository = courseCategoryRepository;
    }


    public List<CourseCategory> getAllCategories() {
        return courseCategoryRepository.findAll();
    }


    public CourseCategory getCategoryById(Long id) {
        return courseCategoryRepository.findById(id).orElse(null);
    }


    public CourseCategory createCategory(CourseCategory courseCategory) {
        return courseCategoryRepository.save(courseCategory);
    }


    public CourseCategory updateCategory(Long id, CourseCategory updatedCategory) {
        CourseCategory existingCategory = getCategoryById(id);
        existingCategory.setTitle(updatedCategory.getTitle());
        return courseCategoryRepository.save(existingCategory);
    }


    public void deleteCategory(Long id) {
        CourseCategory category = getCategoryById(id);
        courseCategoryRepository.delete(category);
    }
}
