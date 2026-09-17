package library;

public class BookNotRentedException extends RuntimeException {
	public BookNotRentedException(String message) {
		super(message);
	}
}
