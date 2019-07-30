package br.com.codenation.centralerros.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public class ServidorResource {

	@Path("status")
	@GET
	public String status() {
		return "ok";
	}
}
