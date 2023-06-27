package io.com.zolthan31.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.com.zolthan31.dtos.OrdemDeServicoDTO;
import io.com.zolthan31.services.OrdemDeServicoService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/ordem-de-servico")
public class OrdemDeServicoResource {
	
	@Autowired
	private OrdemDeServicoService ordemDeServicoService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<OrdemDeServicoDTO> findById(@PathVariable Integer id) {
		OrdemDeServicoDTO objDto = new OrdemDeServicoDTO(ordemDeServicoService.findById(id));
		return ResponseEntity.ok().body(objDto);		
	}
	
	@GetMapping
	public ResponseEntity<List<OrdemDeServicoDTO>> findAll() {
		List<OrdemDeServicoDTO> listDTO = ordemDeServicoService.findAll()
				.stream()
				.map(obj -> new OrdemDeServicoDTO(obj))
				.collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDTO);
		
	}
	
	@PostMapping
	public ResponseEntity<OrdemDeServicoDTO> create(@Valid @RequestBody OrdemDeServicoDTO objDTO) {
		objDTO = new OrdemDeServicoDTO(ordemDeServicoService.create(objDTO));
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(objDTO.getId())
				.toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<OrdemDeServicoDTO> update(@Valid @RequestBody OrdemDeServicoDTO objDTO) {			
		objDTO = new OrdemDeServicoDTO(ordemDeServicoService.update(objDTO));
		return ResponseEntity.ok().body(objDTO);
	}

}
