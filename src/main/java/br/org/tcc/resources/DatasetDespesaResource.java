package br.org.tcc.resources;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.org.tcc.entity.DatasetDespesaResult;
import br.org.tcc.repository.DatasetDespesaRepository;

import java.util.List;


@Path("/despesas")
@Produces(MediaType.APPLICATION_JSON)
public class DatasetDespesaResource {

    @Inject
	DatasetDespesaRepository repository;

	@GET
	public List<DatasetDespesaResult> list() {
		return repository.listAll();
	}

	@POST
	public Response create(DatasetDespesaResult datasetDespesa) {
		DatasetDespesaResult entity = repository.save(datasetDespesa);
		return Response.ok(repository).status(Response.Status.CREATED).build();
	}

	@DELETE
	@Path("{id}")
	public Response remove(@PathParam("id") Long id) {
		repository.remove(id);
		return Response.status(Response.Status.NO_CONTENT).build();
	}

}