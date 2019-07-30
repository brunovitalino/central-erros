package br.com.codenation.centralerros.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.codenation.centralerros.dao.UsuarioDAO;
import br.com.codenation.centralerros.model.Usuario;
import br.com.codenation.centralerros.utils.Convert;

@Path("/usuario")
public class UsuarioResource {

	@GET
	@Produces(MediaType.APPLICATION_ATOM_XML)
	public String busca() {
		Usuario usuario = new UsuarioDAO().busca(1l);
		return Convert.fromObjectToXML(usuario);
	}
}
