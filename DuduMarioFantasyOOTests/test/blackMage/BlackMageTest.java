package blackMage;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import game.BlackMage;
import game.Inimigo;

public class BlackMageTest {

    static class DummyInimigo extends Inimigo {
        public DummyInimigo(String nome, int hp, int atk, int def) {
            super(nome, hp, atk, def);
        }
    }

    @Test
    void testConstrutorNome() {
        BlackMage bm = new BlackMage("Mago brabo");
        assertEquals("Mago brabo", bm.getNome());
        assertEquals(1, bm.getLvl());
        assertEquals(15, bm.getHp());
        assertEquals(5, bm.getAtk());
        assertEquals(5, bm.getDef());
        assertEquals(10, bm.getMp());
        assertEquals(15, bm.getHpA());
        assertEquals(10, bm.getMpA());
    }

    @Test
    void testConstrutorNomeLevel() {
        BlackMage bm = new BlackMage("Mago brabo", 3);
        assertEquals("Mago brabo", bm.getNome());
        assertEquals(3, bm.getLvl());
        assertEquals(15 + 3 * 2, bm.getHp());
        assertEquals(5 + 1 * 2, bm.getAtk());
        assertEquals(5 + 1 * 2, bm.getDef());
        assertEquals(10 + 5 * 2, bm.getMp());
        assertEquals(15 + 3 * 2, bm.getHpA());
        assertEquals(10 + 5 * 2, bm.getMpA());
    }

    @Test
    void testLevelUp() {
        BlackMage bm = new BlackMage("Mago brabo");
        bm.levelUp();
        assertEquals(2, bm.getLvl());
        assertEquals(18, bm.getHp());
        assertEquals(6, bm.getAtk());
        assertEquals(6, bm.getDef());
        assertEquals(15, bm.getMp());
        assertEquals(18, bm.getHpA());
        assertEquals(15, bm.getMpA());
    }

    @Test
    void testHabilidadeAtaqueBasico() {
        BlackMage bm = new BlackMage("Mago brabo");
        DummyInimigo inimigo = new DummyInimigo("Goblin", 20, 5, 1);
        ArrayList<Inimigo> inimigos = new ArrayList<>();
        inimigos.add(inimigo);

        int dano = bm.habilidades(1, inimigos, 0);
        assertTrue(dano >= 5 && dano <= 12); // roll(7) + 5 atk
        assertEquals(20 - (dano - 1), inimigo.getHpA()); // defesa de 1 reduz dano
    }

    @Test
    void testFireballNivelBaixo() {
        BlackMage bm = new BlackMage("Mago brabo");
        ArrayList<Inimigo> inimigos = new ArrayList<>();
        inimigos.add(new DummyInimigo("Slime", 10, 2, 0));

        int resultado = bm.habilidades(4, inimigos, 0); // Fireball precisa de lvl 10
        assertEquals(-1, resultado); // muito baixo
    }

    @Test
    void testFireballNivelAlto() {
        BlackMage bm = new BlackMage("Mago brabo", 10);
        ArrayList<Inimigo> inimigos = new ArrayList<>();
        DummyInimigo i1 = new DummyInimigo("Slime azul", 50, 2, 0);
        DummyInimigo i2 = new DummyInimigo("Slime verde", 50, 2, 0);
        inimigos.add(i1);
        inimigos.add(i2);

        int totalDano = bm.habilidades(4, inimigos, 0);
        assertTrue(totalDano > 0);
        assertEquals(2, inimigos.stream().filter(i -> i.getHpA() < 50).count());
    }

    @Test
    void testTDarkHoleNivelBaixo() {
        BlackMage bm = new BlackMage("Mago brabo");
        ArrayList<Inimigo> inimigos = new ArrayList<>();
        inimigos.add(new DummyInimigo("slime", 20, 3, 1));
        int resultado = bm.habilidades(5, inimigos, 0); // DarkHole precisa de lvl 15
        assertEquals(-1, resultado);
    }

    @Test
    void testDarkHoleNivelAlto() {
        BlackMage bm = new BlackMage("Mago brabo", 15);
        ArrayList<Inimigo> inimigos = new ArrayList<>();
        DummyInimigo i1 = new DummyInimigo("Dragão azul", 100, 5, 0);
        DummyInimigo i2 = new DummyInimigo("Dragão vermelho", 100, 5, 0);
        inimigos.add(i1);
        inimigos.add(i2);

        int danoTotal = bm.habilidades(5, inimigos, 0);
        assertTrue(danoTotal > 0);
        assertTrue(i1.getHpA() < 100 && i2.getHpA() < 100);
    }

    @Test
    void testFireboltNivelBaixo() {
        BlackMage bm = new BlackMage("Mago brabo");
        ArrayList<Inimigo> inimigos = new ArrayList<>();
        DummyInimigo inimigo = new DummyInimigo("Zumbi", 20, 4, 2);
        inimigos.add(inimigo);

        int resultado = bm.habilidades(2, inimigos, 0); // Firebolt (lvl 2)
        assertEquals(-1, resultado); // ainda nível 1
    }

    @Test
    void testFireboltNivelAlto() {
        BlackMage bm = new BlackMage("Mago brabo", 2);
        ArrayList<Inimigo> inimigos = new ArrayList<>();
        DummyInimigo inimigo = new DummyInimigo("Zumbi", 25, 4, 2);
        inimigos.add(inimigo);

        int dano = bm.habilidades(2, inimigos, 0); // Firebolt
        assertTrue(dano > 0);
        assertEquals(12, bm.getMpA()); // 15 - 3 de custo
    }
    
    @Test
    void testDarkSphereNivelBaixo() {
        BlackMage bm = new BlackMage("Mago brabo");
        ArrayList<Inimigo> inimigos = new ArrayList<>();
        DummyInimigo inimigo = new DummyInimigo("Zumbi", 20, 3, 1);
        inimigos.add(inimigo);

        int resultado = bm.habilidades(3, inimigos, 0); // DarkSphere (lvl 5)
        assertEquals(-1, resultado); // ainda nível 1
    }

    @Test
    void testDarkSphereNivelAlto() {
        BlackMage bm = new BlackMage("Mago brabo", 5);
        ArrayList<Inimigo> inimigos = new ArrayList<>();
        DummyInimigo inimigo = new DummyInimigo("Zumbi", 30, 4, 2);
        inimigos.add(inimigo);

        int dano = bm.habilidades(3, inimigos, 0); // DarkSphere
        assertTrue(dano > 0);
        assertEquals(23, bm.getMpA()); // 30 - 7 de custo
    }
    
    @Test
    void testManaSuficiente() {
        BlackMage bm = new BlackMage("Mago brabo", 5); // lvl suficiente para DarkSphere
        ArrayList<Inimigo> inimigos = new ArrayList<>();
        DummyInimigo inimigo = new DummyInimigo("Zumbi", 20, 3, 1);
        inimigos.add(inimigo);

        bm.setMpA(6); // menos do que o necessário
        int result = bm.habilidades(3, inimigos, 0); // DarkSphere
        assertEquals(-1, result); // Mana insuficiente
    }
}