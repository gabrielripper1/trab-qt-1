package whiteMage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.*;

import game.Goblin;
import game.Guerreiro;
import game.Inimigo;
import game.Personagem;
import game.WhiteMage;

@DisplayName("Classe para testes da Classe White Mage")
public class WhiteMageTest {

    WhiteMage mago;
    Guerreiro aliado;

    @BeforeEach
    void setUp() {
        mago = new WhiteMage("Merlin", 15); // nível máximo necessário para testar tudo
        aliado = new Guerreiro("Aliado", 10);
    }

    //! 1. Level Up
    @Test
    @DisplayName("Level Up, retorna os valores do personagem Upado")
    void testLevelUp() {
        int prevHp = mago.getHp();
        int prevAtk = mago.getAtk();
        int prevDef = mago.getDef();
        int prevMagia = mago.getMagia();
        int prevMp = mago.getMp();
        int prevMpA = mago.getMpA();

        mago.levelUp();

        assertEquals(prevHp + 4, mago.getHp());
        assertEquals(prevAtk + 1, mago.getAtk());
        assertEquals(prevDef + 2, mago.getDef());
        assertEquals(prevMagia + 3, mago.getMagia());
        assertEquals(prevMp + 5, mago.getMp());
        assertEquals(prevMpA + 5, mago.getMpA());
    }

    //! 2. habilidades
    @Test
    @DisplayName("Ataque inválido retorna -1")
    void testHabilidadesInvalidAttack() {

        int result = mago.habilidades(99, new ArrayList<>(), 0);
        assertEquals(-1, result);
    }

    @Test
    @DisplayName("Alvo inválido retorna -1")
    void testHabilidadesInvalidTarget() {

        ArrayList<Personagem> aliados = new ArrayList<>();
        aliados.add(aliado);
        int result = mago.habilidades(1, aliados, 5);
        assertEquals(-1, result);
    }

    @Test
    @DisplayName("Ataque básico (qualAtaque = 1) executa corretamente")
    void testHabilidadesAtaqueBasico() {

        Goblin inimigo = new Goblin("Fred");
        ArrayList<Inimigo> inimigos = new ArrayList<>();
        inimigos.add(inimigo);
        int result = mago.habilidades(1, inimigos, 0);
        assertTrue(result > 0);
    }

    @Test
    @DisplayName("Heal com level < 2 retorna -1")
    void testHabilidadesHealLevelLow() {

        mago.setLvl(1);
        ArrayList<Personagem> aliados = new ArrayList<>();
        aliados.add(aliado);
        int result = mago.habilidades(2, aliados, 0);
        assertEquals(-1, result);
    }

    @Test
    @DisplayName("Heroism com level < 5 retorna -1")
    void testHabilidadesHeroismLevelLow() {

        mago.setLvl(4);
        ArrayList<Personagem> aliados = new ArrayList<>();
        aliados.add(aliado);
        int result = mago.habilidades(3, aliados, 0);
        assertEquals(-1, result);
    }

    @Test
    @DisplayName("Revive com level < 10 retorna -1")
    void testHabilidadesReviveLevelLow() {

        mago.setLvl(9);
        ArrayList<Personagem> aliados = new ArrayList<>();
        aliados.add(aliado);
        int result = mago.habilidades(4, aliados, 0);
        assertEquals(-1, result);
    }

    @Test
    @DisplayName("Holy Light com level < 15 retorna -1")
    void testHabilidadesHolyLightLevelLow() {

        mago.setLvl(14);
        ArrayList<Personagem> aliados = new ArrayList<>();
        aliados.add(aliado);
        int result = mago.habilidades(5, aliados, 0);
        assertEquals(-1, result);
    }

    @Test
    @DisplayName("Heroism com level >= 5 executa corretamente")
    void testHabilidadesHeroismLevelOk() {

        mago.setLvl(5);
        ArrayList<Personagem> aliados = new ArrayList<>();
        aliados.add(aliado);
        int result = mago.habilidades(3, aliados, 0);
        assertEquals(0, result);
    }

    @Test
    @DisplayName("Revive com level >= 10 executa corretamente")
    void testHabilidadesReviveLevelOk() {

        mago.setLvl(10);
        aliado.tomaDano(999, true);
        ArrayList<Personagem> aliados = new ArrayList<>();
        aliados.add(aliado);
        int result = mago.habilidades(4, aliados, 0);
        assertEquals(0, result);
        assertFalse(aliado.taMorto());
    }

    @Test
    @DisplayName("Holy Light com level >= 15 executa corretamente")
    void testHabilidadesHolyLightLevelOk() {
        mago.setLvl(15);
        aliado.tomaDano(10, true);
        ArrayList<Personagem> aliados = new ArrayList<>();
        aliados.add(aliado);
        int result = mago.habilidades(5, aliados, 0);
        assertEquals(65, result);
        assertEquals(aliado.getHp(), aliado.getHpA());
    }

    //! 3. Magia Heal
    @Test
    @DisplayName("magiaHeal retorna -1 com mana insuficiente")
    void testMagiaHealSemMana() {
        mago.setMpA(4);

        aliado.tomaDano(10, true);

        int result = mago.magiaHeal(aliado);

        assertEquals(-1, result);
    }

    @Test
    @DisplayName("magiaHeal aplica cura corretamente")
    void testMagiaHealSucesso() {
        aliado.tomaDano(10, true);

        int vidaAntes = aliado.getHpA();
        int result = mago.magiaHeal(aliado);

        assertTrue(result > 0);
        assertTrue(aliado.getHpA() > vidaAntes);
    }

    //! 4. Magia Heroism
    @Test
    @DisplayName("magiaHeroism falha se alvo estiver morto")
    void testMagiaHeroismAlvoMorto() {

        ArrayList<Personagem> aliados = new ArrayList<>();

        aliados.add(aliado);
        aliado.tomaDano(999, true);

        int result = mago.magiaHeroism(aliados, 0);

        assertEquals(-1, result);
    }

    @Test
    @DisplayName("magiaHeroism falha se mana for insuficiente")
    void testMagiaHeroismSemMana() {
        ArrayList<Personagem> aliados = new ArrayList<>();

        mago.setMpA(4);

        aliados.add(aliado);
        aliado.tomaDano(999, true);

        int result = mago.magiaHeroism(aliados, 0);

        assertEquals(-1, result);
    }

    @Test
    @DisplayName("magiaHeroism incrementa stacks e aumenta ataque")
    void testMagiaHeroismSucesso() {
        ArrayList<Personagem> aliados = new ArrayList<>();

        aliados.add(aliado);

        int atkAntes = aliado.getAtk();
        mago.magiaHeroism(aliados, 0);

        assertTrue(aliado.getAtk() > atkAntes);
    }

    //! 5. Magia Revive
    @Test
    @DisplayName("magiaRevive retorna -1 com mana insuficiente")
    void testMagiaReviveSemMana() {
        mago.setMpA(10);
        aliado.tomaDano(999, true);
        int result = mago.magiaRevive(aliado);
        assertEquals(-1, result);
    }

    @Test
    @DisplayName("magiaRevive revive personagem corretamente")
    void testMagiaReviveSucesso() {
        mago.setMpA(40);
        aliado.tomaDano(999, true);
        mago.magiaRevive(aliado);
        assertFalse(aliado.taMorto());  
    }

    //! 6. Magia Holy Light
    @Test
    @DisplayName("magiaHolyLight retorna -1 com mana insuficiente")
    void testMagiaHolyLightSemMana() {
        mago.setMpA(10);
        aliado.tomaDano(20, true);
        int result = mago.magiaHolyLight(aliado);
        assertEquals(-1, result);

    }

    @Test
    @DisplayName("magiaHolyLight cura personagem por completo")
    void testMagiaHolyLightSucesso() {
        mago.setMpA(100);
        aliado.tomaDano(20, true);
        mago.magiaHolyLight(aliado);
        assertEquals(aliado.getHp(), aliado.getHpA());
    }

    //TODO 7. Heroism
    @Test
    @DisplayName("fimHeroism remove buffs de ataque aplicados por heroism")
    void testFimHeroism() {
        ArrayList<Personagem> aliados = new ArrayList<>();
        var fred = new Guerreiro("Fred", 10);

        aliados.add(fred);

        mago.setMpA(100);
        
        int atkAntes = fred.getAtk();
        
        mago.magiaHeroism(aliados, 0);
        assertTrue(fred.getAtk() > atkAntes);
        
        mago.fimHeroism(aliados);
        assertEquals(atkAntes, fred.getAtk());
    }
}
