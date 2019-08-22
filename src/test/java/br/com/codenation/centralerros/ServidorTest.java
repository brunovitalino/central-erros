package br.com.codenation.centralerros;

import org.junit.Assert;
import org.junit.Test;

public class ServidorTest extends BaseTests {
	
	@Test
	public void testaQueAConexaoComOServidorFunciona() {
		String conteudo = doRequest("/status").get(String.class);
		Assert.assertEquals("ok", conteudo);
	}

}