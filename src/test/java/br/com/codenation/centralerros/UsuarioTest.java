package br.com.codenation.centralerros;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.Assert;
import org.junit.Test;

import br.com.codenation.centralerros.model.Usuario;
import br.com.codenation.centralerros.utils.Convert;

public class UsuarioTest extends BaseTest {
	
	private String clientUri = getClientURI();

	@Test
	public void testaQueBuscarUmUsuarioRetornaOUsuarioEsperado() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(clientUri);
		String conteudo = target.path("/usuario").request().get(String.class);
		Usuario usuario = (Usuario) Convert.fromXMLtoObject(conteudo);
		Assert.assertEquals("abc3de", usuario.getToken());
	}

}