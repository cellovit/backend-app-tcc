package br.org.tcc.utils;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Date;
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

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import br.org.tcc.dto.DatastoreRequestDTO;
import br.org.tcc.dto.DatastoreSearchResultDTO;
import br.org.tcc.dto.RecordDTO;
import br.org.tcc.entity.DatasetResource;
import br.org.tcc.enums.ColumnType;
import br.org.tcc.repository.DatasetResourceRepository;

@ApplicationScoped
public class DatasetUtils {

	@Inject
	DatasetResourceRepository datasetRepository;

	private final static String MSG_ERRO_STRING = "Não foi possivel encontrar o objeto com os parâmetros definidos";
	private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("YYYY/MM/DD")
			.withResolverStyle(ResolverStyle.STRICT);

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

		DatasetResource datasetResource = this.datasetRepository.getByCategoriaAndExercicio(categoria, exercicio)
				.orElseThrow(() -> new NoResultException(MSG_ERRO_STRING));

		DatastoreRequestDTO request = new DatastoreRequestDTO();

		request.setDistinct("true");
		request.setLimit(100);
		request.setResource_id(datasetResource.getResourceId());

		return request;
	}

	public ColumnType categorizaColuna(String json, String columnName)
			throws JsonMappingException, JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();

		JsonNode root = mapper.readTree(json);
		JsonNode nodeValue = root.get(columnName);

		if (nodeValue.isNumber()) {
			return ColumnType.Numerical;
		} else if (hasTypeDate(nodeValue.toString())) {
			return ColumnType.Date;
		} else if (nodeValue.isTextual()) {
			return ColumnType.Categorical;
		}

		return ColumnType.None;

	}	
	
	// https://stackoverflow.com/questions/39204438/grouping-json-response-with-keys-in-java-android-studio
	
	private String JsonGroupBy() throws Exception {
        String jsonString = "{\"code\": \"0\",\"data\": [{\"chrDesigName\": \"Developer\",\"chrempName\": \"Test Employee1\"},{\"chrDesigName\": \"Developer\",\"chrempName\": \"Test Employee2\"},{\"chrDesigName\": \"Tester\",\"chrempName\": \"Test Employee3\"},{\"chrDesigName\": \"Analyst\",\"chrempName\": \"Test Employee4\"},{\"chrDesigName\": \"Developer\",\"chrempName\": \"Test Employee5\"},{\"chrDesigName\": \"Tester\",\"chrempName\": \"Test Employee6\"}]}";

        ObjectMapper mapper = new ObjectMapper();

        // Map<String, Object> dataMap = mapper.readValue(jsonString,
        // new TypeReference<Map<String, Object>>() {
        // });
        //
        // System.out.println(dataMap.get("data") +
        // ((ArrayList)dataMap.get("data")).get(0).getClass().getName()) ;

        JsonArray dataArray = new JsonParser().parse(jsonString).getAsJsonObject().getAsJsonArray("data");

        HashMap<String, List<String>> designationsMap = new HashMap<String, List<String>>();

        for (JsonElement element : dataArray) {
        	
            String designation = element.getAsJsonObject().get("chrDesigName").getAsString(); // groupBy tag
            String empName = element.getAsJsonObject().get("chrempName").getAsString(); // sum value
            
            if (designationsMap.containsKey(designation)) {
                designationsMap.get(designation).add(empName);
            } else {
                ArrayList<String> emptyList = new ArrayList<String>();
                emptyList.add(empName);
                designationsMap.put(designation, emptyList);
            }
        }

        StringWriter result = new StringWriter();
        mapper.writeValue(result, designationsMap);
        return result.toString();

    }
	
	private String JsonGroupByCategoricalColumn(String jsonString, String groupByKey) throws Exception {
        
        ObjectMapper mapper = new ObjectMapper();

        
        JsonArray dataArray = new JsonParser().parse(jsonString).getAsJsonObject().getAsJsonArray("data");

        HashMap<String, List<String>> designationsMap = new HashMap<String, List<String>>();

        for (JsonElement element : dataArray) {
        	
            String designation = element.getAsJsonObject().get("chrDesigName").getAsString(); // groupBy tag
            String empName = element.getAsJsonObject().get("chrempName").getAsString(); // sum value
            
            if (designationsMap.containsKey(designation)) {
                designationsMap.get(designation).add(empName);
            } else {
                ArrayList<String> emptyList = new ArrayList<String>();
                emptyList.add(empName);
                designationsMap.put(designation, emptyList);
            }
        }

        StringWriter result = new StringWriter();
        mapper.writeValue(result, designationsMap);
        return result.toString();

    }
	
	private static boolean hasTypeDate(String stringFromTxt) {
		try {
			DATE_TIME_FORMAT.parse(stringFromTxt);
			return true;
		} catch (DateTimeParseException dtpe) {
			return false;
		}
	}

}
