package br.com.codenation.centralerros.dao;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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

	public Usuario busca(long id) {
		return banco.get(id);
	}
	
	public void adiciona(Usuario usuario) {
		long id = contador.incrementAndGet();
		usuario.setId(id);
		banco.put(id, usuario);
	}
	
	public Usuario adiciona() {
		Usuario usuario = new Usuario();
		usuario.setToken("KUsx9iJWOAo9tmuYU1LErzUdS8XM46vPmS5cCWma");
		usuario.setNome("Administrador");
		usuario.setEmail("admin@admin.com");
		usuario.setPassword("123456");
		usuario.setDataCadastro( new Timestamp(System.currentTimeMillis()) );
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db_mySql_server");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
		em.close();
		
		emf.close();
		
		return usuario;
	}
	
}
