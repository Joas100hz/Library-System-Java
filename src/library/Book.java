package library;

public class Book {
	private int id;
	private static int nextId = 1;
	private String name;
	private String author;
	private String category;
	private BookStatus status;
	private Customer customer;

	public Book(String name, String author, String category) {
		this.id = nextId++;
		this.name = name;
		this.author = author;
		this.category = category;
		this.status = BookStatus.AVAILABLE;
	}

	public boolean isAvailable() {
		return status == BookStatus.AVAILABLE;
	}

	public void rentTo(Customer customer) {
		if (!isAvailable()) {
			throw new BookUnavailableException("The book '" + name + "' is currently unavailable.");
		}

		this.status = BookStatus.RENTED;
		this.customer = customer;
	}

	public void returning() {
		this.status = BookStatus.AVAILABLE;
		this.customer = null;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAuthor() {
		return author;
	}

	public String getCategory() {
		return category;
	}

	public BookStatus getStatus() {
		return status;
	}

	public Customer getCustomer() {
		return customer;
	}

}
