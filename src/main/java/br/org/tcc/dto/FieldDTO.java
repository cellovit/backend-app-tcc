package br.org.tcc.dto;

import java.io.Serializable;

public class FieldDTO implements Serializable {

	private static final long serialVersionUID = -885734237176800477L;

	private String id;
	private String type;

	public FieldDTO() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
