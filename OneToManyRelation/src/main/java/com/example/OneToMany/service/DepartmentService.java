package com.example.OneToMany.service;

import com.example.OneToMany.entity.Department;

import java.util.List;

public interface DepartmentService {
    Department saveDepartment(Department department);
    Department updateDepartment(Long id, Department department);
    Department getDepartmentById(Long id);
    List<Department> getAllDepartments();
}



































