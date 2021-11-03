/*
Questao4
convertendo caracteres de uma String aleatoriamente, usando a biblioteca Random
Aluno: Gustavo Lopes Rodrigues
Matricula: 655264
*/

//Importando classe "Random"
import java.util.Random;

public class Questao4 
{

	public static void main(String[] args) 
	{

		//Declarando variaveis
		String entrada,novaFrase;
		boolean pararLeitura = false;

		//Iniciando o gerador 
		Random gerador = new Random();
		gerador.setSeed(4);
	
		//Iniciando a leitura das Strings
		do {
			entrada = MyIO.readLine("");
			if (isFim(entrada) == true)  
			{//Se a String inserida for igual a "FIM"
				pararLeitura = true;//Sinalize para parar a leitura
			}
			else {//Se nao
				novaFrase = randomizador(entrada,gerador);//Crie a nova String e volte para a funcao principal
				MyIO.println(novaFrase);
			}
		} while ( pararLeitura == false); // fim da leitura

	}

	public static String randomizador(String frase,Random gerador) 
	{ 

		//Iniciando as variaveis
		char randomNum1 = (char)('a' + (Math.abs(gerador.nextInt())%26)), randomNum2 = (char)('a' + (Math.abs(gerador.nextInt())%26));
		String novaFrase = "";

		//Iniciando a conversao da frase
		for (int i = 0; i < frase.length(); i++) {
			if (frase.charAt(i) == (char)randomNum1) {
				novaFrase += randomNum2;
			}
			else {
				novaFrase += frase.charAt(i);
			}
		}
		
		return novaFrase;//Devolvendo(retornando)String para a funcao principal

	}

	public static boolean isFim(String entrada) 
	{

		return (entrada.length() <= 3 && entrada.charAt(0) == 'F' && entrada.charAt(1) == 'I' && entrada.charAt(2) == 'M' );

	}

}
		
