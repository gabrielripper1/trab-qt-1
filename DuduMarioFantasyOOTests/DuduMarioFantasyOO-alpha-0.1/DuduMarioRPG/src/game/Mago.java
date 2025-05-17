package game;

/*      Ideia:
Diferenciar as classes que usam mana e magia das demais. Colocar essas mecanicas
nesta interface.*/
public interface Mago {
	public int getMagia();
    public void setMagia(int magia);
    public int getMp();
    public void setMp(int mp);
    public int getMpA();
    public void setMpA(int mpA);
    public void regenMP();
}
