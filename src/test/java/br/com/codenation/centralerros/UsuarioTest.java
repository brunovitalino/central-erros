package br.com.codenation.centralerros;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.URI;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Test;

import br.com.codenation.centralerros.model.Usuario;
import br.com.codenation.centralerros.utils.ConvertUtil;

public class UsuarioTest extends BaseTests {
	
	@Test
	public void testaQueBuscarUmUsuarioRetornaOUsuarioEsperado() {
		String conteudo = getRequest("/usuarios/1").get(String.class);
		Usuario usuario = (Usuario) ConvertUtil.fromXMLtoObject(conteudo);
		Assert.assertEquals("KUsx9iJWOAo9tmuYU1LErzUdS8XM46vPmS5cCWma", usuario.getToken());
	}
	
	@Test
	public void testaQueBuscarUmUsuarioRetornaUmaListaDeUsuarios() {
		String conteudo = getRequest("/usuarios").get(String.class);
		Assert.assertTrue(ConvertUtil.fromXMLtoObject(conteudo) instanceof List);
	}
	
	@Test
	public void testaSalvarNovoUsuario() {
		Usuario usuario = new Usuario();
		usuario.setNome("Teste");
		usuario.setEmail("teste@teste.com");
		usuario.setPassword("123456");
		String conteudoTeste1 = ConvertUtil.fromObjectToXML(usuario);
		
		Entity<String> entity = Entity.entity(conteudoTeste1, MediaType.APPLICATION_XML);
		Response response = getRequest("/usuarios").post(entity, Response.class);
		assertEquals(201, response.getStatus());
		
		URI location = response.getLocation();
		String conteudoTeste2 = getRequest(location.getPath()).get(String.class);
		assertTrue(conteudoTeste2.contains("teste@teste.com"));
	}

}