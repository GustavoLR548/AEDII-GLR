/*
Lab04
Verificando se uma expressao e valida(possui o mesmo numero de parenteses
Aluno: Gustavo Lopes Rodrigues
Matricula: 655264

*/

public class Lab04
{
	
	public static void main(String[] args)
	{
	
		//Declarando variaveis
		String entrada;
		boolean pararLeitura = false,saida;
		
		do 
		{//Leitura da entrada
			
			entrada = MyIO.readLine();
			
			if ( isFim(entrada) == true ) 
			{// Se for lido "FIM"

				pararLeitura = true;

			}
			else 
			{// Caso contrario

				saida = verificar(entrada);

				if( saida == true )
					MyIO.println("correto");
				else
					MyIO.println("incorreto");				

			}
			

		} while ( pararLeitura == false );	
		
	}

	public static boolean verificar(String entrada)
	{
	
		//Declaracao de Entrada
		boolean resp = true;
		int contadorA = 0,contadorB = 0;

		for ( int i = 0; i < entrada.length() ; i++ )
		{//Verificacao da entrada

			if ( entrada.charAt(i) == '(')
				contadorA++;
			if ( entrada.charAt(i) == ')')
				contadorB++;		
			if ( contadorB > contadorA ) 
				i = entrada.length();

		}
		
		if( contadorA != contadorB )//se o numero de '(' for diferente do numero de ')'
			resp = false;


		return resp;

	}
	
	public static boolean isFim(String entrada) 
	{
		//verificando se foi lido 'FIM'
		return (entrada.length() <= 3 && entrada.charAt(0) == 'F' && entrada.charAt(1) == 'I' && entrada.charAt(2) == 'M' );

	}

}

