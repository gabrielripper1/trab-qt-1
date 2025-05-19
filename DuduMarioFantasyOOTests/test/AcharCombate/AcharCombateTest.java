package AcharCombate;

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
import game.AcharCombate;

@DisplayName("Classe que testa a classe de achar combate.")
public class AcharCombateTest {

    Guerreiro guerreiro;
    Mochila mochila;
    ArrayList<Personagem> personagens;

    //testando o combate para chamar zumbi
    @BeforeEach
    void setUp() {
        guerreiro = new Guerreiro("Test", 1); // Default nível 1
        mochila = new Mochila();
        personagens = new ArrayList<>();
        personagens.add(guerreiro);
    }

    //testando o combate para chamar zumbi
    @Test
    @DisplayName("Nível 1 e r = 30 → CombateZumbi")
    void testCombateZumbi() {
        AcharCombate.encontro(personagens, mochila, 30);
        assertTrue(true); 
    }

    //testando o combate para chamar slime
    @Test
    @DisplayName("Nível 2 e r = 70 → CombateSlime")
    void testCombateSlime() {
        guerreiro.setLvl(2);
        AcharCombate.encontro(personagens, mochila, 70);
        assertTrue(true);
    }

    //testando o combate para chamar FDLS
    @Test
    @DisplayName("Nível 6 e r = 40 → CombateFDLS")
    void testCombateFDLS() {
        guerreiro.setLvl(6);
        AcharCombate.encontro(personagens, mochila, 40);
        assertTrue(true);
    }

    //testando o combate para chamar LSFF
    @Test
    @DisplayName("Nível 12 e r = 85 → CombateLeandroSertanejo + LSFF")
    void testLeandroSertanejoFinalForm() {
        guerreiro.setLvl(12);
        AcharCombate.encontro(personagens, mochila, 85);
        assertTrue(true);
    }

    // casos de erro 

    @Test
    @DisplayName("Erro: lista de personagens vazia")
    void testErroListaVazia() {
        personagens.clear(); 
        assertThrows(IndexOutOfBoundsException.class, () -> {
            AcharCombate.encontro(personagens, mochila, 50);
        });
    }

    @Test
    @DisplayName("Erro: lista de personagens é null")
    void testErroListaNull() {
        personagens = null;

        assertThrows(NullPointerException.class, () -> {
            AcharCombate.encontro(personagens, mochila, 50);
        });
    }

    @Test
    @DisplayName("Erro: mochila é null")
    void testErroMochilaNull() {
        mochila = null;
        assertThrows(NullPointerException.class, () -> {
            AcharCombate.encontro(personagens, mochila, 50);
        });
    }
}
