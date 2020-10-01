package br.org.tcc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public List<Object> getDatasetRecords(String categoriaDataset, int exercicio) {
		DatastoreRequestDTO request = this.datasetUtils.prepareDatasetSearchRequest(categoriaDataset, exercicio);

		try {

			String records = datasetRecifeService.getDatasetResult(request.getResource_id(), request.getLimit(),
					request.getDistinct());

			DatastoreResponseDTO datastoreResponse = new Gson().fromJson(records, DatastoreResponseDTO.class);

			return datastoreResponse.getResult().getRecords();

		} catch (Exception ex) {
			System.out.println(ex.toString());
		}

		return null;
	}
	
//	private List<Object> parseDatasetColumns() {
//		
//		
//		
//		return new ArrayList<>();
//	}

	private Map<ColumnType, List<String>> categorizaColunasDataset(String xAxis, String yAxis, List<Object> json) {

		Map<ColumnType, List<String>> colCategories = new HashMap<ColumnType, List<String>>();

		List<String> categoricalColumns = new ArrayList<>();
		List<String> dateColumns = new ArrayList<>();
		List<String> numericColumns = new ArrayList<>();

		String obj1 = json.get(0).toString();

		JsonObject jsonObject = new JsonParser().parse(obj1).getAsJsonObject();

		jsonObject.keySet().forEach(x -> {

			try {
				ColumnType colunmCategory = this.datasetUtils.categorizaColuna(obj1, x);

				switch (colunmCategory) {

				case Categorical:
					categoricalColumns.add(x);
				case Date:
					dateColumns.add(x);				
				case Numerical:
					numericColumns.add(x);
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

	public List<ChartType> resolveChartType(String json) throws JsonMappingException, JsonProcessingException {

		List<ChartType> chartList = new ArrayList<>();

//		int xAxisType = this.datasetUtils.categorizaColuna(json, xAxis).getValue();
//		int yAxisType = this.datasetUtils.categorizaColuna(json, yAxis).getValue();
//		
//		if (xAxisType == 1 && yAxisType == 3) {
//			chartList.add(ChartType.Bar);
//		}
//		
//		if ()

		return null;

	}

}
