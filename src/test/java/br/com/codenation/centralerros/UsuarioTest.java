package br.com.codenation.centralerros;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.codenation.centralerros.model.Usuario;
import br.com.codenation.centralerros.model.utils.UsuariosWrapper;
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
		usuario = new Usuario("TestToken", "TestName", "teste@teste.com", "123456");
	}
	
	@Test
	public void testaQueBuscarUmUsuarioRetornaUmaListaDeUsuarios() {
//		UsuariosWrapper usuariosWrapper = doRequest("/usuarios").get(UsuariosWrapper.class);
		//Usuario[] usuariosArray = doRequest("/usuarios").accept(MediaType.APPLICATION_XML).get(Usuario[].class);
		
		// TESTA XML
		String content = doRequest("/usuarios").accept(MediaType.APPLICATION_XML).get(String.class);
		UsuariosWrapper usuariosWrapper = ConvertUtil.toObject(content, UsuariosWrapper.class);
		List<Usuario> usuarios = usuariosWrapper.getUsuarios();
		Assert.assertTrue(usuarios.get(0).getToken().equals("KUsx9iJWOAo9tmuYU1LErzUdS8XM46vPmS5cCWma"));

		// TESTA JSON
		content = doRequest("/usuarios").accept(MediaType.APPLICATION_JSON).get(String.class);
		Usuario[] usuariosArray = ConvertUtil.toObject(content, Usuario[].class);
		usuarios = Arrays.asList(usuariosArray);
		Assert.assertTrue(usuarios.get(0).getToken().equals("KUsx9iJWOAo9tmuYU1LErzUdS8XM46vPmS5cCWma"));
	}
	
	@Test
	public void testaQueBuscarUmUsuarioRetornaOUsuarioEsperado() {
		// TESTA XML
		//Usuario usuario = doRequest("/usuarios/1").accept(MediaType.APPLICATION_XML).get(Usuario.class);
		String content = doRequest("/usuarios/1").accept(MediaType.APPLICATION_XML).get(String.class);
		Usuario usuario = ConvertUtil.toObject(content, Usuario.class);
		Assert.assertEquals("KUsx9iJWOAo9tmuYU1LErzUdS8XM46vPmS5cCWma", usuario.getToken());
		
		// TESTA JSON
		content = doRequest("/usuarios/1").accept(MediaType.APPLICATION_JSON).get(String.class);
		usuario = (Usuario) ConvertUtil.toObject(content, Usuario.class);
		Assert.assertEquals("KUsx9iJWOAo9tmuYU1LErzUdS8XM46vPmS5cCWma", usuario.getToken());
	}
	
	@Test
	public void testaSalvarNovoUsuario() {
		// TESTA XML
		usuario.setNome("Teste Salvar XML");
		String usuarioXML = ConvertUtil.toXML(usuario);
		
		Entity<String> entity = Entity.entity(usuarioXML, MediaType.APPLICATION_XML);
		Response response = doRequest("/usuarios").post(entity, Response.class);
		assertEquals(201, response.getStatus());
		
		URI location = response.getLocation();
		
		String usuarioXMLSalvo = doRequest(location.getPath()).get(String.class);
		assertTrue(usuarioXMLSalvo.contains("Teste Salvar XML"));
		
		// TESTA JSON
		usuario.setNome("Teste Salvar JSON");
		String usuarioJSON = ConvertUtil.toJson(usuario);
		
		entity = Entity.entity(usuarioJSON, MediaType.APPLICATION_JSON);
		response = doRequest("/usuarios").post(entity, Response.class);
		assertEquals(201, response.getStatus());
		
		location = response.getLocation();
		
		String usuarioJSONSalvo = doRequest(location.getPath()).get(String.class);
		assertTrue(usuarioJSONSalvo.contains("Teste Salvar JSON"));
	}
	
	@Test
	public void testaAtualizarUsuario() {
		Usuario usuario = this.usuario;
		usuario.setNome("Teste Desatualizado");
		String usuarioOld = ConvertUtil.toXML(usuario);
		
		Entity<String> entity = Entity.entity(usuarioOld, MediaType.APPLICATION_XML);
		Response response = doRequest("/usuarios").post(entity, Response.class);
		
		assertEquals(201, response.getStatus());
		assertTrue(usuarioOld.contains("Teste Desatualizado"));
		
		URI location = response.getLocation();
		
		// TESTA XML
		String usuarioXML = doRequest( location.getPath() ).accept(MediaType.APPLICATION_XML).get(String.class);
		usuario = ConvertUtil.toObject(usuarioXML, Usuario.class);
		usuario.setNome("Teste Atualizado XML");
		
		entity = Entity.entity( ConvertUtil.toXML(usuario) , MediaType.APPLICATION_XML );
		response = doRequest( location.getPath() ).put(entity, Response.class);
		
		assertEquals(204, response.getStatus());
		String usuarioXMLAtualizado = doRequest( location.getPath() ).get(String.class);
		assertTrue(usuarioXMLAtualizado.contains("Teste Atualizado XML"));
		
		// TESTA JSON
		String usuarioJSON = doRequest( location.getPath() ).accept(MediaType.APPLICATION_JSON).get(String.class);
		usuario = ConvertUtil.toObject(usuarioJSON, Usuario.class);
		usuario.setNome("Teste Atualizado JSON");
		
		entity = Entity.entity( ConvertUtil.toJson(usuario) , MediaType.APPLICATION_JSON );
		response = doRequest( location.getPath() ).put(entity, Response.class);
		
		assertEquals(204, response.getStatus());
		String usuarioJSONAtualizado = doRequest( location.getPath() ).get(String.class);
		assertTrue(usuarioJSONAtualizado.contains("Teste Atualizado JSON"));
	}
	
	@Test
	public void testaRemoverUsuario() {
		String usuario = ConvertUtil.toXML(this.usuario);
		
		Entity<String> entity = Entity.entity(usuario, MediaType.APPLICATION_XML);
		Response response = doRequest("/usuarios").post(entity, Response.class);
		assertEquals(201, response.getStatus());
		
		URI location = response.getLocation();
		
		response = doRequest( location.getPath() ).delete();
		assertEquals(204, response.getStatus());
	}
}