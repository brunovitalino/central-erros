package br.com.codenation.centralerros.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String token;
	private String nome;
	private String email;
	private String password;
	private Timestamp dataCadastro;
	private Timestamp dataAtualizacao;
	
	public Usuario() {
		this.dataCadastro = this.dataAtualizacao = new Timestamp(System.currentTimeMillis());
	}
	
	public Usuario(String token, String nome, String email, String password) {
		this.token = token;
		this.nome = nome;
		this.email = email;
		this.password = password;
		this.dataCadastro = this.dataAtualizacao = new Timestamp(System.currentTimeMillis());
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
	public Timestamp getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Timestamp dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public Timestamp getDataAtualizacao() {
		return dataAtualizacao;
	}
	public void setDataAtualizacao(Timestamp dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
}
