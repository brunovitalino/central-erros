package br.com.codenation.centralerros.utils;

import java.util.Collection;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

public class ConvertUtil {

	private static XStream xStream;
	private static Gson gson;

	
	private static XStream getXStreamInstance() {
		if (xStream == null) {
			xStream = new XStream();
			configurarSegurancaDoXStream();
		}
		return xStream;
	}
	
	public static Object fromXMLtoObject(String conteudo) {
		return getXStreamInstance().fromXML(conteudo);
	}
	
	public static String fromObjectToXML(Object objeto) {
		return getXStreamInstance().toXML(objeto);
	}

	private static void configurarSegurancaDoXStream() {
		// Remove permissoes existentes
		xStream.addPermission(NoTypePermission.NONE);
		// Adiciona algumas permissoes basicas
		xStream.addPermission(NullPermission.NULL);
		xStream.addPermission(PrimitiveTypePermission.PRIMITIVES);
		xStream.allowTypeHierarchy(Collection.class);
		// Adiciona sua propria permissao
		xStream.allowTypesByWildcard(new String[] {"br.com.codenation.centralerros.model.**"});
	}

	
	private static Gson getGsonInstance() {
		if (gson == null) {
			gson = new Gson();
		}
		return gson;
	}
	
	public static Object fromJsonToObject(String conteudo, Class<?> tipoObjeto) {
		return gson.fromJson(conteudo, tipoObjeto);
	}
	
	public static String fromObjectToJson(Object object) {
		return getGsonInstance().toJson(object);
	}
	
}
