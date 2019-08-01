package br.com.codenation.centralerros;

import org.junit.Assert;
import org.junit.Test;

import br.com.codenation.centralerros.model.Usuario;
import br.com.codenation.centralerros.utils.ConvertUtil;

public class UsuarioTest extends BaseTests {
	
	@Test
	public void testaQueBuscarUmUsuarioRetornaOUsuarioEsperado() {
		String conteudo = getRequest("/usuario").get(String.class);
		Usuario usuario = (Usuario) ConvertUtil.fromXMLtoObject(conteudo);
		Assert.assertEquals("abc3de", usuario.getToken());
	}

}