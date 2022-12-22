package com.capgemini.EmployeeManaement.service;

import com.capgemini.EmployeeManaement.exception.EmployeeAlreadyExistsException;
import com.capgemini.EmployeeManaement.exception.EmployeeNotFoundException;
import com.capgemini.EmployeeManaement.model.Employee;
import com.capgemini.EmployeeManaement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository emprepo;

    @Override
    public List<Employee> getEmployee() {
        return emprepo.findAll();
    }

    @Override
    public Employee findEmployeeById(int id) {
        return emprepo.findById(id).orElseThrow(() -> new EmployeeNotFoundException(("Employee with id " + id + " " +
                "does" +
                " not exist")));

    }

    @Override
    public Employee addEmployee(Employee employee) throws EmployeeAlreadyExistsException {
        if (emprepo.existsByEmailId(employee.getEmailId()))
            throw new EmployeeAlreadyExistsException("Employee with mail id " + employee.getEmailId() + " exist");
        else
            return emprepo.save(employee);
    }

    @Override
    public void deleteEmployee(int id) {

        emprepo.deleteById(id);

    }

    @Override
    public Employee updateEmployee(Employee employee) {

        java.util.Optional<Employee> result = emprepo.findById(employee.getEmployeeId());
        Employee existing = result.get();

        existing.setEmployeeId(employee.getEmployeeId());
        existing.setEmployeeFirstName(employee.getEmployeeFirstName());
        existing.setEmployeeLastName(employee.getEmployeeLastName());
        existing.setEmailId(employee.getEmailId());
        return emprepo.save(existing);

    }

    @Override
    public Page<Employee> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.emprepo.findAll(pageable);
    }


}

