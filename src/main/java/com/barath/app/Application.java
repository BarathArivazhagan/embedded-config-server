package com.barath.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.web.bind.annotation.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;


import java.util.Objects;

@SpringBootApplication
@EnableConfigServer
@RestController
@RequestMapping(value = "/customer")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	@Value("${hello.name:default}")
	private String name;


    private final CustomerService customerService;

    public Application(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    public String handleHome(){
        return "Welcome to Customer Application";
    }

    @PostMapping(value="/add")
    public String addCustomer(@RequestBody Customer customer){
        if(customer !=null){
            customerService.addCustomer(customer);
            return "Customer is added successfully";
        }
        return "Customer is not  added successfully. Check the logs for error ";
    }


    @RequestMapping(value="/get")
    public Customer getCustomer(@RequestParam("id") long customerId){

        return customerService.getCustomer(customerId);
    }

    @GetMapping(value="/getCustomerByName")
    public Customer getCustomer(@RequestParam("name") String customerName){
        return customerService.getCustomer(customerName);
    }

    @PutMapping("/update")
    public String updateCustomer(){
        return "Welcome to Customer Application";
    }

    @ExceptionHandler(Exception.class)
    public String handleError(Exception ex){
        return ex.getMessage();
    }



    @PostConstruct
	public void init(){
		System.out.println("NAme "+name);
	}

}





