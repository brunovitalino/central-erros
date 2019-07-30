package br.com.codenation.centralerros;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;

import org.junit.Assert;
import org.junit.Test;

public class ServidorTest extends BaseTest {
	
	private String clientUri = getClientURI();

	@Test
	public void testaQueAConexaoComOServidorFunciona() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(clientUri);
		Builder request = target.path("/status").request();
		String conteudo = request.get(String.class);
		Assert.assertEquals("ok", conteudo);
	}
}