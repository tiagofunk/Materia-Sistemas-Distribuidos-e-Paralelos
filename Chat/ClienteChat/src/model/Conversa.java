package model;

import java.util.ArrayList;
import java.util.List;

public class Conversa {
	
	private Cliente cliente;
	private Contato contato;
	private List<Mensagem> listaMensagens = new ArrayList<>();
	
	public void adicionarMensagem(Mensagem m) {
		listaMensagens.add(m);
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}
	
	

}
