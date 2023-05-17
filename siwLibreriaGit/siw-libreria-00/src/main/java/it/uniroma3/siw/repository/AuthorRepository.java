package it.uniroma3.siw.repository;
import it.uniroma3.siw.model.*;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository  extends CrudRepository<Author,Long>{
	public boolean existsAuthorByNomeAndCognome(String nome,String cognome);
}
