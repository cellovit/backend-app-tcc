package br.org.tcc.dto;

import java.io.Serializable;

public class DatastoreResponseDTO implements Serializable {

	private static final long serialVersionUID = 3671426885669119867L;

	private DatastoreSearchResultDTO result;
	private String help;
	private boolean success;

	public DatastoreResponseDTO() {

	}

	public DatastoreSearchResultDTO getResult() {
		return result;
	}

	public void setResult(DatastoreSearchResultDTO result) {
		this.result = result;
	}

	public String getHelp() {
		return help;
	}

	public void setHelp(String help) {
		this.help = help;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
