package game;

public class CombatePdEM extends Combate{
	public CombatePdEM() {
		ProfessorDoEnsinoMedio p1 = new ProfessorDoEnsinoMedio("Professor do Ta!Ok!Ne!");
		ProfessorDoEnsinoMedio p2 = new ProfessorDoEnsinoMedio("Professor que ta dando uma viajada");
		ProfessorDoEnsinoMedio p3 = new ProfessorDoEnsinoMedio("Professor ocupado jogando playstation");
		ProfessorDoEnsinoMedio p4 = new ProfessorDoEnsinoMedio("Professor FINALMENTE SEXTA FEIRA");
		this.inimigos.add(p1);this.inimigos.add(p2);this.inimigos.add(p3);this.inimigos.add(p4);
		this.xp=300;
		this.nomeI="os Professor do Ensino Medio";
	}
}
