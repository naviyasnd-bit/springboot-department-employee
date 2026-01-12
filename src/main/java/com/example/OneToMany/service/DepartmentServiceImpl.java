package com.example.OneToMany.service;

import com.example.OneToMany.entity.Department;
import com.example.OneToMany.entity.Employee;
import com.example.OneToMany.fileHandling.ExcelFileHandler;
import com.example.OneToMany.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository repository;
    private final ExcelFileHandler excelHandler;

    public DepartmentServiceImpl(DepartmentRepository repository, ExcelFileHandler excelHandler) {
        this.repository = repository;
        this.excelHandler = excelHandler;
    }

    @Override
    public Department saveDepartment(Department department) {
        try {
            // Link employees to department
            if (department.getEmployees() != null) {
                for (Employee e : department.getEmployees()) {
                    e.setDepartment(department);
                }
            }

            Department saved = repository.save(department);

            // Export CSV after saving
            excelHandler.writeDepartmentsToExcel(repository.findAll());
            return saved;

        } catch (Exception e) {
            System.out.println("Error saving department: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Department updateDepartment(Long id, Department department) {
        try {
            Department existing = repository.findById(id).orElse(null);

            if (existing == null) {
                System.out.println("Department not found with id: " + id);
                return null;
            }

            existing.setName(department.getName());

            if (department.getEmployees() != null) {
                for (Employee e : department.getEmployees()) {
                    e.setDepartment(existing);
                }
                existing.setEmployees(department.getEmployees());
            }

            Department updated = repository.save(existing);

            // Export CSV after updating
            excelHandler.writeDepartmentsToExcel(repository.findAll());
            return updated;

        } catch (Exception e) {
            System.out.println("Error updating department: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Department getDepartmentById(Long id) {
        try {
            Department dept = repository.findById(id).orElse(null);
            if (dept == null) {
                System.out.println("Department not found with id: " + id);
            }
            return dept;
        } catch (Exception e) {
            System.out.println("Error fetching department: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Department> getAllDepartments() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            System.out.println("Error fetching all departments: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void exportDepartmentsToExcel() {
        try {
            List<Department> departments = repository.findAll();
            excelHandler.writeDepartmentsToExcel(departments);
        } catch (Exception e) {
            System.out.println("Error exporting departments: " + e.getMessage());
        }
    }
}








