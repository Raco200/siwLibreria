package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Student;
import it.uniroma3.siw.repository.StudentRepository;


@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;

	@Transactional
	public Student getById(Long matricola) {
		return studentRepository.findById(matricola).orElse(null);
	}

}
