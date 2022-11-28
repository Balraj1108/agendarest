package it.prova.agendarest.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.agendarest.model.Agenda;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AgendaDTO {

	
	private Long id;
	
	@NotBlank(message = "{descrizione.notblank}")
	private String descrizione;
	
	@NotNull(message = "{dataOraInizio.notnull}")
	private LocalDate dataOraInizio;
	
	@NotNull(message = "{dataOraFine.notnull}")
	private LocalDate dataOraFine;
	
	@JsonIgnoreProperties(value = { "agende" })
	@NotNull(message = "{utente.notnull}")
	private UtenteDTO utenteDTO;
	
	public AgendaDTO() {
	}

	public AgendaDTO(Long id,  String descrizione,
			LocalDate dataOraInizio,
			 LocalDate dataOraFine,
			 UtenteDTO utenteDTO) {
		super();
		this.id = id;
		this.descrizione = descrizione;
		this.dataOraInizio = dataOraInizio;
		this.dataOraFine = dataOraFine;
		this.utenteDTO = utenteDTO;
	}

	public AgendaDTO(Long id,  String descrizione,
			 LocalDate dataOraInizio,
			 LocalDate dataOraFine) {
		super();
		this.id = id;
		this.descrizione = descrizione;
		this.dataOraInizio = dataOraInizio;
		this.dataOraFine = dataOraFine;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public LocalDate getDataOraInizio() {
		return dataOraInizio;
	}

	public void setDataOraInizio(LocalDate dataOraInizio) {
		this.dataOraInizio = dataOraInizio;
	}

	public LocalDate getDataOraFine() {
		return dataOraFine;
	}

	public void setDataOraFine(LocalDate dataOraFine) {
		this.dataOraFine = dataOraFine;
	}

	public UtenteDTO getUtenteDTO() {
		return utenteDTO;
	}

	public void setUtenteDTO(UtenteDTO utenteDTO) {
		this.utenteDTO = utenteDTO;
	}
	
	public Agenda buildAgendaModel() {
		Agenda result = new Agenda(this.id, this.descrizione, this.dataOraInizio, this.dataOraFine);
		if (this.utenteDTO != null)
			result.setUtente(this.utenteDTO.buildUtenteModel(true));

		return result;
	}

	public static AgendaDTO buildAgendaDTOFromModel(Agenda agendaModel, boolean includeUtenti) {
		AgendaDTO result = new AgendaDTO(agendaModel.getId(), agendaModel.getDescrizione(), agendaModel.getDataOraInizio(),
				agendaModel.getDataOraFine());

		if (includeUtenti)
			result.setUtenteDTO(UtenteDTO.buildUtenteDTOFromModel(agendaModel.getUtente(), false));

		return result;
	}

	public static List<AgendaDTO> createAgendaDTOListFromModelList(List<Agenda> modelListInput, boolean includeUtenti) {
		return modelListInput.stream().map(agendaEntity -> {
			return AgendaDTO.buildAgendaDTOFromModel(agendaEntity, includeUtenti);
		}).collect(Collectors.toList());
	}

	
	
	
	
	
	
	
	
}
