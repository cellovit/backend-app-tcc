package br.org.tcc.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import br.org.tcc.service.CkanService;
import br.org.tcc.service.DatasetRecifeService;
import minek.ckan.v3.datastore.model.DatastoreInfo;
import minek.ckan.v3.datastore.model.DatastoreSearchResult;
import minek.ckan.v3.datastore.model.Field;
import minek.ckan.v3.datastore.model.DatastoreSearchResult.Records;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

@Path("/data")
@Produces(MediaType.APPLICATION_JSON)
public class DataResource {

	@Inject
	@RestClient
	DatasetRecifeService datasetService;

	@GET
//	@Path("/{categoria}/{exercicio}")
	public Response getData() throws Exception {


		Map<String, Object> parameters = new HashMap<>();
		
		parameters.put("resource_id", "dc9744c1-ab3d-4597-b8ce-a01c9ee2fdda");
		parameters.put("limit", 100);
		// parameters.put("fields", Arrays.asList("mes_movimentacao", "valor_pago"));
		

		Object records = datasetService.getDatasetResult("dc9744c1-ab3d-4597-b8ce-a01c9ee2fdda");
		
		return Response.ok().entity(records).build();
	}

	// params: {
    //     resource_id: 'dc9744c1-ab3d-4597-b8ce-a01c9ee2fdda',
    //     fields: ['mes_movimentacao', 'valor_pago', 'valor_empenhado', 'valor_liquidado'],
    //     filters: '{"mes_movimentacao": ["1", "2", "3", "4", "5", "6"]}',
    //     sort: 'mes_movimentacao',
    //     distinct: 'true',
    //     // limit: '40000'
    //     limit: limit.toString()
    //   },
    //   responseType: 'json'
    // });


}
