package br.org.tcc.entity;

import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class DatasetResource extends PanacheEntity {

    private String categoria;
    private int exercicio;
    private String resourceId;

    public DatasetResource() {

    }

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public int getExercicio() {
		return exercicio;
	}

	public void setExercicio(int exercicio) {
		this.exercicio = exercicio;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

    
}
