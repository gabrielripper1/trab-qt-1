IMPORTANTE: A copia do arquivo Save.txt junto de PersonagensArquivo.java é apenas um "backup", o programa não lê ela.
O arquivo Save.txt precisa ser salvo junto dos binarios, na mesma pasta que PersonagensArquivo.class, representei pela pasta bin.

- A classe MochilaArquivo ainda nao funciona. - -

Caso não consiga achar a pasta, rode esse codigo na main para criar um arquivo Save.txt novo com personagens lvl 1:

Guerreiro p1=new Guerreiro("GUERREIRO");personagens.add(p1);
BlackMage p2=new BlackMage("MAGO");personagens.add(p2);
WhiteMage p3=new WhiteMage("CLERIGO");personagens.add(p3);
Rogue p4=new Rogue("ROGUE");personagens.add(p4);       
PersonagensArquivo.salvaTodos(personagens);
personagens.clear();