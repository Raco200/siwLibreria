package it.uniroma3.siw.repository;
import it.uniroma3.siw.model.Book;

import java.util.List;
import org.springframework.data.repository.CrudRepository;



public interface BookRepository extends CrudRepository<Book,Long> {
	
	public boolean existsBookByTitleAndISBN(String title,String isbn);
	
	public List<Book> findByISBN (String isbn);
	
	public List<Book> findByTitle (String title);
	
}
