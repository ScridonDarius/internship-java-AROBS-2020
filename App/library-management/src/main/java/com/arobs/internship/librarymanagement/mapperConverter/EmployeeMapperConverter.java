package com.arobs.internship.librarymanagement.mapperConverter;

import com.arobs.internship.librarymanagement.controller.api.request.EmployeeRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.request.EmployeeUpdateDTO;
import com.arobs.internship.librarymanagement.controller.api.response.EmployeeDTO;
import com.arobs.internship.librarymanagement.controller.api.response.EmployeeResponseDTO;
import com.arobs.internship.librarymanagement.model.Employee;
import org.modelmapper.ModelMapper;

public class EmployeeMapperConverter {

    private static ModelMapper modelMapper = new ModelMapper();

    public static EmployeeResponseDTO generateDTOResponseFromEntity(Employee employee) {
        return modelMapper.map(employee, EmployeeResponseDTO.class);
    }

    public static EmployeeDTO generateBookRequestEmployeeFromEntity(Employee employee) {
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    public static Employee generateEntityFromDTORegistration(EmployeeRegistrationDTO employeeRegistrationDTO) {
        return modelMapper.map(employeeRegistrationDTO, Employee.class);
    }

    public static EmployeeUpdateDTO generateDTOUpdateFromEntity(Employee employee) {
        return modelMapper.map(employee, EmployeeUpdateDTO.class);
    }
}
