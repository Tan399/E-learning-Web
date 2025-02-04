package com.example.ProjectElearning.Controller;

import com.example.ProjectElearning.Model.CourseMaterial;
import com.example.ProjectElearning.Service.CourseMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/course-materials")
public class CourseMaterialController {
    @Autowired
    private CourseMaterialService courseMaterialService;

    @GetMapping
    public List<CourseMaterial> getAllCourseMaterials() {
        return courseMaterialService.getAllCourseMaterials();
    }

    @GetMapping("/{id}")
    public CourseMaterial getCourseMaterialById(@PathVariable Long id) {
        return courseMaterialService.getCourseMaterialById(id);
    }

    @PostMapping
    public CourseMaterial createCourseMaterial(@RequestBody CourseMaterial courseMaterial) {
        return courseMaterialService.createCourseMaterial(courseMaterial);
    }

    @DeleteMapping("/{id}")
    public void deleteCourseMaterial(@PathVariable Long id) {
        courseMaterialService.deleteCourseMaterial(id);
    }
}
