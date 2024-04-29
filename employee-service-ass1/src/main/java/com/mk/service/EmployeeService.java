
package com.mk.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mk.entity.Address;
import com.mk.entity.Employee;
import com.mk.feign.AddressFeign;
import com.mk.repository.AddressRepository;
import com.mk.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private AddressFeign addressFeign;

	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public Employee findEmployeeById(Integer empId) {
		Optional<Employee> optional= employeeRepository.findById(empId);
		if (optional.isEmpty()) {
			return null;
		}
		Employee employee = optional.get();
		return employee;
	}

	public Employee salaryIncrement(Integer empId) {

		Optional<Employee> optional= employeeRepository.findById(empId);
		if (optional.isEmpty()) {
			return null;
		}
		Employee employee = optional.get();
		if (employee.getEmpDepartment().equals("development") && employee.getEmpYOJ() < 2015) {
			Double empSalary = employee.getEmpSalary();
			Double increment = (empSalary * 10) / 100;
			employee.setEmpSalary(empSalary + increment);

			employeeRepository.save(employee);
			return employee;
		}

		return employee;
	}

	public Employee deleteEmployeeById(Integer empId) {
		Optional<Employee> optional= employeeRepository.findById(empId);
		if (optional.isEmpty()) {
			return null;
		}
		Employee employee = optional.get();
		employeeRepository.deleteById(empId);

		return employee;
	}

	public Employee saveEmployeeAddress(Integer empId) {
		Optional<Employee> optional= employeeRepository.findById(empId);
		if (optional.isEmpty()) {
			return null;
		}
		Employee employee = optional.get();

		Address address= addressFeign.findAddressById(employee.getEmpId());
		addressRepository.save(address);
		employee.setAddressId(empId);
		employeeRepository.save(employee);
		return employee;
	}
}
