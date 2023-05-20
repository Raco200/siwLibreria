package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import it.uniroma3.siw.model.Student;
import it.uniroma3.siw.repository.StudentRepository;

@Controller
public class StudentController {
	@Autowired StudentRepository studentRepository;

	@GetMapping("/formNewStudent")
	public String getStudent(Model model) {
		model.addAttribute("student",new Student());
		
		return "formNewStudent.html";
	}
	@PostMapping("/formNewStudent")
	public String saveStudent(@ModelAttribute("student")Student student,Model model) {
		if(!this.studentRepository.existsStudentByMatricola(student.getMatricola())) {
			this.studentRepository.save(student);
			model.addAttribute("student",student);
			return "Student.html";
		}
		else {
			model.addAttribute("messaggioErrore","Questo libro esiste già");
			return "formNewAuthor.html";
			
		}
	
		
	}
	@GetMapping("/Student/{id}")
	public String getStudentbyId (Model model,@PathVariable("id")Long matricola) {
		model.addAttribute("student",this.studentRepository.findById(matricola).get());
		return ("Student.html");
	}
}
