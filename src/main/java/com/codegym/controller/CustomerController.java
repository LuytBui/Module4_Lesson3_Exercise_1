package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping("")
    public ModelAndView showList(){
        List<Customer> customers = customerService.findAll();
        return new ModelAndView("customer/list", "customers", customers);
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm(){
        Customer customer = new Customer();
        return new ModelAndView("customer/create", "customer", customer);
    }

    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute Customer customer) {
        customer.setId((int) (Math.random() * 10000));
        customerService.save(customer);
        return "redirect:/customers";
    }

    @GetMapping("/{id}/edit")
    public ModelAndView showEditForm(@PathVariable int id) {
        Customer customer = customerService.findById(id);
        return new ModelAndView("customer/edit", "customer", customer);
    }

    @PostMapping("/update")
    public String updateCustomer(@ModelAttribute Customer customer) {
        customerService.update(customer.getId(), customer);
        return "redirect:/customers";
    }

    @GetMapping("/{id}/delete")
    public ModelAndView showDeleteForm(@PathVariable int id) {
        Customer customer = customerService.findById(id);
        return new ModelAndView("customer/delete", "customer", customer);
    }

    @PostMapping("/delete")
    public String deleteCustomer(@ModelAttribute Customer customer) {
        customerService.remove(customer.getId());
        return "redirect:/customers";
    }

    @GetMapping("/{id}/view")
    public ModelAndView viewCustomer(@PathVariable int id) {
        Customer customer = customerService.findById(id);
        return new ModelAndView("customer/view", "customer", customer);
    }
}
