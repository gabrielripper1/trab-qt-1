package game;

import arquivo.PersonagensArquivo;
import java.util.ArrayList;
import java.util.Scanner;

public class GameMain {

    public static void main(String[] args) {
        
        ArrayList<Personagem> personagens=new ArrayList<Personagem>();
        Mochila mochila=new Mochila();
        
        //adiciona alguns itens na mochila ToDo:implementar MochilaArquivo
        PocaoFogo pocao1=new PocaoFogo();
        PocaoCura pocao2=new PocaoCura();
        mochila.adicionaItem(pocao1);mochila.adicionaItem(pocao1);
        mochila.adicionaItem(pocao2);mochila.adicionaItem(pocao2);
        
        //carrega os personagens do arquivo
        PersonagensArquivo.carregaPersonagens(personagens);
        
        Scanner teclado = new Scanner(System.in);
        int escolha;
        System.out.println("Bem vindo ao jogo!");
        while(true) {
            System.out.println("1.Procurar um combate\n2.Descansar na cidade\n3.Olhar os personagens\n4.Sair do jogo");
            escolha = teclado.nextInt();
            if(escolha==1) {
                AcharCombate.encontro(personagens,mochila);
            }
            if(escolha==2) {
                RegenChars.regenChars(personagens);//ToDo: loja
            }
            if(escolha==3) {
                for(int i=0;i<4;i++) {
                        personagens.get(i).imprime();
                }mochila.imprime();
            }
            if(escolha==4) {
                teclado.close();
                PersonagensArquivo.salvaTodos(personagens);//salva o estado dos personagens
                return;
            }
        }
    }
}
