package com.example.OneToMany.fileHandling;

import com.example.OneToMany.entity.Department;
import com.example.OneToMany.entity.Employee;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Component
public class ExcelFileHandler {

    private static final String FILE_PATH =
        "D:/Eclipse Workspace/DatabaseRelationTypes/OneToManyEntity/OneToManyRelation/departments.csv";

    public void writeDepartmentsToExcel(List<Department> departments) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            writer.write("DepartmentID,DepartmentName,EmployeeNames\n");

            for (Department dept : departments) {
                String employeeNames = "";

                if (dept.getEmployees() != null) {
                    for (Employee emp : dept.getEmployees()) {
                        if (!employeeNames.isEmpty()) {
                            employeeNames += " | ";
                        }
                        employeeNames += emp.getName();
                    }
                }

                writer.write(dept.getId() + "," + dept.getName() + "," + employeeNames + "\n");
            }

            System.out.println("CSV Export Done! File path: " + FILE_PATH);

        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}








