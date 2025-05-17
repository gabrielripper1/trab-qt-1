package game;

public class CombateZumbi extends Combate{
	public CombateZumbi() {
		Zumbi zumbiSB = new Zumbi("Sem braco");
		Zumbi zumbiSP = new Zumbi("Sem perna");
		Zumbi zumbiSC = new Zumbi("Sem cabeca");
		Zumbi zumbiN = new Zumbi("Normal");
		this.inimigos.add(zumbiN);this.inimigos.add(zumbiSB);this.inimigos.add(zumbiSP);this.inimigos.add(zumbiSC);
		this.xp=50;
		this.nomeI="os zumbis";
	}

}
