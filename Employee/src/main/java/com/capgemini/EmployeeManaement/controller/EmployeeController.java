package com.capgemini.EmployeeManaement.controller;

import com.capgemini.EmployeeManaement.exception.EmployeeNotFoundException;
import com.capgemini.EmployeeManaement.model.Employee;
import com.capgemini.EmployeeManaement.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class EmployeeController {


    @Autowired
    EmployeeServiceImpl empimp;

    //IOC
    //dependecny injection
    @GetMapping("/")
    public String getEmployee(Model model) {
        return findPaginated(1, model);

    }

//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }

    @GetMapping("/add")
    public String launchAddEmployeePage(Model model) {
        model.addAttribute("employee", new Employee());
        return "AddEmployee";
    }

    @PostMapping("/addEmployee")
    public String createEmployee(Employee employee) {
        empimp.addEmployee(employee);
        return "redirect:/";
    }


    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") int id) throws EmployeeNotFoundException {
        model.addAttribute("employee", empimp.findEmployeeById(id));
        return "EditEmployee";

    }

    @PostMapping("/updateEmployee")
    public String updateEmployee(Employee employee) {
        empimp.updateEmployee(employee);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") int id) throws EmployeeNotFoundException {
        empimp.findEmployeeById(id);
        empimp.deleteEmployee(id);
        return "redirect:/";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 5;
        Page<Employee> page = empimp.findPaginated(pageNo, pageSize);
        List<Employee> listEmployees = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("employees", listEmployees);
        return "AllEmployee";
    }


}