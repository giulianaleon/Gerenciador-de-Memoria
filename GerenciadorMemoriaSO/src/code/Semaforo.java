package code;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import view.Interface;

/**
 *
 * @author giuliana
 */

public class Semaforo {

    //static Semaphore a = new Semaphore(0);  //Semaforo(0) todas as acquire chamadas ser�o bloqueadas   (alocador)
    //static Semaphore d = new Semaphore(0); 	//Semaforo(0) todas as acquire chamadas ser�o bloqueadas   (desalocador)
    Semaphore a = new Semaphore(0);
    Semaphore d = new Semaphore(0);
    public long tempoFinal = 0;
    volatile int verificacao = 0;
    public int tamanhoTotal;
    public int[] vetorHeap = new int[tamanhoTotal];
    public int percentualMem = 0;
    public int quantidadeHeap = 0;
    public int percentualMinimo = 0;
    List <Segmentos> ocupados = new ArrayList<Segmentos>();   //List<String> x = new ArrayList<String>();
    List <Segmentos> livres = new ArrayList<Segmentos>();     //Inicialmente toda a heap est� livre

    public long getTempoFinal() {
        return tempoFinal;
    }

    public void setTempoFinal(long tempoFinal) {
        this.tempoFinal = tempoFinal;
    }

    public int getVerificacao() {
        return verificacao;
    }

    public void setVerificacao(int verificacao) {
        this.verificacao = verificacao;
    }

    public void setTamanho(int tamanhoTotal, int percentualMemUs, int percentualMinimoUs) {
        this.percentualMem = percentualMemUs;
        this.tamanhoTotal = tamanhoTotal;
        this.percentualMinimo = percentualMinimoUs;

        int[] vector = new int[tamanhoTotal];  //Cria um vetor de tamanho tamanhoTotal

        for(int i = 0; i< tamanhoTotal; i++){   //Zerando todas as posi��es do vetor
            vector[i] = 0;
        }

        Segmentos novo = new Segmentos(0, tamanhoTotal,0);
        livres.add(novo);

        this.percentualMem = tamanhoTotal * percentualMemUs;
        this.percentualMem = this.percentualMem / 100;
        this.vetorHeap = vector;

        this.percentualMinimo = tamanhoTotal * percentualMinimoUs;   //  <--------------------- PORCENTAGEM MINIMA
        this.percentualMinimo = this.percentualMinimo / 100;
    }

    public void imprimirHeap(){
        for(int i = 0; i<tamanhoTotal;i++)
        {
            System.out.println("["+i+"]="+vetorHeap[i]);
        }
    }

    public void imprimirLivres()
    {
        System.out.println("---------------------------");
        for(Segmentos s : livres){
            System.out.println("Inicio="+s.getInicio()+";Tamanho="+s.getTamanho());
        }
        System.out.println("---------------------------");
    }

    public void imprimirOcupados()
    {
        System.out.println("---------------------------");
        for(Segmentos s : ocupados){
            System.out.println("Inicio="+s.getInicio()+";Tamanho="+s.getTamanho()+";ID="+s.getID());
        }
        System.out.println("---------------------------");
    }

}
