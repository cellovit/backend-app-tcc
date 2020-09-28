package br.org.tcc.resources;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.org.tcc.entity.Product;
import br.org.tcc.repository.ProductRepository;

import java.util.List;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {

	@Inject
	ProductRepository productRepository;

	@GET
	public List<Product> list() {
		return productRepository.listAll();
	}

	@POST
	public Response create(@Valid Product product) {
		Product productEntity = productRepository.save(product);
		return Response.ok(productEntity).status(Response.Status.CREATED).build();
	}

	@PUT
	@Path("{id}")
	public Response update(@PathParam("id") Long id, Product product) {
		Product productUpdated = productRepository.update(id, product);

		return Response.ok(productUpdated).build();
	}

	@DELETE
	@Path("{id}")
	public Response remove(@PathParam("id") Long id) {
		productRepository.remove(id);
		return Response.status(Response.Status.NO_CONTENT).build();
	}

}
