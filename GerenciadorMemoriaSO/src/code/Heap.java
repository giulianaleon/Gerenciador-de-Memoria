package code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import code.Desalocador;

/**
 *
 * @author giuliana
 */
// Vetor de alocados e � uma lista, assim como livres.

public class Heap {
    public int tamanhoTotal; //tamanho total da heap
    public int[] vetorHeap = new int[tamanhoTotal]; //a heap em si
    public int percentualMem = 0; //valor bruto de quantos elementos podem ter na heap antes d desalocar
    public int percentualMemUsuario = 0; //percentual maximo de ocupação da heap
    public int percentualMinimo = 0;     //percentual minimo de ocupação da heap
    public int percentualMemMinimo = 0;  //valor bruto de quantos elementos devem ter na heap para parar de desalocar
    public int quantidadeHeap = 0;
    List <Segmentos> ocupados = new ArrayList<Segmentos>();  		 //List<String> x = new ArrayList<String>();
    List <Segmentos> livres = new ArrayList<Segmentos>(); 			//inicialmente toda a heap est� livre

    public void setTamanho(int tamanhoTotal, int percentualMemUsuario, int percentualMinimo) {
        this.tamanhoTotal = tamanhoTotal;
        this.percentualMemUsuario = percentualMemUsuario;
        this.percentualMinimo= percentualMinimo;

        int[] vector = new int[tamanhoTotal];
        for(int i = 0; i< tamanhoTotal; i++){
            vector[i]=0;
        }

        Segmentos novo = new Segmentos(0, tamanhoTotal, 0);
        livres.add(novo);

        this.percentualMem = tamanhoTotal * percentualMemUsuario;
        this.percentualMem = this.percentualMem / 100;               ////  <--------------------- PORCENTAGEM MAX
        this.vetorHeap = vector;


        this.percentualMemMinimo = tamanhoTotal * percentualMinimo;   //  <--------------------- PORCENTAGEM MINIMA
        this.percentualMemMinimo = this.percentualMemMinimo / 100;

    }

    public void imprimirHeap() {
        for(int i = 0; i<tamanhoTotal;i++) {
            System.out.println("["+i+"]="+vetorHeap[i]);
        }

        System.out.println(""+livres.size()+"");
    }


    public void imprimirOcupados()
    {
        System.out.println("---------------------------");
        for(Segmentos s : ocupados){
            System.out.println("Inicio="+s.getInicio()+";Tamanho="+s.getTamanho()+";ID="+s.getID());
        }
        System.out.println("---------------------------");
    }

    public int inserirHeap(int valorReq){

        if(quantidadeHeap >= percentualMem) {  // Se a quantidade de elementos contidos na heap � maior que o meu percentual de elementos que posso ter
            //Preciso desalocar pois est� MUITO ocupada
            //Preciso mandar o valor da requisi��o para poder alocar pois ele retorna direto para o while
            // Mem�ria ocupada cerca de x% da sua capacidade total...

            desalocar();
        }

        Segmentos s = livres.get(0); //Sempre a posi��o 0

        //Retirando livres para leitura tamanho e posi��o
        int posicao = 0;
        posicao = s.getInicio();//posi��o de inic�o de espa�os livres
        int tamanhoDisponivel = 0;
        tamanhoDisponivel = s.getTamanho();

        if(valorReq > tamanhoDisponivel) { // Se o valor da requisi��o for maior que o tamanho disponivel
            if(somatorio() >= valorReq){
                compacta();
            }else{  //Se mesmo compactando não tiver espaço o suficiente
                desalocar();
            }
            return valorReq;
        }
        else{
               //Posso inserir na heap
            int id = gerarID();
            Segmentos novo = new Segmentos(posicao, valorReq, id);

            if(ocupados.size()==0){
                ocupados.add(novo);
            }else{
                ocupados.add(novo);
                Collections.sort(ocupados);  //Ordena de maior a menor
            }


            for(int i = 0 ; i<valorReq ; i++){

                vetorHeap[posicao]=1;
                posicao++;
                quantidadeHeap++;
            }

            int tamNovo = 0;
            tamNovo = tamanhoDisponivel - valorReq;
            s.setInicio(posicao);
            s.setTamanho(tamNovo);
            livres.set(0, s);
            Collections.sort(livres);

            return 0;

        }

    }

    public int somatorio(){

        int soma = 0;
        int t = livres.size();  //Espa�os livres

        for(int i = 0; i<t; i++) {
            Segmentos s = livres.get(i);
            soma = soma + s.getTamanho();  //Soma os espa�os livres
        }
        return soma;
    }

    public void desalocar() {
        //Tirar da lista de ocupados e por na lista de livres


        do{


        int t = 0;
        int inicio=0;
        //Desaloca o segmento que está ocupando mais espaço
        Segmentos s = ocupados.get(0);//pega maior
        t = s.getTamanho();
        inicio = s.getInicio();


        for(int i = 0; i<t; i++)
        {
            vetorHeap[inicio] = 0;
            inicio++;
            quantidadeHeap--;
        }

        livres.add(s);
        Collections.sort(livres);   //Coloca em ordem

        ocupados.remove(0);
        }while (quantidadeHeap >= percentualMinimo);
    }

    public void compacta()  //realizada por conta da fragmentação externa
    {

        int soma;
        int inicio1;
        int inicio2;
        int contador = 0;

        int tamanhoLivres = livres.size();
        int tamanhoOcupados = ocupados.size();


        while(contador < tamanhoTotal)
        {

            for(int i = 0; i<tamanhoLivres; i++)
            {
                Segmentos livre = livres.get(i);
                inicio1 = livre.getInicio();
                soma = livre.getInicio()+livre.getTamanho();
                for(int j = 0; j < tamanhoOcupados; j++)
                {
                    Segmentos ocupado = ocupados.get(j);
                    inicio2 = ocupado.getInicio();
                    if(soma == ocupado.getInicio() && vetorHeap[ocupado.getInicio()]==1){

                        for(int k = 0; k< ocupado.getTamanho();k++){
                            vetorHeap[inicio1]= vetorHeap[inicio2];
                            vetorHeap[inicio2]=0;
                            inicio1++;
                            inicio2++;
                        }

                        livre.setInicio(livre.getInicio()+ocupado.getTamanho());
                        livres.set(i, livre);
                        ocupado.setInicio(ocupado.getInicio()-livre.getTamanho());
                        ocupados.set(j, ocupado);

                    }
                }
            }
            contador++;
        }

        int novoTamanho = 0;
        int posicao = tamanhoTotal;

        for(int k = 0; k < tamanhoLivres; k++)
        {
            Segmentos s = livres.get(k);
            if(posicao > s.getInicio())
            {
                posicao = s.getInicio();
            }
            novoTamanho = novoTamanho + s.getTamanho();
        }


        for(int k = 0; k<tamanhoLivres; k++){
            livres.remove(0);
        }

        Segmentos novo = new Segmentos(posicao, novoTamanho,0);
        livres.add(novo);

    }




    public static int gerarID() {
        Random random = new Random();
        return random.nextInt(1000)+1;
    }
}
