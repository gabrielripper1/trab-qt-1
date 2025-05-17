package game;

import java.util.ArrayList;

public class AcharCombate {
	public static void encontro(ArrayList<Personagem> personagens,Mochila mochila) {
		Guerreiro n = (Guerreiro)personagens.get(0);
		int l=n.getLvl();
		int r=RandomRoll.encounterRoll();
		if(l<5) {
			if(r>=50) {
				CombateSlime combate = new CombateSlime();
				System.out.println("Combate contra 2 slimes encontrado!");
				combate.lutar(personagens,mochila);
			}
			if(r<50) {
				CombateZumbi combate = new CombateZumbi();
				System.out.println("Combate contra 4 zumbis encontrado!");
				combate.lutar(personagens, mochila);
			}
		}
		if(l>=5 && l<=10) {
			if(r<=35) {
				CombateLagartoMago combate = new CombateLagartoMago();
				System.out.println("Combate contra 2 Lizard Wizards encontrado!");
				combate.lutar(personagens, mochila);
			}
			if(r>35 && r<=65) {
				CombateFDLS combate = new CombateFDLS();
				System.out.println("Ah nao, combate contra o fa clube do Leandro Sertanejo encontrado!");
				combate.lutar(personagens, mochila);
			}
			if(r>70) {
				CombatePdEM combate = new CombatePdEM();
				System.out.println("Apareceram professor do ensino medio para te impedir!");
				combate.lutar(personagens, mochila);
			}
		}
		if(l>11) {
			if(r<=90) {
				CombateLeandroSertanejo combate = new CombateLeandroSertanejo();
				System.out.println("Finalmente, ele, O LEANDRO SERTANEJO!!!");
				combate.lutar(personagens, mochila);
				System.out.println("Leandro Sertanejo: Essa nem eh minha forma final MUAHAHAHA");
				RegenChars.regenChars(personagens);
				CombateLSFF combate2 = new CombateLSFF();
				combate2.lutar(personagens, mochila);
			}
			if(r>90) {
				System.out.println("Um Ral de Cueca foi encontrado... pera, por que ele esta aqui???\nBem, ele te deseja boa sorte na batalha final");
			}
		}
	}
}
