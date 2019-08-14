package br.com.codenation.centralerros;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class Servidor {
	
	private static final String ENDERECO = "http://localhost:8080";
	private static final String PACOTE_DOS_SERVICOS = "br.com.codenation.centralerros.resource";
	private static HttpServer server;

	public static void main(String[] args) throws IOException {
		inicializaServidor();
		System.out.println("Servidor rodando...");
		System.in.read();
		finalizaServidor();
		System.out.println("Servidor parado.");
	}
	
	public static void inicializaServidor() {
		if (server == null) {
			URI serverUri = URI.create(ENDERECO);
			ResourceConfig config = new ResourceConfig().packages(PACOTE_DOS_SERVICOS);
			server = GrizzlyHttpServerFactory.createHttpServer(serverUri, config);
		}
	}

	@SuppressWarnings("deprecation")
	public static void finalizaServidor() {
		if (server != null)	server.stop();
	}
}
