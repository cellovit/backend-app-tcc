package br.org.tcc.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;

import br.org.tcc.entity.DatasetResource;
import br.org.tcc.repository.DatasetResourceRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import minek.ckan.CkanClient;
import minek.ckan.CkanClientFactory;
import minek.ckan.v3.datastore.model.DatastoreSearchResult;
import minek.ckan.v3.datastore.model.DatastoreSearchResult.Records;
import minek.ckan.v3.datastore.model.Field;
import minek.ckan.v3.datastore.model.enums.RecordsFormat;
import minek.ckan.v3.datastore.service.DatastoreService;
import minek.ckan.v3.datastore.service.command.DatastoreSearch;

@ApplicationScoped
public class CkanService {

	Config config = ConfigProvider.getConfig();
	String datasetUrl = config.getValue("tcc.config.datasetRecife.url", String.class);

	CkanClientFactory factory = new CkanClientFactory(datasetUrl, null);
	CkanClient client = factory.build();

	@Inject
	DatasetResourceRepository datasetRepository;
	
	public int getTotalResult(String categoria, int exercicio, List<String> fields) throws Exception {

		return this.calldataservice(categoria, exercicio, fields).getTotal();

	}

	public Records getDatasetResult(String categoria, int exercicio, List<String> fields) throws Exception {

		return this.calldataservice(categoria, exercicio, fields).getRecords();

	}

	public List<Field> getDatasetFields(String categoria, int exercicio) throws Exception {

		return this.calldataservice(categoria, exercicio, new ArrayList<String>()).getFields();

	}

	private DatastoreSearchResult calldataservice(String categoria, int exercicio, List<String> fields)
			throws Exception {

		try {

			DatasetResource datasetResource = this.datasetRepository.getByCategoriaAndExercicio(categoria, exercicio)//
					.orElseThrow(() -> new Exception("não foi possivel encontrar dataset"));

			DatastoreSearch datastoreSearch = DatastoreSearch.builder() //
					.resourceId(UUID.fromString(datasetResource.getResourceId())) //
					.fields(fields) //
					.recordsFormat(RecordsFormat.lists).build();

			DatastoreService service = client.dataStoreService();

			return service.datastoreSearch(datastoreSearch).execute().body();

		} catch (IOException e) {
			// TODO: handle exception
		}

		return new DatastoreSearchResult();
	}

}
