package game;

import java.io.Serializable;
import java.util.ArrayList;

/*      Ideia:
Um item que causa dano aos inimigos. */
public class PocaoFogo extends Item implements Serializable{
    
    public PocaoFogo(){
        this.setNome("Pocao de Fogo");
        this.setValor(30);
    }

    @Override
    public int usar(ArrayList alvo) {
        if(alvo.isEmpty())return -1;
        if(!(alvo.get(0) instanceof Inimigo)){
            System.out.print("Entrada Invalida para Item\n"); return -1;
        }
        for(int i=0; i<alvo.size();i++)((Inimigo)alvo.get(i)).tomaDano(20,true);
        this.setQuantidade(this.getQuantidade()-1);
        return this.getQuantidade();
    }

    @Override
    public boolean ehBebivel() {
        return false;
    }

    @Override
    public int usar(Object alvo) {
        System.out.println("Entrada Invalida\n");
        return -1;
    }
    
}