package br.org.tcc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import br.org.tcc.dto.DatastoreRequestDTO;
import br.org.tcc.dto.DatastoreResponseDTO;
import br.org.tcc.enums.ChartType;
import br.org.tcc.enums.ColumnType;
import br.org.tcc.utils.DatasetUtils;

@ApplicationScoped
public class DatasetService {

	@Inject
	DatasetUtils datasetUtils;

	@Inject
	@RestClient
	DatasetRecifeService datasetRecifeService;

	public String getDatasetRecords(String categoriaDataset, int exercicio) {
		DatastoreRequestDTO request = this.datasetUtils.prepareDatasetSearchRequest(categoriaDataset, exercicio);

		try {

			return datasetRecifeService.getDatasetResult(request.getResource_id(), request.getLimit(),
					request.getDistinct());

		} catch (Exception ex) {
			System.out.println(ex.toString());
		}

		return null;
	}

	public List<ChartType> resolveChartTypes(String json, String xAxis, String yAxis)
			throws JsonMappingException, JsonProcessingException, Exception {

		String objectsString = json.toString();
		List<ChartType> chartList = new ArrayList<>();

		Map<ColumnType, List<String>> columnsList = this.categorizaColunasDataset(json);

		List<String> categoricalColumns = columnsList.get(ColumnType.Categorical);
		List<String> dateColumns = columnsList.get(ColumnType.Date);
		List<String> numericColumns = columnsList.get(ColumnType.Numerical);

		Set<JsonPrimitive> xAxisValues = this.datasetUtils.JsonGetColumnValues(objectsString, xAxis);

		if (categoricalColumns.contains(xAxis) && numericColumns.contains(yAxis) && xAxisValues.size() < 3) {

			chartList.add(ChartType.Pie);
			chartList.add(ChartType.Bar);
			return chartList;

		}

		if (categoricalColumns.contains(xAxis) && numericColumns.contains(yAxis)) {
			chartList.add(ChartType.Bar);
			return chartList;
		}

		if (dateColumns.contains(xAxis) && numericColumns.contains(yAxis)) {

			chartList.add(ChartType.Line);
			return chartList;
		}

		if (numericColumns.contains(xAxis) && numericColumns.contains(yAxis)) {
			chartList.add(ChartType.Scatter);
		}

		return null;

	}

	public Map<ColumnType, List<String>> categorizaColunasDataset(String json) {

		DatastoreResponseDTO datastoreResponse = new Gson().fromJson(json, DatastoreResponseDTO.class);

		List<Object> records = datastoreResponse.getResult().getRecords();

		Map<ColumnType, List<String>> colCategories = new HashMap<ColumnType, List<String>>();

		List<String> categoricalColumns = new ArrayList<>();
		List<String> dateColumns = new ArrayList<>();
		List<String> numericColumns = new ArrayList<>();

		String json1 = new Gson().toJson(records.get(0));

		JsonObject jsonObject = new JsonParser().parse(json1).getAsJsonObject();

		jsonObject.keySet().forEach(x -> {

			try {
				ColumnType colunmCategory = this.datasetUtils.categorizaColuna(json1, x);

				switch (colunmCategory) {

				case Categorical:
					categoricalColumns.add(x);
					break;
				case Date:
					dateColumns.add(x);
					break;
				case Numerical:
					numericColumns.add(x);
					break;
				case None:
					break;
				default:
					break;

				}

			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});

		colCategories.put(ColumnType.Categorical, categoricalColumns);
		colCategories.put(ColumnType.Date, dateColumns);
		colCategories.put(ColumnType.Numerical, numericColumns);

		return colCategories;

	}

	private List<Object> parseDatasetColumns(Map<ColumnType, List<String>> categories, List<Object> json) {

		// TODO: parse numerical

		// TODO: parse date

		return new ArrayList<>();
	}

}
