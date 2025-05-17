package game;

import java.io.Serializable;
import java.util.ArrayList;

/*      Ideia:
Mochila unica para todos os personagens, guarda itens que podem ser usados, alem
do ouro conseguido.*/
public class Mochila implements Serializable{
    
    private ArrayList<Item> itens=new ArrayList<Item>();
    private int ouro=0;
    
    public void adicionaItem(Item novo){
        int indice=indexOfNome(novo.getNome());//checa se o item ja existe na lista
        if(indice>=0){
            itens.get(indice).setQuantidade();//adiciona 1 a quantidade do item
            return;
        }
        itens.add(novo);//adiciona o item novo na lista
    }
    
    public void imprime(){
        System.out.println("\n - Mochila -\nOuro: "+this.getOuro());
        if(itens.isEmpty())System.out.println("Vazio");
        for(int i=0;i<itens.size();i++)System.out.println((i+1)+". "+itens.get(i).getNome()+" X "+itens.get(i).getQuantidade());
        System.out.println();
    }
    
    public int usaItem(int indice, Personagem heroi, ArrayList<Inimigo> inimigos, int qualInimigo){
        try{//checa se o Item existe e se ainda tem cargas sobrando
            if(itens.get(indice).getQuantidade()<=0){itens.remove(indice);return -1;}//se nao houver mais itens
        }catch(IndexOutOfBoundsException ex){System.out.print("Item invalido\n");return -1;}//se nao achar o indice
        if(itens.get(indice).ehBebivel()){
            if(itens.get(indice).usar(heroi)<=0)itens.remove(indice);//se for o ultimo item e uma pocao
        }else{
            if(itens.get(indice).usar(inimigos)<=0)itens.remove(indice);//se for o ultimo item e um causador de dano
        }
        return 1;
    }
    
    public int indexOfNome(String nome){//retorna o indice de um item dado um nome, ou -1 caso nao exista na lista
        for(int i=0;i<itens.size();i++){
            if(itens.get(i).getNome()==nome)return i;
        }
        return -1;
    }
    
    public Item getItem(int index){
        if(index>=itens.size() | index<0)return null;
        return itens.get(index);
    }
    
    public int getOuro() {return ouro;}
    public void setOuro(int aOuro) {ouro = aOuro;}
}