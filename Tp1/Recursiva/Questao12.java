/*
Questao12
Criptografia de Cesar em Java
Aluno: Gustavo Lopes Rodrigues
Matricula: 655264
*/

public class Questao12
{
	public static void main(String[] args) 
	{ 

		//Declarando variaveis;
		String frase,stringCriptografada;
		boolean pararLeitura = false;

		//Iniciando repeticao da leitura da String
		do {
			frase = MyIO.readLine("");
			if (frase.length() <= 3 && frase.charAt(0) == 'F' && frase.charAt(1) == 'I' && frase.charAt(2) == 'M' ) 
			{ // Se a String lida for igual a "FIM"
				pararLeitura = true;//sinalize para parar a leitura
			}
			else 
			{ // Se nao
				stringCriptografada = criptografar(frase,0);
				MyIO.println(stringCriptografada);
			}
		} while ( pararLeitura == false) ;

	}

	public static String criptografar(String frase,int letra) 
	{

		//Declarando variaveis
		int ASCII = 0;
		String novaFrase = "";
		//Criptografando a frase inserida

		if (letra < frase.length()) 
		{ //leitura da string 
			//Convertendo o caractere para numero, criptografando e convertendo novamente para caractere
			ASCII = frase.charAt(letra); 
			ASCII += 3;
			//Inserindo o caractere criptografado em um array de caracteres
			novaFrase += (char)ASCII;
			novaFrase += criptografar(frase,letra + 1);
		}

		return novaFrase;//Devolvendo(retornando) String para a funcao principal

	} 

}

