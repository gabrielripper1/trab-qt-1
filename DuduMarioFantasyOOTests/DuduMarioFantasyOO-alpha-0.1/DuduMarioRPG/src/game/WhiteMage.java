package game;

import java.util.ArrayList;

/*      Comentario:
    Atencao a mecanica Heroism*/
public class WhiteMage extends Personagem implements Mago{
	
	private static final long serialVersionUID = 1L;
	private int magia=7;
    private int mp=10;
    private int mpA=mp;
    
    ArrayList<Integer> heroismStacks=new ArrayList<Integer>();
    
    public WhiteMage(String nome){
        super(nome,1,17,5,5);
        for(int i=0;i<4;i++)this.heroismStacks.add(0);
    }
    
    public WhiteMage(String nome, int level){
        super(nome,1,17,5,5);
        for(int i=0;i<4;i++)this.heroismStacks.add(0);
        for(int i=1;i<level;i++)this.levelUp();
        if(level>1)this.setXp(NivelXp.xpParaProxNivel(level-1));
    }
    
    @Override
    public void levelUp(){
        this.setLvl(this.getLvl()+1);
        this.setHp(this.getHp()+4);
        this.setAtk(this.getAtk()+1);
        this.setDef(this.getDef()+2);
        this.setHpA(this.getHpA()+4);
        this.setMagia(this.getMagia() + 3);
        this.setMp(this.getMp() + 5);
        this.setMpA(this.getMpA() + 5);
    }
    
    @Override
    public int habilidades(int qualAtaque, @SuppressWarnings("rawtypes") ArrayList alvos, int qualAlvo){
        
        if(alvos.isEmpty() | qualAlvo<0 | qualAlvo>= alvos.size()){System.out.print("Alvo Invalido\n");return -1;}
        
        //if(this.mpA<this.mp)this.mpA++; //regenera a mana passivamente todo turno, ativa varias vezes se o usuario errar a entrada de proposito
        
        switch (qualAtaque) {
            case 1 -> {
                return this.ataqueBasico((Inimigo)alvos.get(qualAlvo));
            }
            case 2 -> {
                if(this.getLvl()>=2){return this.magiaHeal((Personagem)alvos.get(qualAlvo));}
                System.out.print("Nivel muito baixo\n");return -1;
            }
            case 3 -> {
                if(this.getLvl()>=5){return this.magiaHeroism(alvos, qualAlvo);}
                System.out.print("Nivel muito baixo\n");return -1;
            }
            case 4 -> {
                if(this.getLvl()>=10){return this.magiaRevive((Personagem)alvos.get(qualAlvo));}
                System.out.print("Nivel muito baixo\n");return -1;
            }
            case 5 -> {
                if(this.getLvl()>=15){return this.magiaHolyLight((Personagem)alvos.get(qualAlvo));}
                System.out.print("Nivel muito baixo\n");return -1;
            }
            default -> { System.out.print("Ataque Invalido\n");return -1;
            }
        }
    }
    
    public int magiaHeal(Personagem personagem){
        int cura;
        if(this.getMpA()<5){
            System.out.print("Mana insuficiente\n");
            return -1;
        }
        System.out.print("HEAL: ");
        this.setMpA(this.getMpA() - 5);
        cura=RandomRoll.danoroll(5)+this.getMagia();
        cura=personagem.curar(cura);
        
        return cura;
    }
    
    //reseta no fim de cada combate, nao reseta caso o aliado seja morto e revivido (mas posso implementar)
    public int magiaHeroism(ArrayList<Personagem> personagens,int qualAlvo){
        if(personagens.get(qualAlvo).taMorto()){System.out.print(personagens.get(qualAlvo).getNome()+"esta morto\n");return -1;}
        if(this.getMpA()<7){
            System.out.print("Mana insuficiente\n");
            return -1;
        }
        System.out.print("HEROISM: ");
        this.setMpA(this.getMpA() - 7);
        this.heroismStacks.set(qualAlvo, this.heroismStacks.get(qualAlvo)+1); //ToDo achar forma melhor de somar 1 ao valor.
        personagens.get(qualAlvo).setAtk((int)(personagens.get(qualAlvo).getAtk()*1.2));
        System.out.print(personagens.get(qualAlvo).getNome()+" aumentou o ataque para "+personagens.get(qualAlvo).getAtk()+"\n");
        return qualAlvo;
    }
    
    public int magiaRevive(Personagem personagem){
        if(this.getMpA()<(this.getMp()*0.2)){
            System.out.print("Mana insuficiente\n");
            return -1;
        }
        System.out.print("REVIVE: ");
        this.setMpA(this.getMpA() - (int) (this.getMp() * 0.2));
        if(personagem.taMorto()){
            personagem.setHpA(1);//tira do estado taMorto()
            personagem.curar((int)(personagem.getHp()*0.3));//cura 30% vida
        }
        return 0;
    }
    
    public int magiaHolyLight(Personagem personagem){
        int cura;
        if(this.getMpA()<(this.getMp()*0.2)){
            System.out.print("Mana insuficiente\n");
            return -1;
        }
        System.out.print("HOLY LIGHT: ");
        this.setMpA(this.getMpA() - (int) (this.getMp() * 0.2));
        cura=personagem.getHp();
        cura=personagem.curar(cura);
        
        return cura;
    }
    
    public void fimHeroism(ArrayList<Personagem> personagens){//mecanica WhiteMage
        for(int i=0;i<4;i++){
            for(int j=0;j<this.heroismStacks.get(i);j++)
                personagens.get(i).setAtk((int)(personagens.get(i).getAtk()*0.833333));
        }this.heroismStacks.replaceAll(e -> 0);//reseta o Array para 0
    }
    
        @Override
    public void showActions(){  //mostra as acoes possiveis ao jogador
    	System.out.println("Turno do "+this.getNome()+"  "+this.getHpA()+"/"+this.getHp());
        System.out.println("0.Mochila\n1.Ataque basico");
        if(getLvl()>=2){
            System.out.println("2.Heal");
        }if(getLvl()>=5){
            System.out.println("3.Heroism");
        }if(getLvl()>=10){
            System.out.println("4.Revive");
        }if(getLvl()>=15){
            System.out.println("5.Holy Light");
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