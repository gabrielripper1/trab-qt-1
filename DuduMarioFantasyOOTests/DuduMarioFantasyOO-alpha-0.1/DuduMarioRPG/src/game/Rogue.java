package game;
import java.util.ArrayList;

/*      Comentarios:
Mudanca no calculo de dano auto-inflingido.*/
public class Rogue extends Personagem{

	private static final long serialVersionUID = 1L;

	public Rogue(String nome){
        super(nome,1,15,7,5);
    }
    
    public Rogue(String nome, int level){
        super(nome,1,15,7,5);
        for(int i=1;i<level;i++)this.levelUp();
        if(level>1)this.setXp(NivelXp.xpParaProxNivel(level-1));
    }
    
    @Override
    public void levelUp(){
        this.setLvl(this.getLvl()+1);
        this.setHp(this.getHp()+3);
        this.setAtk(this.getAtk()+4);
        this.setDef(this.getDef()+1);
        this.setHpA(this.getHpA()+3);
    }
    
    @Override
    public int habilidades(int qualAtaque, @SuppressWarnings("rawtypes") ArrayList inimigos, int qualInimigo){
        
        if(inimigos.isEmpty() | qualInimigo<0 | qualInimigo>= inimigos.size()){System.out.print("Alvo Invalido\n");return -1;}
        
        switch (qualAtaque) {
            case 1 -> {
                return this.ataqueBasico((Inimigo)inimigos.get(qualInimigo));
            }
            case 2 -> {
                if(this.getLvl()>=2){return this.ataqueSneak((Inimigo)inimigos.get(qualInimigo));}
                System.out.print("Nivel muito baixo\n");return -1;
            }
            case 3 -> {
                if(this.getLvl()>=5){return this.ataqueBloodyFinish((Inimigo)inimigos.get(qualInimigo));}
                System.out.print("Nivel muito baixo\n");return -1;
            }
            case 4 -> {
                if(this.getLvl()>=10){return this.ataqueDeathLotus(inimigos);}
                System.out.print("Nivel muito baixo\n");return -1;
            }
            case 5 -> {
                if(this.getLvl()>=15){return this.ataqueDeathSentence((Inimigo)inimigos.get(qualInimigo));}
                System.out.print("Nivel muito baixo\n");return -1;
            }
            default -> { System.out.print("Ataque Invalido\n");return -1;
            }
        }
    }
    
    public int ataqueSneak(Inimigo inimigo){
        int dano;
        dano=RandomRoll.danoroll(10);
        dano+=(2*this.getAtk());
        System.out.print("SNEAK ATTACK: ");
        this.tomaDano((int)(this.getHpA()*0.1),true);
        inimigo.tomaDano(dano);
        return dano;
    }
    
    public int ataqueBloodyFinish(Inimigo inimigo){
        int dano;
        dano=RandomRoll.danoroll(10);
        dano+=(4*this.getAtk());
        System.out.print("BLOODY FINISH: ");
        this.tomaDano((int)(this.getHpA()*0.5),true);
        inimigo.tomaDano(dano);
        return dano;
    }
    
    public int ataqueDeathLotus(ArrayList<Inimigo> inimigos){
        int danoTotal=0;
        System.out.print("DEATH LOTUS: ");
        this.tomaDano((int)(this.getHpA()*0.5),true);
        for(int i=0;i<inimigos.size();i++){
            int dano=RandomRoll.danoroll(10);
            dano+=(2*this.getAtk());
            inimigos.get(i).tomaDano(dano);
            danoTotal+=dano;
        }
        return danoTotal;
    }
    
    public int ataqueDeathSentence(Inimigo inimigo){
        int dano;
        dano=RandomRoll.danoroll(20);
        dano+=(6*this.getAtk());
        System.out.print("DEATH SENTENCE: ");
        this.tomaDano((int)(this.getHpA()*0.8),true);
        inimigo.tomaDano(dano);
        return dano;
    }
    
        @Override
    public void showActions(){  //mostra as acoes possiveis ao jogador
    	System.out.println("Turno do "+this.getNome()+"  "+this.getHpA()+"/"+this.getHp());
        System.out.println("0.Mochila\n1.Ataque basico");
        if(getLvl()>=2){
            System.out.println("2.Sneak Attack");
        }if(getLvl()>=5){
            System.out.println("3.Bloody Finish");
        }if(getLvl()>=10){
            System.out.println("4.Death Lotus");
        }if(getLvl()>=15){
            System.out.println("5.Death Sentence");
        }
    }
}

