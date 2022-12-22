package com.capgemini.EmployeeManaement.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //    @ExceptionHandler(JDBCConnectionException.class)
//    public String handleConnectionError(Exception ex) {
//
//        LOGGER.error(ex.getMessage(), ex);
//
//        return "connect_error";
//    }
//    @ExceptionHandler(Exception.class)
//    public String handelException(Exception e) {
//        return "connect_error";
//    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ModelAndView handleEmployeeNotFoundException(Exception e) {
        ModelAndView mav = new ModelAndView("EmployeeNotFound");
        mav.addObject("message", e.getLocalizedMessage());
        return mav;
    }

    @ExceptionHandler(EmployeeAlreadyExistsException.class)
    public ModelAndView handleEmployeeAlreadyExistsException(Exception e) {
        ModelAndView mav = new ModelAndView("EmployeeAlreadyExist");
        mav.addObject("message", e.getLocalizedMessage());
        return mav;
    }
}
