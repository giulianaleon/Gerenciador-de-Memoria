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
            while(gestor.getVerificacao() == 0){     //Verifica para poder rodar sempre a thread sem parar
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
    
        while(fila.verifica()!= true){   //Enquanto a fila n�o estiver em 0 
        	
            valorReq = fila.removerFilaC();    //Remove do vetor de req e coloca na variavel valorReq
            
            if(gestor.quantidadeHeap >= gestor.percentualMem) {  //Se a quantidade de elementos contidos na heap � maior que o percentual de elementos que posso ter    
            	//desaloca
            	gestor.d.release();    //Libera o recurso do semaforo  
            	gestor.a.acquire();    //Requisitamos acesso
            }
            
           Segmentos s = gestor.livres.get(0);  //lista do tipo Segmentos
       
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
        	   //Se eu posso inserir na heap
               //Removo livre[0] e insiro um novo com as posi��es livres que sobraram e ordeno
               //Insiro em ocupados e ordeno
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
                 Collections.sort(gestor.livres);  //Ordena os semaforos livre
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
    	
    	int soma = 0;
    	int inicio1 = 0;
    	int inicio2 = 0;
    	int contador = 0;
    	int tamanhoLivres = gestor.livres.size();
    	int tamanhoOcupados = gestor.ocupados.size();
        
    	while(contador < gestor.tamanhoTotal) {   //Enquanto o contador for menor que o tamanho total de elementos NO semaforo
    		for(int i = 0; i<tamanhoLivres; i++) {   //Percorre todos os elementos livres
    			Segmentos livre = gestor.livres.get(i);  //Cria um objetos de livres de tamanho i
    			inicio1 = livre.getInicio();    //Inicio 1 � o bloco que t� livre
    			soma = livre.getInicio()+livre.getTamanho(); //Soma o tamanho que ta livre com o tamanho dos segmentos
                    
    			for(int j = 0; j < tamanhoOcupados; j++){    //Percorre todos os elementos ocupados 
                  
    				Segmentos ocupado = gestor.ocupados.get(j);  //Cria um objeto de ocupados de tamanho j
                    inicio2 = ocupado.getInicio();   //Inicio 2 � o bloco que ta ocupado
                    	if(soma == ocupado.getInicio() && gestor.vetorHeap[ocupado.getInicio()]==1){   //Testa para ver se tem elementos (= 1 por causa da HEAP)
                           for(int k = 0; k< ocupado.getTamanho();k++){ //Percorre o tamanho de cada requisi��o
                               gestor.vetorHeap[inicio1]= gestor.vetorHeap[inicio2];  //Faz uma c�pia de inicio1
                               gestor.vetorHeap[inicio2]=0;  //Zera a c�pia de ocupados
                               inicio1++;    //Vai para pr�xima posi��o
                               inicio2++;    //Vai para pr�xima posi��o
                           }
                           //Depois de percorrer toda lista de ocupados, clonar uma e zerar
                           livre.setInicio(livre.getInicio()+ocupado.getTamanho()); //Inicio da fila recebe a primeira requisi��o com seu tamanho
                           gestor.livres.set(i, livre); //Seta o tamanho de livres
                           ocupado.setInicio(ocupado.getInicio()-livre.getTamanho()); //Fila de ocupados recebe o inicio da requisi��o MENOS o seu tamanho
                           gestor.ocupados.set(j, ocupado); //Seta o tamanho de ocu�dps
                       }
                   }
               }           
    	   contador++;  //Anda mais um elemento nas listas 
       }
       
       int novoTamanho = 0;
       int posicao = gestor.tamanhoTotal;
       
       for(int k = 0; k < tamanhoLivres; k++) {  //Enquanto tiver tamanho livre
           Segmentos s = gestor.livres.get(k); //Manda esses livres para s
          
           if(posicao > s.getInicio()) {   //Se a posi��o desse elemento for maior que a posi��o de inicio da fila	
               posicao = s.getInicio();   //Essa posi��o � realocada para o inicio
           }
           novoTamanho = novoTamanho + s.getTamanho();  //A lista � reajeitada para o novo tamanho que ela possue ap�s a inser��o do elemento
       } 
       for(int k = 0; k<tamanhoLivres; k++){   // Se K for menor que os tamanhos disponiveis
           gestor.livres.remove(0);  //Remove a primeira posi��o de liveres
       }
       Segmentos novo = new Segmentos(posicao, novoTamanho, 0);  //O novo segmento recebe uma posi��o, um tamanho e um ID zero pois ainda n foi alocado com nenhuma req
       gestor.livres.add(novo);  //Esse novo segmento � adicionado na fila de livres
   }  
    
    public static int gerarID() {
    	Random random = new Random();
    	return random.nextInt(1001)+1;
    }
    

}

