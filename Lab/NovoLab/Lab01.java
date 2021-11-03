/*
Lab01: 
Contar quantas palavras maiusculas estao presentes em uma String
Aluno: Gustavo Lopes Rodrigues
Matricula: 655264
*/
public class Lab01
{ 
	public static void main(String[] args) 
	{ 

		//Iniciando as variaveis
		String palavra;
		int charMaiusc[] = new int[100], contador=0;
		boolean pararLeitura = false;

		do //Repeticao da leitura da string
		{
  			palavra = MyIO.readLine("");

			if (isFim(palavra) == true) 
			{ //Parar leitura..
				pararLeitura = true; //...se a String lida for igual a "FIM", sinalizando isso pelo booleano
			}
			else 
			{ //Caso contrario, faca a leitura da palavra e conte o numero de letras maiusculas
				charMaiusc[contador] = verificarPalavra(palavra);
				contador++;
			}
		} while ( pararLeitura == false );// fim da leitura

		saida(contador,charMaiusc); // Chamando a funcao pra leitura do resultado das leituras

	} 

	public static int verificarPalavra( String palavra ) 
	{ 

		//iniciando variaveis
		int numCharMaiusc = 0;

		for (int i = 0; i < palavra.length() ; i++ ) 
		{ //Fazendo a verificacao de cada caractere da palavra recebida da "main"

			if (palavra.charAt(i) >= 'A' && palavra.charAt(i) <= 'Z') 
			{ //Se o caractere lido for maiusculo
			       numCharMaiusc++;//Aumente em 1 na variavel que esta contando o numero de caracteres maiusculos
			}	
		}

		return numCharMaiusc; //retorne o numero de caracteres maiusculos

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
				
