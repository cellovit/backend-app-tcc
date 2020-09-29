package br.org.tcc.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Despesa extends PanacheEntity {

	private int ano_movimentacao;
	private Date mes_movimentacao;

	private int orgao_codigo;
	private String orgao_nome;
	private String unidade_codigo;
	private String unidade_nome;
	private int categoria_economica_codigo;
	private String categoria_economica_nome;

	private int grupo_despesa_codigo;
	private String grupo_despesa_nome;

	private int modalidade_aplicacao_codigo;
	private String modalidade_aplicacao_nome;

	private int elemento_codigo;
	private String elemento_nome;

	private int subelemento_codigo;
	private String subelemento_nome;

	private int funcao_codigo;
	private String funcao_nome;

	private int subfuncao_codigo;
	private String subfuncao_nome;

	private int programa_codigo;
	private String programa_nome;

	private int acao_codigo;
	private String acao_nome;

	private int fonte_recurso_codigo;
	private String fonte_recurso_nome;

	private int empenho_ano;
	private int empenho_modalidade_nome;
	private String empenho_modalidade_codigo;

	private int empenho_numero;
	private int subempenho;
	private String indicador_subempenho;

	private int credor_codigo;
	private String credor_nome;

	private int modalidade_licitacao_codigo;
	private String modalidade_licitacao_nome;

	private int valor_empenhado;
	private int valor_liquidado;
	private int valor_pago;

	public int getAno_movimentacao() {
		return ano_movimentacao;
	}

	public void setAno_movimentacao(int ano_movimentacao) {
		this.ano_movimentacao = ano_movimentacao;
	}

	public Date getMes_movimentacao() {
		return mes_movimentacao;
	}

	public void setMes_movimentacao(Date mes_movimentacao) {
		this.mes_movimentacao = mes_movimentacao;
	}

	public int getOrgao_codigo() {
		return orgao_codigo;
	}

	public void setOrgao_codigo(int orgao_codigo) {
		this.orgao_codigo = orgao_codigo;
	}

	public String getOrgao_nome() {
		return orgao_nome;
	}

	public void setOrgao_nome(String orgao_nome) {
		this.orgao_nome = orgao_nome;
	}

	public String getUnidade_codigo() {
		return unidade_codigo;
	}

	public void setUnidade_codigo(String unidade_codigo) {
		this.unidade_codigo = unidade_codigo;
	}

	public String getUnidade_nome() {
		return unidade_nome;
	}

	public void setUnidade_nome(String unidade_nome) {
		this.unidade_nome = unidade_nome;
	}

	public int getCategoria_economica_codigo() {
		return categoria_economica_codigo;
	}

	public void setCategoria_economica_codigo(int categoria_economica_codigo) {
		this.categoria_economica_codigo = categoria_economica_codigo;
	}

	public String getCategoria_economica_nome() {
		return categoria_economica_nome;
	}

	public void setCategoria_economica_nome(String categoria_economica_nome) {
		this.categoria_economica_nome = categoria_economica_nome;
	}

	public int getGrupo_despesa_codigo() {
		return grupo_despesa_codigo;
	}

	public void setGrupo_despesa_codigo(int grupo_despesa_codigo) {
		this.grupo_despesa_codigo = grupo_despesa_codigo;
	}

	public String getGrupo_despesa_nome() {
		return grupo_despesa_nome;
	}

	public void setGrupo_despesa_nome(String grupo_despesa_nome) {
		this.grupo_despesa_nome = grupo_despesa_nome;
	}

	public int getModalidade_aplicacao_codigo() {
		return modalidade_aplicacao_codigo;
	}

	public void setModalidade_aplicacao_codigo(int modalidade_aplicacao_codigo) {
		this.modalidade_aplicacao_codigo = modalidade_aplicacao_codigo;
	}

	public String getModalidade_aplicacao_nome() {
		return modalidade_aplicacao_nome;
	}

	public void setModalidade_aplicacao_nome(String modalidade_aplicacao_nome) {
		this.modalidade_aplicacao_nome = modalidade_aplicacao_nome;
	}

	public int getElemento_codigo() {
		return elemento_codigo;
	}

	public void setElemento_codigo(int elemento_codigo) {
		this.elemento_codigo = elemento_codigo;
	}

	public String getElemento_nome() {
		return elemento_nome;
	}

	public void setElemento_nome(String elemento_nome) {
		this.elemento_nome = elemento_nome;
	}

	public int getSubelemento_codigo() {
		return subelemento_codigo;
	}

	public void setSubelemento_codigo(int subelemento_codigo) {
		this.subelemento_codigo = subelemento_codigo;
	}

	public String getSubelemento_nome() {
		return subelemento_nome;
	}

	public void setSubelemento_nome(String subelemento_nome) {
		this.subelemento_nome = subelemento_nome;
	}

	public int getFuncao_codigo() {
		return funcao_codigo;
	}

	public void setFuncao_codigo(int funcao_codigo) {
		this.funcao_codigo = funcao_codigo;
	}

	public String getFuncao_nome() {
		return funcao_nome;
	}

	public void setFuncao_nome(String funcao_nome) {
		this.funcao_nome = funcao_nome;
	}

	public int getSubfuncao_codigo() {
		return subfuncao_codigo;
	}

	public void setSubfuncao_codigo(int subfuncao_codigo) {
		this.subfuncao_codigo = subfuncao_codigo;
	}

	public String getSubfuncao_nome() {
		return subfuncao_nome;
	}

	public void setSubfuncao_nome(String subfuncao_nome) {
		this.subfuncao_nome = subfuncao_nome;
	}

	public int getPrograma_codigo() {
		return programa_codigo;
	}

	public void setPrograma_codigo(int programa_codigo) {
		this.programa_codigo = programa_codigo;
	}

	public String getPrograma_nome() {
		return programa_nome;
	}

	public void setPrograma_nome(String programa_nome) {
		this.programa_nome = programa_nome;
	}

	public int getAcao_codigo() {
		return acao_codigo;
	}

	public void setAcao_codigo(int acao_codigo) {
		this.acao_codigo = acao_codigo;
	}

	public String getAcao_nome() {
		return acao_nome;
	}

	public void setAcao_nome(String acao_nome) {
		this.acao_nome = acao_nome;
	}

	public int getFonte_recurso_codigo() {
		return fonte_recurso_codigo;
	}

	public void setFonte_recurso_codigo(int fonte_recurso_codigo) {
		this.fonte_recurso_codigo = fonte_recurso_codigo;
	}

	public String getFonte_recurso_nome() {
		return fonte_recurso_nome;
	}

	public void setFonte_recurso_nome(String fonte_recurso_nome) {
		this.fonte_recurso_nome = fonte_recurso_nome;
	}

	public int getEmpenho_ano() {
		return empenho_ano;
	}

	public void setEmpenho_ano(int empenho_ano) {
		this.empenho_ano = empenho_ano;
	}

	public int getEmpenho_modalidade_nome() {
		return empenho_modalidade_nome;
	}

	public void setEmpenho_modalidade_nome(int empenho_modalidade_nome) {
		this.empenho_modalidade_nome = empenho_modalidade_nome;
	}

	public String getEmpenho_modalidade_codigo() {
		return empenho_modalidade_codigo;
	}

	public void setEmpenho_modalidade_codigo(String empenho_modalidade_codigo) {
		this.empenho_modalidade_codigo = empenho_modalidade_codigo;
	}

	public int getEmpenho_numero() {
		return empenho_numero;
	}

	public void setEmpenho_numero(int empenho_numero) {
		this.empenho_numero = empenho_numero;
	}

	public int getSubempenho() {
		return subempenho;
	}

	public void setSubempenho(int subempenho) {
		this.subempenho = subempenho;
	}

	public String getIndicador_subempenho() {
		return indicador_subempenho;
	}

	public void setIndicador_subempenho(String indicador_subempenho) {
		this.indicador_subempenho = indicador_subempenho;
	}

	public int getCredor_codigo() {
		return credor_codigo;
	}

	public void setCredor_codigo(int credor_codigo) {
		this.credor_codigo = credor_codigo;
	}

	public String getCredor_nome() {
		return credor_nome;
	}

	public void setCredor_nome(String credor_nome) {
		this.credor_nome = credor_nome;
	}

	public int getModalidade_licitacao_codigo() {
		return modalidade_licitacao_codigo;
	}

	public void setModalidade_licitacao_codigo(int modalidade_licitacao_codigo) {
		this.modalidade_licitacao_codigo = modalidade_licitacao_codigo;
	}

	public String getModalidade_licitacao_nome() {
		return modalidade_licitacao_nome;
	}

	public void setModalidade_licitacao_nome(String modalidade_licitacao_nome) {
		this.modalidade_licitacao_nome = modalidade_licitacao_nome;
	}

	public int getValor_empenhado() {
		return valor_empenhado;
	}

	public void setValor_empenhado(int valor_empenhado) {
		this.valor_empenhado = valor_empenhado;
	}

	public int getValor_liquidado() {
		return valor_liquidado;
	}

	public void setValor_liquidado(int valor_liquidado) {
		this.valor_liquidado = valor_liquidado;
	}

	public int getValor_pago() {
		return valor_pago;
	}

	public void setValor_pago(int valor_pago) {
		this.valor_pago = valor_pago;
	}

}