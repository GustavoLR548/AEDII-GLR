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

/**
 * Lista estatica com algoritmo de Ordenacao heapsort
 * @author Max do Val Machado
 * @Modificada por MysteRys337
 * @version 2 01/2015
 */
class Lista {
   private StarWars[] array;
   private int n;
   private int numComparacoes,numMovimentacoes;
 
 
   /**
    * Construtor da classe.
    */
   public Lista () {
      this(6);
	  numComparacoes = numMovimentacoes = 0;
   }
 
 
   /**
    * Construtor da classe.
    * @param tamanho Tamanho da lista.
    */
   public Lista (int tamanho){
      array = new StarWars[tamanho];
      n = numComparacoes = numMovimentacoes = 0;
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
         MyIO.println(array[i].imprimir());
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
    * Algoritmo de ordenacao countingsort.
    */

    public int getMaior() {

       double maior = array[0].getPeso();
 
        for (int i = 0; i < n; i++) {
         if( maior < array[i].getPeso() ){
            maior = array[i].getPeso();
         }
        }
       return (int)maior;    
    }
 
 
    /**
     * Algoritmo de ordenacao Countingsort.
     */
    public void countingsort() throws Exception{
      //Array para contar o numero de ocorrencias de cada elemento

      long startTime = System.nanoTime();
      RandomAccessFile arq = new RandomAccessFile("matrÄ±cula_countingsort.txt","rw");

      int numComparacoes = 0,numMovimentacoes = 0;

      double[] count = new double[getMaior() + 1];
      StarWars[] ordenado = new StarWars[n];
 
      //Inicializar cada posicao do array de contagem 
	  for (int i = 0; i < count.length; count[i] = 0.0, i++);

      //Agora, o count[i] contem o numero de elemento iguais a i
      for (int i = 0; i < n; count[(int)array[i].getPeso()]++, i++) {
			System.out.println(i);
	  }

      //Agora, o count[i] contem o numero de elemento menores ou iguais a i
      for(int i = 1; i < count.length; count[i] += count[i-1], i++);

      //Ordenando
      for(int i = n-1; i >= 0; ordenado[(int)count[(int)array[i].getPeso()]-1] = array[i].clone(), count[(int)array[i].getPeso()]--, i--,numComparacoes++,numMovimentacoes++);

      //Copiando para o array original
      for(int i = 0; i < n; array[i] = ordenado[i].clone(), i++);

      long endTime   = System.nanoTime();

      arq.writeUTF("655264\t"+numComparacoes+"\t"+numMovimentacoes+"\t" + (endTime - startTime));

      arq.close();

   }


}
 

public class Questao15
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

		lista.countingsort();

		lista.mostrar();

	}


	public static String ISO88591toUTF8(String strISO) throws Exception 
	{

		byte[] isoBytes = strISO.getBytes("ISO-8859-1");
		return new String(isoBytes, "UTF-8");

	}

}

