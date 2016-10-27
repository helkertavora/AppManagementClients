package br.com.unifor.AppManagementClients.jobs;

import javax.faces.bean.ApplicationScoped;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.unifor.AppManagementClients.dao.ClienteDao;
import br.com.unifor.AppManagementClients.model.Cliente;

@Component
@ApplicationScoped
public class JobListarCliente {
	@SuppressWarnings("unchecked")
	@Scheduled(cron = "9 50 * * * *")
	public void atualizarUsuarioManha(){
		ClienteDao clienteDao = new ClienteDao();
		for (Cliente cliente : clienteDao.todos("nome")) { 
			System.out.println("Cliente : " + cliente.getNome());
		}
	}	
	
}
