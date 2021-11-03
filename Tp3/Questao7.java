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
 * Lista dinamica
 * @author Max do Val Machado
 * @version 2 01/2015
 */

class CelulaDupla {
	public StarWars elemento;
	public CelulaDupla ant;
	public CelulaDupla prox;

	/**
	 * Construtor da classe.
	 */
	public CelulaDupla() {
		this.elemento = null;
		this.ant = this.prox = null;
	}


	/**
	 * Construtor da classe.
	 * @param elemento int inserido na celula.
	 */
	public CelulaDupla(StarWars elemento) {
		this.elemento = elemento;
		this.ant = this.prox = null;
	}
}
	

/**
 * Lista dupla dinamica
 * @author Max do Val Machado
 * @version 2 01/2015
 */
class ListaDupla {
	private CelulaDupla primeiro;
	private CelulaDupla ultimo;
	private int numComparacoes,numMovimentacoes;

	/**
	 * Construtor da classe que cria uma lista dupla sem elementos (somente no cabeca).
	 */
	public ListaDupla() {
		primeiro = new CelulaDupla();
		ultimo = primeiro;
		numComparacoes = numMovimentacoes = 0;
	}

	/**
	 * Insere um elemento na primeira posicao da lista.
	 * @param x int elemento a ser inserido.
	 */
	public void inserirInicio(StarWars x) {
		CelulaDupla tmp = new CelulaDupla(x);

		tmp.ant = primeiro;
		tmp.prox = primeiro.prox;
		primeiro.prox = tmp;
		if(primeiro == ultimo){
			ultimo = tmp;
		}else{
			tmp.prox.ant = tmp;
		}
		tmp = null;
	}

	/**
	 * Insere um elemento na ultima posicao da lista.
	 * @param x int elemento a ser inserido.
	 */
	public void inserirFim(StarWars x) {
		ultimo.prox = new CelulaDupla(x);
		ultimo.prox.ant = ultimo;
		ultimo = ultimo.prox;
	}

	/**
	 * Remove um elemento da primeira posicao da lista.
	 * @return resp int elemento a ser removido.
	 * @throws Exception Se a lista nao contiver elementos.
	 */
	public StarWars removerInicio() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");
		}

		CelulaDupla tmp = primeiro;
		primeiro = primeiro.prox;
		StarWars resp = primeiro.elemento.clone();
		tmp.prox = primeiro.ant = null;
		tmp = null;
		return resp;
	}

	/**
	 * Remove um elemento da ultima posicao da lista.
	 * @return resp int elemento a ser removido.
	 * @throws Exception Se a lista nao contiver elementos.
	 */
	public StarWars removerFim() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");
		} 
		StarWars resp = ultimo.elemento.clone();
		ultimo = ultimo.ant;
		ultimo.prox.ant = null;
		ultimo.prox = null;
		return resp;
	}

	/**
	 * Insere um elemento em uma posicao especifica considerando que o 
	 * primeiro elemento valido esta na posicao 0.
	 * @param x int elemento a ser inserido.
	 * @param pos int posicao da insercao.
	 * @throws Exception Se <code>posicao</code> invalida.
	 */
	public void inserir(StarWars x, int pos) throws Exception {

		int tamanho = tamanho();

		if(pos < 0 || pos > tamanho){
			throw new Exception("Erro ao inserir posicao (" + pos + " / tamanho = " + tamanho + ") invalida!");
		} else if (pos == 0){
			inserirInicio(x);
		} else if (pos == tamanho){
			inserirFim(x);
		} else {
			// Caminhar ate a posicao anterior a insercao
			CelulaDupla i = primeiro;
			for(int j = 0; j < pos; j++, i = i.prox);

			CelulaDupla tmp = new CelulaDupla(x);
			tmp.ant = i;
			tmp.prox = i.prox;
			tmp.ant.prox = tmp.prox.ant = tmp;
			tmp = i = null;
		}
	}

	/**
	 * Remove um elemento de uma posicao especifica da lista
	 * considerando que o primeiro elemento valido esta na posicao 0.
	 * @param posicao Meio da remocao.
	 * @return resp int elemento a ser removido.
	 * @throws Exception Se <code>posicao</code> invalida.
	 */
	public StarWars remover(int pos) throws Exception {
		StarWars resp;
		int tamanho = tamanho();

		if (primeiro == ultimo){
			throw new Exception("Erro ao remover (vazia)!");

		} else if(pos < 0 || pos >= tamanho){
			throw new Exception("Erro ao remover (posicao " + pos + " / " + tamanho + " invalida!");
		} else if (pos == 0){
			resp = removerInicio();
		} else if (pos == tamanho - 1){
			resp = removerFim();
		} else {
			// Caminhar ate a posicao anterior a insercao
			CelulaDupla i = primeiro.prox;
			for(int j = 0; j < pos; j++, i = i.prox);

			i.ant.prox = i.prox;
			i.prox.ant = i.ant;
			resp = i.elemento.clone();
			i.prox = i.ant = null;
			i = null;
		}

		return resp;
	}

	/**
	 * Mostra os elementos da lista separados por espacos.
	 */
	public void mostrar() {
		for (CelulaDupla i = primeiro.prox; i != null; i = i.prox) {
			System.out.println(i.elemento.imprimir());
		}
	}

	/**
	 * Mostra os elementos da lista de forma invertida 
	 * e separados por espacos.
	 */
	public void mostrarInverso() {
		for (CelulaDupla i = ultimo; i != primeiro; i = i.ant){
			System.out.println(i.elemento.imprimir());
		}
	}

    public CelulaDupla getCelulaPos(int pos)
    {
        CelulaDupla resp;
		
        int i = 0;

        for(resp = primeiro.prox; i < pos; resp = resp.prox,i++);

        return resp;
    }

	/**
	 * Calcula e retorna o tamanho, em numero de elementos, da lista.
	 * @return resp int tamanho
	 */
	public int tamanho() {

		int tamanho = 0; 
		for(CelulaDupla i = primeiro; i != ultimo; i = i.prox, tamanho++);
		return tamanho;

	}

    public void quicksort() throws Exception{

	  CelulaDupla i = primeiro.prox,j = primeiro.prox.prox;
	  int contador1 = 0, contador2 = 1;
      long startTime = System.nanoTime();

      RandomAccessFile arq = new RandomAccessFile("matrıcula_quicksort2.txt","rw");
      quicksortCabelo(0, tamanho()-1);

	  while ( contador1 != tamanho()-1 && contador2 != tamanho()-1 )
	  {
			while (j.elemento.getCorDoCabelo().compareTo(i.elemento.getCorDoCabelo()) == 0 ) 
			{

					contador2++;
					j = j.prox;
		
			}
	
			if ( contador2 - contador1 != 0 )
				quicksortNome(contador1,contador2-1);
	
			
			contador1 = contador2;	
			i = j;	
	

	  }

      long endTime   = System.nanoTime();

      arq.writeUTF("655264\t"+numComparacoes+"\t"+numMovimentacoes+"\t" + (endTime - startTime));

	  arq.close();

   }
 
    /**
     * Algoritmo de ordenacao Quicksort.
    * @param int esq inicio do array a ser ordenado
    * @param int dir fim do array a ser ordenado
     */
    private void quicksortCabelo(int esq, int dir) {
        int i = esq, j = dir;
        StarWars pivo = getCelulaPos((int)((esq+dir)/2)).elemento.clone();

        while (i <= j) {
            while (getCelulaPos(i).elemento.getCorDoCabelo().compareTo(pivo.getCorDoCabelo()) < 0 ) {
				i++;
				numComparacoes++;
            } 

			while (getCelulaPos(j).elemento.getCorDoCabelo().compareTo(pivo.getCorDoCabelo()) > 0) {
				j--;
				numComparacoes++;
            } 

			if (i <= j) {
				numMovimentacoes++;
                swap(getCelulaPos(i), getCelulaPos(j));
                i++;
                j--;
            }
        }
        if (esq < j)  quicksortCabelo(esq, j);
        if (i < dir)  quicksortCabelo(i, dir);
    }

    private void quicksortNome(int esq, int dir) {
        int i = esq, j = dir;
        StarWars pivo = getCelulaPos((int)((esq+dir)/2)).elemento.clone();

        while (i <= j) {
            while (getCelulaPos(i).elemento.getNome().compareTo(pivo.getNome()) < 0 ) {
				i++;
				numComparacoes++;
            } 

			while (getCelulaPos(j).elemento.getNome().compareTo(pivo.getNome()) > 0) {
				j--;
				numComparacoes++;
            } 

			if (i <= j) {
				numMovimentacoes++;
                swap(getCelulaPos(i), getCelulaPos(j));
                i++;
                j--;
            }
        }
        if (esq < j)  quicksortNome(esq, j);
        if (i < dir)  quicksortNome(i, dir);
    }

   public void swap(CelulaDupla i, CelulaDupla j) {

      StarWars temp = i.elemento.clone();
      i.elemento = j.elemento.clone();
      j.elemento = temp.clone();

   }

}



public class Questao7
{

	public static void main(String[] args) throws Exception
	{

		StarWars a = new StarWars();
		ListaDupla lista = new ListaDupla();

		String entrada = MyIO.readLine();

		while( entrada.contains("FIM") != true )
		{

			a = new StarWars(ISO88591toUTF8(entrada));
			lista.inserirFim(a);

			entrada = MyIO.readLine();

		}

		lista.quicksort();

		lista.mostrar();
	}


	public static String ISO88591toUTF8(String strISO) throws Exception 
	{

		byte[] isoBytes = strISO.getBytes("ISO-8859-1");
		return new String(isoBytes, "UTF-8");

	}

}


