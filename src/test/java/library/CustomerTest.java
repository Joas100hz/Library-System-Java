package library;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerTest {
	
	private Customer customer;
	
	@BeforeEach //configuração padrão da instância de customer para cada teste
	void setUp() {
		customer = new Customer("customer", "11122233377");
	}

	@Test
	void newCustomerHasNoRentedBooks() {

		assertEquals("customer", customer.getName());
		assertEquals("11122233377", customer.getCpf());
		assertTrue(customer.getRentedBooks().isEmpty());
		
		//as informações devem ser coerentes ao da instanciação
	}

	@Test
	void rentBookAddsBookToRentedList() {
		Book book = new Book("book", "author", "category");

		customer.rentBook(book); //cliente aluga livro

		assertTrue(customer.getRentedBooks().contains(book));
		assertEquals(1, customer.getRentedBooks().size()); //A lista deve conter o livro alugado
		assertEquals(BookStatus.RENTED, book.getStatus());
	}


	@Test
	void rentedBookNotAvailable() {

		Customer customer2 = new Customer("Customer2", "12345678911");
		Book book = new Book("book", "author", "category");

		customer.rentBook(book); // cliente1 aluga um livro

		assertThrows(BookUnavailableException.class, () -> {
			customer2.rentBook(book); // cliente2 não deve conseguir alugar um livro já alugado
		});
	}

	
	@Test
	void returnBookRemovesBookFromRentedList() {
		Book book = new Book("book", "author", "category");

		customer.rentBook(book);
		customer.returnBook(book); //cliente devolve livro que fora alugado

		assertFalse(customer.getRentedBooks().contains(book));
		assertTrue(customer.getRentedBooks().isEmpty());	  //Após a devolução, cliente não deve ter livro na lista
		assertEquals(BookStatus.AVAILABLE, book.getStatus());
	}

	@Test
	void returnBookThrowsWhenBookWasNotRentedByCustomer() {
		Book book = new Book("book", "author", "category"); //cliente não alugará livro

		assertThrows(BookNotRentedException.class, () -> customer.returnBook(book)); //exceção deve ser lançada
	}

	@Test
	void getRentedBooksReturnsDefensiveCopy() {
		Book book = new Book("book", "author", "category");

		customer.rentBook(book);
		customer.getRentedBooks().clear(); // tenta alterar a lista retornada

		assertEquals(1, customer.getRentedBooks().size()); // estado interno não deve mudar
	}
}
