package arquivo;

import game.GameException;
import game.Item;
import game.Mochila;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/*      IMPORTANTE
Ainda nao esta funcionando.
ToDo: Resolver problemas de serializacao
ToDo: Salvar itens e gold separadamente e criar uma mochila dentro de carregaMochila
 */
public class MochilaArquivo {
    public static String caminho=System.getProperty("java.class.path")+System.getProperty("file.separator")+"arquivo"+System.getProperty("file.separator")+"Mochila.txt";
    
    public static Mochila carregaMochila() throws GameException{
        Mochila retorno=new Mochila();
        Item temp = null;
        
        FileInputStream input;
        try {
            input = new FileInputStream(caminho);
            ObjectInputStream lerInput = new ObjectInputStream(input);
            temp =((Item)lerInput.readObject());
            while(temp!=null){
                retorno.adicionaItem(temp);
                temp =((Item)lerInput.readObject());
            }
            input.close();
            return retorno;
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo nao encontrado!");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Classe nao encontrada!");
        }
        throw new GameException();
    }
    
    public static int salvaMochila(Mochila mochila){
        apaga();
        for(int i=0;i<10;i++){
            Item temp=((Item)mochila.getItem(i));
            if(temp!=null)MochilaArquivo.salvaItem(temp);
            else break;
        }
        return 1;
    }
    
    
    public static int salvaItem(Item temp){
        try {
            FileOutputStream arquivo = new FileOutputStream(caminho,true);
            ObjectOutputStream itemSalvo= new ObjectOutputStream(arquivo);
            itemSalvo.writeObject(temp);
            itemSalvo.flush();
            itemSalvo.close();
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
            System.out.println("Arquivo nao encontrado!");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
