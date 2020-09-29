package br.org.tcc.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import br.org.tcc.dto.DatastoreRequestDTO;
import br.org.tcc.dto.DatastoreSearchResultDTO;
import br.org.tcc.dto.RecordDTO;
import br.org.tcc.dto.DespesaDTO;
import br.org.tcc.entity.DatasetResource;
import br.org.tcc.repository.DatasetResourceRepository;

@ApplicationScoped
public class DatasetUtils {
	
	@Inject
	DatasetResourceRepository datasetRepository;
	
	private final static String MSG_ERRO_STRING = "Não foi possivel encontrar o objeto com os parâmetros definidos";

	public DatastoreSearchResultDTO convertToDto(String json) throws JsonMappingException, JsonProcessingException {
		
		DatastoreSearchResultDTO dto = new DatastoreSearchResultDTO();

		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(json);
		JsonNode result = root.get("result");
		
		
		// fields
		JsonNode fields = mapper.readTree("fields");
		
		fields.forEach(field -> {
			
		});
		
		// records
		JsonNode records = result.get("records");
		
		
		
		// System.out.println(node1);
		
		return dto;
	}
	
	public DatastoreRequestDTO prepareDatasetSearchRequest(String categoria, int exercicio) {
		
		DatasetResource datasetResource = this.datasetRepository.getByCategoriaAndExercicio(categoria, exercicio).orElseThrow(() -> new NoResultException(MSG_ERRO_STRING));
		
		DatastoreRequestDTO request = new DatastoreRequestDTO();

		request.setDistinct("true");
		request.setLimit(100);
		request.setResource_id(datasetResource.getResourceId());
		
		return request;
	}
	
	public List<RecordDTO> resolveRecordType(String categoria, List<Object> records) {
		
		 return records.stream().map(record -> RecordFactory.create(categoria)).collect(Collectors.toList());
		
		 // RecordFactory.create(categoria);
		
	}
	
	public List<DespesaDTO> resolveRecordType(List<Object> records) {
		return null;
		
		
	}

}
