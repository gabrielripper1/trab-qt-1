package game;

public class CombateLeandroSertanejo extends Combate{
	public CombateLeandroSertanejo() {
		LeandroSertanejo ls = new LeandroSertanejo();
		CapangasLS capanga = new CapangasLS("Capaganda do Leandro Sertanejo");
		CapangasLS pet = new CapangasLS("Pet do Leandro Sertanejo");
		this.inimigos.add(ls);this.inimigos.add(capanga);this.inimigos.add(pet);
		this.xp=2000;
		this.nomeI="Leandro Sertanejo e seus capangas";
	}
}
