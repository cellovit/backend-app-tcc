package br.org.tcc.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import br.org.tcc.entity.DatasetDespesa;

import java.util.List;

@ApplicationScoped
public class DatasetDespesaRepository implements PanacheRepository<DatasetDespesa> {

    public List<DatasetDespesa> list() {
		return listAll();
	}

	@Transactional
	public DatasetDespesa save(DatasetDespesa product) {
		persist(product);
		return product;
	}

	@Transactional
	public void remove(Long id) {
		DatasetDespesa productEntity = findById(id);

		delete(productEntity);
	}

}