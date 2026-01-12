package com.example.OneToMany.controller;

import com.example.OneToMany.entity.Department;
import com.example.OneToMany.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public Department save(@RequestBody Department department) {
        return service.saveDepartment(department);
    }

    @PutMapping("/update/{id}")
    public Department update(@PathVariable Long id, @RequestBody Department department) {
        return service.updateDepartment(id, department);
    }

    @GetMapping("/get/{id}")
    public Department getById(@PathVariable Long id) {
        return service.getDepartmentById(id);
    }

    @GetMapping("/all")
    public List<Department> getAll() {
        return service.getAllDepartments();
    }
}





