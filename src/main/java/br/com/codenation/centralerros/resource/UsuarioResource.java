package br.com.codenation.centralerros.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.codenation.centralerros.dao.UsuarioDAO;
import br.com.codenation.centralerros.model.Usuario;
import br.com.codenation.centralerros.utils.ConvertUtil;

@Path("usuario")
public class UsuarioResource {

	@Path("new")
	@GET
	@Produces(MediaType.APPLICATION_ATOM_XML)
	public String adiciona() {
		Usuario usuario = new UsuarioDAO().popula();
		return ConvertUtil.fromObjectToXML(usuario);
	}

	@GET
	@Produces(MediaType.APPLICATION_ATOM_XML)
	public String busca() {
		Usuario usuario = new UsuarioDAO().buscaMemoria(1l);
		return ConvertUtil.fromObjectToXML(usuario);
	}

	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_ATOM_XML)
	public String busca(@PathParam("id") Long id) {
		Usuario usuario = new UsuarioDAO().find(id);
		return ConvertUtil.fromObjectToXML(usuario);
	}
}
