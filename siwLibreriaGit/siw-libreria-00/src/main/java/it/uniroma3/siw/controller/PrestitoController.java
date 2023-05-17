package it.uniroma3.siw.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



import it.uniroma3.siw.service.BookService;


@Controller
public class PrestitoController {

	@Autowired
	BookService bookService;
	
	@GetMapping(value="/addPrestito")
	public String addPrestito(Model model) {
		model.addAttribute("books",bookService.getAll());
		return "addPrestito.html";
	}
	
//	@PostMapping("/recensione/{id}/{username}")
//	public String formRecensione(@ModelAttribute("recensione") Recensione recensione,
//			@PathVariable("id") Long idMovie,@PathVariable("username") String username, Model model) {
//
//		Movie movie=movieRepository.findById(idMovie).get();
//		Optional<Credentials> credentials=credentialsRepository.findByUsername(username);
//		if (!recensioneRepository.existsByMovieAndCredentials(movie, credentials.orElse(null))) {
//			recensione.setCredentials(credentials.orElse(null));
//			recensione.setMovie(movie);
//			movie.aggiungiRecensione(recensione);
//			credentials.orElse(null).aggiungiRecensione(recensione);
//
//			this.recensioneRepository.save(recensione); 
//			model.addAttribute("recensione",recensione);
//
//			return "recensioneRiuscita.html";
//		} 
//		else {
//			model.addAttribute("messaggioErrore", "hai già scritto una recensione per questo film");
//			return "formRecensione.html";
//
//		}
//	}
//
//
//	@GetMapping(value="/formRecensione/{id}/{username}")
//	public String newRecensione(@ModelAttribute("recensione") Recensione recensione,
//			@PathVariable("id") Long idMovie,@PathVariable("username") String username, Model model) {
//		
//		model.addAttribute("recensione", new Recensione());
//		return "formRecensione.html";
//	}

}
