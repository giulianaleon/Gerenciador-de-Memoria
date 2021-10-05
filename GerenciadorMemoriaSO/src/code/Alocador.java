package code;

import java.io.IOException;
import java.util.Collections;
import java.util.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author giuliana
 */

// Runnable faz a chamada da thread (multithreads)
public class Alocador implements Runnable {

    VetorRequisicao fila;
    Semaforo gestor;
    
    
    public Alocador(Semaforo gestor, VetorRequisicao fila) {
        this.gestor = gestor; 
        this.fila = fila; 
    }
    
    @Override
    public void run() {    //Chamada do object.run
        
        try {
            while(gestor.getVerificacao() == 0){     //Verifica se ainda há oq inserir
                inserir();
            }
        }  catch (InterruptedException ex) {
            Logger.getLogger(Alocador.class.getName()).log(Level.SEVERE, null, ex); 
        } catch (IOException ex) {
            Logger.getLogger(Alocador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void inserir() throws InterruptedException, IOException {
        
        int valorReq = 0 ;
        int a = 0; //Variaveis para sair das condi��es 
        int b = 0; //Variaveis para sair das condi��es 
    
        while(fila.verifica()!= true){   //Faz isso enquanto o vetor não estiver vazio
        	
            valorReq = fila.removerFilaC();    //Remove do vetor de req e coloca na variavel valorReq
            
            if(gestor.quantidadeHeap >= gestor.percentualMem) {  //Se a quantidade de elementos contidos na heap � maior que o percentual de elementos que posso ter    
            	//desaloca
            	gestor.d.release();    //Libera a desalocação
            	gestor.a.acquire();
            }
            
           Segmentos s = gestor.livres.get(0);  //Pega o buraco de maior tamanho da lista de livres
       
           int posicao = 0;
           posicao = s.getInicio();   //Posi��o de inicio de espa�os livres
           int tamanhoDisponivel = 0;
           tamanhoDisponivel = s.getTamanho();   //Tamanho dos segmentos livres
           
           if(valorReq > tamanhoDisponivel) { //Se o valor da requisi��o � maior que o tamanho disponivel (tamanho disponivel = buraco)        
        	   while(valorReq > tamanhoDisponivel) {   //Enquanto o valor de requisi��o for maior que o tamanho disponivel (buraco)
        		   if(somatorio() >= valorReq){    //Se o somatorio de espa�os livres for maior igual que o valor da requisi��o faz a compacta��o de buracos
        			   compacta();
                        }else{   //Mesmo compactando, n�o possui espa�o suficiente
                        	gestor.d.release();     //desaloca
                            gestor.a.acquire();     //desaloca
                       }
                     
                      	s = gestor.livres.get(0);  //Pega o primeiro bloco
                      	posicao = s.getInicio(); //Posi��o de inicio de espa�os livres
                      	tamanhoDisponivel = 0;
                      	tamanhoDisponivel = s.getTamanho();
                   }
                   
        	   		a = 1;
        	   		b = 1;
           } 
           
           if(a == 0 || a == 1 && b == 1) {  

        	   int id = gerarID();
        	   
        	   Segmentos novo = new Segmentos(posicao, valorReq, id);
        	   
               if(gestor.ocupados.size()==0){
                    gestor.ocupados.add(novo);
                }else{
                    gestor.ocupados.add(novo);
                    Collections.sort(gestor.ocupados);
                }


                 for(int i = 0;i<valorReq; i++) {    //Inserindo na heap
                	 gestor.vetorHeap[posicao]= 1;   //ID
                	 posicao++;
                	 gestor.quantidadeHeap++;
                }
                
                 int tamNovo = 0;
                 tamNovo = tamanhoDisponivel - valorReq;  //Novo valor da heap
                 s.setInicio(posicao);
                 s.setTamanho(tamNovo);
                 gestor.livres.set(0, s);
                 Collections.sort(gestor.livres);  //Ordena os segmentos livres
                 a=0;  //Zera as variaveis
                 b=0;  //Zera as variaveis
           }
        }
        
        long t= 0;
       
        if(fila.verifica() == true){
            t = 0;    
            t = System.currentTimeMillis();  

            gestor.setTempoFinal(t);
        }
 
        gestor.setVerificacao(1);  
        gestor.a.acquire();
  
    }
    
    public int somatorio() {
    	
    	int soma = 0;
        int t = gestor.livres.size();  //Espa�os livres no semaforo
        
        for(int i = 0; i<t; i++) {  //Enquanto tiver espa�os livres no semaforo
        	
        	Segmentos s = gestor.livres.get(i);
            soma = soma + s.getTamanho();
        }
        return soma;
    }    
        
    public void compacta() {   //COMPACTA��O POR CAUSA DA FRAGMENTA��O
    	
    	int soma;
    	int inicio1;
    	int inicio2;
    	int contador = 0;
    	int tamanhoLivres = gestor.livres.size();
    	int tamanhoOcupados = gestor.ocupados.size();
        
    	while(contador < gestor.tamanhoTotal) {   //Se o contador for menor que o tamanho total do semaforo
    		
    		for(int i = 0; i<tamanhoLivres; i++) {   //Se i for menor que o espa�o de tamanhos livres
                
    			Segmentos livre = gestor.livres.get(i);
    			inicio1 = livre.getInicio();    //Inicio 1 � o bloco que t� livre
    			soma = livre.getInicio()+livre.getTamanho(); 
                    
    			for(int j = 0; j < tamanhoOcupados; j++){
                  
    				Segmentos ocupado = gestor.ocupados.get(j);
                    inicio2 = ocupado.getInicio();   //Inicio 2 � o bloco que ta ocupado
                    	if(soma == ocupado.getInicio() && gestor.vetorHeap[ocupado.getInicio()]==1){   //Testa para ver se tem
                           for(int k = 0; k< ocupado.getTamanho();k++){
                               gestor.vetorHeap[inicio1]= gestor.vetorHeap[inicio2];
                               gestor.vetorHeap[inicio2]=0;  //ZERA O OCUPADO
                               inicio1++;    
                               inicio2++;  
                           }

                           livre.setInicio(livre.getInicio()+ocupado.getTamanho());
                           gestor.livres.set(i, livre);
                           ocupado.setInicio(ocupado.getInicio()-livre.getTamanho());
                           gestor.ocupados.set(j, ocupado);

                       }
                   }
               }           
          
    	   contador++;
       }
       
       int novoTamanho = 0;
       int posicao = gestor.tamanhoTotal;
       
       
       for(int k = 0; k < tamanhoLivres; k++) {
           Segmentos s = gestor.livres.get(k);
          
           if(posicao > s.getInicio()) {
               posicao = s.getInicio();
           }
           novoTamanho = novoTamanho + s.getTamanho();
       }
      
       
       for(int k = 0; k<tamanhoLivres; k++){
           gestor.livres.remove(0);
       }
       
       Segmentos novo = new Segmentos(posicao, novoTamanho, 0);
       gestor.livres.add(novo);  
   }  



    public static int gerarID() {
    	Random random = new Random();
    	return random.nextInt(1001)+1;
    }
    

}
