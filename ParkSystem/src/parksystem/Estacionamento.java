package parksystem;


import carros.*;
import vagas.*;
import java.util.ArrayList;
import java.util.List;

public class Estacionamento {    
    public List<Vaga> vagas = new ArrayList();
    private static Estacionamento uniqueInstance;
    
    private Estacionamento(String filial){
        FactoryVagas fabrica;
        
        if (filial.equals("Recife")){
            fabrica = new FactoryVagasRecife();
        }
        else if (filial.equals("São Lourenço")){
            
        }
    }
    
    //Implementação do SINGLETON
    public synchronized static Estacionamento getEstacionamento(String filial){
        if (uniqueInstance == null)
            uniqueInstance = new Estacionamento(filial);
        return uniqueInstance;
    }
    
    //linka a vaga ao carro
    public void estacionar(Carro carro, Vaga vaga){
        if (!carro.isIdoso & !carro.isPCD && vaga.isIdoso | vaga.isPCD){
            System.out.println("O Carro de placa: |" + carro.getPlaca() + 
                "| NÃO TEM PERMISSÃO PARA ESTACIONAR na vaga: |" + vaga.id + "|" );
            return;
        } else if (carro.isIdoso && !carro.isPCD && vaga.isPCD){
            System.out.println("O Carro de placa: |" + carro.getPlaca() + 
                "| NÃO TEM PERMISSÃO PARA ESTACIONAR na vaga: |" + vaga.id + "|" );
            return;
        } else if (carro.isPCD && !carro.isIdoso && vaga.isIdoso) {
            System.out.println("O Carro de placa: |" + carro.getPlaca() + 
                "| NÃO TEM PERMISSÃO PARA ESTACIONAR na vaga: |" + vaga.id + "|" );
            return;
        }
        System.out.println("O Carro de placa: |" + carro.getPlaca() + 
                "| ESTACIONOU na vaga: |" + vaga.id + "|" );
        carro.vaga = vaga;
        carro.estacionado = true;
        carro.vaga.setCarro(carro);
    }

    public void sair(Vaga vaga){
        if (vaga.carro == null){
            System.out.println("NÃO HÁ CARRO NA VAGA");
            return;
        }
            
        System.out.println("O Carro de placa: |" + vaga.carro.getPlaca() + 
                "| SAIU da vaga: |" + vaga.id + "|" );
        vaga.liberarVaga();
        vaga.isParked = false;
    }
    
    //implementar a QUANTIDADE LIMITE DO ESTACIONAMENTO
    
    /*
    public void setVagas(){
        for(int j = 0; j <= this.vagas[0].length; j++){
            vagas[2][j].especial = true;
        }
    }*/
}