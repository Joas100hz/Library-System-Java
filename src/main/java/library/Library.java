package library;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Library {

    private List<Book> books = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    
    public void addBook(Book book) {
    	books.add(book);
    }
    
    public void addCustomer(Customer customer) {
    	customers.add(customer);
    }
 
    public List<Book> getBooks(){
    	return new ArrayList<>(books);
    }
    
    public List<Customer> getCustomers(){
    	return new ArrayList<>(customers);
    }

    public Optional<Book> findBookById(int id){
    	for (Book book : books) {
    		if (book.getId() == id){
    			return Optional.of(book);
    		}
    	}
    	
    	return Optional.empty();
    	
    	}
    	
    	public Optional<Customer> findCustomerById(int id){
    	    for (Customer customer : customers) {
    	    	if (customer.getId() == id){
    	    		return Optional.of(customer);
    	    	}
    	    }
    	    return Optional.empty();	
    	}
}