package library;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Menu {

    private Library library;
    private Scanner scan;

    public Menu() {
        library = new Library();
        scan = new Scanner(System.in);
    }

    public void start() {

        int option = -1;

        while (option != 0) {

            showMenu();

            option = scan.nextInt();
            scan.nextLine();

            switch (option) {

                case 1 -> listBooks();

                case 2 -> findBook();

                case 3 -> registerBook();

                case 4 -> listCustomers();

                case 5 -> registerCustomer();

                case 6 -> rentBook();

                case 7 -> returnBook();

                case 0 -> System.out.println("Exiting...");

                default -> System.out.println("Invalid option.");
            }
        }

        scan.close();
    }

    private void showMenu() {

        System.out.println("""
                ===== LIBRARY SYSTEM =====

                BOOKS
                1 - List books
                2 - Find book by ID
                3 - Register book

                CUSTOMERS
                4 - List customers
                5 - Register customer

                RENTAL
                6 - Rent book
                7 - Return book

                0 - Exit

                Choose an option:
                """);
    }

    private void listBooks() {

        List<Book> books = library.getBooks();

        if (books.isEmpty()) {

            System.out.println("No books registered!");
            return;
        }

        for (Book book : books) {

            System.out.println(
                    "\nID: " + book.getId()
                    + "\nStatus: " + book.getStatus()
                    + "\nName: " + book.getName()
                    + "\nAuthor: " + book.getAuthor()
                    + "\nCategory: " + book.getCategory()
                    + "\n"
            );
        }
    }

    private void findBook() {

        System.out.println("Type the book ID:");

        int id = scan.nextInt();

        Optional<Book> bookResult = library.findBookById(id);

        if (bookResult.isPresent()) {

            Book book = bookResult.get();

            System.out.println(
                    "\nID: " + book.getId()
                    + "\nStatus: " + book.getStatus()
                    + "\nName: " + book.getName()
                    + "\nAuthor: " + book.getAuthor()
                    + "\nCategory: " + book.getCategory()
                    + "\n"
            );

        } else {

            System.out.println("Book not found!");
        }
    }

    private void registerBook() {

        System.out.println("Type the book name:");
        String bookName = scan.nextLine();

        System.out.println("Type the book author:");
        String author = scan.nextLine();

        System.out.println("Type the book category:");
        String category = scan.nextLine();

        Book newBook = new Book(
                bookName,
                author,
                category
        );

        library.addBook(newBook);

        System.out.println("Book registered successfully!");

        System.out.println("Book ID: " + newBook.getId());
    }

    private void listCustomers() {

        List<Customer> customers = library.getCustomers();

        if (customers.isEmpty()) {

            System.out.println("No customers registered!");
            return;
        }

        for (Customer customer : customers) {

            System.out.println(
                    "\nID: " + customer.getId()
                    + "\nName: " + customer.getName()
                    + "\nCPF: " + customer.getCpf()
                    + "\n"
            );
        }
    }

    private void registerCustomer() {

        System.out.println("Type the customer name:");
        String name = scan.nextLine();

        System.out.println("Type the customer CPF:");
        String cpf = scan.nextLine();

        Customer customer = new Customer(name, cpf);

        library.addCustomer(customer);

        System.out.println("Customer registered successfully!");

        System.out.println("Customer ID: " + customer.getId());
    }

    private void rentBook() {

        System.out.println("\n===== AVAILABLE BOOKS =====");

        List<Book> books = library.getBooks();

        if (books.isEmpty()) {

            System.out.println("No books registered!");
            return;
        }

        for (Book book : books) {

            System.out.println(
                    "ID: " + book.getId()
                    + " | Name: " + book.getName()
                    + " | Status: " + book.getStatus()
            );
        }

        System.out.println("\n===== CUSTOMERS =====");

        List<Customer> customers = library.getCustomers();

        if (customers.isEmpty()) {

            System.out.println("No customers registered!");
            return;
        }

        for (Customer customer : customers) {

            System.out.println(
                    "ID: " + customer.getId()
                    + " | Name: " + customer.getName()
            );
        }

        System.out.println("\nType the customer ID:");

        int customerId = scan.nextInt();

        Optional<Customer> customerResult =
                library.findCustomerById(customerId);

        if (customerResult.isEmpty()) {

            System.out.println("Customer not found!");
            return;
        }

        Customer customer = customerResult.get();

        System.out.println("\nType the book ID:");

        int bookId = scan.nextInt();

        Optional<Book> bookResult =
                library.findBookById(bookId);

        if (bookResult.isEmpty()) {

            System.out.println("Book not found!");
            return;
        }

        Book book = bookResult.get();

        try {

            customer.rentBook(book);

            System.out.println("Book rented successfully!");

        } catch (BookUnavailableException exc) {

            System.out.println(exc.getMessage());
        }
    }

    private void returnBook() {

        System.out.println("\n===== BOOKS =====");

        List<Book> books = library.getBooks();

        if (books.isEmpty()) {

            System.out.println("No books registered!");
            return;
        }

        for (Book book : books) {

            System.out.println(
                    "ID: " + book.getId()
                    + " | Name: " + book.getName()
                    + " | Status: " + book.getStatus()
            );
        }

        System.out.println("\n===== CUSTOMERS =====");

        List<Customer> customers = library.getCustomers();

        if (customers.isEmpty()) {

            System.out.println("No customers registered!");
            return;
        }

        for (Customer customer : customers) {

            System.out.println(
                    "ID: " + customer.getId()
                    + " | Name: " + customer.getName()
            );
        }

        System.out.println("\nType the customer ID:");

        int customerId = scan.nextInt();

        Optional<Customer> customerResult =
                library.findCustomerById(customerId);

        if (customerResult.isEmpty()) {

            System.out.println("Customer not found!");
            return;
        }

        Customer customer = customerResult.get();

        System.out.println("\nType the book ID to be returned:");

        int bookId = scan.nextInt();

        Optional<Book> bookResult =
                library.findBookById(bookId);

        if (bookResult.isEmpty()) {

            System.out.println("Book not found!");
            return;
        }

        Book book = bookResult.get();

        try {

            customer.returnBook(book);

            System.out.println("Book returned successfully!");

        } catch (BookNotRentedException exc) {

            System.out.println(exc.getMessage());
        }
    }
}