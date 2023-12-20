package br.com.rodrigobraz.OrderSystemJava;

import br.com.rodrigobraz.OrderSystemJava.controllers.City;
import br.com.rodrigobraz.OrderSystemJava.controllers.State;
import br.com.rodrigobraz.OrderSystemJava.entities.Address;
import br.com.rodrigobraz.OrderSystemJava.entities.Category;
import br.com.rodrigobraz.OrderSystemJava.entities.Customer;
import br.com.rodrigobraz.OrderSystemJava.entities.Product;
import br.com.rodrigobraz.OrderSystemJava.entities.enums.CustomerType;
import br.com.rodrigobraz.OrderSystemJava.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class OrderSystemJavaApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AddressRepository addressRepository;

	public static void main(String[] args) {
		SpringApplication.run(OrderSystemJavaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Category cat1 = new Category(null, "IT");
		Category cat2 = new Category(null, "Office");

		Product p1 = new Product(null, "Computer", 2000.00);
		Product p2 = new Product(null, "Printer", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);

		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));

		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));

		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));

		State state1 = new State(null, "Minas Gerais");
		State state2 = new State(null,"São Paulo");

		City city1 = new City(null, "Uberlandia", state1);
		City city2 = new City(null, "São Paulo", state2);
		City city3 = new City(null, "Campinas", state2);

		state1.getCities().addAll(Arrays.asList(city1));
		state1.getCities().addAll(Arrays.asList(city2, city3));

		stateRepository.saveAll(Arrays.asList(state1, state2));
		cityRepository.saveAll(Arrays.asList(city1, city2, city3));

		Customer customer1 = new Customer(null,
				"Maria Silva",
				"maria@gmail.com",
				"36378912377",
				CustomerType.NATURAL_PERSON);
		customer1.getPhoneNumbers().addAll(Arrays.asList("27363323", "993838393"));

		Address address1 = new Address(null,
				"Rua Flores",
				"300",
				"Apto 203",
				"Jardim",
				"38220834",
				customer1, city1);

		Address address2 = new Address(null,
				"Avenida Matos",
				"105",
				"Sala 800",
				"Centro",
				"38777012",
				customer1, city2);

		customer1.getAddresses().addAll(Arrays.asList(address1, address2));

		customerRepository.saveAll(Arrays.asList(customer1));
		addressRepository.saveAll(Arrays.asList(address1, address2));

	}
}
