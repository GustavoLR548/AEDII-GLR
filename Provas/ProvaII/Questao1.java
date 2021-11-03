import java.util.Random;

class CelulaM {

	public int elemento;
	public CelulaM inf,dir,esq,sup;
	
	public CelulaM() {
		
		this.elemento = 0;
		this.sup = null;
		this.inf = null;
		this.dir = null;
		this.esq = null;
	}
		
	public CelulaM(int elemento) {
	
		this.elemento = elemento;
		this.sup = null;
		this.inf = null;
		this.dir = null;
		this.esq = null;
	}
		
}

class Matriz {

	private CelulaM inicio,inserir;
	private int coluna,linha;

	public Matriz() {
		
		this.coluna = this.linha = 3;
		this.inicio = new CelulaM();
		this.inserir = this.inicio;
		construir();

	}

	public Matriz(int tam) {		

		this.coluna = this.linha = tam;
		this.inicio = new CelulaM();
		this.inserir = this.inicio;
		construir();

	}	

	public Matriz(int linha,int coluna) {		

		this.coluna = coluna;
		this.linha = linha;
		this.inicio = new CelulaM();
		this.inserir = this.inicio;
		construir();

	}	

	public int getColuna() {
		return this.coluna;
	}

	public int getLinha() {
		return this.linha;
	}

	public CelulaM getInicio() {
		return this.inicio;
	}

	private void construir() {

		CelulaM tmp = new CelulaM();
		CelulaM tmp2 = inicio;
		for ( int i = 0 ; i < linha - 1 ; i++ ) {
			tmp2.dir = tmp;
			tmp.esq = tmp2;
			tmp2 = tmp;
			tmp = null;
			tmp = new CelulaM();
		}

		tmp = null;
		tmp2 = inicio;
		CelulaM tmp3 = null;
		
		for ( int i = 0; i < coluna - 1; i++ ) {
	
			tmp = new CelulaM();
			tmp2.inf = tmp;
			tmp.sup = tmp2;
			tmp2 = tmp;
			tmp3 = tmp2;
			tmp = null;

			
			for ( int j = 0 ; j < linha - 1; j++ ) {

				tmp = new CelulaM();
				tmp3.dir = tmp;
				tmp.esq = tmp3;
				tmp3.sup.dir.inf = tmp;
				tmp.sup = tmp3.sup.dir;
				tmp3 = tmp;
				tmp = null;

			}		
			
			tmp3 = null;
		
		}

		tmp2 = null;
	
	}
	
	public void mostrar() {

		for (CelulaM i = inicio; i != null ; i=i.inf ) {
			for ( CelulaM j = i ; j != null ; j=j.dir) {
				System.out.print(j.elemento + "  ");
			}	
			System.out.println("");
		}

	}

	public void mostrarPaoDeQueijo() {

		int contador = 0;

		for (CelulaM i = inicio; i != null ; i=i.inf ) {
			for ( CelulaM j = i ; j != null ; j=j.dir) {
				if ( j.elemento == 1 ) {
					System.out.print("9");
				}
				else {
				    if (j.sup != null) {
						if ( j.sup.elemento == 1) {
							contador++;
						}
					}	
				    if (j.esq != null) {
						if ( j.esq.elemento == 1) {
							contador++;
						}
					}
				    if (j.dir != null) {
						if ( j.dir.elemento == 1) {
							contador++;
						}
					}
				    if (j.inf != null) {
						if ( j.inf.elemento == 1) {
							contador++;
						}
					}
					System.out.print(contador);
					contador = 0;
				}
			}	
			System.out.println();
		}

	}

	public void mostrarDiagonalPrincipal() {

		CelulaM i;		

		for ( i = inicio; i.dir != null ; i=i.dir.inf) {
			System.out.print(i.elemento + "  " );
		}
			System.out.print(i.elemento + "  " );
	}

	public void mostrarDiagonalSecundaria() {

		CelulaM i;

		for ( i=inicio ; i.dir != null; i=i.dir);

		for ( /**/; i.esq != null ; i=i.inf.esq) {	
			System.out.print(i.elemento + "  ");
		}
		System.out.print(i.elemento + "  " );
	}

	public Matriz soma(Matriz m1, Matriz m2) throws Exception{

		if(m1.getLinha() != m2.getLinha() || m1.getColuna() != m2.getColuna()) 
			throw new Exception("Erro! As duas matrizes são de tamanhos diferentes");
				
		Matriz resp = new Matriz(m1.getLinha(),m2.getColuna());	
		
		for (CelulaM i = m1.getInicio(),j = m2.getInicio(),x = resp.getInicio() ; i != null ; i = i.inf , j = j.inf, x = x.inf ) {
			for ( CelulaM k = i, l = j, y = x ; k != null ; k = k.dir , l = l.dir, y = y.dir) {
				y.elemento = k.elemento + l.elemento;
			}
		}		
		return resp;
	}

	public Matriz multiplicacao(Matriz m1, Matriz m2) throws Exception{

		if(m1.getLinha() != m2.getColuna() || m1.getColuna() != m2.getLinha()) 
			throw new Exception("Erro! As duas matrizes são de tamanhos diferentes");
				
		Matriz resp = new Matriz(m1.getLinha(),m1.getColuna());	
		
		for (CelulaM i = m1.getInicio(),x = resp.getInicio() ; x != null ; i = i.inf, x = x.inf ) {
			for ( CelulaM l = m2.getInicio(), y = x ; y != null ; l = l.dir, y = y.dir) {
				CelulaM j = l;
				CelulaM h = i;
				while( j != null ) {
					y.elemento += j.elemento * h.elemento;
					j = j.inf;	
					h = h.dir;
				}
			}
		}		
		return resp;
	}

	public void inserir(int elemento) throws Exception {

		if (this.inserir == null) {
			throw new Exception("Erro! Matriz cheia."); 
		}

		inserir.elemento = elemento;
		if(this.inserir.dir != null) {
			inserir = inserir.dir;
		}
		else {
			if ( inserir.inf != null ) {
				inserir = inserir.inf;
				while(inserir.esq != null) {
					inserir = inserir.esq;
				}
			}
			else {
				inserir = null;
			}
		}
	}

	public Matriz gerar() throws Exception {
		
		Random gerador = new Random();

		Matriz resp = new Matriz(this.getLinha(),this.getColuna());

		for ( int i = 0; i < this.getLinha() * this.getColuna(); i++) {
			resp.inserir(gerador.nextInt(100));
		}

		return resp;

	}

}

public class Questao1
{

	public static void main(String[] args) throws Exception{
		Matriz m1 = null,m2 = null,m3 = new Matriz();
		int l,c;
		String entrada = MyIO.readLine();
		String[] Split;
		while (entrada.contains("FIM") != true) {
			Split = entrada.split(" ");
			l = Integer.parseInt(Split[0]);
			c = Integer.parseInt(Split[1]);
			m1 = new Matriz(c,l);
			for ( int k = 0; k < l * c; k++)
			{
				m1.inserir(MyIO.readInt());
			}
			m1.mostrarPaoDeQueijo();
			entrada = MyIO.readLine();
		}
	}
}
		
