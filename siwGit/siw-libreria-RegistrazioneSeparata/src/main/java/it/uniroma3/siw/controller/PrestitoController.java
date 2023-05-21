package it.uniroma3.siw.controller;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Book;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Prestito;
import it.uniroma3.siw.model.Student;
import it.uniroma3.siw.service.BookService;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.PrestitoService;
import it.uniroma3.siw.service.StudentService;
import it.uniroma3.siw.service.UserService;
import it.uniroma3.siw.model.User;



@Controller
public class PrestitoController {

	@Autowired
	BookService bookService;
	@Autowired
	PrestitoService prestitoService;
	@Autowired
	CredentialsService credentialsService;
	@Autowired
	UserService userService;
	@Autowired	
	StudentService studentService;
	
	@GetMapping(value="/addPrestito")
	public String addPrestito(Model model) {
		model.addAttribute("books",bookService.getAll());
		return "addPrestito.html";
	}
	
	@PostMapping("/prestito/{id}/{username}")
	public String formPrestito(@ModelAttribute("prestito") Prestito prestito,
			@PathVariable("id") Long idBook,@PathVariable("username") String username, Model model) {

		Book book=bookService.getById(idBook);
	    Credentials credentials= credentialsService.getCredentials(username);
	    User user= credentials.getUser();
	    
		if(prestito.getDataFine().compareTo(prestito.getDataInizio())>0 && user.getMatricolaStudente()!=null) {
			Student student= studentService.getById(user.getMatricolaStudente());
			List<Prestito> prestiti = student.getPrestiti();
			prestiti.add(prestito);
			student.setPrestiti(prestiti);
			book.setPrestito(prestito);
			prestito.setBook(book);
			prestito.setStudent(student);
			return "prestitoRiuscito.html";
		} 
		else {
			model.addAttribute("messaggioErrore", "hai già scritto una prestito per questo film");
			return "formPrestito.html";

		}
	}


	@GetMapping(value="/formPrestito/{id}/{username}")
	public String newPrestito(@ModelAttribute("prestito") Prestito prestito,
			@PathVariable("id") Long idBook,@PathVariable("username") String username, Model model) {
		
		model.addAttribute("prestito", new Prestito());
		return "formPrestito.html";
	}

}
