/*
Lab02 
Contar quantas palavras maiusculas estao presentes em uma String
Aluno: Gustavo Lopes Rodrigues
Matricula: 655264
*/

public class Lab02
{ 

	public static void main(String[] args) 
	{ 

		//Iniciando as variaveis

		String entrada;
		int charMaiusc[] = new int[100], contador=0;
		boolean pararLeitura = false;

		do //Repeticao da leitura da string
		{
  			entrada = MyIO.readLine("");

			if (entrada.length() <= 3 && entrada.charAt(0) == 'F' && entrada.charAt(1) == 'I' && entrada.charAt(2) == 'M' ) 
			{ //Parar leitura..
				pararLeitura = true; //...se a String lida for igual a "FIM", sinalizando isso pelo booleano
			}
			else 
			{ //Caso contrario, faca a leitura da palavra e conte o numero de letras maiusculas

				charMaiusc[contador] = verfPalavra(entrada, entrada.length() - 1);
				contador++;

			}
		} while ( pararLeitura == false );// fim da leitura

		saida(contador,charMaiusc); // Chamando a funcao pra leitura do resultado das leituras
	} 

	public static int verfPalavra( String palavra , int letra ) 
	{ 

		int contador = 0;

		if (letra >= 0 ) 
		{ //Fazendo a verificacao de cada caractere da palavra recebida da "main"

			if (palavra.charAt(letra) >= 'A' && palavra.charAt(letra) <= 'Z') 
			{ //Se o caractere lido for maiusculo
			       contador++;
			}
			       contador += verfPalavra(palavra,letra - 1);
	
		}
		return contador; //retorne o numero de caracteres maiusculos
	} 
		
	public static void saida(int contador,int charMaiusc[]) 
	{
		
		for (int i = 0 ; i < contador; i++) 
		{
			MyIO.println(charMaiusc[i]);//Leitura do numero de caracteres maiusculos em cada palavra
		}
	}

	public static boolean isFim(String entrada) 
	{
		return (entrada.length() <= 3 && entrada.charAt(0) == 'F' && entrada.charAt(1) == 'I' && entrada.charAt(2) == 'M' );
	}
}
				
