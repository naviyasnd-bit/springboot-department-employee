package com.example.OneToMany.service;

import com.example.OneToMany.entity.Department;
import com.example.OneToMany.entity.Employee;
import com.example.OneToMany.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository repository;

    public DepartmentServiceImpl(DepartmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Department saveDepartment(Department department) {
        if (department.getEmployees() != null) {
            for (Employee e : department.getEmployees()) {
                e.setDepartment(department);
            }
        }
        return repository.save(department);
    }

    @Override
    public Department updateDepartment(Long id, Department department) {
        Department existing = repository.findById(id).orElseThrow(() -> new RuntimeException("Department not found"));
        existing.setName(department.getName());

        if (department.getEmployees() != null) {
            for (Employee e : department.getEmployees()) {
                e.setDepartment(existing);
            }
            existing.setEmployees(department.getEmployees());
        }
        return repository.save(existing);
    }

    @Override
    public Department getDepartmentById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Department not found"));
    }

    @Override
    public List<Department> getAllDepartments() {
        return repository.findAll();
    }
}







