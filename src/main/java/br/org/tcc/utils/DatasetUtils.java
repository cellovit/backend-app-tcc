package br.org.tcc.utils;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

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
		request.setLimit(499);
		request.setResource_id(datasetResource.getResourceId());

		return request;
	}

	public ColumnType categorizaColuna(String json, String columnName)
			throws JsonMappingException, JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();

		JsonNode root = mapper.readTree(json);
		JsonNode nodeValue = root.get(columnName);

		if (nodeValue.isNumber() || isNumeric((nodeValue.asText().replaceAll(",", ".")))) {
			// System.out.println("NUMERICAL");
			return ColumnType.Numerical;
		} else if (hasTypeDate(nodeValue.toString())) {
			// System.out.println("DATE");
			return ColumnType.Date;
		} else if (nodeValue.isTextual()) {
			// System.out.println("CATEGORICAL");
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

	private String JsonGroupByPropertyAndSum() throws Exception {
		String jsonString = "{\"code\": \"0\",\"data\": [{\"chrDesigName\": \"Developer\",\"chrempName\": \"Test Employee1\"},{\"chrDesigName\": \"Developer\",\"chrempName\": \"Test Employee2\"},{\"chrDesigName\": \"Tester\",\"chrempName\": \"Test Employee3\"},{\"chrDesigName\": \"Analyst\",\"chrempName\": \"Test Employee4\"},{\"chrDesigName\": \"Developer\",\"chrempName\": \"Test Employee5\"},{\"chrDesigName\": \"Tester\",\"chrempName\": \"Test Employee6\"}]}";

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

	public Set<JsonPrimitive> JsonGetColumnValues(String jsonObjectsString, String groupByKey) throws Exception {

		JsonObject fullResponse = new JsonParser().parse(jsonObjectsString).getAsJsonObject();
		JsonArray dataArray = fullResponse.getAsJsonObject("result").get("records").getAsJsonArray();

		Set<JsonPrimitive> columnValues = new HashSet<>();

		dataArray.forEach(x -> {

			JsonObject jsonObject = x.getAsJsonObject();
			columnValues.add(jsonObject.get(groupByKey).getAsJsonPrimitive());

		});

		return columnValues;

	}

	private static boolean hasTypeDate(String stringFromTxt) {
		try {
			DATE_TIME_FORMAT.parse(stringFromTxt);
			return true;
		} catch (DateTimeParseException dtpe) {
			return false;
		}
	}

	public boolean isNumeric(String strNum) {
		Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

		if (strNum == null) {
			return false;
		}
		return pattern.matcher(strNum).matches();
	}

}
