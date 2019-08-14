package br.com.codenation.centralerros;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.URI;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.codenation.centralerros.model.Usuario;
import br.com.codenation.centralerros.utils.ConvertUtil;

public class UsuarioTest extends BaseTests {
	
	private Usuario usuario;

	@BeforeClass
	public static void init() {
		Servidor.inicializaServidor();
	}

	@AfterClass
	public static void finish() {
		Servidor.finalizaServidor();
	}
	
	@Before
	public void setup() {
		usuario = new Usuario("TokenTeste", "Teste", "teste@teste.com", "123456");
	}
	
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
		usuario.setNome("Teste Salvar");
		String usuarioXML = ConvertUtil.fromObjectToXML(usuario);
		
		Entity<String> entity = Entity.entity(usuarioXML, MediaType.APPLICATION_XML);
		Response response = getRequest("/usuarios").post(entity, Response.class);
		assertEquals(201, response.getStatus());
		
		URI location = response.getLocation();
		String usuarioXMLSalvo = getRequest(location.getPath()).get(String.class);
		assertTrue(usuarioXMLSalvo.contains("Teste Salvar"));
	}
	
	@Test
	public void testaAtualizarUsuario() {
		usuario.setNome("Teste Atualizar");
		
		String usuarioXML = ConvertUtil.fromObjectToXML(usuario);
		Entity<String> entity = Entity.entity(usuarioXML, MediaType.APPLICATION_XML);
		
		Response response = getRequest("/usuarios").post(entity, Response.class);
		assertEquals(201, response.getStatus());
		
		URI location = response.getLocation();
		String usuarioXMLSalvo = getRequest( location.getPath() ).get(String.class);
		assertTrue(usuarioXMLSalvo.contains("Teste Atualizar"));
		
		
		usuario = (Usuario) ConvertUtil.fromXMLtoObject(usuarioXMLSalvo);
		usuario.setNome("Teste Atualizado");
		
		String usuarioXMLAtualizar = ConvertUtil.fromObjectToXML(usuario);
		entity = Entity.entity(usuarioXMLAtualizar, MediaType.APPLICATION_XML);
		
		Response responseAtualizada = getRequest( location.getPath() ).put(entity, Response.class);
		assertEquals(204, responseAtualizada.getStatus());
		
		String usuarioXMLAtualizado = getRequest( location.getPath() ).get(String.class);
		assertTrue(usuarioXMLAtualizado.contains("Teste Atualizado"));
	}
	
	@Test
	public void testaRemoverUsuario() {
		usuario.setToken("TokenTesteRemover");
		String usuarioXML = ConvertUtil.fromObjectToXML(usuario);
		
		Entity<String> entity = Entity.entity(usuarioXML, MediaType.APPLICATION_XML);
		Response response = getRequest("/usuarios").post(entity, Response.class);
		assertEquals(201, response.getStatus());
		
		URI location = response.getLocation();
		response = getRequest( location.getPath() ).delete();
		assertEquals(204, response.getStatus());
	}
}