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
        try {
            return service.saveDepartment(department);
        } catch (Exception e) {
            System.out.println("Error in save API: " + e.getMessage());
            return null;
        }
    }

    @PutMapping("/update/{id}")
    public Department update(@PathVariable Long id, @RequestBody Department department) {
        try {
            return service.updateDepartment(id, department);
        } catch (Exception e) {
            System.out.println("Error in update API: " + e.getMessage());
            return null;
        }
    }

    @GetMapping("/get/{id}")
    public Department getById(@PathVariable Long id) {
        try {
            return service.getDepartmentById(id);
        } catch (Exception e) {
            System.out.println("Error in getById API: " + e.getMessage());
            return null;
        }
    }

    @GetMapping("/all")
    public List<Department> getAll() {
        try {
            return service.getAllDepartments();
        } catch (Exception e) {
            System.out.println("Error in getAll API: " + e.getMessage());
            return null;
        }
    }

    @GetMapping("/export")
    public String exportCSV() {
        try {
            service.exportDepartmentsToExcel();
            return "CSV exported successfully!";
        } catch (Exception e) {
            return "Error exporting CSV: " + e.getMessage();
        }
    }
}







