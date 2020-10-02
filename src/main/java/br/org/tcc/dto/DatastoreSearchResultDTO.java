package br.org.tcc.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DatastoreSearchResultDTO implements Serializable {

	private static final long serialVersionUID = 8549618190763614715L;

//    private boolean includeTotal;
//    private boolean distinct;
//    private boolean plain;
//    private Records records;
//    private Integer total;

	@JsonProperty("include_total")
	private boolean includeTotal;
	@JsonProperty("resource_id")
	private String resourceId;
	private boolean distinct;
	private String sort;
	private List<Object> records;
	private List<FieldDTO> fields;
	private Integer total;
	@JsonProperty("records_format")
	private String recordsFormat;

	public boolean isIncludeTotal() {
		return includeTotal;
	}

	public void setIncludeTotal(boolean includeTotal) {
		this.includeTotal = includeTotal;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public boolean isDistinct() {
		return distinct;
	}

	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	public List<Object> getRecords() {
		return records;
	}

	public void setRecords(List<Object> records) {
		this.records = records;
	}

	public List<FieldDTO> getFields() {
		return fields;
	}

	public void setFields(List<FieldDTO> fields) {
		this.fields = fields;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getRecordsFormat() {
		return recordsFormat;
	}

	public void setRecordsFormat(String recordsFormat) {
		this.recordsFormat = recordsFormat;
	}

}
