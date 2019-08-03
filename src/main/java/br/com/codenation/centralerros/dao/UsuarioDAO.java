package br.com.codenation.centralerros.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.codenation.centralerros.model.Usuario;
import br.com.codenation.centralerros.utils.JPAUtil;
import br.com.codenation.centralerros.utils.TokenUtil;

public class UsuarioDAO {

	
	// DAOs PRINCIPAIS

	public Usuario find(long id) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		Usuario usuario = em.find(Usuario.class, id);
		
		em.getTransaction().commit();
		em.close();
//		new JPAUtil().closeEntityManager();
		
		return usuario;
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> findAll() {
		
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		String jpql = "Select u from Usuario u";
		
		Query query = em.createQuery(jpql);
		List<Usuario> usuarios = query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		return usuarios;
	}
	
	public void save(Usuario usuario) {
		
		if (usuario.getToken()==null) usuario.setToken(new TokenUtil().getNewToken());
		Timestamp horaAtual = new Timestamp(System.currentTimeMillis());
		usuario.setDataCadastro(horaAtual);
		usuario.setDataAtualizacao(horaAtual);
		
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		em.persist(usuario);
		
		em.getTransaction().commit();
		em.close();
//		new JPAUtil().closeEntityManager();
	}

	public void update(Long idUsuarioAntigo, Usuario usuarioAtualizado) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		Usuario usuario = new Usuario();
		usuario = em.find(Usuario.class, idUsuarioAntigo);
		usuario.setNome( usuarioAtualizado.getNome() );
		usuario.setEmail( usuarioAtualizado.getEmail() );
		usuario.setPassword( usuarioAtualizado.getPassword() );
		usuario.setDataAtualizacao( new Timestamp(System.currentTimeMillis()) );
		
		em.getTransaction().commit();
		em.close();
	}

	public Usuario remove(Long id) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		Usuario usuario = em.find(Usuario.class, id);
		em.remove(usuario);
		
		em.getTransaction().commit();
		em.close();
		
		return usuario;
	}
	
	
	// DAOs AUXILIARES
	
	@SuppressWarnings("unchecked")
	public List<String> allTokens() {
		
		List<String> tokens = new ArrayList<>();
		
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		String jpql = "Select u from Usuario u";
		
		Query query = em.createQuery(jpql);
		List<Usuario> usuarios = query.getResultList();
		usuarios.stream().forEach(e->tokens.add(e.getToken()));
		
		em.getTransaction().commit();
		em.close();
//		new JPAUtil().closeEntityManager();
		
		return tokens;
	}
	
}
