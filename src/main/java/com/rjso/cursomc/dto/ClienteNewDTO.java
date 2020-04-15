package com.rjso.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.rjso.cursomc.services.validation.ClienteInsert;

@ClienteInsert
public class ClienteNewDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "O campo 'nome' é de preenchimento obrigatório.")
	@Length(min = 5, max = 120, message = "Nome deve ter de 5 a 120 caracteres.")
	private String nome;
	
	@NotEmpty(message = "O campo 'email' é de preenchimento obrigatório.")
	@Email(message = "Email inválido")
	private String email;
	
	@NotEmpty(message = "O campo 'cpf/cnpj' é de preenchimento obrigatório.")
	private String cpfOuCnpj;
	
	private Integer tipoCliente;
	
	private String senha;
	
	@NotEmpty(message = "O campo 'logradouro' é de preenchimento obrigatório.")
	private String logradouro;
	
	@NotEmpty(message = "O campo 'número' é de preenchimento obrigatório.")
	private String numero;
	
	private String complemento;
	private String bairro;
	
	@NotEmpty(message = "O campo 'cep' é de preenchimento obrigatório.")
	private String cep;
	
	private Integer cidadeId;
	
	@NotEmpty(message = "O campo 'telefone' é de preenchimento obrigatório.")
	private String telefone1;
	
	private String telefone2;
	private String telefone3;
	
	public ClienteNewDTO() {
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

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public Integer getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(Integer tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	
	public String getSenha() {
		return senha;
	}

	@NotEmpty(message = "O campo 'senha' é de preenchimento obrigatório.")
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Integer getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getTelefone3() {
		return telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}
}
