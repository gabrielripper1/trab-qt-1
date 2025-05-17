  
package game;

import java.util.ArrayList;
/*      Comentarios:
    Usei a mesma logica, porem com sintaxe 'instanceof' para a interface Mago e
pequenas modificacoes em imprime para printar a mana.*/
public class RegenChars {
    
    public static void regenChars(ArrayList<Personagem> personagens) {
        
        for(int i=0;i<personagens.size();i++) {
            personagens.get(i).regenHP();
            if(personagens.get(i) instanceof Mago)((Mago) personagens.get(i)).regenMP();
            personagens.get(i).imprime();
        }
    }
}