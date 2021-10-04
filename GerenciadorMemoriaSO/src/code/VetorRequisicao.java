package code;

/**
 *
 * @author giuliana
 */

//O vetor de requisição é uma fila circular

public class VetorRequisicao {
     
     public int TAMANHO = 0;  //Tamanho inicial da fila
     public  int quantidade=0;
     private Integer[]vetor;    

     public void setTAMANHO(int q) {
        this.TAMANHO = q;
        vetor = new Integer[TAMANHO];
    }
     
     public int getTAMANHO() {
    	 return quantidade;
     }
     
     public  boolean isFilaVazia (){
         return quantidade==0;
     }
     
     public boolean isFilaCheia(){
         for (Integer elementoFila : vetor) {
                 if(elementoFila==null){ //Não está cheia
                     return false;
                 }
         }
         return true; //Ela está cheia
     }
     
     public void incluirFilaC (int valor){
      if (isFilaCheia() == false){ 
          int pos = 0;
            for (Integer elementoFila : vetor){
                if(elementoFila==null){            //Posição livre
                    vetor[pos]=valor;
                    quantidade++;
                break;
                }
             pos++;
            }    
          
      }
      else{
           System.out.println("\n Não é possível inserir, ela está cheia");
      }      
     }
     
     public int removerFilaC (){
        
    	 if(isFilaVazia()){
             System.out.println("\n Erro fila vazia");
             return 0;
         }else{
             Integer filaNova [] = new Integer [vetor.length-1];
                for (int i = 1; i < vetor.length; i++) {
                    filaNova[i-1]= vetor[i];  
                }
                int valor;
                valor = vetor[0];
                vetor=filaNova;
                quantidade--;
          return valor;
         }  
     }
     
     public boolean verifica() {
         if(quantidade == 0)
         {
             return true;
         }else{
             return false;
         }
     }
     
     public void print() {
    	System.out.println("\n Fila=[inicio=0, fim=" + quantidade + "]");
      
    		for (int i = 0; i < quantidade; i++) {
    			System.out.println("\n Elemento na posição " + i + ", valor:" + vetor[i]);
    		}	
 
    		System.out.println(" ");
     }
}