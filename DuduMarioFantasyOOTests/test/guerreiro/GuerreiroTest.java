package guerreiro;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import game.Guerreiro;

class Inimigo {
    public int danoRecebido = 0;
    public void tomaDano(int dano) { this.danoRecebido += dano; }
    public int getDanoRecebido() { return danoRecebido; }
}

public class GuerreiroTest {

    @Test
    void testConstrutorNome() {
        Guerreiro g = new Guerreiro("Gabriel");
        assertEquals("Gabriel", g.getNome());
        assertEquals(1, g.getLvl());
        assertEquals(20, g.getHp());
        assertEquals(7, g.getAtk());
        assertEquals(5, g.getDef());
        assertEquals(20, g.getHpA());
    }

    @Test
    void testConstrutorNomeLevel() {
        Guerreiro g = new Guerreiro("Daniel", 3);
        assertEquals("Daniel", g.getNome());
        assertEquals(3, g.getLvl());
        assertEquals(20 + 5 * 2, g.getHp());
        assertEquals(7 + 3 * 2, g.getAtk());
        assertEquals(5 + 2 * 2, g.getDef());
        assertEquals(20 + 5 * 2, g.getHpA());
    }

    @Test
    void testLevelUp() {
        Guerreiro g = new Guerreiro("Guilherme");
        g.levelUp();
        assertEquals(2, g.getLvl());
        assertEquals(25, g.getHp());
        assertEquals(10, g.getAtk());
        assertEquals(7, g.getDef());
        assertEquals(25, g.getHpA());
    }

    @Test
    void testHabilidadesAtaqueBasico() {
        Guerreiro g = new Guerreiro("Deniel");
        ArrayList<Inimigo> inimigos = new ArrayList<>();
        Inimigo i = new Inimigo();
        inimigos.add(i);
        int result = g.habilidades(1, inimigos, 0);
        assertTrue(result >= 0); // O dano deve ser >= 0
    }

    @Test
    void testHabilidadesAtaqueBashNivelBaixo() {
        Guerreiro g = new Guerreiro("Gabriel");
        ArrayList<Inimigo> inimigos = new ArrayList<>();
        inimigos.add(new Inimigo());
        int result = g.habilidades(2, inimigos, 0);
        assertEquals(-1, result); // Nível muito baixo
    }

    @Test
    void testHabilidadesAtaqueBashNivelAlto() {
        Guerreiro g = new Guerreiro("Gabriel", 5);
        ArrayList<Inimigo> inimigos = new ArrayList<>();
        inimigos.add(new Inimigo());
        int result = g.habilidades(2, inimigos, 0);
        assertTrue(result >= 0); // O dano deve ser >= 0
    }

    @Test
    void testHabilidadesAtaqueCleaveNivelBaixo() {
        Guerreiro g = new Guerreiro("Gabriel");
        ArrayList<Inimigo> inimigos = new ArrayList<>();
        inimigos.add(new Inimigo());
        int result = g.habilidades(3, inimigos, 0);
        assertEquals(-1, result); // Nível muito baixo
    }

    @Test
    void testHabilidadesAtaqueCleaveNivelAlto() {
        Guerreiro g = new Guerreiro("Gabriel", 10);
        ArrayList<Inimigo> inimigos = new ArrayList<>();
        inimigos.add(new Inimigo());
        inimigos.add(new Inimigo());
        int result = g.habilidades(3, inimigos, 0);
        assertTrue(result >= 0); // O dano deve ser >= 0
    }

    @Test
    void testHabilidadesAtaqueTauntNivelBaixo() {
        Guerreiro g = new Guerreiro("Gabriel");
        ArrayList<Inimigo> inimigos = new ArrayList<>();
        inimigos.add(new Inimigo());
        int result = g.habilidades(4, inimigos, 0);
        assertEquals(-1, result); // Nível muito baixo
    }

    @Test
    void testHabilidadesAtaqueTauntNivelAlto() {
        Guerreiro g = new Guerreiro("Gabriel", 15);
        ArrayList<Inimigo> inimigos = new ArrayList<>();
        inimigos.add(new Inimigo());
        int result = g.habilidades(4, inimigos, 0);
        assertEquals(0, result);
        assertTrue(g.isTaunting());
        assertEquals(5 + 5 * 14, g.getDef()); // Defesa aumentada em +5
    }

    @Test
    void testFimTaunt() {
        Guerreiro g = new Guerreiro("Gabriel", 15);
        ArrayList<Inimigo> inimigos = new ArrayList<>();
        inimigos.add(new Inimigo());
        g.habilidades(4, inimigos, 0); // Ativa taunt
        assertTrue(g.isTaunting());
        g.fimTaunt();
        assertFalse(g.isTaunting());
        assertEquals(5 + 2 * 14, g.getDef()); // Defesa volta ao normal
    }
}