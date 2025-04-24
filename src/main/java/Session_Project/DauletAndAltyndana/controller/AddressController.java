package Session_Project.DauletAndAltyndana.controller;

import Session_Project.DauletAndAltyndana.model.Address;
import Session_Project.DauletAndAltyndana.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/addresses")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping
    public String listAddresses(Model model) {
        model.addAttribute("addresses", addressService.getAllAddresses());
        return "address-list";
    }

    @GetMapping("/add")
    public String showNewForm(Model model) {
        model.addAttribute("address", new Address());
        return "address-form";
    }

    @PostMapping("/add")
    public String addAddress(@ModelAttribute Address address) {
        addressService.saveAddress(address);
        return "redirect:/addresses";
    }

    @PostMapping("/edit/{id}")
    public String editAddress(@PathVariable Long id, @ModelAttribute Address address) {
        address.setId(id);
        addressService.saveAddress(address);
        return "redirect:/addresses";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Address address = addressService.getAddressById(id).orElse(null);
        if (address == null) {
            model.addAttribute("errorMessage", "Адрес с таким ID не найден");
            return "error";
        }
        model.addAttribute("address", address);
        return "address-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
        return "redirect:/addresses";
    }
}