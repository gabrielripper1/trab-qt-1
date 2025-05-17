package game;

public class CombateFDLS extends Combate{
	public CombateFDLS() {
		FaDoLeandroSertanejo fa1 = new FaDoLeandroSertanejo("Fan Numero #1");
		FaDoLeandroSertanejo fa2 = new FaDoLeandroSertanejo("Fan mais velha");
		FaDoLeandroSertanejo fa3 = new FaDoLeandroSertanejo("Fan mais animada");
		FaDoLeandroSertanejo fa4 = new FaDoLeandroSertanejo("Fan mais agressiva");
		this.inimigos.add(fa1);this.inimigos.add(fa2);this.inimigos.add(fa3);this.inimigos.add(fa4);
		this.xp=300;
		this.nomeI="as fans do Leandro Sertanejo";
	}
}
