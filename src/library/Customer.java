		package library;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	private int id;
	private static int nextId = 1;
	private String name;
	private String cpf;
	private List<Book> rentedBooks = new ArrayList<>();

	public Customer(String name, String cpf) {
		this.id = nextId++;
		this.name = name;
		this.cpf = cpf;
	}

	public void rentBook(Book book) {
		book.rentTo(this);
		rentedBooks.add(book);

	}

	public void returnBook(Book book) {
		if (!rentedBooks.contains(book)) {
			throw new BookNotRentedException("The customer hasn't rented this book.");
		}

		book.returning();
		rentedBooks.remove(book);
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCpf() {
		return cpf;
	}

	public List<Book> getRentedBooks() {
		return new ArrayList<Book>(rentedBooks);
	}

}
