package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Book;

import it.uniroma3.siw.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	BookRepository bookRepository;

	@Transactional
	public List<Book> getAll() {
		List<Book> result = new ArrayList<>();
      Iterable<Book> iterable = this.bookRepository.findAll();
      for(Book book : iterable)
          result.add(book);
      return result;
	}
	
//	@Transactional
//    public List<User> getAllUsers() {
//        List<User> result = new ArrayList<>();
//        Iterable<User> iterable = this.userRepository.findAll();
//        for(User user : iterable)
//            result.add(user);
//        return result;
//    }
	
	
	@Transactional
	public Book getById(Long id) {
		return bookRepository.findById(id).get();
	}
	
	
}
