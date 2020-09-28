package br.org.tcc.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class DatasetDespesa extends PanacheEntity {


	private int ano_movimentacao ;
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


}