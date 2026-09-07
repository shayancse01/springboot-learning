package com.shayancse.shayan.prod_ready_features.module4prod_ready_features;

import com.shayancse.shayan.prod_ready_features.module4prod_ready_features.clients.EmployeeClient;
import com.shayancse.shayan.prod_ready_features.module4prod_ready_features.dto.EmployeeDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Module4prodReadyFeaturesApplicationTests {

	@Autowired
	private EmployeeClient employeeClient;

	@Test
	@Order(3)
	void getAllEmployeesTest()
	{
		List<EmployeeDTO> employeeDTOList = employeeClient.getAllEmployees();
		System.out.println(employeeDTOList);
	}

	@Test
	@Order(2)
	void getEmployeeByIdTest() {
		EmployeeDTO employeeDTO = employeeClient.getEmployeeById(1L);
		System.out.println(employeeDTO);
	}

	@Test
	@Order(1)
	void createNewEmployeeTest() {
		EmployeeDTO employeeDTO = new EmployeeDTO(null, "shayan", "shayan@gmail.com", 2,
				"USER", 5000.0, LocalDate.of(2020, 12, 1), true);

		EmployeeDTO savedEmployeeDTO = employeeClient.createNewEmployee(employeeDTO);
		System.out.println(savedEmployeeDTO);
	}

}
