package game;

import java.util.Random;

public class RandomRoll {
	public static int danoroll(int max){
        Random r = new Random();
        int ro=r.nextInt(max);
        return ro;
    }
	public static int encounterRoll() {
		Random r = new Random();
		int ro=r.nextInt(100);
		return ro;
	}
	public static int targetRoll() {
		Random r = new Random();
		int ro=r.nextInt(4);
		return ro;
	}
}
