import java.util.Arrays;

public class Lab07 {
	public static void main(String[] args) throws Exception {

		int C = MyIO.readInt(),N;
		for ( int i = 0 ; i < C ; i++ ) {
			N = MyIO.readInt();
			String tmp = MyIO.readLine();
			String[] Split = tmp.split(" ");

			if ( Split[0].length() <= 2 ) {
				MyIO.println(Split[1]);
			}
			else {
				imprimirPosfixo(arrayDeChar(Split[1]),arrayDeChar(Split[0]),N);
				MyIO.println("");
			}

		}

	}

	public static int procurar(char array[], char x, int n) { 
		for (int i = 0; i < n; i++) 
		    if (array[i] == x) 
		        return i; 
		return -1; 
	} 

	public static void imprimirPosfixo(char infixo[], char prefixo[], int n) { 

		int raizPos = procurar(infixo, prefixo[0], n);
	  
		if (raizPos != 0) 
		    imprimirPosfixo(infixo, Arrays.copyOfRange(prefixo, 1, n), raizPos); 
	  
		if (raizPos != n - 1) 
		    imprimirPosfixo(Arrays.copyOfRange(infixo, raizPos+1, n), Arrays.copyOfRange(prefixo, 1+raizPos, n), n - raizPos - 1); 
	  
		MyIO.print( prefixo[0] ); 
	}

	public static char[] arrayDeChar(String entrada) {

		char[] resp = new char[entrada.length()];
	
		for ( int i = 0; i < entrada.length() ; i++ ) {
			resp[i] = entrada.charAt(i);
		}

		return resp;

	}
}
