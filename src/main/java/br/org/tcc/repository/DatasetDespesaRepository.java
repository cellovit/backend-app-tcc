package br.org.tcc.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import br.org.tcc.entity.DatasetDespesaResult;

import java.util.List;

@ApplicationScoped
public class DatasetDespesaRepository implements PanacheRepository<DatasetDespesaResult> {

    public List<DatasetDespesaResult> list() {
		return listAll();
	}

	@Transactional
	public DatasetDespesaResult save(DatasetDespesaResult product) {
		persist(product);
		return product;
	}

	@Transactional
	public void remove(Long id) {
		DatasetDespesaResult productEntity = findById(id);

		delete(productEntity);
	}

}