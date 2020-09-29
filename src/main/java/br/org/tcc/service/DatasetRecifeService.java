package br.org.tcc.service;

import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import br.org.tcc.dto.DatastoreResponseDTO;

@RegisterRestClient
public interface DatasetRecifeService {

	@GET
	public String getDatasetResult(@QueryParam("resource_id") String resource_id, @QueryParam("limit") int limit,
			@QueryParam("distinct") String distinct, @QueryParam("sort") String sort);

}
