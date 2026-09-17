package library;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BookTest {

	@Test
	void availableBookInstance() {
		Book book = new Book("book", "author", "category");

		assertEquals("book", book.getName()); // nome deve corresponder ao informado no construtor
		assertEquals("author", book.getAuthor()); // autor deve corresponder ao informado no construtor
		assertEquals("category", book.getCategory()); // categoria deve corresponder à informada no construtor
		assertTrue(book.isAvailable()); // livro deve nascer disponível
		assertNull(book.getCustomer()); // livro não deve ter cliente associado na criação
	}

	@Test
	void rentToCustomer() {
		Customer customer = new Customer("customer", "11122233377");
		Book book = new Book("book", "author", "category");

		book.rentTo(customer); // aluga livro diretamente para o cliente

		assertFalse(book.isAvailable()); // livro não deve mais estar disponível
		assertEquals(BookStatus.RENTED, book.getStatus()); // status deve mudar para alugado
		assertEquals(customer, book.getCustomer()); // cliente associado deve ser o que alugou
	}

	@Test
	void returningMakesBookAvailableAgain() {
		Customer customer = new Customer("customer", "11122233377");
		Book book = new Book("book", "author", "category");

		book.rentTo(customer); // aluga o livro antes de testar a devolução
		book.returning(); // devolve o livro

		assertTrue(book.isAvailable()); // livro deve voltar a estar disponível
		assertEquals(BookStatus.AVAILABLE, book.getStatus()); // status deve voltar para disponível
		assertNull(book.getCustomer()); // cliente associado deve ser removido
	}
}