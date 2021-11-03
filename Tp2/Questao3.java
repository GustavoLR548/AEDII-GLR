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

		
		String resp = " ## " + getNome() + " ## " + getAltura() + " ## 0";

		/*if( getPeso() == (int)getPeso() )
			resp += (int)getPeso();
		else
			resp += getPeso();
         */
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
			
			Split[11] = Split[11].replace(",","");
			Split[11] = Split[11].replace(".","");
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

/**
 * Lista estatica
 * @author Max do Val Machado
 * @Modificada por MysteRys337
 * @version 2 01/2015
 */
class Lista {
   private StarWars[] array;
   private int n;
 
 
   /**
    * Construtor da classe.
    */
   public Lista () {
      this(6);
   }
 
 
   /**
    * Construtor da classe.
    * @param tamanho Tamanho da lista.
    */
   public Lista (int tamanho){
      array = new StarWars[tamanho];
      n = 0;
   }
 
 
   /**
    * Insere um elemento na primeira posicao da lista e move os demais
    * elementos para o fim da lista.
    * @param x int elemento a ser inserido.
    * @throws Exception Se a lista estiver cheia.
    */
   public void inserirInicio(StarWars x) throws Exception {
 
      //validar insercao
      if(n >= array.length){
         throw new Exception("Erro ao inserir!");
      } 
 
      //levar elementos para o fim do array
      for(int i = n; i > 0; i--){
         array[i] = array[i-1].clone();
      }
 
      array[0] = x.clone();
      n++;
   }
 
 
   /**
    * Insere um elemento na ultima posicao da lista.
    * @param x int elemento a ser inserido.
    * @throws Exception Se a lista estiver cheia.
    */
   public void inserirFim(StarWars x) throws Exception {
 
      //validar insercao
      if(n >= array.length){
         throw new Exception("Erro ao inserir!");
      }
 
      array[n] = x.clone();
      n++;
   }
 
 
   /**
    * Insere um elemento em uma posicao especifica e move os demais
    * elementos para o fim da lista.
    * @param x int elemento a ser inserido.
    * @param pos Posicao de insercao.
    * @throws Exception Se a lista estiver cheia ou a posicao invalida.
    */
   public void inserir(StarWars x, int pos) throws Exception {
 
      //validar insercao
      if(n >= array.length || pos < 0 || pos > n){
         throw new Exception("Erro ao inserir!");
      }
 
      //levar elementos para o fim do array
      for(int i = n; i > pos; i--){
         array[i] = array[i-1].clone();
      }
 
      array[pos] = x.clone();
      n++;
   }
 
 
   /**
    * Remove um elemento da primeira posicao da lista e movimenta 
    * os demais elementos para o inicio da mesma.
    * @return resp int elemento a ser removido.
    * @throws Exception Se a lista estiver vazia.
    */
   public StarWars removerInicio() throws Exception {
 
      //validar remocao
      if (n == 0) {
         throw new Exception("Erro ao remover!");
      }
 
      StarWars resp = array[0].clone();
      n--;
 
      for(int i = 0; i < n; i++){
         array[i] = array[i+1].clone();
      }
 
      return resp;
   }
 
 
   /**
    * Remove um elemento da ultima posicao da lista.
    * @return resp int elemento a ser removido.
    * @throws Exception Se a lista estiver vazia.
    */
   public StarWars removerFim() throws Exception {
 
      //validar remocao
      if (n == 0) {
         throw new Exception("Erro ao remover!");
      }
 
      return array[--n];
   }
 
 
   /**
    * Remove um elemento de uma posicao especifica da lista e 
    * movimenta os demais elementos para o inicio da mesma.
    * @param pos Posicao de remocao.
    * @return resp int elemento a ser removido.
    * @throws Exception Se a lista estiver vazia ou a posicao for invalida.
    */
   public StarWars remover(int pos) throws Exception {
 
      //validar remocao
      if (n == 0 || pos < 0 || pos >= n) {
         throw new Exception("Erro ao remover!");
      }
 
      StarWars resp = array[pos].clone();
      n--;
 
      for(int i = pos; i < n; i++){
         array[i] = array[i+1].clone();
      }
 
      return resp;
   }
 
 
   /**
    * Mostra os elementos da lista separados por quebra de linha.
    */
   public void mostrar (){

      for(int i = 0; i < n; i++){
         MyIO.println("[" + i + "] " + array[i].imprimir());
      }

   }
 
 
   /**
    * Procura um elemento e retorna se ele existe.
    * @param x int elemento a ser pesquisado.
    * @return <code>true</code> se o array existir,
    * <code>false</code> em caso contrario.
    */
   public boolean pesquisar(StarWars x) {
      boolean retorno = false;
      for (int i = 0; i < n && retorno == false; i++) {
         retorno = (ehIgual(array[i],x) == true);
      }
      return retorno;
   }
   public void mostrarRec (){
      System.out.print("[ ");
      mostrarRec(0);
      System.out.println("]");
   }
 
   public void mostrarRec(int i){
      if(i < n){
         System.out.print(array[i].imprimir() + " ");
         mostrarRec(i + 1);
      }
   }

   public boolean ehIgual(StarWars a, StarWars b){
      return (a.getNome().equals( b.getNome() ) == true && a.getCorDoCabelo().equals(b.getCorDoCabelo()) == true && a.getCorDaPele().equals( b.getCorDaPele() )  == true && a.getCorDosOlhos().equals( b.getCorDosOlhos() ) == true && a.getAnoNascimento().equals( b.getAnoNascimento() ) == true && a.getGenero().equals( b.getGenero() ) == true  && a.getHomeworld().equals( b.getHomeworld() ) == true && a.getAltura() == b.getAltura() && a.getPeso() == b.getPeso() ); 
 
}

}

public class Questao3
{

	public static void main(String[] args) throws Exception
	{

		StarWars a = new StarWars();
		Lista lista = new Lista(100);
		String Split[];
		int n,numInteiro;

		String entrada = MyIO.readLine();

		while( entrada.contains("FIM") != true )
		{

			a = new StarWars(ISO88591toUTF8(entrada));
			lista.inserirFim(a);

			entrada = MyIO.readLine();

		}

		n = MyIO.readInt();

		for ( int i = 0 ; i < n ; i++ )
		{
		
			entrada = MyIO.readLine();
			Split = entrada.split(" ");
			
			if ( Split[0].equals("R*") == true )
			{

				numInteiro = Integer.parseInt(Split[1]);
				a = lista.remover(numInteiro);
				MyIO.println("(R) " + a.getNome() );

			}
			else if ( Split[0].equals("I*") == true ) 
			{

				numInteiro = Integer.parseInt(Split[1]);
				a = new StarWars(ISO88591toUTF8(Split[2]));
				lista.inserir(a,numInteiro);


			}
			else if ( Split[0].equals("RI") == true )
			{

				a = lista.removerInicio();
				MyIO.println("(R) " + a.getNome() );
		
			}
			else if ( Split[0].equals("RF") == true )
			{

				a = lista.removerFim();
				MyIO.println("(R) " + a.getNome() );
		
			}
			else if ( Split[0].equals("II") == true ) 
			{

				a = new StarWars(ISO88591toUTF8(Split[1]));
				lista.inserirInicio(a);


			}
			else if ( Split[0].equals("IF") == true ) 
			{

				a = new StarWars(Split[1]);
				lista.inserirFim(a);


			}


		}

		lista.mostrar();

	}


	public static String ISO88591toUTF8(String strISO) throws Exception 
	{

		byte[] isoBytes = strISO.getBytes("ISO-8859-1");
		return new String(isoBytes, "UTF-8");

	}

}

