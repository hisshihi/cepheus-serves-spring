package com.example.web.cepheusservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//@SpringBootTest
class CepheusServiceApplicationTests {

	Calculator underTest = new Calculator();
	@Test
	void isShouldAddTwoNumbers() {

//		Given
		int numberOne = 23;
		int numberTwo = 5;

//		When
		int result = underTest.add(numberOne, numberTwo);

//		Then
		assertThat(result).isEqualTo(28);
	}

	class Calculator {
		public int add(int a, int b) {
			return a + b;
		}
	}

}
