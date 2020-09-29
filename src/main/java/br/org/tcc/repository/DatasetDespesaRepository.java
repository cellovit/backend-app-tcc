package br.org.tcc.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import br.org.tcc.entity.Despesa;

import java.util.List;

@ApplicationScoped
public class DatasetDespesaRepository implements PanacheRepository<Despesa> {

    public List<Despesa> list() {
		return listAll();
	}

	@Transactional
	public Despesa save(Despesa product) {
		persist(product);
		return product;
	}

	@Transactional
	public void remove(Long id) {
		Despesa productEntity = findById(id);

		delete(productEntity);
	}

}