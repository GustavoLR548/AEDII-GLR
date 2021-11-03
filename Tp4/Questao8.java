import java.io.*;
import java.util.*;

class StarWars
{
	private String nome,corDoCabelo,corDaPele,corDosOlhos,anoNascimento,genero,homeworld;
	private int altura;
	private double peso;
	
	public StarWars ( ) 
	{

		nome = corDoCabelo = corDaPele = corDosOlhos = anoNascimento = genero = homeworld = "";
		altura = 0;
		peso = 0.0;

	}

	public StarWars (String diretorio) 
	{

		configPersonagem(diretorio);

	}

	public StarWars clone()
	{

		StarWars tmp = new StarWars();
		tmp.nome = this.nome;
		tmp.corDoCabelo = this.corDoCabelo;
		tmp.corDaPele = this.corDaPele;
		tmp.corDosOlhos = this.corDosOlhos;
		tmp.anoNascimento = this.anoNascimento;
		tmp.genero = this.genero;
		tmp.homeworld = this.homeworld;
		tmp.altura = this.altura;
		tmp.peso = this.peso;

		return tmp;	

	}

	public String getNome()
	{

		return nome;

	}

	public String getCorDoCabelo()
	{

		return corDoCabelo;

	}

	public String getCorDaPele()
	{

		return corDaPele;

	}

	public String getCorDosOlhos()
	{

		return corDosOlhos;

	}

	public String getAnoNascimento()
	{

		return anoNascimento;

	}

	public String getGenero()
	{

		return genero;

	}
	
	public String getHomeworld()
	{

		return homeworld;

	}

	public int getAltura()
	{	

		return altura;

	}

	public double getPeso()
	{	

		return peso;

	}

	public void setNome(String novoNome)
	{

		nome = novoNome;

	}

	public void setCorDoCabelo(String novaCorDoCabelo)
	{

		corDoCabelo = novaCorDoCabelo;

	}

	public void setCorDaPele(String novaCorDaPele)
	{

		corDaPele = novaCorDaPele;

	}
	
	public void setCorDosOlhos(String novaCorDosOlhos)
	{

		corDosOlhos = novaCorDosOlhos;

	}

	public void setAnoNascimento(String novoAnoNascimento)
	{

		anoNascimento = novoAnoNascimento;

	}

	public void setGenero(String novoGenero)
	{

		genero = novoGenero;

	}

	public void setHomeworld(String novoHomeworld)
	{

		homeworld = novoHomeworld;

	}

	public void setAltura(int novaAltura)
	{

		altura = novaAltura;

	}

	public void setPeso(double novoPeso)
	{

		peso = novoPeso;

	}
	
	public String imprimir()
	{

		
		String resp = " ## " + getNome() + " ## " + getAltura() + " ## ";

		if( getPeso() == (int)getPeso() )
			resp += (int)getPeso();
		else
			resp += getPeso();

		resp += " ## " + getCorDoCabelo() + " ## " +  getCorDaPele() + " ## " + getCorDosOlhos() + " ## " + getAnoNascimento() + " ## " + getGenero() + " ## " + getHomeworld() + " ## ";
		return resp;

	}

	public String pegarTexto(String diretorio)
	{
		String texto = "",linha = "";
		try {
			RandomAccessFile arq = new RandomAccessFile(diretorio,"r");
			linha = arq.readLine();

			while (linha != null) 
			{

				texto += linha;
				linha = arq.readLine();

			}

			arq.close();
		} 

		catch (IOException excecao) 
		{

			excecao.printStackTrace();

		}

		return texto;
	}

	public void configPersonagem(String diretorio)
	{

		int numInteiro;
		double numDouble;

		String texto = pegarTexto(diretorio),Split[];

		Split = texto.split("\'");

		setNome(Split[3]);
		
		if(Split[7].contains("unknown") == true)
		{

			setAltura(0);
		
		}		
		else
		{

			numInteiro = Integer.parseInt(Split[7]);
			setAltura(numInteiro);

		}
		if(Split[11].contains("unknown") == true)
		{

			setPeso(0);

		}
		else
		{
			
			numDouble = Double.parseDouble(Split[11]);
			setPeso(numDouble);

		}

		setCorDoCabelo(Split[15]);
		setCorDaPele(Split[19]);
		setCorDosOlhos(Split[23]);
		setAnoNascimento(Split[27]);
		setGenero(Split[31]);
		setHomeworld(Split[35]);

	}

} 



class No {
   public char elemento;
   public int tamanho = 255;
   public No[] prox;
   public boolean folha;
   
   public No (){
      this(' ');
   }

   public No (char elemento){
      this.elemento = elemento;

      prox = new No [tamanho];

      for (int i = 0; i < tamanho; i++){
         prox[i] = null;
      }

      folha = false;
   }

}

class ArvoreTrie {
   private No raiz;

   public ArvoreTrie(){
      raiz = new No();
   }

   public No getRaiz() {
	  return this.raiz;
   }

   public void inserir(String s) throws Exception {
	  if ( pesquisar(s) == false )
     	 inserir(s, raiz, 0);
   }
   public void inserir(String s, No no, int i) throws Exception {


      if(no.prox[s.charAt(i)] == null){
         no.prox[s.charAt(i)] = new No(s.charAt(i));

         if(i == s.length() - 1){
            no.prox[s.charAt(i)].folha = true;
         }else{
            inserir(s, no.prox[s.charAt(i)], i + 1);
         }

      } else if (no.prox[s.charAt(i)].folha == false && i < s.length() - 1){
         inserir(s, no.prox[s.charAt(i)], i + 1);

      } else {
         throw new Exception("Erro ao inserir!");
      } 
   }


   public boolean pesquisar(String s) throws Exception {
      return pesquisar(s, raiz, 0);
   }

   public boolean pesquisar(String s, No no, int i) throws Exception {
      boolean resp;

      if(no.prox[s.charAt(i)] == null){
         resp = false;
      } else if(i == s.length() - 1){
         resp = (no.prox[s.charAt(i)].folha == true);
      } else if(i < s.length() - 1 ){
         resp = pesquisar(s, no.prox[s.charAt(i)], i + 1);
      } else {
         throw new Exception("Erro ao pesquisar!");
      }

      return resp;
   }


   public void mostrar(){
      mostrar("", raiz);
   }

   public void mostrar(String s, No no) {
      if(no.folha == true){
		String tmp = s + no.elemento;
		MyIO.println(tmp.trim());
      } else {
         for(int i = 0; i < no.prox.length; i++){
            if(no.prox[i] != null){
               mostrar(s + no.elemento, no.prox[i]);
            }
         }
      }
   }

   public void getElementos(No no) throws Exception{
      getElementos("",no);
   }

   private void getElementos(String s, No no) throws Exception {
      if(no.folha == true){
		String tmp = s + no.elemento;
		inserir(tmp.trim());
      } else {
         for(int j = 0; j < no.prox.length; j++){
            if(no.prox[j] != null){
               getElementos(s + no.elemento, no.prox[j]);
            }
         }
      }
   }

	public void merge(ArvoreTrie a, ArvoreTrie b) throws Exception{

		getElementos(a.getRaiz());
		getElementos(b.getRaiz());
	}
   
}

public class Questao8 {

	public static void main(String[] args) throws Exception{

		ArvoreTrie trie1 = new ArvoreTrie();
		ArvoreTrie trie2 = new ArvoreTrie();
		ArvoreTrie trie3 = new ArvoreTrie();

		StarWars tmp = new StarWars();
		boolean resp;

		String entrada = MyIO.readLine();

		while( entrada.contains("FIM") != true )
		{

			tmp = new StarWars(ISO88591toUTF8(entrada));
			trie1.inserir(tmp.getNome());

			entrada = MyIO.readLine();

		}

		entrada = MyIO.readLine();

		while( entrada.contains("FIM") != true )
		{

			tmp = new StarWars(ISO88591toUTF8(entrada));
			trie2.inserir(tmp.getNome());

			entrada = MyIO.readLine();

		}
		
		trie3.merge(trie1,trie2);	

		entrada = MyIO.readLine();
		
		while( entrada.contains("FIM") != true )
		{			

			resp = trie3.pesquisar(ISO88591toUTF8(entrada));

			MyIO.print(entrada);

			if ( resp == true)
				MyIO.println(" SIM");
			else
				MyIO.println (" NÃO");	

			entrada = MyIO.readLine();	
		
		}
		
	}
	public static String ISO88591toUTF8(String strISO) throws Exception 
	{

		byte[] isoBytes = strISO.getBytes("ISO-8859-1");
		return new String(isoBytes, "UTF-8");

	}
}












