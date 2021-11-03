public class Questao2 {

	public static void main(String[] args) 
	{

		//Declarando variaveis
		String entrada,resp;
		int contador = 0,n;
		boolean pararLeitura = false;

	
		do {
			entrada = MyIO.readLine();
			if (isFim(entrada) == true)  
			{

				pararLeitura = true;

			}
			else {

				n = Integer.parseInt(entrada);
				String[] estrutura = new String[n];

				for ( int i = 0 ; i < n ; i++ ) {

					estrutura[i] = MyIO.readLine();

				}

				resp = adivinharEstrutura(estrutura);
				System.out.println(resp);
				
			}

		} while (pararLeitura == false); 
		

	} 

	public static String adivinharEstrutura(String[] input) {

		int[] array = new int[input.length/2];
	
		String resp = "queue";

		int contador = 0;

		for ( int i = 0 ; i < input.length/2 ; i++ ) {

			array[i] = input[i].charAt(2) - 48;

		}
	
		if (input[input.length/2].charAt(2) - 48 == array[array.length - 1]) {	
			resp = "stack";
			
		}
		else {
			for ( int j = 0 ; j < array.length ; j++ ) {

				if(input[input.length/2].charAt(2) - 48 != array[j])
					contador++;

			}
	
			if ( contador == array.length ) {
				resp = "impossible";
						
			}

		}

		return resp;

	}

	public static boolean isFim(String entrada) 
	{

		return (entrada.length() <= 3 && entrada.charAt(0) == 'F' && entrada.charAt(1) == 'I' && entrada.charAt(2) == 'M' );

	}

}
