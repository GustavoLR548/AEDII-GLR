/*
Questao10 
Confere se uma palavra e um palindromo ou nao
Aluno: Gustavo Lopes Rodrigues
Matricula: 655264
*/

public class Questao10{
	
	public static void main(String[] args) {

		//Declarando variaveis
		String entrada;
		int contador = 0;
		boolean resposta[],pararLeitura = false;
		resposta = new boolean[1000];

		//Iniciando a repeticao da leitura da string
		do {
			entrada = MyIO.readLine("");
			if (isFim(entrada) == true) 
			{ //Se a palavra inserida for igual a "FIM"
				pararLeitura = true;//Mude o valor da variavel booleana para parar a leitura
			}
			else {// Se nao, faca a leitura da palavra
				resposta[contador] = verificarPalavra(entrada, 0); // conferindo se a variavel e um palindromo
				contador++;
			}
		} while (pararLeitura == false); //fim da leitura

		saida(resposta, contador); // leitura da saida

	} 

	public static boolean verificarPalavra(String palavra, int letra) 
	{ 

		boolean ehPalindromo = true;
		if (letra != (palavra.length() / 2) ) 
		{ //Ler ate a metade da string
			if (palavra.charAt(letra) != palavra.charAt((palavra.length() - 1 ) - letra)) 
			{//Se as letras forem diferentes
			       ehPalindromo = false;
			}
			else 
			{// se nao
			       ehPalindromo = verificarPalavra(palavra,letra + 1);
			}	
		}
		return ehPalindromo; //retorne o resultado da verificacao
		
	}

	public static void saida(boolean resposta[], int contador) 
	{ 

		for (int i = 0; i < contador; i++) 
		{ // leitura de todos as entrada do usuario
 			if (resposta[i] == true) 
			{ // se a palavra for palindromo, imprima "SIM"
				MyIO.println("SIM");
			}
			else 
			{ // Caso contrario, imprima "NAO"
				MyIO.println("NAO");
			}
		} 
	} 

	public static boolean isFim(String entrada) 
	{

		return (entrada.length() <= 3 && entrada.charAt(0) == 'F' && entrada.charAt(1) == 'I' && entrada.charAt(2) == 'M' );

	}

} 
