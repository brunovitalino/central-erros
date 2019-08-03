package br.com.codenation.centralerros.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.codenation.centralerros.utils.DBFillUtil;

@Path("/")
public class ServidorResource {

	@Path("status")
	@GET
	public String status() {
		return "ok";
	}
	
	// Popula o banco de dados com todas entidades necessarias
	@Path("dbfill")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String adiciona() {
		new DBFillUtil().fillUsuarios();
		return "<status>Banco de dados preenchido com sucesso!</status>";
	}
}
