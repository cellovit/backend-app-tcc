package br.org.tcc.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import br.org.tcc.entity.DatasetResource;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class DatasetResourceRepository implements PanacheRepository<DatasetResource> {

	public List<DatasetResource> list() {
		return listAll();
	}

	public Optional<DatasetResource> getByResourceId(String resourceId) {

		Map<String, Object> params = new HashMap<>();
		params.put("resourceId", resourceId);

		try {
			return Optional.ofNullable(find("resourceId = :resourceId", params).firstResult());
		} catch (NoResultException e) {
			return Optional.empty();
		}
	}

	public Optional<DatasetResource> getByCategoriaAndExercicio(String categoria, int exercicio) {

		Map<String, Object> params = new HashMap<>();
		params.put("categoria", categoria);
		params.put("exercicio", exercicio);

		try {
			return Optional.ofNullable(find("categoria = :categoria and exercicio = :exercicio", params).firstResult());
		} catch (NoResultException e) {
			return Optional.empty();
		}
	}

	@Transactional
	public DatasetResource save(DatasetResource datasetResource) {
		persist(datasetResource);
		return datasetResource;
	}

	@Transactional
	public DatasetResource update(Long id, DatasetResource datasetResource) {
		DatasetResource datasetEntity = findById(id);

		datasetEntity.setCategoria(datasetResource.getCategoria());
		datasetEntity.setExercicio(datasetResource.getExercicio());
		datasetEntity.setResourceId(datasetResource.getResourceId());

		persist(datasetEntity);
		return datasetEntity;
	}

	@Transactional
	public void remove(Long id) {
		DatasetResource datasetEntity = findById(id);

		delete(datasetEntity);
	}

}
