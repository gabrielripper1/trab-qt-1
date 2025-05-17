package game;

import java.util.ArrayList;

/*      Comentarios:
Agora implementa 'Dano Verdadeiro', variante de tomaDano(), que recebe true e
ignora a defesa.*/
public abstract class Inimigo {
	private String nome;
    private int hp;
    private int hpA;
    private int atk;
    private int def=0;
    
    public Inimigo(String nome, int hp, int atk,int def){
        this.nome=nome;
        this.hp=hp;
        this.hpA=hp;
        this.atk=atk;
        this.def=def;
    }
    
    public int atacar(ArrayList<Personagem> personagens){
        int qualAtaque=1;//ToDO random
        int alvo=0;//ToDo random lembrar de re-rolar caso Personagem.taMorto()
        switch (qualAtaque) {
            case 1 -> {
                return this.ataqueBasico((Personagem)personagens.get(alvo));
            }/*
            case 2 ->//implementar os outros ataques do Inimigo
                return this.ataqueEmArea(personagens);
            }*/
            default -> { System.out.print("Ataque Invalido\n");return 0;
            }
        }
    }
    
    public int atacar(Personagem personagem){
        int qualAtaque=1;//ToDO random
        switch (qualAtaque) {
            case 1 -> {
                return this.ataqueBasico(personagem);
            }/*
            case 2 ->//implementar os outros ataques do Inimigo
                return this.ataqueEmArea(personagens);
            }*/
            default -> { System.out.print("Ataque Invalido\n");return 0;
            }
        }
    }
    
    public int ataqueBasico(Personagem personagem){
        int dano;
        dano=RandomRoll.danoroll(5);
        dano+=(this.getAtk());
        personagem.tomaDano(dano);
        return dano;
    }
    
    public void tomaDano(int dano){  //reduz o hp do player com a entrada de dano, ja fatorando a redu��o pela def
        dano-=this.getDef();
        if(dano<=0){
            dano=1;  //min 1 de dano causado
        }
        this.setHpA(this.getHpA() - dano);
        System.out.print("O "+this.getNome()+" toma "+dano+" de dano\n");
    }
    
    public void tomaDano(int dano,boolean bool){
        if(bool){
            setHpA(getHpA() - dano);
            System.out.print("O "+this.getNome()+" toma "+dano+" de dano\n");
            return;
        }tomaDano(dano);
    }
    
    public boolean taMorto(){
        if(this.getHpA()>0)return false;
        else {
            System.out.print("O "+this.getNome()+" morreu\n");
            return true;
        }
    }
    
    public void printHP(){
        System.out.println(getHpA()+"/"+getHp());
    }
    
    public int getHP(){return getHpA();}
    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}
    public int getHp() {return hp;}
    public void setHp(int hp) {this.hp = hp;}
    public int getHpA() {return hpA;}
    public void setHpA(int hpA) {this.hpA = hpA;}
    public int getAtk() {return atk;}
    public void setAtk(int atk) {this.atk = atk;}
    public int getDef() {return def;}
    public void setDef(int def) {this.def = def;}
}