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
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.codenation.centralerros.dao.UsuarioDAO;
import br.com.codenation.centralerros.model.Usuario;
import br.com.codenation.centralerros.model.utils.UsuariosWrapper;
import br.com.codenation.centralerros.utils.ConvertUtil;

@Path("usuarios")
public class UsuarioResource {

	// Busca todos usuarios
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//	@Produces(MediaType.APPLICATION_XML)
	public Response findAll() {
		List<Usuario> usuarios = new UsuarioDAO().findAll();
//		UsuariosWrapper genericEntity = new UsuariosWrapper(usuarios);
		GenericEntity<List<Usuario>> genericEntity = new GenericEntity<List<Usuario>>(usuarios) {};
		return Response.ok(genericEntity).build();
	}

	// Busca usuario pelo id
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response findOne(@PathParam("id") Long id) {
		Usuario usuario = new UsuarioDAO().findOne(id);
		return Response.ok( usuario ).build();
	}

	// Salva novo usuario
	@POST
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response save(Usuario usuario) {
		new UsuarioDAO().save(usuario);
		URI uri = URI.create("/usuarios/" + usuario.getId());
		return Response.created(uri).build();
	}

	// Atualiza usuario
	@PUT
	@Path("{id}")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response update(String content, @PathParam("id") Long id) {
		Usuario usuario = ConvertUtil.toObject(content, Usuario.class);
		new UsuarioDAO().update(id, usuario);
		return Response.noContent().build();
	}

	// Remove usuario
	@Path("{id}")
	@DELETE
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response delete(@PathParam("id") Long id) {
		new UsuarioDAO().delete(id);
		return Response.noContent().build();
	}
}
