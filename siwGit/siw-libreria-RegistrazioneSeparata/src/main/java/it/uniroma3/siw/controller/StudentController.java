package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Student;
import it.uniroma3.siw.repository.StudentRepository;

@Controller
public class StudentController {
	@Autowired StudentRepository studentRepository;

	@GetMapping("/admin/formNewStudent")
	public String getStudent(Model model) {
		model.addAttribute("student",new Student());
		
		return "admin/formNewStudent.html";
	}
	@PostMapping("/admin/formNewStudent")
	public String saveStudent(@ModelAttribute("student")Student student,Model model) {
		if(!this.studentRepository.existsStudentByMatricola(student.getMatricola())) {
			this.studentRepository.save(student);
			model.addAttribute("student",student);
			return "admin/Student.html";
		}
		else {
			model.addAttribute("messaggioErrore","Questo studente esiste già");
			return "admin/formNewStudent.html";
			
		}
	
		
	}
	@GetMapping("/admin/student/{id}")
	public String getStudentbyId (Model model,@PathVariable("id")Long matricola) {
		model.addAttribute("student",this.studentRepository.findById(matricola).get());
		return ("admin/Student.html");
	}
	
	@GetMapping("/admin/students")
	public String getStudents(Model model) {
		model.addAttribute("students",this.studentRepository.findAll());
		return ("admin/Students.html");
	}
	
	@GetMapping("/admin/deleteStudent")
	public String deleteBook(Model model,@RequestParam Long id) {
		this.studentRepository.deleteById(id);
		model.addAttribute("students",this.studentRepository.findAll());
		return "admin/students.html";
	}
}
