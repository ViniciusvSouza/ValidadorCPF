package com.itspay.entity;

import io.quarkus.hibernate.orm.panache.Panache;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Requisicao extends PanacheEntity {
    public int numRequisicoes;
    public String usuario;
    public int mes;
    public int ano;


    public static Requisicao findRelatorioMes(String usuario, int mes,int ano) {
        return find("from Requisicao where usuario = ?1 and mes =?2 and ano=?3", usuario, mes, ano).firstResult();
    }


}
