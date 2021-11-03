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
	public int elemento; // Conteudo do no.
	public No esq; // No da esquerda.
	public No dir; // No da direita.
    public No2 outro;
	
   /**
	 * Construtor da classe.
	 * @param elemento Conteudo do no.
	 */
	public No(int elemento) {
		this.elemento = elemento;
		this.esq = null;
		this.dir = null;
        this.outro = null;
	}

	/**
	 * Construtor da classe.
	 * @param elemento Conteudo do no.
	 * @param esq No da esquerda.
	 * @param dir No da direita.
	 */
	public No(int elemento, No esq, No dir) {
		this.elemento = elemento;
		this.esq = esq;
		this.dir = dir;
        this.outro = null;
	}
}

class No2 {
	public StarWars elemento; // Conteudo do no.
	public No2 esq; // No da esquerda.
	public No2 dir; // No da direita.
	
   /**
	 * Construtor da classe.
	 * @param elemento Conteudo do no.
	 */
	public No2(StarWars elemento) {
		this.elemento = elemento.clone();
		this.esq = null;
		this.dir = null;
	}

	/**
	 * Construtor da classe.
	 * @param elemento Conteudo do no.
	 * @param esq No2 da esquerda.
	 * @param dir No2 da direita.
	 */
	public No2(StarWars elemento, No2 esq, No2 dir) {
		this.elemento = elemento.clone();
		this.esq = esq;
		this.dir = dir;
	}
}
/**
 * Arvore de arvore
 * @author Max do Val Machado
 */
class ArvoreArvore {
	private No raiz; // Raiz da arvore.

	/**
	 * Construtor da classe.
	 */
	public ArvoreArvore() throws Exception {
		raiz = null;
        construir();
	}

	private void construir() throws Exception {
		inserirINT(7);
        inserirINT(3);
		inserirINT(11);
        inserirINT(1);
		inserirINT(5);
        inserirINT(9);
        inserirINT(12);
        inserirINT(0);
        inserirINT(2);
        inserirINT(4);
        inserirINT(6);
        inserirINT(8);
        inserirINT(10);
        inserirINT(13);
        inserirINT(14);
	}

	/**
	 * Metodo publico iterativo para pesquisar elemento.
	 * @param elemento Elemento que sera procurado.
	 * @return <code>true</code> se o elemento existir,
	 * <code>false</code> em caso contrario.
	 */
	public boolean pesquisar(StarWars elemento) {
		System.out.print(elemento.getNome() + " raiz ");
		return pesquisar(raiz, elemento);
	}

	private boolean pesquisar(No no, StarWars x) {
      boolean resp;
		if (no == null) { 
         resp = false;

      } else if (x.getAltura() % 15 == no.elemento ) { 
         resp = pesquisarSegundaArvore(no.outro, x.getNome()); 

      } else if (x.getAltura() % 15 < no.elemento ) { 
		 System.out.print("esq ");
         resp = pesquisar(no.esq, x); 

      } else { 
		 System.out.print("dir ");
         resp = pesquisar(no.dir, x); 
      }
      return resp;
	}

	private boolean pesquisarSegundaArvore(No2 no, String x) {
      boolean resp;
		if (no == null) { 
         resp = false;

      } else if (x.equals(no.elemento.getNome())) { 
         resp = true; 

      } else if (x.compareTo(no.elemento.getNome()) < 0) { 
		 System.out.print("ESQ ");
         resp = pesquisarSegundaArvore(no.esq, x); 

      } else { 
		 System.out.print("DIR ");
         resp = pesquisarSegundaArvore(no.dir, x); 
      }
      return resp;
	}

   public void inserirINT(int x) throws Exception {
      raiz = inserirINT(x,raiz);
   }

   private No inserirINT(int x, No i) throws Exception {
		if (i == null) {
         i = new No(x);

      } else if (x < i.elemento ) {
         i.esq = inserirINT(x, i.esq);

      } else if (x > i.elemento ) {
         i.dir = inserirINT(x, i.dir);

      } else {
         throw new Exception("Erro ao inserir!");
      }

		return i;
	}

   public void inserir(StarWars x) throws Exception{
      raiz = inserir(x,raiz);
   }

   private No inserir(StarWars x, No i) throws Exception {
		if (i == null) {
         throw new Exception("Erro ao inserir!");

      } else if (x.getAltura() % 15 == i.elemento) {
         i.outro = inserirSegundaArvore(x, i.outro);

      } else if (x.getAltura() % 15 < i.elemento ) {
         i.esq = inserir(x, i.esq);

      } else {
         i.dir = inserir(x, i.dir); 
      }

		return i;
	}

   private No2 inserirSegundaArvore(StarWars x, No2 i) throws Exception {
      if (i == null) {
         i = new No2(x);

      } else if (x.getNome().compareTo(i.elemento.getNome()) < 0 ) {
         i.esq = inserirSegundaArvore(x, i.esq);

      } else if (x.getNome().compareTo(i.elemento.getNome()) > 0) {
         i.dir = inserirSegundaArvore(x, i.dir);

      } else {
         throw new Exception("Erro ao inserir!"); 
      }

		return i;
	}

	/**
	 * Metodo publico iterativo para exibir elementos.
	 */
	public boolean mostrarPre(String x) {
		System.out.print(x + " raiz ");
		return mostrarPre(raiz, x);
	}

	/**
	 * Metodo privado recursivo para exibir elementos.
	 * @param i No em analise.
	 */
	private boolean mostrarPre(No i,String x) {
		boolean resp = false;
		if (i != null) {
			resp = mostrarPreOutro(i.outro,x);
			if ( resp == false ) {
				System.out.print("esq ");
				resp = mostrarPre(i.esq,x); // Elementos da esquerda.
			} if ( resp == false ) {
				System.out.print("dir ");
				resp = mostrarPre(i.dir,x); // Elementos da direita.
			}
		}
		return resp;
	}
	private boolean mostrarPreOutro(No2 i,String x) {
		boolean resp = false;
		if (i != null) {
			if ( x.contains(i.elemento.getNome()) == true ) {
				resp = true;
			}
			else {
				System.out.print("ESQ ");
				resp = mostrarPreOutro(i.esq,x); // Elementos da esquerda.
				if ( resp == false ) {
					System.out.print("DIR ");
					resp = mostrarPreOutro(i.dir,x); // Elementos da direita.
				}
			}
		}
		return resp;
	}

}

public class Questao2 {

	public static void main(String[] args) throws Exception{

		ArvoreArvore a = new ArvoreArvore();
		StarWars tmp = new StarWars();
		boolean resp;

		String entrada = MyIO.readLine();

		while( entrada.contains("FIM") != true )
		{

			tmp = new StarWars(ISO88591toUTF8(entrada));
			a.inserir(tmp);

			entrada = MyIO.readLine();

		}

		entrada = MyIO.readLine();

		while( entrada.contains("FIM") != true )
		{
		
			resp = a.mostrarPre(ISO88591toUTF8(entrada));

			if ( resp == true)
				MyIO.println("SIM");
			else
				MyIO.println ("N"+(char)195+"O");	

			entrada = MyIO.readLine();	
		
		}

	}

	public static String ISO88591toUTF8(String strISO) throws Exception 
	{

		byte[] isoBytes = strISO.getBytes("ISO-8859-1");
		return new String(isoBytes, "UTF-8");

	}

}
