package br.com.codenation.centralerros.dao;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.persistence.EntityManager;

import br.com.codenation.centralerros.model.Usuario;
import br.com.codenation.centralerros.utils.JPAUtil;

public class UsuarioDAO {
	
	private static Map<Long, Usuario> banco = new HashMap<>();
	private static AtomicLong contador = new AtomicLong(1);
	
	static {
		Usuario usuario1 = new Usuario("abc3de", "Bruno Vitalino", "bv@hot.com", "123456");
		usuario1.setId(1l);
		banco.put(1l, usuario1);
		Usuario usuario2 = new Usuario("def5gh", "Breno Oliveira", "bo@hot.com", "654321");
		usuario2.setId(1l);
		banco.put(2l, usuario2);
	}

	public Usuario buscaMemoria(long id) {
		return banco.get(id);
	}

	public Usuario find(long id) {

		EntityManager em = new JPAUtil().getEntityManager();
		
		em.getTransaction().begin();
		Usuario usuario = em.find(Usuario.class, id);
		em.getTransaction().commit();
		
		em.close();
		
		return usuario;
	}
	
	public void adiciona(Usuario usuario) {
		long id = contador.incrementAndGet();
		usuario.setId(id);
		banco.put(id, usuario);
	}
	
	public Usuario popula() {
		Usuario usuario = new Usuario();
		usuario.setToken("KUsx9iJWOAo9tmuYU1LErzUdS8XM46vPmS5cCWma");
		usuario.setNome("Administrador");
		usuario.setEmail("admin@admin.com");
		usuario.setPassword("123456");
		usuario.setDataCadastro( new Timestamp(System.currentTimeMillis()) );
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
		
		em.close();
		
		return usuario;
	}
	
}
