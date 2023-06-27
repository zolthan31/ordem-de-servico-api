package io.com.zolthan31.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.com.zolthan31.domain.Cliente;
import io.com.zolthan31.domain.OrdemDeServico;
import io.com.zolthan31.domain.Tecnico;
import io.com.zolthan31.domain.enums.Prioridade;
import io.com.zolthan31.domain.enums.Status;
import io.com.zolthan31.dtos.OrdemDeServicoDTO;
import io.com.zolthan31.repositories.OrdemDeServicoRepository;
import io.com.zolthan31.services.exceptions.ObjectNotFoundException;

@Service
public class OrdemDeServicoService {

	@Autowired
	private OrdemDeServicoRepository ordemDeServicoRepository;

	@Autowired
	private TecnicoService tecnicoService;

	@Autowired
	private ClienteService clienteService;

	public OrdemDeServico findById(Integer id) {

		Optional<OrdemDeServico> obj = ordemDeServicoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado!! Id: " + id + ", Tipo:" + OrdemDeServico.class.getName()));
	}

	public List<OrdemDeServico> findAll() {
		return ordemDeServicoRepository.findAll();
	}

	public OrdemDeServico create(@Valid OrdemDeServicoDTO objDTO) {
		return fromDTO(objDTO);
	}

	public OrdemDeServico update(@Valid OrdemDeServicoDTO objDTO) {
		findById(objDTO.getId());
		return fromDTO(objDTO);

	}

	private OrdemDeServico fromDTO(OrdemDeServicoDTO objDTO) {
		OrdemDeServico newObj = new OrdemDeServico();
		newObj.setId(objDTO.getId());
		newObj.setObservacoes(objDTO.getObservacoes());
		newObj.setPrioridade(Prioridade.toEnum(objDTO.getPrioridade()));
		newObj.setStatus(Status.toEnum(objDTO.getStatus()));

		Tecnico tecnico = tecnicoService.findById(objDTO.getTecnico());
		Cliente cliente = clienteService.findById(objDTO.getCliente());

		newObj.setTecnico(tecnico);
		newObj.setCliente(cliente);
		
		if(newObj.getStatus().getCode().equals(2)) {
			newObj.setDataFechamento(LocalDateTime.now());
		}
		
		return ordemDeServicoRepository.save(newObj);
	}

}
