package com.itspay.service;

import com.itspay.entity.Requisicao;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ApplicationScoped
public class RelatorioService {

    public float recuperaValor(String usuario, int mes, int ano){

        return (float) (Requisicao.findRelatorioMes(usuario,mes,ano).numRequisicoes * 0.10);


    }
    @Transactional
    public void registraRequisicao(String usuario){
        String[] anoMes = mesAnoAtt();
        int ano = Integer.parseInt(anoMes[0]);
        int mes = Integer.parseInt(anoMes[1]);

        Requisicao requisicao = Requisicao.findRelatorioMes(usuario, mes, ano);
        if (requisicao == null){
            criarRegistroRequisicao(usuario, mes, ano);

        }else{
            requisicao.numRequisicoes++;
            requisicao.persist();
        }

    }
    private void criarRegistroRequisicao(String usuario, int mes, int ano){
        Requisicao requisicao = new Requisicao();
        requisicao.usuario = usuario;
        requisicao.mes = mes;
        requisicao.ano = ano;

        requisicao.numRequisicoes = 1;
        //salvar no banco:
        requisicao.persist();
    }
    private String[] mesAnoAtt(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM");
        LocalDateTime now = LocalDateTime.now();
        String[] anoMes = dtf.format(now).split("/");

        return anoMes;



    }
}
