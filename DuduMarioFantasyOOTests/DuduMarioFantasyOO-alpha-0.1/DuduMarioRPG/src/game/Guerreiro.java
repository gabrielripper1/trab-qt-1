package game;

import java.util.ArrayList;

public class Guerreiro extends Personagem{
    static final long serialVersionUID = 1L;
    private boolean taunt=false; //mecanica Guerreiro
    
    public Guerreiro(String nome){
        super(nome,1,20,7,5);
    }
    
    public Guerreiro(String nome, int level){
        super(nome,1,20,7,5);
        for(int i=1;i<level;i++)this.levelUp();
        if(level>1)this.setXp(NivelXp.xpParaProxNivel(level-1));
    }
    
    @Override
    public void levelUp(){
        this.setLvl(this.getLvl()+1);
        this.setHp(this.getHp()+5);
        this.setAtk(this.getAtk()+3);
        this.setDef(this.getDef()+2);
        this.setHpA(this.getHpA()+5);
    }
    
    @Override
    public int habilidades(int qualAtaque, @SuppressWarnings("rawtypes") ArrayList inimigos, int qualInimigo){
        
        if(inimigos.isEmpty() | qualInimigo<0 | qualInimigo>= inimigos.size()){System.out.print("Alvo Invalido\n");return -1;}
        
        switch (qualAtaque) {
            case 1 -> {
                return this.ataqueBasico((Inimigo)inimigos.get(qualInimigo));
            }
            case 2 -> {
                if(this.getLvl()>=5){return this.ataqueBash((Inimigo)inimigos.get(qualInimigo));}
                System.out.print("Nivel muito baixo\n");return -1;
            }
            case 3 -> {
                if(this.getLvl()>=10){return this.ataqueCleave(inimigos);}
                System.out.print("Nivel muito baixo\n");return -1;
            }
            case 4 -> {
                if(this.getLvl()>=15){return this.ataqueTaunt(inimigos);}
                System.out.print("Nivel muito baixo\n");return -1;
            }
            default -> { System.out.print("Ataque Invalido\n");return -1;
            }
        }
    }
    
    public int ataqueBash(Inimigo inimigo){
        int dano;
        dano=RandomRoll.danoroll(10);
        dano+=(2*this.getAtk());
        this.tomaDano((int)(this.getHpA()*0.1),true); //Recebe dano "Verdadeiro" que ignora defesa
        System.out.print("BASH: ");
        inimigo.tomaDano(dano);
        return dano;
    }
    
    public int ataqueCleave(ArrayList<Inimigo> inimigos){
        int dano=0;
        System.out.print("CLEAVE: ");
        for(int i=0;i<inimigos.size();i++){
            dano+=this.ataqueBasico(inimigos.get(i));
        }
        return dano;
    }
    
    public int ataqueTaunt(ArrayList<Inimigo> inimigos){//se o taunt durar mais que 1 turno vai ser necessario checar isTaunting antes
        this.taunt=true;
        this.setDef(this.getDef()+5); //colocar o numero de defesa
        System.out.print("TAUNT\n");
        return 0;
    }
    
    @Override
    public void showActions(){  //mostra as acoes possiveis ao jogador
    	System.out.println("Turno do "+this.getNome()+"  "+this.getHpA()+"/"+this.getHp());
        System.out.println("0.Mochila\n1.Ataque basico");
        if(getLvl()>=5){
            System.out.println("2.Bash");
        }if(getLvl()>=10){
            System.out.println("3.Cleave");
        }if(getLvl()>=15){
            System.out.println("4.Taunt");
        }
    }
    
    public boolean isTaunting(){
        return this.taunt;
    }
    
    public void fimTaunt(){
        if(this.taunt){
            this.taunt=false;
            this.setDef(this.getDef()-5); //colocar o numero de defesa
        }
    }
}
