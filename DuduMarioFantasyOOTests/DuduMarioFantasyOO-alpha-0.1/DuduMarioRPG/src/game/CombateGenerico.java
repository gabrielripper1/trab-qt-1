package game;

/* Apenas para testes */
public class CombateGenerico extends Combate{
    public CombateGenerico(){
    Goblin goblinVerde=new Goblin("Verde");
    Goblin goblinMarrom=new Goblin("Marrom");
    Goblin goblinGrande=new Goblin("Grande");
    Goblin goblinPequeno=new Goblin("Pequeno");

    this.inimigos.add(goblinVerde);this.inimigos.add(goblinMarrom);
    this.inimigos.add(goblinGrande);this.inimigos.add(goblinPequeno);
    this.xp=1300;
    this.nomeI="os Goblins";    
    }
}
