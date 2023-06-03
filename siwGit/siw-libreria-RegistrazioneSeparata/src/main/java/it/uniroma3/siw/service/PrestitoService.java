package it.uniroma3.siw.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Prestito;
import it.uniroma3.siw.model.Student;
import it.uniroma3.siw.repository.PrestitoRepository;

@Service
public class PrestitoService {

	@Autowired
	PrestitoRepository prestitoRepository;

	@Transactional
	public void save(Prestito prestito) {
		prestitoRepository.save(prestito);
		
	}

	@Transactional
	public List<Prestito> getPrestitiAttivi(Student student) {
		List<Prestito> prestitiAttivi= new ArrayList<>();
		 List<Prestito> iterable = student.getPrestiti();
	      for(Prestito prestito : iterable) {
	    	 if(prestito.getDataFine().compareTo(LocalDate.now())>0)
	    		  prestitiAttivi.add(prestito);
	      }
		
		return prestitiAttivi;
	}

	@Transactional
	public List<Prestito> getPrestitiConclusi(Student student) {
		List<Prestito> prestitiConclusi= new ArrayList<>();
		 List<Prestito> iterable = student.getPrestiti();
	      for(Prestito prestito : iterable) {
	    	 if(prestito.getDataFine().compareTo(LocalDate.now())<0)
	    		  prestitiConclusi.add(prestito);
	      }
		
		return prestitiConclusi;
	}

	
}
