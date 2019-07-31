package br.com.codenation.centralerros;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;

public class BaseTests {

	private static final String CLIENT_URI = "http://localhost:8080";

	protected static Builder getRequest(String acao) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(CLIENT_URI);
		Builder request = target.path(acao).request();
		return request;
	}

}
