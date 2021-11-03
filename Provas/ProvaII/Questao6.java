/**
 * Fila dinamica
 * @author Max do Val Machado
 * @version 2 01/2015
 */
class Fila {
	private Celula primeiro;
	private Celula ultimo;


	/**
	 * Construtor da classe que cria uma fila sem elementos (somente no cabeca).
	 */
	public Fila() {
		primeiro = new Celula();
		ultimo = primeiro;
	}


	/**
	 * Insere elemento na fila (politica FIFO).
	 * @param x int elemento a inserir.
	 */
	public void inserir(int x) {
		ultimo.prox = new Celula(x);
		ultimo = ultimo.prox;
	}


	/**
	 * Remove elemento da fila (politica FIFO).
	 * @return Elemento removido.
	 * @trhows Exception Se a fila nao tiver elementos.
	 */
	public int remover() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover!");
		}

      Celula tmp = primeiro;
		primeiro = primeiro.prox;
		int resp = primeiro.elemento;
      tmp.prox = null;
      tmp = null;
		return resp;
	}


	/**
	 * Mostra os elementos separados por espacos.
	 */
	public void mostrar() {
		System.out.print("[ ");
		
		for(Celula i = primeiro.prox; i != null; i = i.prox) {
			System.out.print(i.elemento + " ");
		}
		
		System.out.println("] ");
	}
	public void separar(Fila f1,Fila f2) {	
		Celula i = f1.primeiro;
		for (/**/; i.prox != null; i = i.prox);
		Celula j = f2.primeiro;
		for (/**/; j.prox != null; j = j.prox);
		Celula k = this.primeiro;
		while ( k != null ) {
			if ( k != null && k.elemento % 2 != 0) {
				i.prox = new Celula(k.elemento);
				i = i.prox;
			} 
			if ( k != null && k.elemento % 2 == 0) {
				j.prox = new Celula(k.elemento);
				j = j.prox;	
			} 
			k = k.prox;
		}
	}

}

public class Questao6 {
	public static void main(String[] args) {
		Fila f = new Fila();
		Fila f1 = new Fila();
		Fila f2 = new Fila();
		f.inserir(5);
		f.inserir(3);
        f.inserir(4);
		f.inserir(1);
		f1.inserir(39);
		f1.inserir(214);
        f1.inserir(43);
		f1.inserir(84);
		f2.inserir(16);
		f2.inserir(8);
		f2.inserir(95);
		f2.inserir(12);
		f1.mostrar();
		f.separar(f1,f2);
		f1.mostrar();

	}
}
