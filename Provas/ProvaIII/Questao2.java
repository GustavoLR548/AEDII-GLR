class Celula {
	public int elemento; // Elemento inserido na celula.
	public Celula prox; // Aponta a celula prox.
	
	public Celula() { this(0); }

	public Celula(int elemento) {
      this.elemento = elemento;
      this.prox = null;
	}
}
class Pilha {
	private Celula topo;

	public Pilha() {
		topo = null;
	}

	public void inserir(int x) {
		Celula tmp = new Celula(x);
		tmp.prox = topo;
		topo = tmp;
		tmp = null;
	}

	public void mostrar() {
		System.out.print("[ ");
		for(Celula i = topo; i != null; i = i.prox){
			System.out.print(i.elemento + " ");
		}
		System.out.println("] ");
	}
	
	public void Soma(){
		int soma = somaRec(topo);
		if ( soma % 3 == 0) 
			MyIO.println(1);
		else 
			MyIO.println(0);
	}

	public int somaRec(Celula i) {
		int resp = 0;
		
		if(i != null)
			resp += i.elemento + somaRec(i.prox);
		
		return resp;
	}
 
	public void mostrar(Celula i) {
		if(i != null){
			mostrar(i.prox);
			System.out.print("- " + i.elemento + " -");
		}
	}
}


public class Questao2 {
	public static void main(String[] args) throws Exception{

		int c;
		
		c = MyIO.readInt();
		while ( c > 0 ) {
			Pilha p = new Pilha();
			for (int i = 0; i < c * 3 ; i++) {
				p.inserir(MyIO.readInt());
			}
			p.Soma();
			c = MyIO.readInt();
		}
	}
}
