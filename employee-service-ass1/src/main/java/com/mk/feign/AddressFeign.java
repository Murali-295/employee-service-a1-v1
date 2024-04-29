package com.mk.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mk.entity.Address;

@FeignClient(value = "ADDRESS-SERVICE", url = "http://localhost:8085")
public interface AddressFeign {

	@PostMapping("/address/save")
	Address saveAddress(@RequestBody Address address);

	@GetMapping(value = "/address/id{id}")
	Address findAddressById(@PathVariable("id") Integer empId);

	@PutMapping("/address/update{id}")
	Address updateAddressById(@PathVariable("id") Integer addressId);

	@DeleteMapping("/address/delete{id}")
	public Address deleteAddressById(@PathVariable("id") Integer addressId);

}
