package it.prova.agendarest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.agendarest.model.Agenda;
import it.prova.agendarest.repository.agenda.AgendaRepository;
import it.prova.agendarest.web.api.exception.FilmNotFoundException;


@Service
@Transactional(readOnly = true)
public class AgendaServiceImpl implements AgendaService{

	
	@Autowired
	private AgendaRepository repository;

	public List<Agenda> listAllElements(boolean eager) {
		if (eager)
			return (List<Agenda>) repository.findAllAgendaEager();

		return (List<Agenda>) repository.findAll();
	}

	public Agenda caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	public Agenda caricaSingoloElementoEager(Long id) {
		return repository.findSingleAgendaEager(id);
	}

	@Transactional
	public Agenda aggiorna(Agenda filmInstance) {
		return repository.save(filmInstance);
	}

	@Transactional
	public Agenda inserisciNuovo(Agenda filmInstance) {
		return repository.save(filmInstance);
	}

	@Transactional
	public void rimuovi(Long idToRemove) {
		repository.findById(idToRemove)
				.orElseThrow(() -> new FilmNotFoundException("Agenda not found con id: " + idToRemove));
		repository.deleteById(idToRemove);
	}
}
