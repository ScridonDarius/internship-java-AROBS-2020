package com.arobs.internship.librarymanagement.service.mapperConverter;

import com.arobs.internship.librarymanagement.controller.api.request.EmployeeRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.request.EmployeeUpdateDTO;
import com.arobs.internship.librarymanagement.controller.api.response.EmployeeResponseDTO;
import com.arobs.internship.librarymanagement.model.Employee;
import org.modelmapper.ModelMapper;

public class EmployeeMapperConverter {

    static ModelMapper modelMapper = new ModelMapper();

    public static EmployeeResponseDTO generateDTOResponseFromEntity(Employee employee) {
        return modelMapper.map(employee, EmployeeResponseDTO.class);
    }

    public static Employee generateEntityFromDTOResponse(EmployeeResponseDTO employeeResponseDTO) {
        return modelMapper.map(employeeResponseDTO, Employee.class);
    }

    public static EmployeeRegistrationDTO generateDTORegistrationFromEntity(Employee employee) {
        return modelMapper.map(employee, EmployeeRegistrationDTO.class);
    }

    public static Employee generateEntityFromDTORegistration(EmployeeRegistrationDTO employeeRegistrationDTO) {
        return modelMapper.map(employeeRegistrationDTO, Employee.class);
    }

    public static EmployeeUpdateDTO generateDTOUpdateFromEntity(Employee employee) {
        return modelMapper.map(employee, EmployeeUpdateDTO.class);
    }

    public static Employee generateEntityFromDTOUpdate(EmployeeUpdateDTO employeeUpdateDTO) {
        return modelMapper.map(employeeUpdateDTO, Employee.class);
    }
}
