package br.com.codenation.centralerros.model.utils;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonValue;

import br.com.codenation.centralerros.model.Usuario;

@XmlRootElement(name="usuarioes")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsuariosWrapper {
//  @XmlElementWrapper(name="usuarios")
	@XmlElement(name="usuario")
//	@XmlAnyElement(lax = true)
	@JsonValue
	private List<Usuario> usuarios;
	
	public UsuariosWrapper() {}
	
	public UsuariosWrapper(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}
}
