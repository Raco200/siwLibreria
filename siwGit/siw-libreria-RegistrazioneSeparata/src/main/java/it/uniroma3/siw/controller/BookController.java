package it.uniroma3.siw.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Author;
import it.uniroma3.siw.model.Book;
import it.uniroma3.siw.repository.AuthorRepository;
import it.uniroma3.siw.repository.BookRepository;
import javax.transaction.Transactional;


@Controller
public class BookController {
	@Autowired BookRepository libreriaRepository;
	@Autowired AuthorRepository authorRepository;
	
	@GetMapping("/index")
	public String index() {
		return "index.html";
	}
	
	@GetMapping("/formNewBook")
	public String fromNewBook(Model model) {
		model.addAttribute("book",new Book());
		return "formNewBook.html";
	}

	@PostMapping("/libri")
	public String newMovie (@ModelAttribute("book")Book book,Model model) {
		if(!libreriaRepository.existsBookByTitleAndIsbn(book.getTitle(),book.getIsbn())) {
			this.libreriaRepository.save(book);
			model.addAttribute("book",book);
			return "Book.html";
		}
		else {
			model.addAttribute("messaggioErrore","Questo libro esiste già");
			return "formNewBook.html";
			
		}
		
		
	}
	@GetMapping("/libri/{Id}")
	public String getBook (@PathVariable("Id")Long id , Model model) {
		model.addAttribute("book",this.libreriaRepository.findById(id).get());
		return "Book.html";
	}
	@GetMapping("/libri")
	public String libri (Model model) {
		model.addAttribute("libri",this.libreriaRepository.findAll());
		return "libri.html";
	}
	@GetMapping ("/formSearchTitle")
	public String formSearchTitle() {
		return "formSearchTitle.html";
	}
	@PostMapping ("/searchTitle")
	public String searchTitle (Model model,@RequestParam String title) {
		model.addAttribute("libri",this.libreriaRepository.findByTitle(title));
		return "foundBooks.html";}
	
	@GetMapping ("/formSearchisbn")
	public String formSearchBook() {
		return "formSearchisbn.html";
	}
	@PostMapping("/searchBooks")
	public String searchBooks(Model model,@RequestParam String isbn) {
		model.addAttribute("libri",this.libreriaRepository.findByIsbn(isbn));
		return "foundBooks.html";
	}
	@GetMapping("/delete")
	public String deleteBook(Model model,@RequestParam Long id) {
		this.libreriaRepository.deleteById(id);
		model.addAttribute("libri",this.libreriaRepository.findAll());
		return "libri.html";
	}
	
	@Transactional
	@GetMapping("/authorsToAdd/{idAuthor}/{idBook}")
	public String addAuthorToBook(Model model,@PathVariable("idAuthor")Long idauthor,@PathVariable("idBook")Long idbook) {
		Book book=this.libreriaRepository.findById(idbook).get();
		Author author=this.authorRepository.findById(idauthor).get();
		book.setAutore(author);
		author.getLibri().add(book);
		this.authorRepository.save(author);
		this.libreriaRepository.save(book);
		model.addAttribute("book",book);
		
		return ("Book.html");
	}
}
