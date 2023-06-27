package io.com.zolthan31.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.com.zolthan31.domain.Cliente;
import io.com.zolthan31.domain.OrdemDeServico;
import io.com.zolthan31.domain.Tecnico;
import io.com.zolthan31.domain.enums.Prioridade;
import io.com.zolthan31.domain.enums.Status;
import io.com.zolthan31.repositories.ClienteRepository;
import io.com.zolthan31.repositories.OrdemDeServicoRepository;
import io.com.zolthan31.repositories.TecnicoRepository;

@Service
public class DBService {
	
	@Autowired
	private TecnicoRepository repository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private OrdemDeServicoRepository ordemDeServicoRepository;
	
	
	public void instanciaDB() {		
		Tecnico t1 = new Tecnico(null, "Valdir Cesar", "464.774.990-88", "(47)98888-8888");
		Tecnico t2 = new Tecnico(null, "Rosivaldo Lima", "203.547.320-99", "(47)95555-5555");
		Cliente c1 = new Cliente(null, "Beatriz Segal", "468.754.930-63", "(47)97777-7777");
		
		OrdemDeServico os1 = new OrdemDeServico(null, Prioridade.ALTA, "Teste created OS", Status.ANDAMENTO, t1, c1);
		
		t1.getList().add(os1);
		c1.getList().add(os1);
		
		repository.saveAll(Arrays.asList(t1, t2));
		clienteRepository.saveAll(Arrays.asList(c1));
		ordemDeServicoRepository.saveAll(Arrays.asList(os1));
		
	}

}
