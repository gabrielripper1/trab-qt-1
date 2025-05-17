package game;

public class CombateLSFF extends Combate{
	public CombateLSFF() {
		LeandroSertanejoFF l = new LeandroSertanejoFF();
		this.inimigos.add(l);
		this.xp=0;
		this.nomeI="Leandro Sertanejo Forma Final";
	}
}
