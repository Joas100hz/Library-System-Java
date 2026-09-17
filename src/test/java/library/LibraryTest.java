package library;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LibraryTest {
	
	private Library library;
	
	@BeforeEach   //configuração padrão da instância de library para cada teste
	void setUp() { 
		library = new Library();
	}
	
	@Test
	void addBookInGetBooks() {

		Book book = new Book("book", "author", "category");

	    library.addBook(book); //adiciona livro à lista de livros

	    assertTrue(library.getBooks().contains(book));//livro deve estar presente na lista
	    assertEquals(1, library.getBooks().size());
	}

	@Test
	void addCustomerInGetCustomers() {

		Customer customer = new Customer("customer", "11122233377");

		library.addCustomer(customer); //adiciona cliente à lista de clientes

		assertTrue(library.getCustomers().contains(customer)); //cliente deve estar presente na lista
		assertEquals(1, library.getCustomers().size());
	}

	@Test
	void getBooksReturnsDefensiveCopy() {

		Book book = new Book("book", "author", "category");

		library.addBook(book);
		library.getBooks().clear(); // tenta alterar a lista retornada

		assertEquals(1, library.getBooks().size()); // estado interno não deve mudar
	}

	@Test
	void getCustomersReturnsDefensiveCopy() {

		Customer customer = new Customer("customer", "11122233377");

		library.addCustomer(customer);
		library.getCustomers().clear(); // tenta alterar a lista retornada

		assertEquals(1, library.getCustomers().size()); // estado interno não deve mudar
	}

	@Test
	void findBookById() {

		Book book = new Book("book", "author", "category");
	    library.addBook(book);

	    Optional<Book> found = library.findBookById(book.getId()); //instância da busca por id

	    assertTrue(found.isPresent()); //livro correto deve está presente
	    assertEquals(book, found.get());
	}

	@Test
	void findCustomerById() {

		Customer customer = new Customer("customer", "11122233377");
	    library.addCustomer(customer);

	    Optional<Customer> found = library.findCustomerById(customer.getId()); //instância da busca por id

	    assertTrue(found.isPresent()); //cliente correto deve está presente
	    assertEquals(customer, found.get());
	}
	
	@Test
	void findBookByIdReturnsEmptyWhenNotFound() {

	    Optional<Book> found = library.findBookById(999); //instância vazia

	    assertTrue(found.isEmpty()); //instância deve estar vazia
	}

	@Test
	void findCustomerByIdReturnsEmptyWhenNotFound() {

	    Optional<Customer> found = library.findCustomerById(999); //instância vazia

	    assertTrue(found.isEmpty()); //instância deve estar vazia
	}
}
