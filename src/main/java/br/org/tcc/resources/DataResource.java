package br.org.tcc.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.org.tcc.service.CkanService;
import br.org.tcc.service.DatasetRecifeService;
import minek.ckan.v3.datastore.model.DatastoreInfo;
import minek.ckan.v3.datastore.model.Field;
import minek.ckan.v3.datastore.model.DatastoreSearchResult.Records;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

@Path("/data")
@Produces(MediaType.APPLICATION_JSON)
public class DataResource {

	@Inject
	CkanService ckanService;
	
	@Inject
	DatasetRecifeService datasetService;

	@GET
	@Path("/{categoria}/{exercicio}")
	public Response getData(@PathParam("categoria") String categoria, @PathParam("exercicio") int exercicio) throws Exception {


		Map<String, String> parameters = new HashMap<>();


		Object records = datasetService.getDatasetResult(parameters);
		
		return Response.ok().entity("records").build();
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

	@GET
	@Path("/{categoria}/{exercicio}")
	public Response getDatasetResult(@PathParam("categoria") String categoria, @PathParam("exercicio") int exercicio) throws Exception {

		DatastoreInfo records = ckanService.getDatasetResult(categoria, exercicio);
		
		return Response.ok().entity(records).build();
	}
	
	// @GET
	// @Path("/fields/{categoria}/{exercicio}")
	// public Response getDatasetFields(@PathParam("categoria") String categoria, @PathParam("exercicio") int exercicio) throws Exception {

	// 	try {
	// 		List<Field> fields = ckanService.getDatasetFields(categoria, exercicio);

	// 		return Response.ok().entity(fields).build();
	// 	} catch (Exception ex) {
	// 		System.out.println(ex.toString());
	// 	}
	// 	return null;

		
	// }

}
