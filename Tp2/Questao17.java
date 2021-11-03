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
 * Lista estatica com algoritmo de Ordenacao por Selecao
 * @author Max do Val Machado
 * @Modificada por MysteRys337
 * @version 2 01/2015
 */
class Lista {
   private StarWars[] array;
   private int n,numComparacoes = 0,numMovimentacoes = 0;
 
 
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
    * Retorna o array criado pela lista
    */
   public StarWars[] getArray(){
	return array;
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
         System.out.println(array[i].imprimir());
      }

   }
 
 
   /**
    * Procura um elemento e retorna se ele existe.
    * @param x int elemento a ser pesquisado.
    * @return <code>true</code> se o array existir,
    * <code>false</code> em caso contrario.
    */
   public boolean pesquisar(String x) {
      boolean retorno = false;
      for (int i = 0; i < n && retorno == false; i++) {
         retorno = (x.equals(array[i].getNome()) == true);
      }
      return retorno;
   }

   public void mostrarRec (){
      mostrarRec(0);
   }
 
   public void mostrarRec(int i){
      if(i < n){
         System.out.print(array[i].imprimir());
         mostrarRec(i + 1);
      }
   }

   /**
    * Algoritmo de ordenacao por selecao.
    */
	public void mergesort() throws Exception
	{

   	 	long startTime = System.nanoTime();

	    RandomAccessFile arq = new RandomAccessFile("matrıcula_mergesort.txt","rw");				

		mergesort(array, 0,n - 1);

    	long endTime   = System.nanoTime();

    	arq.writeUTF("655264\t"+numComparacoes+"\t"+numMovimentacoes+"\t" + (endTime - startTime));

		arq.close();

	}	

	public void mergesort(StarWars[] array, int l, int r) 
	{
 
   		 if (l < r) 
	    { 
    	    // Same as (l+r)/2, but avoids overflow for 
    	    // large l and h 
    	    int m = l+(r-l)/2; 
  	
    	    // Sort first and second halves 
    	    mergesort(array, l, m); 
    	    mergesort(array, m+1, r); 
  
    	    merge(array, l, m, r); 
    	} 
	} 

	public void merge(StarWars[] array, int l, int m, int r) 
	{ 
    	int i, j, k; 
   		int n1 = m - l + 1; 
    	int n2 =  r - m; 
  
    	/* create temp arrays */
    	StarWars[] L, R;

		L = new StarWars[n1];
		R = new StarWars[n2];
  
    	/* Copy data to temp arrays L[] and R[] */
    	for (i = 0; i < n1; i++) 
       		L[i] = array[l + i].clone(); 
    	for (j = 0; j < n2; j++) 
        	R[j] = array[m + 1+ j].clone(); 
  
    	/* Merge the temp arrays back into arr[l..r]*/
    	i = 0; // Initial index of first subarray 
    	j = 0; // Initial index of second subarray 
   		k = l; // Initial index of merged subarray 
    	while (i < n1 && j < n2) 
    	{ 
        	if (L[i].getHomeworld().compareTo(R[j].getHomeworld()) <= 0) 
        	{ 
            	array[k] = L[i].clone(); 
            	i++; 
        	} 
        	else
        	{ 
            	array[k] = R[j].clone(); 
            	j++; 
        	} 
        	k++; 
    	} 
  
    	/* Copy the remaining elements of L[], if there 
       		are any */
    	while (i < n1) 
    	{ 
        	array[k] = L[i].clone(); 
        	i++; 
        	k++; 
    	} 
  
    	/* Copy the remaining elements of R[], if there 
       		are any */
    	while (j < n2) 
    	{ 
        	array[k] = R[j].clone(); 
        	j++; 
        	k++; 
    	} 
	} 
	

	}
 

public class Questao17
{

	public static void main(String[] args) throws Exception
	{
	
		StarWars a = new StarWars();
		Lista lista = new Lista(41);
		
		String entrada = MyIO.readLine();

		while( entrada.contains("FIM") != true )
		{

			a = new StarWars(ISO88591toUTF8(entrada));
			lista.inserirFim(a);

			entrada = MyIO.readLine();

		}

		lista.mergesort();

		lista.mostrar();

	}


	public static String ISO88591toUTF8(String strISO) throws Exception 
	{

		byte[] isoBytes = strISO.getBytes("ISO-8859-1");
		return new String(isoBytes, "UTF-8");

	}

}

