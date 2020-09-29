package br.org.tcc.utils;

import br.org.tcc.dto.DespesaDTO;
import br.org.tcc.dto.RecordDTO;

public final class RecordFactory {

	public RecordFactory() {
		throw new IllegalStateException("Utility class");
	}

	public static RecordDTO create(String categoria) {

		switch (categoria) {

		case "despesas": {
			return new DespesaDTO();
		}

		default:
			return null;

		}

	}

}
