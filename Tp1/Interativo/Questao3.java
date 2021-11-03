/*
Questao3
Criptografia de Cesar em Java
Aluno: Gustavo Lopes Rodrigues
Matricula: 655264
*/
public class Questao3
{
	public static void main(String[] args) 
	{ 

		//Declarando variaveis;
		String entrada,stringCriptografada;
		boolean pararLeitura = false;

		//Iniciando repeticao da leitura da String
		do {
			entrada = MyIO.readLine("");
			if (isFim(entrada) == true)  
			{ // Se a String lida for igual a "FIM"
				pararLeitura = true;//sinalize para parar a leitura
			}
			else 
			{ // Se nao
				stringCriptografada = criptografar(entrada);//Criptografe a frase inserida e volte pra funcao; 
				MyIO.println(stringCriptografada);
			}
		} while ( pararLeitura == false);

	}

	public static String criptografar(String frase) 
	{

		//Declarando variaveis
		int ASCII = 0;
		String novaFrase = "";

		//Criptografando a frase inserida
		for (int i = 0; i < frase.length(); i++ ) 
		{
			ASCII = frase.charAt(i);
			ASCII += 3;
			novaFrase += (char)ASCII;
		}

		return novaFrase;//Devolvendo(retornando) String para a funcao principal

	} 

	public static boolean isFim(String entrada) 
	{

		return (entrada.length() <= 3 && entrada.charAt(0) == 'F' && entrada.charAt(1) == 'I' && entrada.charAt(2) == 'M' );

	}
}

