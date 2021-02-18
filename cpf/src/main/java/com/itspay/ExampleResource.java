package com.itspay;

import com.itspay.dto.RelatorioDto;
import com.itspay.dto.ValidacaoCpfDto;
import com.itspay.service.RelatorioService;
import com.itspay.service.ValidacaoCpfService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/cpf")
public class ExampleResource {

    @Inject
    ValidacaoCpfService validacaoCpfService;

    @Inject
    RelatorioService relatorioService;


    @GET
    @Path("/{usuario}/{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public ValidacaoCpfDto cpf(@PathParam("usuario")String usuario, @PathParam("cpf")String cpf) {
        //mapeando o cpf da linha 21


        ValidacaoCpfDto validacaoCpfDto = new ValidacaoCpfDto();

        validacaoCpfDto.cpfInformado = cpf;
        validacaoCpfDto.cpfValido = validacaoCpfService.validarCpf(cpf);
        relatorioService.registraRequisicao(usuario);

        return validacaoCpfDto;
    }
    @GET
    @Path("/{usuario}/{mes}/{ano}")
    @Produces(MediaType.APPLICATION_JSON)
    public RelatorioDto relatorio(@PathParam("usuario")String usuario,
                              @PathParam("mes")int mes,
                              @PathParam("ano")int ano) {

        float valor = relatorioService.recuperaValor(usuario, mes, ano);
        RelatorioDto relatorioDto = new RelatorioDto();

        relatorioDto.valor = valor;


        return relatorioDto;
    }
}