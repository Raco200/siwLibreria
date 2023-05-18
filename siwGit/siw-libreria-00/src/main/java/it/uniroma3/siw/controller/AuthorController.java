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

@Controller
public class AuthorController {
	@Autowired AuthorRepository authorRepository;
	@Autowired BookRepository bookRepository;
	
	
	@GetMapping("/formNewAuthor")
	public String fromNewAuthor(Model model) {
		model.addAttribute("author",new Author());
		return "formNewAuthor.html";
	}
	
	@PostMapping("/Author")
	public String newAuthore(@ModelAttribute("author")Author author,Model model) {
		if(!authorRepository.existsAuthorByNomeAndCognome(author.getNome(),author.getCognome())) {
			this.authorRepository.save(author);
			model.addAttribute("author",author);
			return "Author.html";
		}
		else {
			model.addAttribute("messaggioErrore","Questo libro esiste già");
			return "formNewAuthor.html";
			
		}
	}
	
	@GetMapping("/authorsToAdd/{idBook}")
	public String getAllAuthors (Model model,@PathVariable("idBook")Long idbook) {
		model.addAttribute("authors",this.authorRepository.findAll());
		model.addAttribute("book",this.bookRepository.findById(idbook).get());
		return "authorsToAdd.html";
	}
	@GetMapping("/Author/{id}")
	public String getAuthor(Model model,@PathVariable("id")Long id) {
		model.addAttribute("author",this.authorRepository.findById(id).get());
		return "Author.html";
	}
	
}
