/*
Lab0201
Junte duas strings e organize as mesmas
Aluno: Gustavo Lopes Rodrigues
Matricula: 655264

*/

public class Lab0201
{
	
	public static void main(String[] args)
	{
	
		//Declarando variaveis
		String entrada, saida;
		boolean pararLeitura = false;
		
		do 
		{//Leitura da entrada
			
			entrada = MyIO.readLine();
			
			if ( isFim(entrada) == true ) 
			{// Se for lido "FIM"

				pararLeitura = true;

			}
			else 
			{// Caso contrario

				saida = preconcat(entrada);
				MyIO.println(saida);				

			}
			

		} while ( pararLeitura == false );	
		
	}
	
	public static String preconcat(String entrada) 
	{

		//Declaracao das variaveis
		int pos = 0;
		String split1 = "" ,split2 = "";

		//Separacao da 'entrada' feita pelo usuario a partir do espaco em branco -> ' '
		while(entrada.charAt(pos) != ' ') 
		{
			split1 += entrada.charAt(pos);
			pos++;
		}		
		
		pos++;

		while(pos < entrada.length() )
		{

			split2 += entrada.charAt(pos);
			pos++;

		}

		return concat(split1,split2,0);
	
	}

	public static String concat(String entrada1, String entrada2, int pos)
	{
		//Declarando variavel saida
		String saida = "";
		
		if ( pos < entrada1.length() || pos < entrada2.length() )
		{//fazendo juntamento das duas strings
	
			if ( pos < entrada1.length() )
			{
		
				saida += entrada1.charAt(pos);
	
			}	
			if (pos < entrada2.length() )
			{
		
				saida += entrada2.charAt(pos);

			}
			
			saida += concat(entrada1,entrada2,++pos);
		
		}
		
		return saida;//retornando a string unida

	}

	public static boolean isFim(String entrada) 
	{
		//verificando se foi lido 'FIM'
		return (entrada.length() <= 3 && entrada.charAt(0) == 'F' && entrada.charAt(1) == 'I' && entrada.charAt(2) == 'M' );

	}

}

