package br.com.codenation.centralerros.resource;

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

	// Popula o banco
	@Path("popula")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String adiciona() {
		Usuario usuario = new UsuarioDAO().popula();
		return ConvertUtil.fromObjectToXML(usuario);
	}

	// Busca usuario pelo id
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String find(@PathParam("id") Long id) {
		Usuario usuario = new UsuarioDAO().find(id);
		return ConvertUtil.fromObjectToXML(usuario);
	}

	// Salva um novo usuario
	@POST
	@Produces(MediaType.APPLICATION_XML)
	public String save(String conteudo) {
		Usuario usuario = (Usuario) ConvertUtil.fromXMLtoObject(conteudo);
		new UsuarioDAO().save(usuario);
		return "sucesso";
	}

	// Atualiza as informacoes de um usuario existente
	@PUT
	@Produces(MediaType.APPLICATION_XML)
	public String update(String conteudo) {
		Usuario usuario = (Usuario) ConvertUtil.fromXMLtoObject(conteudo);
		new UsuarioDAO().update(usuario);
		return "sucesso";
	}
}
