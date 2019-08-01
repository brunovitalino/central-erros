package br.com.codenation.centralerros.utils;

import br.com.codenation.centralerros.dao.UsuarioDAO;
import br.com.codenation.centralerros.model.Usuario;

public class DBFillUtil {
	
	UsuarioDAO usuarioDAO = new UsuarioDAO();
	
	public void fillUsuarios() {
		Usuario usuario = new Usuario();
		usuario.setToken("KUsx9iJWOAo9tmuYU1LErzUdS8XM46vPmS5cCWma");
		usuario.setNome("Administrador");
		usuario.setEmail("admin@admin.com");
		usuario.setPassword("123456");
		usuarioDAO.save(usuario);
	}
	
}
