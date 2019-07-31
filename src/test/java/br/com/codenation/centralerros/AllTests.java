package br.com.codenation.centralerros;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ServidorTest.class, UsuarioTest.class })
public class AllTests {
	
	@BeforeClass
	public static void init() {
		Servidor.inicializaServidor();
	}

	@AfterClass
	public static void finish() {
		Servidor.finalizaServidor();
	}
	
}
