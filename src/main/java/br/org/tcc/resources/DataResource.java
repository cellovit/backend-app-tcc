package br.org.tcc.resources;

import javax.ws.rs.GET;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.*;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.google.gson.Gson;

import br.org.tcc.dto.DatastoreRequestDTO;
import br.org.tcc.dto.DatastoreResponseDTO;
import br.org.tcc.service.DatasetRecifeService;
import br.org.tcc.utils.DatasetUtils;

import javax.inject.Inject;

@Path("/data")
@Produces(MediaType.APPLICATION_JSON)
public class DataResource {

	@Inject
	@RestClient
	DatasetRecifeService datasetService;
	
	@Inject
	DatasetUtils datasetUtils;

	@GET
	public Response getData() throws Exception {

		DatastoreRequestDTO request = new DatastoreRequestDTO();

		request.setDistinct("true");
		request.setLimit(100);
		request.setResource_id("dc9744c1-ab3d-4597-b8ce-a01c9ee2fdda");
		request.setSort("mes_movimentacao");
		
		try {
			String records = datasetService.getDatasetResult(request.getResource_id(), request.getLimit(), request.getDistinct(), request.getSort());
			
			// this.datasetUtils.convertToDto(records);
			
			
			DatastoreResponseDTO dto = new Gson().fromJson(records, DatastoreResponseDTO.class);

			return Response.ok().entity(dto).build();
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}

		return Response.ok().entity(null).build();
		
	}

}
