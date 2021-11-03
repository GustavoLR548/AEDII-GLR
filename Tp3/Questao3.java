import java.io.*;
import java.util.*;
import java.lang.Math; 

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
 * Lista dinamica
 * @author Max do Val Machado
 * @version 2 01/2015
 */

class Celula {
	public StarWars elemento; // Elemento inserido na celula.
	public Celula prox; // Aponta a celula prox.
	
	public Celula() { 
		this.elemento = null;
		this.prox = null;
    }

	public Celula(StarWars elemento) {
      this.elemento = elemento.clone();
      this.prox = null;
	}
}


class Pilha {
	private Celula topo;
	public int contador;

	public Pilha() {
		topo = null;
	}

	public void inserir(StarWars x) {
		Celula tmp = new Celula(x);
		tmp.prox = topo;
		topo = tmp;
		tmp = null;
	}

	public StarWars remover() throws Exception {
		if (topo == null) {
			throw new Exception("Erro ao remover!");
		}

		StarWars resp = topo.elemento.clone();
		Celula tmp = topo;
		topo = topo.prox;
		tmp.prox = null;
		tmp = null;
		return resp;
	}

	public void mostrar() {
		mostrar(topo);
		contador = 0;
	}

	public void mostrar(Celula i) {

		if ( i.prox != null ) {
			mostrar(i.prox);
			contador++;
		}
		System.out.println("["+contador+"] " + i.elemento.imprimir());
	}

}

class Fila {
	private Celula primeiro;
	private Celula ultimo;
	private int tam;


	/**
	 * Construtor da classe que cria uma fila sem elementos (somente no cabeca).
	 */
	public Fila() {
		primeiro = new Celula();
		ultimo = primeiro;
		tam = 0;
	}

	public int getTam() {
		return tam;
	}
	/**
	 * Insere elemento na fila (politica FIFO).
	 * @param x int elemento a inserir.
	 */
	public void inserir(StarWars x) {
		ultimo.prox = new Celula(x);
		ultimo = ultimo.prox;
		tam++;
	}


	/**
	 * Remove elemento da fila (politica FIFO).
	 * @return Elemento removido.
	 * @trhows Exception Se a fila nao tiver elementos.
	 */
	public StarWars remover() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover!");
		}

        Celula tmp = primeiro;
		primeiro = primeiro.prox;
		StarWars resp = primeiro.elemento.clone();
        tmp.prox = null;
        tmp = null;
		tam--;
		return resp;
	}

	public void imprimirMediaAltura() {

		float acumulador = 0;
		int n = 0;

    	Celula i;
    	for (i = primeiro.prox; i != null; i = i.prox) {

    		acumulador += i.elemento.getAltura();	
			n++;

    	}

		float resp = acumulador/n;

		System.out.println(Math.round(resp));

}

	/**
	 * Mostra os elementos separados por espacos.
	 */
	public void mostrar() {
		int contador = 0;
		for (Celula i = primeiro.prox; i != null; i = i.prox,contador++) {
			System.out.println("["+contador+"] " + i.elemento.imprimir());
		}
	}
}


public class Questao3
{

	public static void main(String[] args) throws Exception
	{

		StarWars a = new StarWars(),tmp = new StarWars();
		Fila fila = new Fila();
		String Split[];
		int n;

		String entrada = MyIO.readLine();

		while( entrada.contains("FIM") != true )
		{

			a = new StarWars(ISO88591toUTF8(entrada));
		
			if ( fila.getTam() == 5 )
			{

				tmp = fila.remover();

			}
			fila.inserir(a);
			fila.imprimirMediaAltura();

			entrada = MyIO.readLine();

		}

		n = MyIO.readInt();

		for ( int i = 0 ; i < n ; i++ )
		{
		
			entrada = MyIO.readLine();
			Split = entrada.split(" ");
			
			if ( Split[0].equals("I") == true )
			{

				a = new StarWars(ISO88591toUTF8(Split[1]));

				if ( fila.getTam() == 5 )
				{

					tmp = fila.remover();

			    }
				fila.inserir(a);
				fila.imprimirMediaAltura();

			}
			else if ( Split[0].equals("R") == true ) 
			{

				a = fila.remover();
				MyIO.println("(R) " + a.getNome() );

			}


		}
		fila.mostrar();

	}


	public static String ISO88591toUTF8(String strISO) throws Exception 
	{

		byte[] isoBytes = strISO.getBytes("ISO-8859-1");
		return new String(isoBytes, "UTF-8");

	}

}


