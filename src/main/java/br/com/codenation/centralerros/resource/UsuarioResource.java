package br.com.codenation.centralerros.resource;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.codenation.centralerros.dao.UsuarioDAO;
import br.com.codenation.centralerros.model.Usuario;
import br.com.codenation.centralerros.utils.ConvertUtil;

@Path("usuarios")
public class UsuarioResource {

	// Busca usuario pelo id
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response find(@PathParam("id") Long id) {
		Usuario usuario = new UsuarioDAO().find(id);
		String conteudo = ConvertUtil.fromObjectToXML(usuario);
		return Response.ok(conteudo).build();
	}

	// Busca todos usuarios
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response find() {
		List<Usuario> usuarios = new UsuarioDAO().findAll();
		String conteudo = ConvertUtil.fromObjectToXML(usuarios);
		return Response.ok(conteudo).build();
	}

	// Salva novo usuario
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response save(String conteudo) {
		Usuario usuario = (Usuario) ConvertUtil.fromXMLtoObject(conteudo);
		new UsuarioDAO().save(usuario);
		URI uri = URI.create("/usuario/" + usuario.getId());
		return Response.created(uri).build();
	}

	// Atualiza usuario
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response update(String conteudo) {
		Usuario usuario = (Usuario) ConvertUtil.fromXMLtoObject(conteudo);
		new UsuarioDAO().update(usuario);
		return Response.noContent().build();
	}

	// Remove usuario
	@Path("{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_XML)
	public Response remove(@PathParam("id") Long id) {
		new UsuarioDAO().remove(id);
		return Response.noContent().build();
	}
}
