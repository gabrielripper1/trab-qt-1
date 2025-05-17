package game;

public class CombateLagartoMago extends Combate{
	public CombateLagartoMago() {
		LagartoMago azul = new LagartoMago("Lizard Wizard Azul");
		LagartoMago vermelho = new LagartoMago("Lizard Wizard Vermelho");
		LagartoMago verde = new LagartoMago("Lizard Wizard Verde");
		this.inimigos.add(vermelho);this.inimigos.add(azul);this.inimigos.add(verde);
		this.xp=300;
		this.nomeI="os LizardWizards";
	}
}
