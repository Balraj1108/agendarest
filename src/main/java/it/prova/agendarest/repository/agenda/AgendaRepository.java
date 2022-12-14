package it.prova.agendarest.repository.agenda;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.agendarest.model.Agenda;

public interface AgendaRepository extends CrudRepository<Agenda, Long> {

	@Query("from Agenda a join fetch a.utente where a.id = ?1")
	Agenda findSingleAgendaEager(Long id);
	
	//List<Agenda> findByTitoloAndGenere(String titolo, String genere);
	
	@Query("select a from Agenda a join fetch a.utente au where au.username = ?1")
	List<Agenda> FindByUsername(String username);
	
	@Query("select a from Agenda a join fetch a.utente")
	List<Agenda> findAllAgendaEager();
}
