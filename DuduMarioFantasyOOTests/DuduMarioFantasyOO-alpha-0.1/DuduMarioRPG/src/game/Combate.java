package game;

import java.util.ArrayList;
import java.util.Scanner;

/*      Ideia:
Juntar as mecanicas e metodos de todas as classes e criar um padrao de combates
a ser seguido. ToDo: separar os turnos dos personagens e inimigos em metodos
e deixar o metodo lutar mais customizavel para as subclasses.*/
abstract class Combate {
Scanner teclado = new Scanner(System.in);
    
    ArrayList<Inimigo> inimigos= new ArrayList<Inimigo>();
    String nomeI;
    int xp;
    public Combate(){};//SUBCLASSES : adicionar os inimigos no construtor
    
    public void lutar(ArrayList<Personagem> personagens, Mochila mochila){
        //variaveis auxiliares
        int saida=0;
        int entradaHabilidade;
        int entradaAlvo;
        int entradaItem=-1;
        
        while(saida==0){//saida caso todos os inimigos estejam mortos
            
            System.out.println("- - - Turno dos jogadores - - -\n");
            for(int i=0;i<personagens.size();i++){
                
                if(personagens.get(i).taMorto()==false){//checando se o Personagem esta morto
                    
                    personagens.get(i).showActions();
                    while(true){//repetir entrada caso invalido
                        
                        System.out.println("Qual habilidade usar?\n");
                        entradaHabilidade=teclado.nextInt();//habilidades comecam do 1, diferente do Array, 0 para mochila
                        
                        if(entradaHabilidade==0){//escolhendo item
                            mochila.imprime();
                            System.out.println("Qual item usar?\n");
                            entradaItem=teclado.nextInt()-1;
                        }
                        
                        System.out.println("Qual alvo?\n");
                        entradaAlvo=teclado.nextInt()-1; //-1 pois tratamos de Array, logo posicao 1 = 0

                        if(entradaHabilidade==0){if(mochila.usaItem(entradaItem, personagens.get(i), inimigos, entradaAlvo)>=0)break;}
                        else if(personagens.get(i) instanceof WhiteMage & entradaHabilidade>1){//trocar para modo cura no caso especifico do clerigo
                            if(personagens.get(i).habilidades(entradaHabilidade, personagens, entradaAlvo)>=0)break;
                        }else if(personagens.get(i).habilidades(entradaHabilidade, this.inimigos, entradaAlvo)>=0)break;
                    }
                    for(int j=this.inimigos.size()-1; j>=0;j--){//tira os inimigos da lista antes que o proximo heroi/inimigo ataque
                        if(this.inimigos.get(j).taMorto())this.inimigos.remove(j);
                    }
                }if(this.inimigos.isEmpty())break;
            }if(this.inimigos.isEmpty())break;
            
            System.out.println("- - - Turno dos inimigos - - -\n");
            for(Inimigo inimigoTemp:inimigos){
                System.out.println("Vez do "+inimigoTemp.getNome()+"\n");
                
                if(((Guerreiro)personagens.get(0)).isTaunting() & ((Guerreiro)personagens.get(0)).taMorto()==false)inimigoTemp.atacar(personagens.get(0));//mecanica Taunt do Guerreiro
                else {//cria um array com todas as opcoes de alvos vivos, random escolhe um numero nesse array. Garantida escolha de alvo vivo
                    ArrayList<Integer> targets=new ArrayList<Integer>();
                    for(int i=0; i<personagens.size();i++){
                        if(personagens.get(i).taMorto()==false)targets.add(i);
                    }
                    inimigoTemp.atacar(personagens.get(targets.get(RandomRoll.danoroll(targets.size()))));
                    targets.clear();
                }
                
                for(int i=0; i<personagens.size();i++){//checa quantos personagens estao mortos
                    if(personagens.get(i).taMorto()){
                        saida--;
                    }
                }if(saida!=(-1)*personagens.size())saida=0;
            else if(saida==(-1)*personagens.size())break;
            }
            
            //checando condicoes de parada e resetando algumas mecanicas
            ((Guerreiro)personagens.get(0)).fimTaunt();//So funciona pq o Guerreiro ta na posicao 1 (0)
            
            if(saida!=(-1)*personagens.size())saida=0;
            else if(saida==(-1)*personagens.size())break; //se todos os personagens morreram   
            
        }//fim do while
        
        //mensagens de fim de Combate, recompensas, resets finais
        ((WhiteMage)personagens.get(2)).fimHeroism(personagens);//reseta mecanica Heroism do WhiteMage
        
        if (saida>=0){
            System.out.print("Os personagens derrotaram "+nomeI+"!\nReceberam "+xp+" de experiencia!\n");
            for(int i=0;i<4;i++) {
            	personagens.get(i).ganhaXp(xp);
            }
        }else if(saida<0){
            System.out.print("Os personagens foram derrotados!\n");
        }
    }
}