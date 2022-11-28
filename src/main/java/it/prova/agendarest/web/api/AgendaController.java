package it.prova.agendarest.web.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.prova.agendarest.dto.AgendaDTO;
import it.prova.agendarest.model.Agenda;
import it.prova.agendarest.service.AgendaService;
import it.prova.agendarest.web.api.exception.FilmNotFoundException;
import it.prova.agendarest.web.api.exception.IdNotNullForInsertException;

@RestController
@RequestMapping("api/agenda")
public class AgendaController {

	@Autowired
	private AgendaService agendaService;
	
	@GetMapping
	public List<AgendaDTO> getAll() {
		return AgendaDTO.createAgendaDTOListFromModelList(agendaService.listAllElements(true), true);
	}
	
	// gli errori di validazione vengono mostrati con 400 Bad Request ma
		// elencandoli grazie al ControllerAdvice
		@PostMapping
		public AgendaDTO createNew(@Valid @RequestBody AgendaDTO filmInput) {
			// se mi viene inviato un id jpa lo interpreta come update ed a me (producer)
			// non sta bene
			if (filmInput.getId() != null)
				throw new IdNotNullForInsertException("Non è ammesso fornire un id per la creazione");

			Agenda filmInserito = agendaService.inserisciNuovo(filmInput.buildAgendaModel());
			return AgendaDTO.buildAgendaDTOFromModel(filmInserito, true);
		}

		@GetMapping("/{id}")
		public AgendaDTO findById(@PathVariable(value = "id", required = true) long id) {
			Agenda film = agendaService.caricaSingoloElementoEager(id);

			if (film == null)
				throw new FilmNotFoundException("Agenda not found con id: " + id);

			return AgendaDTO.buildAgendaDTOFromModel(film, true);
		}
}
