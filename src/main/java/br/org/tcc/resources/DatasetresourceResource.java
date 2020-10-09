package br.org.tcc.resources;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.org.tcc.entity.DatasetResource;
import br.org.tcc.repository.DatasetResourceRepository;

@Path("/datasetResource")
@Produces(MediaType.APPLICATION_JSON)
public class DatasetresourceResource {

	@Inject
	DatasetResourceRepository repository;

	@GET
	public Response list() {
		return Response.ok().entity(repository.listAll()).build();
	}

	@GET
	@Path("/resourceId/{resourceId}")
	public Response getByResourceId(@PathParam("resourceId") String resourceId) {

		DatasetResource datasetResource = repository.getByResourceId(resourceId) //
				.orElseThrow(() -> new NoResultException("não foi possivel encontrar o objeto"));
		return Response.ok().entity(datasetResource).build();

	}

	@GET
	@Path("/{categoria}/{exercicio}")
	public Response getByCategoriaAndExercicio(@PathParam("categoria") String categoria,
			@PathParam("exercicio") int exercicio) {

		DatasetResource datasetResource = repository.getByCategoriaAndExercicio(categoria, exercicio) //
				.orElseThrow(() -> new NoResultException("não foi possivel encontrar o objeto"));
		return Response.ok().entity(datasetResource).build();

	}

	@GET
	@Path("/exerciciosDisponiveis/{categoria}")
	public Response getExerciciosDisponiveisByCategoria(@PathParam("categoria") String categoria) {

		List<Integer> exerciciosDisponiveis = repository.getDatasetsByCategoria(categoria).stream()
				.map(x -> x.getExercicio()).collect(Collectors.toList());
		return Response.ok().entity(exerciciosDisponiveis).build();

	}

	@POST
	public Response create(DatasetResource datasetResource) {
		DatasetResource datasetResourceEntity = repository.save(datasetResource);
		return Response.ok(datasetResourceEntity).status(Response.Status.CREATED).build();
	}

	@PUT
	@Path("{id}")
	public Response update(@PathParam("id") Long id, DatasetResource datasetResource) {
		DatasetResource datasetResourceUpdated = repository.update(id, datasetResource);

		return Response.ok(datasetResourceUpdated).build();
	}

	@DELETE
	@Path("{id}")
	public Response remove(@PathParam("id") Long id) {
		repository.remove(id);
		return Response.status(Response.Status.NO_CONTENT).build();
	}

}
