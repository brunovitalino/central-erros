package br.com.codenation.centralerros;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class Servidor {
	
	private static HttpServer server;

	public static void main(String[] args) throws IOException {
		inicializaServidor();
		System.out.println("Servidor rodando...");
		System.in.read();
		finalizaServidor();
		System.out.println("Servidor parado.");
	}

	private static void getInstance() {
		URI serverUri = URI.create("http://localhost:8080");
		ResourceConfig config = new ResourceConfig().packages("br.com.codenation.centralerros.resource");
		server = GrizzlyHttpServerFactory.createHttpServer(serverUri, config);
	}
	
	public static void inicializaServidor() {
		if (server == null)	getInstance();
	}

	@SuppressWarnings("deprecation")
	public static void finalizaServidor() {
		if (server != null)	server.stop();
	}
}
