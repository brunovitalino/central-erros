package br.com.codenation.centralerros.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.core.GenericEntity;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.XStreamException;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import br.com.codenation.centralerros.model.Usuario;
import br.com.codenation.centralerros.model.utils.UsuariosWrapper;

public class ConvertUtil {

	private static XStream xStream;
	private static Gson gson;
	private static JAXBContext context;
	private static ObjectMapper mapper;

	
	// INSTANCIADORES
	
	private static void xStreamConfigSecurity() {
		// Remove permissoes existentes
		xStream.addPermission(NoTypePermission.NONE);
		// Adiciona algumas permissoes basicas
		xStream.addPermission(NullPermission.NULL);
		xStream.addPermission(PrimitiveTypePermission.PRIMITIVES);
		xStream.allowTypeHierarchy(Collection.class);
		// Adiciona sua propria permissao
		xStream.allowTypesByWildcard(new String[] {"br.com.codenation.centralerros.model.**"});
	}
	
	private static void xStreamConfigNames() {
		xStream.omitField(Usuario.class, "br.com.codenation.centralerros.model.Usuario");
	}
	
	public static void xStreamConfigPackagesAlias() {
		xStream.alias("usuario", Usuario.class);
//		xStream.alias("listona", JAXBList.class);
//		xStream.addImplicitArray(ArrayList.class, "lista");
//		xStream.addImplicitCollection(ArrayList.class, "usuarios", "usuario", Usuario.class);
//		xStream.addImplicitCollection(ArrayList.class, "usuarios", Usuario.class);
//		xStream.addImplicitArray(ArrayList.class, "usuarios", Usuario.class);
	}
	
	private static XStream getXStreamInstance() {
		if (xStream == null) {
			xStream = new XStream();
			xStreamConfigSecurity();
			xStreamConfigNames();
			xStreamConfigPackagesAlias();
		}
		return xStream;
	}
	
	private static Gson getGsonInstance() {
		if (gson == null) {
			GsonBuilder gb = new GsonBuilder();
			gb.setPrettyPrinting();
			gb.disableHtmlEscaping();
			gson = gb.create();
		}
		return gson;
	}
	
	private static ObjectMapper getJacksonInstance() {
		return (mapper==null ? new ObjectMapper() : mapper);
	}
	
	private static JAXBContext getJAXBInstance(Class<?> objectType) {
		// Se a instancia for um vetor e tivermos tentando unmarshaller uma Wrapper Class, ocorre erro.
		// Nao esta sendo possivel pegar o tipo da instancia quando nao e nulo, para tratar o erro.
//		if (context == null)
			try { context = JAXBContext.newInstance(objectType); } 
			catch (JAXBException e) { e.printStackTrace(); }
//		else
//			(context.getInst)
		return context;
	}
	
	
	// CONVERSORES
	
	public static String toJson(Object object) {
		try { return getJacksonInstance().writeValueAsString(object); }
		catch (JsonProcessingException e) {	e.printStackTrace(); return null; }
	}
	
	public static String toXML(Object contentObject) {
		try {
			Marshaller marshaller = getJAXBInstance(contentObject.getClass()).createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			//JAXBElement<T> objetoPersonalizado = 
			//		new JAXBElement<T>(new QName("nome_personalizado"), (Class<T>) objeto.getClass(), objeto);
			ByteArrayOutputStream contentBytes = new ByteArrayOutputStream();
			marshaller.marshal(contentObject, contentBytes);
			return contentBytes.toString();
		}catch (JAXBException e) {e.printStackTrace();return null;}
	}
	
//	public static <T> List<T> parseJsonArray(String json, Class<T> classOnWhichArrayIsDefined) {
//		try {
//			ObjectMapper mapper = new ObjectMapper();
//			Class<T[]> arrayClass = 
//					arrayClass = (Class<T[]>) Class.forName("[L" + classOnWhichArrayIsDefined.getName() + ";");
//			T[] objects = mapper.readValue(json, arrayClass);
//			return Arrays.asList(objects);
//		}catch (ClassNotFoundException | IOException e) {e.printStackTrace(); return null;}
//	}
	
	@SuppressWarnings("unchecked")
	public static <T> T toObject(String content, Class<T> objectType) {
		try {
			//return mapper.readValue(content, new TypeReference<List<Usuario>>(){});
			//CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(List.class, Usuario.class);
			//return mapper.readValue(content, collectionType);
			return getJacksonInstance().readValue(content.getBytes(), objectType);
		}catch (IOException e) {
			try { return (T) getXStreamInstance().fromXML(content); }
			catch (NullPointerException | XStreamException e2) {
				try {
					Unmarshaller um = getJAXBInstance(objectType).createUnmarshaller();
					ByteArrayInputStream conteudoBytes = new ByteArrayInputStream(content.getBytes());
					return (T) um.unmarshal(conteudoBytes);
				}catch (JAXBException e3) {
					System.out.println("Convert Exception 1: " + e.toString());
					System.out.println("Convert Exception 2: " + e2.toString());
					System.out.println("Convert Exception 3: " + e3.toString());
					return null;
				}
			}
		}
	}
	
}
