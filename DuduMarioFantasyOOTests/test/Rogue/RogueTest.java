package Rogue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import game.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RogueTest {

    private Rogue rogue;
    private Inimigo inimigoMock;

    @BeforeEach
    void setUp() {
        rogue = new Rogue("TestRogue", 5); // lvl 5 para liberar algumas habilidades
        inimigoMock = mock(Inimigo.class);
    }

    @Test   
    void testLevelUp() {
        int hpAntes = rogue.getHp();
        int atkAntes = rogue.getAtk();
        int defAntes = rogue.getDef();
        rogue.levelUp();
        assertEquals(hpAntes + 3, rogue.getHp());
        assertEquals(atkAntes + 4, rogue.getAtk());
        assertEquals(defAntes + 1, rogue.getDef());
    }

    @Test
    void testAtaqueBasico() {
        ArrayList<Inimigo> inimigos = new ArrayList<>();
        inimigos.add(inimigoMock);
        int resultado = rogue.habilidades(1, inimigos, 0);
        assertTrue(resultado >= 0); // só testa se não falha mesmo
    }

    @Test
    void testAtaqueSneak() {
        try (MockedStatic<RandomRoll> mocked = mockStatic(RandomRoll.class)) {
            mocked.when(() -> RandomRoll.danoroll(10)).thenReturn(5);

            ArrayList<Inimigo> inimigos = new ArrayList<>();
            inimigos.add(inimigoMock);

            int dano = rogue.habilidades(2, inimigos, 0);
            assertEquals(5 + (2 * rogue.getAtk()), dano);
            // aqui testamos se o cálculo do sneak tá certo
        }
    }

    @Test
    void testAtaqueBloodyFinish() {
        try (MockedStatic<RandomRoll> mocked = mockStatic(RandomRoll.class)) {
            mocked.when(() -> RandomRoll.danoroll(10)).thenReturn(5);

            ArrayList<Inimigo> inimigos = new ArrayList<>();
            inimigos.add(inimigoMock);

            int dano = rogue.habilidades(3, inimigos, 0);
            assertEquals(5 + (4 * rogue.getAtk()), dano);
        }
    }

    @Test
    void testAtaqueDeathLotus() {
        try (MockedStatic<RandomRoll> mocked = mockStatic(RandomRoll.class)) {
            mocked.when(() -> RandomRoll.danoroll(10)).thenReturn(5);

            ArrayList<Inimigo> inimigos = new ArrayList<>();
            inimigos.add(inimigoMock);
            inimigos.add(mock(Inimigo.class));

            rogue = new Rogue("Test", 10); // lvl 10 pra liberar o ataque

            int danoTotal = rogue.habilidades(4, inimigos, 0);
            assertEquals((5 + 2 * rogue.getAtk()) * inimigos.size(), danoTotal);
        }
    }

    @Test
    void testAtaqueDeathSentence() {
        try (MockedStatic<RandomRoll> mocked = mockStatic(RandomRoll.class)) {
            mocked.when(() -> RandomRoll.danoroll(20)).thenReturn(10);

            rogue = new Rogue("Test", 15); // lvl 15 pra liberar o ataque

            ArrayList<Inimigo> inimigos = new ArrayList<>();
            inimigos.add(inimigoMock);

            int dano = rogue.habilidades(5, inimigos, 0);
            assertEquals(10 + (6 * rogue.getAtk()), dano);
        }
    }

    @Test
    void testAlvoInvalido() {
        ArrayList<Inimigo> inimigos = new ArrayList<>();
        int resultado = rogue.habilidades(1, inimigos, 0);
        assertEquals(-1, resultado); // nada acontece, alvo é inválido
    }

    @Test
    void testAtaqueInvalido() {
        ArrayList<Inimigo> inimigos = new ArrayList<>();
        inimigos.add(inimigoMock);
        int resultado = rogue.habilidades(99, inimigos, 0);
        assertEquals(-1, resultado); // ataque que não existe
    }

    @Test
    void testNivelMuitoBaixo() {
        Rogue lowRogue = new Rogue("LowLevel", 1);
        ArrayList<Inimigo> inimigos = new ArrayList<>();
        inimigos.add(inimigoMock);

        int resultado = lowRogue.habilidades(2, inimigos, 0); // Sneak exige lvl 2
        assertEquals(-1, resultado);
    }
}
