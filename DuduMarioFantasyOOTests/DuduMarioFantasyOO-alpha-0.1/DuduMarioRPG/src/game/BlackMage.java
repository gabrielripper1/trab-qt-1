package game;

import java.util.ArrayList;

/*      Comentarios:
As magias agora ignoram a defesa do inimigo.*/
public class BlackMage extends Personagem implements Mago{

	private static final long serialVersionUID = 1L;
	private int magia=7;
    private int mp=10;
    private int mpA=mp;
    
    public BlackMage(String nome){
        super(nome,1,15,5,5);
    }
    
    public BlackMage(String nome, int level){
        super(nome,1,15,5,5);
        for(int i=1;i<level;i++)this.levelUp();
        if(level>1)this.setXp(NivelXp.xpParaProxNivel(level-1));
    }
    
    @Override
    public void levelUp(){
        this.setLvl(this.getLvl()+1);
        this.setHp(this.getHp()+3);
        this.setAtk(this.getAtk()+1);
        this.setDef(this.getDef()+1);
        this.setHpA(this.getHpA()+3);
        this.setMagia(this.getMagia() + 3);
        this.setMp(this.getMp() + 5);
        this.setMpA(this.getMpA() + 5);
    }
    
    @Override
    public int habilidades(int qualAtaque, @SuppressWarnings("rawtypes") ArrayList inimigos, int qualInimigo){
        
        if(inimigos.isEmpty() | qualInimigo<0 | qualInimigo>= inimigos.size()){System.out.print("Alvo Invalido\n");return -1;}
        
        //if(this.mpA<this.mp)this.mpA++; //regenera a mana passivamente todo turno, ativa varias vezes se o usuario errar a entrada de proposito
        
        switch (qualAtaque) {
            case 1 -> {
                return this.ataqueBasico((Inimigo)inimigos.get(qualInimigo));
            }
            case 2 -> {
                if(this.getLvl()>=2){return this.magiaFirebolt((Inimigo)inimigos.get(qualInimigo));}
                System.out.print("Nivel muito baixo\n");return -1;
            }
            case 3 -> {
                if(this.getLvl()>=5){return this.magiaDarkSphere((Inimigo)inimigos.get(qualInimigo));}
                System.out.print("Nivel muito baixo\n");return -1;
            }
            case 4 -> {
                if(this.getLvl()>=10){return this.magiaFireball(inimigos);}
                System.out.print("Nivel muito baixo\n");return -1;
            }
            case 5 -> {
                if(this.getLvl()>=15){return this.magiaDarkHole(inimigos);}
                System.out.print("Nivel muito baixo\n");return -1;
            }
            default -> { System.out.print("Ataque Invalido\n");return -1;
            }
        }
    }
    
    public int magiaFirebolt(Inimigo inimigo){
        int dano;
        if(this.getMpA()<3){
            System.out.print("Mana insuficiente\n");
            return -1;
        }
        System.out.print("FIREBOLT: ");
        this.setMpA(this.getMpA() - 3);
        dano=RandomRoll.danoroll(7);
        dano+=(this.getMagia());
        inimigo.tomaDano(dano,true);
        
        return dano;
    }
    
    public int magiaDarkSphere(Inimigo inimigo){
        int dano;
        if(this.getMpA()<7){
            System.out.print("Mana insuficiente\n");
            return -1;
        }
        System.out.print("DARK SPHERE: ");
        this.setMpA(this.getMpA() - 7);
        dano=RandomRoll.danoroll(10);
        dano+=(2*this.getMagia());
        inimigo.tomaDano(dano,true);
        
        return dano;
    }
    
    public int magiaFireball(ArrayList<Inimigo> inimigos){
        int danoTotal=0;
        if(this.getMpA()<(this.getMp()*0.2)){
            System.out.print("Mana insuficiente\n");
            return -1;
        }
        System.out.print("FIREBALL: ");
        this.setMpA(this.getMpA() - (int) (this.getMp() * 0.2));
        for(int i=0;i<inimigos.size();i++){
            int dano=RandomRoll.danoroll(10);
            dano+=(2*this.getMagia());
            danoTotal+=dano;
            inimigos.get(i).tomaDano(dano,true);
        }
        return danoTotal;
    }
    
    public int magiaDarkHole(ArrayList<Inimigo> inimigos){
        int danoTotal=0;
        if(this.getMpA()<(this.getMp()*0.5)){
            System.out.print("Mana insuficiente\n");
            return -1;
        }
        System.out.print("DARK HOLE: ");
        this.setMpA(this.getMpA() - (int) (this.getMp() * 0.5));
        for(int i=0;i<inimigos.size();i++){
            int dano=RandomRoll.danoroll(15);
            dano+=(4*this.getMagia());
            danoTotal+=dano;
            inimigos.get(i).tomaDano(dano,true);
        }
        return danoTotal;
    }
    
        @Override
    public void showActions(){  //mostra as a��es possiveis ao jogador
    	System.out.println("Turno do "+this.getNome()+"  "+this.getHpA()+"/"+this.getHp());
        System.out.println("0.Mochila\n1.Ataque basico");
        if(this.getLvl()>=2){
            System.out.println("2.Firebolt");
        }if(this.getLvl()>=5){
            System.out.println("3.DarkSphere");
        }if(this.getLvl()>=10){
            System.out.println("4.Fireball");
        }if(this.getLvl()>=15){
            System.out.println("5.DarkHole");
        }
    }
    
    @Override
    public void imprime(){
        super.imprime();
        System.out.println("MP: "+getMpA()+"/"+getMp());
    }
    
    @Override
    public int getMagia() {return magia;}
    @Override
    public void setMagia(int magia) {this.magia = magia;}
    @Override
    public int getMp() {return mp;}
    @Override
    public void setMp(int mp) {this.mp = mp;}
    @Override
    public int getMpA() {return mpA;}
    @Override
    public void setMpA(int mpA) {this.mpA = mpA;}
    @Override
    public void regenMP() {this.mpA=this.mp;}
}