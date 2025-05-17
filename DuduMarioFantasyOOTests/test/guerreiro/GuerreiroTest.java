package guerreiro;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import game.Guerreiro;
import game.Inimigo;

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
        Inimigo inimigoMock = mock(Inimigo.class);
        ArrayList<Inimigo> inimigos = new ArrayList<>();
        inimigos.add(inimigoMock);
        int result = g.habilidades(1, inimigos, 0);
        assertTrue(result >= 0);
        verify(inimigoMock, atLeastOnce()).tomaDano(anyInt());
    }

    @Test
    void testHabilidadesAtaqueBashNivelBaixo() {
        Guerreiro g = new Guerreiro("Gabriel");
        Inimigo inimigoMock = mock(Inimigo.class);
        ArrayList<Inimigo> inimigos = new ArrayList<>();
        inimigos.add(inimigoMock);
        int result = g.habilidades(2, inimigos, 0);
        assertEquals(-1, result);
        verify(inimigoMock, never()).tomaDano(anyInt());
    }

    @Test
    void testHabilidadesAtaqueBashNivelAlto() {
        Guerreiro g = new Guerreiro("Gabriel", 5);
        Inimigo inimigoMock = mock(Inimigo.class);
        ArrayList<Inimigo> inimigos = new ArrayList<>();
        inimigos.add(inimigoMock);
        int result = g.habilidades(2, inimigos, 0);
        assertTrue(result >= 0);
        verify(inimigoMock, atLeastOnce()).tomaDano(anyInt());
    }

    @Test
    void testHabilidadesAtaqueCleaveNivelBaixo() {
        Guerreiro g = new Guerreiro("Gabriel");
        Inimigo inimigoMock = mock(Inimigo.class);
        ArrayList<Inimigo> inimigos = new ArrayList<>();
        inimigos.add(inimigoMock);
        int result = g.habilidades(3, inimigos, 0);
        assertEquals(-1, result);
        verify(inimigoMock, never()).tomaDano(anyInt());
    }

    @Test
    void testHabilidadesAtaqueCleaveNivelAlto() {
        Guerreiro g = new Guerreiro("Gabriel", 10);
        Inimigo inimigoMock1 = mock(Inimigo.class);
        Inimigo inimigoMock2 = mock(Inimigo.class);
        ArrayList<Inimigo> inimigos = new ArrayList<>();
        inimigos.add(inimigoMock1);
        inimigos.add(inimigoMock2);
        int result = g.habilidades(3, inimigos, 0);
        assertTrue(result >= 0);
        verify(inimigoMock1, atLeastOnce()).tomaDano(anyInt());
        verify(inimigoMock2, atLeastOnce()).tomaDano(anyInt());
    }

    @Test
    void testHabilidadesAtaqueTauntNivelBaixo() {
        Guerreiro g = new Guerreiro("Gabriel");
        Inimigo inimigoMock = mock(Inimigo.class);
        ArrayList<Inimigo> inimigos = new ArrayList<>();
        inimigos.add(inimigoMock);
        int result = g.habilidades(4, inimigos, 0);
        assertEquals(-1, result);
    }

    @Test
    void testHabilidadesAtaqueTauntNivelAlto() {
        Guerreiro g = new Guerreiro("Gabriel", 15);
        Inimigo inimigoMock = mock(Inimigo.class);
        ArrayList<Inimigo> inimigos = new ArrayList<>();
        inimigos.add(inimigoMock);
        int result = g.habilidades(4, inimigos, 0);
        assertEquals(0, result);
        assertTrue(g.isTaunting());
        assertEquals(5 + 5 * 14, g.getDef());
    }

    @Test
    void testFimTaunt() {
        Guerreiro g = new Guerreiro("Gabriel", 15);
        Inimigo inimigoMock = mock(Inimigo.class);
        ArrayList<Inimigo> inimigos = new ArrayList<>();
        inimigos.add(inimigoMock);
        g.habilidades(4, inimigos, 0); // Ativa taunt
        assertTrue(g.isTaunting());
        g.fimTaunt();
        assertFalse(g.isTaunting());
        assertEquals(5 + 2 * 14, g.getDef());
    }
}