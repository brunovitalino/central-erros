package br.com.codenation.centralerros;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;

public class BaseTests {

	private final String CLIENT_URI = "http://localhost:8080";
	protected static Client client;
	
	protected Builder doRequest(String resourceAction) {
		if (client==null) client = ClientBuilder.newClient();
		WebTarget target = client.target(CLIENT_URI);
		Builder request = target.path(resourceAction).request();
		return request;
	}

}
