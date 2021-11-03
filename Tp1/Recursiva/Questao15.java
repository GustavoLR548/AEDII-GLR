/*
Questao15
Programa que confere se uma String lida eh um numero inteiro, um numero real, composto por vogais ou por consoantes
Aluno : Gustavo Lopes Rodrigues
Matricula : 655264
*/

public class Questao15
{

	public static void main(String[] args) 
	{

		//Iniciando variaveis
		String entrada;
		boolean pararLeitura = false;
		int resposta[] = new int[4];
		
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
				resposta[0] = ehVogal(entrada,0); 
				resposta[1] = ehConsoante(entrada,0);
				resposta[2] = ehInt(entrada,0);
				resposta[3] = ehFloat(entrada,0);
				lehResultado(resposta); //Por fim, leia o resultado

			}

		} while ( pararLeitura == false ); // Fim da leitura

	}

	public static int ehVogal(String entrada,int letra) 
	{

		//iniciando variavel
		int resultado = 1;
		
		if (letra < entrada.length())
	       	{//Conferindo cada caractere para verificar se o mesmo tem caracteres consoantes
			if ( charEhVogal(entrada.charAt(letra)) == false )
			{ // se o caractere lido nao for uma vogal

				resultado =  0; 

			}
			else
			{ // caso contrario

				resultado = ehVogal(entrada, letra + 1);

			}

		}

		return resultado; // retorne a funcao principal o resultado da verificacao

	}
	
	public static int ehConsoante(String entrada, int letra) 
	{
	
		//Iniciando variavel
		int resultado = 1;

		if (letra < entrada.length())
	       	{//Conferindo cada caractere para verificar se o mesmo tem caracteres vogais
			if ( charEhConsoante(entrada.charAt(letra)) == false )
			{// se o caractere lido nao for uma consoante

				resultado = 0;

			}
			else 
			{// se nao

				resultado = ehConsoante(entrada, letra + 1);

			}

		}

		return resultado; // retorne a funcao principal o resultado da verificacao

	}
	
	public static int ehInt(String entrada, int pos) 
	{

		//Iniciando variavel
		int resultado = 1;
		if (pos < entrada.length())

	       	{//Verificando cada caractere

			if ( ehLetra(entrada.charAt(pos)) == true ) 
			{//Se o caractere lido for uma letra

				resultado =  0; //sinalize que esse numero nao e do tipo "int"

			}
			else if ( temSinal(entrada,0) > 0 )
			{//Se for lido um "." ou ","

				resultado =  0; //sinalize que esse numero nao e do tipo "int"

			}
			else
			{ // se nao

				resultado = ehInt(entrada, pos + 1);

			}

		}

		return resultado; // retorne para funcao principal o resultado da verificacao

	} 

	public static int ehFloat(String entrada,int pos) 
	{

		//Iniciando variaveis
		int resultado = 1;

		if (pos < entrada.length())
       		{//Iniciando leitura dos caracteres

			if ( ehLetra(entrada.charAt(pos)) == true ) 
			{//parar a leitura e sinalizar como falso, se o caractere lido for uma letra

				resultado =  0;

			}
			else 
			{	
				resultado = ehFloat(entrada,pos + 1);//chamada recursiva
			}

		}

		if ( entrada.length() > 7 && temSinal(entrada,0) > 1 )
		{//Sinalize como falso, se a String recebida for maior que 7 digitos e tem mais de um sinal

			resultado = 0;

		}
		if ( temSinal(entrada,0) > 1 )
		{//Sinalize como falso, se foi lido mais de um sinal e a String tem menos que 7 digitos

			resultado = 0;
	
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
	
	public static int temSinal(String entrada,int letra) 
	{

		//iniciando variavel
		int resultado = 0;
		
		if ( letra < entrada.length() ) 
		{//Se o caractere lido for ',' ou '.' sinalize como verdadeiro

			if ( entrada.charAt(letra) == '.' || entrada.charAt(letra) == ',' ) 
			{
	
				resultado++;

			}

			resultado += temSinal(entrada,++letra);

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
			

	public static void lehResultado(int resultado[])
	{

		for (int i = 0; i < 4 ; i++ )
		{//Leitura de todos os resultados

			if ( resultado[i] == 1 ) 
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


