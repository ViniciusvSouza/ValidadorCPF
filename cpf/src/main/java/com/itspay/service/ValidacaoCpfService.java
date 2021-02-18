package com.itspay.service;

import com.itspay.entity.Requisicao;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;

@ApplicationScoped
public class ValidacaoCpfService {
    public boolean validarCpf(String cpf){


        if (cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222") || cpf.equals("33333333333")
                || cpf.equals("44444444444") || cpf.equals("55555555555") || cpf.equals("66666666666") || cpf.equals("77777777777") ||
                cpf.equals("88888888888") || cpf.equals("99999999999") || (cpf.length() != 11))
            return false;

        char d10, d11;
        int soma, i, r, num, peso;

        try {
            //primeiro DV
            soma = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                num = (int) (cpf.charAt(i) - 48);
                soma = soma + (num * peso);
                peso = peso - 1;
            }
            r = 11 - (soma % 11);
            if ((r == 10) || (r == 11))
                d10 = '0';
            else d10 = (char) (r + 48);//converte o caractere

            //segundo DV
            soma = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {

                num = (int) (cpf.charAt(i) - 48);
                soma = soma + (num * peso);
                peso = peso - 1;
            }
            r = 11 - (soma % 11);
            if ((r == 10) || (r == 11))
                d11 = 0;
            else d11 = (char) (r + 48);

            if ((d10 == cpf.charAt(9)) && (d11 == cpf.charAt(10)))
                return (true);
            else return (false);
        } catch (InputMismatchException erro) {
            return (false);
        }



    }
    public static String impCpf(String cpf){

        return (cpf.substring(0,3)+"."+cpf.substring(3,6)+"."+cpf.substring(6,9)+"-"+cpf.substring(9,11));

    }


}
