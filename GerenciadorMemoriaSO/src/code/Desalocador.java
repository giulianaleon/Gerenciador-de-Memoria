package code;

import java.io.IOException;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author giuliana
 */

public class Desalocador implements Runnable{


    Semaforo gestor;

    public Desalocador(Semaforo gestor) { 
        this.gestor = gestor;
    }
    
    @Override
    public void run() {
        
        try {
            while(gestor.getVerificacao() == 0){
            desalocar();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Desalocador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Desalocador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void desalocar() throws InterruptedException, IOException{

        do {
            gestor.d.acquire();

            int t = 0;
            int inicio = 0;

            Segmentos s = gestor.ocupados.get(0); //Pega o topo da pilha
            t = s.getTamanho();
            inicio = s.getInicio();

            for (int i = 0; i < t; i++) {   //Desalocando a posicao da lista x de tamanho x
                gestor.vetorHeap[inicio] = 0;
                inicio++;
                gestor.quantidadeHeap--;
            }

            gestor.livres.add(s);
            Collections.sort(gestor.livres);  //Ordena

            gestor.ocupados.remove(0);
            gestor.d.release();
        }while (gestor.quantidadeHeap >= gestor.percentualMinimo);
        gestor.d.acquire();
        gestor.a.release();
    }   

}
