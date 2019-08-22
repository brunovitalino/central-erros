package br.com.codenation.centralerros.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import br.com.codenation.centralerros.model.utils.CustomAdapterTimestamp;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String token;
	private String nome;
	private String email;
	private String password;
	
	@XmlElement(name = "data_cadastro")
    @XmlJavaTypeAdapter(CustomAdapterTimestamp.class)
	@JsonProperty(value="data_cadastro")
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss.SSS")
	private Timestamp dataCadastro;

	@XmlElement(name="data_atualizacao")
	@XmlJavaTypeAdapter(CustomAdapterTimestamp.class)
	@JsonProperty(value="data_atualizacao")
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss.SSS")
	private Timestamp dataAtualizacao;

	
	public Usuario() {}
	
	public Usuario(String token, String nome, String email, String password) {
		this.token = token;
		this.nome = nome;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Timestamp getDataAtualizacao() {
		return dataAtualizacao;
	}
	public void setDataAtualizacao(Timestamp dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
		
	public Timestamp getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Timestamp dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
}
