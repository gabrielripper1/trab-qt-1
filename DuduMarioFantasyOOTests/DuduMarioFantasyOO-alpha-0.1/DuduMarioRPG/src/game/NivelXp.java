package game;

import java.util.HashMap;
import java.util.Map;

/*      Ideia:
HashMap usado para ligar um certo nivel com a xp necessaria para alcancar o
proximo nivel, tornando essa xp variavel invez de estatica.*/
public class NivelXp {
    
    private static Map<Integer,Integer> mapaNivelXp=new HashMap<Integer,Integer>();
    
    public static int xpParaProxNivel(int nivelAtual){
        if(mapaNivelXp.isEmpty())inicializaMap();
        return mapaNivelXp.get(nivelAtual);
    }
    
    public static void inicializaMap(){
        NivelXp.mapaNivelXp.put(1, 30);NivelXp.mapaNivelXp.put(2, 90);
        NivelXp.mapaNivelXp.put(3, 270);NivelXp.mapaNivelXp.put(4, 550);
        NivelXp.mapaNivelXp.put(5, 750);NivelXp.mapaNivelXp.put(6, 1000);
        NivelXp.mapaNivelXp.put(7, 1300);NivelXp.mapaNivelXp.put(8, 1650);
        NivelXp.mapaNivelXp.put(9, 2050);NivelXp.mapaNivelXp.put(10, 2600);
        NivelXp.mapaNivelXp.put(11, 3200);NivelXp.mapaNivelXp.put(12, 3900);
        NivelXp.mapaNivelXp.put(13, 4700);NivelXp.mapaNivelXp.put(14, 5700);
        NivelXp.mapaNivelXp.put(15, 6900);NivelXp.mapaNivelXp.put(16, 8300);
        NivelXp.mapaNivelXp.put(17, 9900);NivelXp.mapaNivelXp.put(18, 11900);
        NivelXp.mapaNivelXp.put(19, 14300);NivelXp.mapaNivelXp.put(20, Integer.MAX_VALUE);
    }
    
}