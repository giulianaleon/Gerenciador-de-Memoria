package code;

/**
 *
 * @author giuliana
 */

//Classe segmentos é uma linha com metodos para o inicio e tamanho

public class Segmentos implements Comparable <Segmentos>{
    
        public int inicio;
        public int tamanho;  
        public int id;   

    public Segmentos(int inicio, int tamanho, int id) {
        this.inicio = inicio;
        this.tamanho = tamanho;
        this.id = id;
    }

    public int getInicio() {
        return inicio;
    }

    public void setInicio(int inicio) {
        this.inicio = inicio;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }
    
    public int getID() {
    	return id;
    }
    
    public void setID(int id) {
    	this.id = id;
    }

    @Override
    public int compareTo(Segmentos outroSegmento) {
         //Ordena por ordem decrescente
        if(this.tamanho > outroSegmento.getTamanho())
        {
            return -1;
        	
        } else if(this.tamanho < outroSegmento.getTamanho()){
            return 1;
        	
        }
        return 0;
    }
}
