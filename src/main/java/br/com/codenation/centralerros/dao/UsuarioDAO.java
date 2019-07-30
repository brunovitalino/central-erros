package br.com.codenation.centralerros.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import br.com.codenation.centralerros.model.Usuario;

public class UsuarioDAO {
	
	private static Map<Long, Usuario> banco = new HashMap<>();
	private static AtomicLong contador = new AtomicLong(1);
	
	static {
		Usuario usuario1 = new Usuario("abc3de", "Bruno Vitalino", "bv@hot.com", "123456");
		usuario1.setId(1l);
		banco.put(1l, usuario1);
//		Usuario usuario2 = new Usuario("def5gh", "Breno Oliveira", "bo@hot.com", "654321");
//		usuario2.setId(1l);
//		banco.put(2l, usuario2);
	}
	
	public void adiciona(Usuario usuario) {
		long id = contador.incrementAndGet();
		usuario.setId(id);
		banco.put(id, usuario);
	}

	public Usuario busca(long id) {
		return banco.get(id);
	}

}
