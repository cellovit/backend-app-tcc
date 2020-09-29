package br.org.tcc.utils;

import javax.enterprise.context.ApplicationScoped;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import br.org.tcc.dto.DatastoreSearchResultDTO;

@ApplicationScoped
public class DatasetUtils {

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

}
