package game;

import java.io.Serializable;
import java.util.ArrayList;

/*      Ideia:
O personagem gasta seu turno bebendo a pocao e recupera 20 HP*/
public class PocaoCura extends Item implements Serializable{
    
    public PocaoCura(){
        this.setNome("Pocao de Cura");
        this.setValor(10);
    }

    @Override
    public int usar(Object alvo) {
        if(!(alvo instanceof Personagem)){
            System.out.print("Entrada Invalida para Item\n"); return -1;
        }
        this.setQuantidade(this.getQuantidade()-1);
        ((Personagem)alvo).curar(20);
        return this.getQuantidade();
    }

    @Override
    public boolean ehBebivel() {
        return true;
    }

    @Override
    public int usar(ArrayList alvo) {
        System.out.println("Entrada Invalida\n");
        return -1;
    }
}