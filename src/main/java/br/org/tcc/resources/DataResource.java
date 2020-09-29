package br.org.tcc.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.org.tcc.service.CkanService;
import minek.ckan.v3.datastore.model.Field;
import minek.ckan.v3.datastore.model.DatastoreSearchResult.Records;

import java.util.List;

import javax.inject.Inject;

@Path("/data")
@Produces(MediaType.APPLICATION_JSON)
public class DataResource {

	@Inject
	CkanService ckanService;

	@GET
	public Response getDatasetResult(@PathParam("categoria") String categoria, @PathParam("exercicio") int exercicio,
			List<String> fields) throws Exception {

		Records records = ckanService.getDatasetResult(categoria, exercicio, fields);
		

		return Response.ok().entity(records).build();
	}
	
	@GET
	public Response getDatasetFields(@PathParam("categoria") String categoria, @PathParam("exercicio") int exercicio) throws Exception {

		List<Field> fields = ckanService.getDatasetFields(categoria, exercicio);
		

		return Response.ok().entity(fields).build();
	}

}
