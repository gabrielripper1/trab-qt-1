package game;

import java.io.Serializable;
import java.util.ArrayList;

/*      Ideia:
Os itens sao objetos finitos, e devem ser removidos da mochila caso usados.
Itens implementam o seu efeito e guardam a quantidade disponivel. */
public abstract class Item implements Serializable{
    
    private int quantidade=1;
    private int valor;
    private String nome;
    
    public abstract int usar(Object alvo);//faz o efeito do Item e retorna quantos itens restam
    
    public abstract int usar(ArrayList alvo);
    
    public abstract boolean ehBebivel();//para diferenciar alvo inimigo e personagem (se um Item eh bebivel ele recebe um heroi como alvo, se nao, recebe os inimigos)
    
    public void setQuantidade(){//aumenta a quantidade em 1
        this.quantidade++;
    }
    
    public void setQuantidade(int q){//aumenta a quantidade em q
        this.quantidade=q;
    }
    
    public int getQuantidade() {return quantidade;}
    public int getValor() {return valor;}
    public void setValor(int valor) {this.valor = valor;}
    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}
}