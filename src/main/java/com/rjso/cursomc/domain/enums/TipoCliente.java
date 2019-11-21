package com.rjso.cursomc.domain.enums;

public enum TipoCliente {
	
	PESSOAFISICA(0, "Pessoa Física"),
	PESSOAJURIDICA(1, "Pessoa Jurídica");
	
	private Integer codigo;
	private String descricao;
	
	private TipoCliente(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static TipoCliente toEnum(Integer codigo) {
		
		if (codigo == null) {
			return null;
		}
		
		for (TipoCliente tc : TipoCliente.values()) {
			if (codigo.equals(tc.getCodigo())) {
				return tc;
			}
		}
		
		throw new IllegalArgumentException("Id do enum TipoCliente inválido: " + codigo);
	}

}
