package com.example.OneToMany.fileHandling;

import com.example.OneToMany.entity.Department;
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
            writer.append("DepartmentID,DepartmentName,EmployeeNames\n");

            for (Department dept : departments) {
                StringBuilder employees = new StringBuilder();
                if (dept.getEmployees() != null) {
                    for (int i = 0; i < dept.getEmployees().size(); i++) {
                        employees.append(dept.getEmployees().get(i).getName());
                        if (i < dept.getEmployees().size() - 1) employees.append(" | ");
                    }
                }
                writer.append(dept.getId().toString())
                        .append(",")
                        .append(dept.getName())
                        .append(",")
                        .append(employees.toString())
                        .append("\n");
            }

            writer.flush();
            System.out.println("Departments written to Excel file successfully: " + FILE_PATH);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


