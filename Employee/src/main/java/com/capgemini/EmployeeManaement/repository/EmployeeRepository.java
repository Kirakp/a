package com.capgemini.EmployeeManaement.repository;

import com.capgemini.EmployeeManaement.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    boolean existsByEmailId(String emailId);
//    Optional<Employee> getAllByEmailId();

}






