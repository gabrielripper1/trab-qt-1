package arquivo;
import game.GameException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import game.Personagem;

/*      Ideia:
Salva os personagens em um arquivo binario. Eh chamado no inicio e no fim da
execucao para carregar e salvar o estado dos personagens. Funciona como um
CheckPoint ou SaveGame.*/
public class PersonagensArquivo {
    
    public static String caminho=System.getProperty("java.class.path")+System.getProperty("file.separator")+"arquivo"+System.getProperty("file.separator")+"Save.txt";
    
    public static Personagem carregaPersonagem(int index) throws GameException{
        
        Personagem retorno = null;
        
        FileInputStream input;
        try {
            input = new FileInputStream(caminho);
            for(int i=0;i<=index;i++){
                ObjectInputStream lerInput = new ObjectInputStream(input);
                retorno =((Personagem)lerInput.readObject());
            }input.close();
            return retorno;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PersonagensArquivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PersonagensArquivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PersonagensArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new GameException();
    }
    
    public static void carregaPersonagens(ArrayList<Personagem> personagens){
        for(int i=0;i<4;i++){
                Personagem teste;
                try {teste = PersonagensArquivo.carregaPersonagem(i);
                    personagens.add(teste);
                } catch (GameException ex) {Logger.getLogger(PersonagensArquivo.class.getName()).log(Level.SEVERE, null, ex);}
            }
    }
    
    public static int salvaTodos(ArrayList<Personagem> personagens){
        apaga();
        for(Personagem var:personagens){
            PersonagensArquivo.salvaPersonagem(var);
        }
        return 1;
    }
    
    
    public static int salvaPersonagem(Personagem personagem){
        try {
            FileOutputStream arquivo = new FileOutputStream(caminho,true);
            ObjectOutputStream personagemSalvo= new ObjectOutputStream(arquivo);
            personagemSalvo.writeObject(personagem);
            personagemSalvo.flush();
            personagemSalvo.close();
            return 1;
        }catch(IOException e){
            System.out.println(e.getMessage());
            return -1;
        }
    }
    
    public static void apaga(){
        try {
            FileOutputStream writer = new FileOutputStream(caminho);
            writer.write(("").getBytes());
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PersonagensArquivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PersonagensArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}