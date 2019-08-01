package br.com.codenation.centralerros.resource;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.codenation.centralerros.dao.UsuarioDAO;
import br.com.codenation.centralerros.model.Usuario;
import br.com.codenation.centralerros.utils.ConvertUtil;

@Path("usuario")
public class UsuarioResource {

	// Busca usuario pelo id
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String find(@PathParam("id") Long id) {
		Usuario usuario = new UsuarioDAO().find(id);
		return ConvertUtil.fromObjectToXML(usuario);
	}

	// Busca todos usuarios
	@Path("all")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String find() {
		List<Usuario> usuarios = new UsuarioDAO().findAll();
		return ConvertUtil.fromObjectToXML(usuarios);
	}

	// Salva um novo usuario
	@POST
	@Produces(MediaType.APPLICATION_XML)
	public String save(String conteudo) {
		Usuario usuario = (Usuario) ConvertUtil.fromXMLtoObject(conteudo);
		new UsuarioDAO().save(usuario);
		return "<status>Salvo com sucesso!</status>";
	}

	// Atualiza as informacoes de um usuario existente
	@PUT
	@Produces(MediaType.APPLICATION_XML)
	public String update(String conteudo) {
		Usuario usuario = (Usuario) ConvertUtil.fromXMLtoObject(conteudo);
		new UsuarioDAO().update(usuario);
		return "<status>Atualizado com sucesso!</status>";
	}
	
	@Path("{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_XML)
	public String remove(@PathParam("id") Long id) {
		new UsuarioDAO().remove(id);
		return "<status>Removido com sucesso!</status>";
	}
}
