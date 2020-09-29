package br.org.tcc.dto;

import java.io.Serializable;

public class DatastoreRequestDTO implements Serializable {

	private static final long serialVersionUID = 3849729457158500236L;

	private String resource_id;
	private int limit;
	private String[] fields;
	private String sort;
	private String distinct;

	public DatastoreRequestDTO() {

	}

	public String getResource_id() {
		return resource_id;
	}

	public void setResource_id(String resource_id) {
		this.resource_id = resource_id;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String[] getFields() {
		return fields;
	}

	public void setFields(String[] fields) {
		this.fields = fields;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getDistinct() {
		return distinct;
	}

	public void setDistinct(String distinct) {
		this.distinct = distinct;
	}

}
