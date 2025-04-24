package Session_Project.DauletAndAltyndana.service;

import Session_Project.DauletAndAltyndana.model.Address;
import Session_Project.DauletAndAltyndana.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Optional<Address> getAddressById(Long id) {
        return addressRepository.findById(id);
    }

    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }

    public Address updateAddress(Long id, Address updatedAddress) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new RuntimeException("Address not found"));
        address.setCity(updatedAddress.getCity());
        address.setStreet(updatedAddress.getStreet());
        address.setBuilding(updatedAddress.getBuilding());
        address.setDepartment(updatedAddress.getDepartment());
        return addressRepository.save(address);
    }
}