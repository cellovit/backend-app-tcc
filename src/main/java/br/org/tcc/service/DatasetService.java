package br.org.tcc.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.org.tcc.enums.ChartType;
import br.org.tcc.utils.DatasetUtils;

@ApplicationScoped
public class DatasetService {

	@Inject
	DatasetUtils datasetUtils;

	public List<ChartType> resolveChartType(String xAxis, String yAxis, String json) throws JsonMappingException, JsonProcessingException {
		
		List<ChartType> chartList = new ArrayList<>();
		
		int xAxisType = this.datasetUtils.categorizaColuna(json, xAxis).getValue();
		int yAxisType = this.datasetUtils.categorizaColuna(json, yAxis).getValue();
		
		if (xAxisType == 1 && yAxisType == 3) {
			chartList.add(ChartType.Bar);
		}
		
		return null;

	}

}
