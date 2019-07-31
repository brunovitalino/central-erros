package br.com.codenation.centralerros;

import org.junit.Assert;
import org.junit.Test;

import br.com.codenation.centralerros.model.Usuario;
import br.com.codenation.centralerros.utils.Convert;

public class UsuarioTest extends BaseTests {
	
	@Test
	public void testaQueBuscarUmUsuarioRetornaOUsuarioEsperado() {
		String conteudo = getRequest("/usuario").get(String.class);
		Usuario usuario = (Usuario) Convert.fromXMLtoObject(conteudo);
		Assert.assertEquals("abc3de", usuario.getToken());
	}

}