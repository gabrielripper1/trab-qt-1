package rogue;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import game.Rogue;

class Inimigo {
    public int danoRecebido = 0;
    public void tomaDano(int dano) { this.danoRecebido += dano; }
    public int getDanoRecebido() { return danoRecebido; }
}

public class RogueTest {

    @Test
    void testConstrutorNome() {
        Rogue r = new Rogue("Gui");
        assertEquals("Gui", r.getNome());
        assertEquals(1, r.getLvl());
        assertEquals(15, r.getHp());
        assertEquals(7, r.getAtk());
        assertEquals(5, r.getDef());
        assertEquals(15, r.getHpA());
    }

    @Test
    void testConstrutorNomeLevel() {
        Rogue r = new Rogue("Cascão", 3);
        assertEquals("Cascão", r.getNome());
        assertEquals(3, r.getLvl());
        assertEquals(15 + 3 * 2, r.getHp());
        assertEquals(7 + 4 * 2, r.getAtk());
        assertEquals(5 + 1 * 2, r.getDef());
        assertEquals(15 + 3 * 2, r.getHpA());
    }

    @Test
    void testLevelUp() {
        Rogue r = new Rogue("Inbonha");
        r.levelUp();
        assertEquals(2, r.getLvl());
        assertEquals(18, r.getHp());
        assertEquals(11, r.getAtk());
        assertEquals(6, r.getDef());
        assertEquals(18, r.getHpA());
    }

    @Test
    void testHabilidadesAtaqueBasico() {
        Rogue r = new Rogue("Merilu");
        ArrayList<Inimigo> inimigos = new ArrayList<>();
        Inimigo i = new Inimigo();
        inimigos.add(i);
        int result = r.habilidades(1, inimigos, 0);
        assertTrue(result >= 0);
    }

    @Test
    void testHabilidadesSneakAttackNivelBaixo() {
        Rogue r = new Rogue("Merilu");
        ArrayList<Inimigo> inimigos = new ArrayList<>();
        inimigos.add(new Inimigo());
        int result = r.habilidades(2, inimigos, 0);
        assertEquals(-1, result);
    }

    @Test
    void testHabilidadesSneakAttackNivelAlto() {
        Rogue r = new Rogue("Merilu", 2);
        ArrayList<Inimigo> inimigos = new ArrayList<>();
        inimigos.add(new Inimigo());
        int result = r.habilidades(2, inimigos, 0);
        assertTrue(result >= 0);
    }

    @Test
    void testHabilidadesBloodyFinishNivelBaixo() {
        Rogue r = new Rogue("Merilu");
        ArrayList<Inimigo> inimigos = new ArrayList<>();
        inimigos.add(new Inimigo());
        int result = r.habilidades(3, inimigos, 0);
        assertEquals(-1, result);
    }

    @Test
    void testHabilidadesBloodyFinishNivelAlto() {
        Rogue r = new Rogue("Merilu", 5);
        ArrayList<Inimigo> inimigos = new ArrayList<>();
        inimigos.add(new Inimigo());
        int result = r.habilidades(3, inimigos, 0);
        assertTrue(result >= 0);
    }

    @Test
    void testHabilidadesDeathLotusNivelBaixo() {
        Rogue r = new Rogue("Merilu");
        ArrayList<Inimigo> inimigos = new ArrayList<>();
        inimigos.add(new Inimigo());
        int result = r.habilidades(4, inimigos, 0);
        assertEquals(-1, result);
    }

    @Test
    void testHabilidadesDeathLotusNivelAlto() {
        Rogue r = new Rogue("Merilu", 10);
        ArrayList<Inimigo> inimigos = new ArrayList<>();
        inimigos.add(new Inimigo());
        inimigos.add(new Inimigo());
        int result = r.habilidades(4, inimigos, 0);
        assertTrue(result >= 0);
    }

    @Test
    void testHabilidadesDeathSentenceNivelBaixo() {
        Rogue r = new Rogue("Merilu");
        ArrayList<Inimigo> inimigos = new ArrayList<>();
        inimigos.add(new Inimigo());
        int result = r.habilidades(5, inimigos, 0);
        assertEquals(-1, result);
    }

    @Test
    void testHabilidadesDeathSentenceNivelAlto() {
        Rogue r = new Rogue("Merilu", 15);
        ArrayList<Inimigo> inimigos = new ArrayList<>();
        inimigos.add(new Inimigo());
        int result = r.habilidades(5, inimigos, 0);
        assertTrue(result >= 0);
    }

    @Test
    void testHabilidadesAtaqueInvalido() {
        Rogue r = new Rogue("Merilu");
        ArrayList<Inimigo> inimigos = new ArrayList<>();
        inimigos.add(new Inimigo());
        int result = r.habilidades(99, inimigos, 0);
        assertEquals(-1, result);
    }

    @Test
    void testHabilidadesAlvoInvalido() {
        Rogue r = new Rogue("Merilu");
        ArrayList<Inimigo> inimigos = new ArrayList<>();
        int result = r.habilidades(1, inimigos, 0);
        assertEquals(-1, result);
    }
}