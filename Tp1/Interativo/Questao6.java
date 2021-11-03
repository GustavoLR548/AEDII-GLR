/*
Questao6
Confere se uma String lida eh um numero inteiro, um numero real, composto por vogais ou por consoantes
Aluno : Gustavo Lopes Rodrigues
Matricula : 655264
*/

public class Questao6
{
	public static void main(String[] args) 
	{

		//Iniciando variaveis
		String entrada;
		boolean pararLeitura = false,resposta[];
		resposta = new boolean[4];
		do 
		{//Iniciando loop de leitura
			entrada = MyIO.readLine("");
			if ( entrada.length() <= 3 && entrada.charAt(0) == 'F' && entrada.charAt(1) == 'I' && entrada.charAt(2) == 'M' ) 
			{//Se a palavra lida for igual a "FIM"
				pararLeitura = true; // Sinalize para parar a leitura
			}
			else 
			{//Senao
				//Em cada ponto do array booleano, armazene o resultado de cada teste
				resposta[0] = ehVogal(entrada); 
				resposta[1] = ehConsoante(entrada);
				resposta[2] = ehInt(entrada);
				resposta[3] = ehFloat(entrada);
				lehResultado(resposta); //Por fim, leia o resultado
			}
		} while ( pararLeitura == false ); // Fim da leitura

	}

	public static boolean ehVogal(String entrada) 
	{

		//iniciando variavel
		boolean resultado = true;
		
		for (int i = 0; i < entrada.length(); i++ )
	       	{//Conferindo cada caractere para verificar se o mesmo tem caracteres consoantes
			if ( charEhVogal(entrada.charAt(i)) == false )
			{
				resultado =  false; 
				i = entrada.length(); // force a parada da leitura se o caractere lido nao for vogal
			}
		}
		return resultado; // retorne a funcao principal o resultado da verificacao

	}
	
	public static boolean ehConsoante(String entrada) 
	{
	
		//Iniciando variavel
		boolean resultado = true;

		for (int i = 0; i < entrada.length(); i++ )
	       	{//Conferindo cada caractere para verificar se o mesmo tem caracteres vogais
			if ( charEhConsoante(entrada.charAt(i)) == false )
			{
				resultado = false;
				i = entrada.length(); // force a parada da leitura se o caractere lido nao for vogal
			}
		}
		return resultado; // retorne a funcao principal o resultado da verificacao

	}
	
	public static boolean ehInt(String entrada) 
	{

		//Iniciando variavel
		boolean resultado = true;

		for (int i = 0; i < entrada.length(); i++ )
	       	{//Verificando cada caractere
			if ( ehLetra(entrada.charAt(i)) == true ) 
			{//Se o caractere lido for uma letra
				resultado =  false; //sinalize que esse numero nao e do tipo "int"
				i = entrada.length(); //force o fim da leitura
			}
			else if ( temSinal(entrada.charAt(i)) == true )
			{//Se o caractere lido for um ',' ou um '.'
				resultado =  false; //sinalize que esse numero nao e do tipo "int"
				i = entrada.length(); //force o fim da leitura
			}
		}
		return resultado; // retorne para funcao principal o resultado da verificacao

	} 

	public static boolean ehFloat(String entrada) 
	{

		//Iniciando variaveis
		boolean resultado = true;
		int numSinais = 0;

		for (int i = 0; i < entrada.length(); i++ )
       		{//Iniciando leitura dos caracteres
			if ( ehLetra(entrada.charAt(i)) == true ) 
			{//parar a leitura e sinalizar como falso, se o caractere lido for uma letra
				resultado =  false;
				i = entrada.length();
			}
			else if ( temSinal(entrada.charAt(i)) == true )
			{//aumentar em 1, se o caractere verificado for um ',' ou '.'
				numSinais++;
			}
		}
		if ( entrada.length() > 7 && numSinais > 1 )
		{//Sinalize como falso, se a String recebida for maior que 7 digitos e tem mais de um sinal
			resultado = false;
		}
		else if ( numSinais > 1 )
		{//Sinalize como falso, se foi lido mais de um sinal e a String tem menos que 7 digitos
			resultado = false;
		}
		return resultado;//Retorne para funcao principal o resultado

	}
	
	public static boolean charEhVogal(char entrada)
	{

		//Iniciando a variavel
		boolean resultado = false;
	
		if ( ehLetra(entrada) == true ) 
		{//Se o caractere lido for uma letra
			if (entrada == 'A' || entrada == 'a' || entrada == 'E' || entrada == 'e' || entrada == 'i' || entrada == 'I' || entrada == 'O' || entrada == 'o' || entrada == 'U' || entrada == 'u') 
			{//Se o mesmo ser uma das vogais, sinalize como verdadeiro
				resultado = true;
			}
		}
		return resultado; //retorne para funcao com o resultado

	}

	public static boolean charEhConsoante(char entrada)
	{
		//Iniciando variavel
		boolean resultado = false;

		if ( ehLetra(entrada) == true ) 
		{//Se o caractere lido for uma letra
			if (entrada != 'A' && entrada != 'a' && entrada != 'E' && entrada != 'e' && entrada != 'i' && entrada != 'I' && entrada != 'O' && entrada != 'o' && entrada != 'U' && entrada != 'u') 
			{//Se o mesmo nao for nenhuma vogal, sinalize como verdadeiro
				resultado = true;
			}
		}
		return resultado; //retorne para a funcao com o resultado

	}
	
	public static boolean temSinal(char entrada) 
	{
		//iniciando variavel
		boolean resultado = false;
	
		if ( entrada == '.' || entrada == ',' ) 
		{//Se o caractere lido for ',' ou '.' sinalize como verdadeiro
			resultado = true;
		}
		return resultado; //retorne para a funcao com o resultado

	}

	public static boolean ehLetra(char entrada)
	{

		boolean resultado = false;
		if ( entrada >= 'a' && entrada <= 'z' || entrada >= 'A' && entrada <= 'Z' )
		{// se o caractere lido for uma letra, sinalize como verdadeiro
			resultado = true;
		}
		return resultado; //retorne para a funcao com o resultado

	}
			

	public static void lehResultado(boolean resultado[])
	{

		for (int i = 0; i < 4 ; i++ )
		{//Leitura de todos os resultados
			if ( resultado[i] == true ) 
			{ // Se o resultado retornado foi como verdadeiro
				MyIO.print("SIM "); //Imprima 'SIM'
			}
			else 
			{ // Caso contrario
				MyIO.print("NAO "); //Imprima 'nao'
			}
		}
		MyIO.println(""); //Salte uma linha, para as proximas leituras

	}

}
	
		 
		 
	



