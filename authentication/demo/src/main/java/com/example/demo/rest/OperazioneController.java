package com.example.demo.rest;

import com.example.demo.entity.Conto;
import com.example.demo.entity.Operazione;

import java.util.List;

public class OperazioneController {


    private Conto conto;


    public void setOperazione(String tipologia, double importo){

        if(tipologia=="prelievo"){
            conto.setSaldo(conto.getSaldo()-importo);
        }else if(tipologia=="versamento"){
            conto.setSaldo(conto.getSaldo()+importo);
        }
    }

}
