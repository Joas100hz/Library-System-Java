package library;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {

		Library library = new Library();

		Scanner scan = new Scanner(System.in);

		int option = -1;

		while (option != 0) {

			System.out.println("""
					===== LIBRARY SYSTEM =====

					1 - List books
					2 - Find book by ID
					3 - Register customer
					4 - Rent book
					5 - Return book
					0 - Exit

					Choose an option:
					""");

			option = scan.nextInt();
			scan.nextLine();

			switch (option) {

			case 1:

				// List books
				List<Book> books = library.getBooks();

				if (!books.isEmpty()) {

					for (Book book : books) {

						System.out.println(
								"\nID: " + book.getId() + "\nStatus: " + book.getStatus() + "\nName: " + book.getName()
										+ "\nAuthor: " + book.getAuthor() + "\nCategory: " + book.getCategory() + "\n");
					}

				} else {
					System.out.println("No books registered!");
				}

				break;

			case 2:

				// Find book by ID
				System.out.println("Type the book ID:");
				int id = scan.nextInt();

				Optional<Book> bookResult = library.findBookById(id);

				if (bookResult.isPresent()) {

					Book foundBook = bookResult.get();

					System.out.println("\nID: " + foundBook.getId() + "\nStatus: " + foundBook.getStatus() + "\nName: "
							+ foundBook.getName() + "\nAuthor: " + foundBook.getAuthor() + "\nCategory: "
							+ foundBook.getCategory() + "\n");

				} else {
					System.out.println("Book not found!");
				}

				break;

			case 3:

				// Register customer
				System.out.println("Type the customer name:");
				String name = scan.nextLine();

				System.out.println("Type the customer CPF:");
				String cpf = scan.nextLine();

				Customer customer = new Customer(name, cpf);

				library.addCustomer(customer);

				System.out.println("Customer registered successfully!");

				break;

			case 4:

				// Rent book
				System.out.println("Type the customer ID:");
				int customerID = scan.nextInt();

				Optional<Customer> customerResult = library.findCustomerById(customerID);

				if (customerResult.isPresent()) {

					Customer customerRent = customerResult.get();

					System.out.println("Type the book ID:");
					int bookID = scan.nextInt();

					Optional<Book> bookToRent = library.findBookById(bookID);

					if (bookToRent.isPresent()) {

						Book bookRent = bookToRent.get();

						try {

							customerRent.rentBook(bookRent);

							System.out.println("Book rented successfully!");

						} catch (BookUnavailableException exc) {

							System.out.println(exc.getMessage());
						}

					} else {
						System.out.println("Book not found!");
					}

				} else {
					System.out.println("Customer not found!");
				}

				break;

			case 5:

				// Return book

				System.out.println("Type the customer ID:");

				int customerId = scan.nextInt();

				Optional<Customer> customerResult5 = library.findCustomerById(customerId);

				if (customerResult5.isPresent()) {

					Customer customerReturn = customerResult5.get();

					System.out.println("Type the book ID to be returned:");

					int bookId = scan.nextInt();

					Optional<Book> bookResult5 = library.findBookById(bookId);

					if (bookResult5.isPresent()) {

						Book bookReturn = bookResult5.get();

						try {

							customerReturn.returnBook(bookReturn);

							System.out.println("Book returned successfully!");

						} catch (BookNotRentedException exc) {

							System.out.println(exc.getMessage());
						}

					} else {

						System.out.println("Book not found!");
					}

				} else {

					System.out.println("Customer not found!");
				}

				break;

			case 0:

				System.out.println("Exiting...");

				break;

			default:

				System.out.println("Invalid option.");
			}
		}

		scan.close();
	}
}