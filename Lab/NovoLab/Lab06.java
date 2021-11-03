import java.io.*;

/**
 * Celula Dupla (lista dupla dinamica)
 * @author Max do Val Machado
 * @version 2 01/2015
 */
class CelulaDupla {

	public String elemento;
	public CelulaDupla ant;
	public CelulaDupla prox;

	/**
	 * Construtor da classe.
	 */
	public CelulaDupla() {
		this("");
	}


	/**
	 * Construtor da classe.
	 * @param elemento int inserido na celula.
	 */
	public CelulaDupla(String elemento) {
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

	/**
	 * Construtor da classe que cria uma lista dupla sem elementos (somente no cabeca).
	 */

	public ListaDupla() {

		primeiro = new CelulaDupla();
		ultimo = primeiro;
	}

	/**
	 * Insere um elemento na primeira posicao da lista.
	 * @param x int elemento a ser inserido.
	 */
	public void inserirInicio(String x) {

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
	public void inserirFim(String x) {

		ultimo.prox = new CelulaDupla(x);
		ultimo.prox.ant = ultimo;
		ultimo = ultimo.prox;
	}

	/**
	 * Remove um elemento da primeira posicao da lista.
	 * @return resp int elemento a ser removido.
	 * @throws Exception Se a lista nao contiver elementos.
	 */
	public String removerInicio() throws Exception {

		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");
		}

		CelulaDupla tmp = primeiro;
		primeiro = primeiro.prox;
		String resp = primeiro.elemento;
		tmp.prox = primeiro.ant = null;
		tmp = null;
		return resp;

	}

	/**
	 * Remove um elemento da ultima posicao da lista.
	 * @return resp int elemento a ser removido.
	 * @throws Exception Se a lista nao contiver elementos.
	 */
	public String removerFim() throws Exception {

		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");
		} 
		String resp = ultimo.elemento;
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
	public void inserir(String x, int pos) throws Exception {

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
	public String remover(int pos) throws Exception {
		String resp;
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
			resp = i.elemento;
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
			System.out.println(i.elemento);
		}
	}

	/**
	 * Mostra os elementos da lista de forma invertida 
	 * e separados por espacos.
	 */
	public void mostrarInverso() {
		System.out.print("[ ");
		for (CelulaDupla i = ultimo; i != primeiro; i = i.ant){
			System.out.print(i.elemento + " ");
		}
		System.out.println("] "); // Termina de mostrar.
	}

	/**
	 * Procura um elemento e retorna se ele existe.
	 * @param x Elemento a pesquisar.
	 * @return <code>true</code> se o elemento existir,
	 * <code>false</code> em caso contrario.
	 */
	public boolean pesquisar(String x) {
		boolean resp = false;
		for (CelulaDupla i = primeiro.prox; i != null; i = i.prox) {
			if(i.elemento.compareTo(x) == 0){
				resp = true;
				i = ultimo;
			}
		}
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

	public void insercao() {

		for ( CelulaDupla i = primeiro.prox.prox ; i != null ; i = i.prox) {

			String tmp = i.elemento;
         	CelulaDupla j = i.ant;

        	 while ((j != null) && (j.elemento.compareTo(tmp) > 0)) {
           		 j.prox.elemento = j.elemento;
            	 j = j.ant;
       		 }
        	 j.prox.elemento = tmp;
      	}
	 }

	public int listaTelefonica()
	{
	
		int resp = 0;
		
		for ( CelulaDupla i = primeiro.prox; i.prox != null ; i = i.prox )
		{

			CelulaDupla j = i.prox;
			int index = 0;

			while ( index < i.elemento.length() && index < j.elemento.length() && i.elemento.charAt(index) == j.elemento.charAt(index) )
			{
				index++;
			}

			resp += index;

		}

		return resp;

	}

}


public class Lab06
{
	
	public static void main(String[] args) throws Exception
	{
	
		//Declarando variaveis
		InputStreamReader r = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(r);

		String n;
		String entrada;
		boolean pararLeitura = false;
		int saida;
		
		do 
		{//Leitura da entrada
			
			n = br.readLine();
			
			if ( n == null || n.length() == 0 ) 
			{// Se for lido "FIM"

				pararLeitura = true;

			}
			else 
			{// Caso contrario

				ListaDupla l = new ListaDupla();

				for ( int i = 0; i < Integer.parseInt(n) ; i++ ) 
				{
					entrada = br.readLine();
					l.inserirFim(entrada);
				}
				l.insercao();

				saida = l.listaTelefonica();	
				
				MyIO.println(saida);

			}
			

		} while ( pararLeitura == false );	
		
	}

}

