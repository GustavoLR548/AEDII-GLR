/*
Questao7
Le um link html e confere a quantidade de padroes presentes
Aluno: Gustavo Lopes Rodrigues
Matricula: 655264
*/

//Importando as bibliotecas necessarias
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Questao7
{

	public static void main(String[] args)
       	{

	        //Declarando variaveis
		String titulo, link,texto;
		boolean pararLeitura = false;
		int numPadroes[];
		numPadroes = new int[25];
		do
		{//leitura do link html
			
			titulo = MyIO.readLine("");
			if (titulo.length() == 3 && titulo.charAt(0) == 'F' && titulo.charAt(1) == 'I' && titulo.charAt(2) == 'M' )
			{//Se "FIM" for lido

				pararLeitura = true;

			}
			else 
			{//Se nao for lido "FIM"

				link = MyIO.readLine("");
				texto = pegarTexto(titulo,link);
				verificarPadroes(texto,numPadroes);
				resultado(numPadroes,titulo);
			} 

		} while ( pararLeitura == false );

	}

	public static String pegarTexto(String titulo, String link)
	{

		String texto = "",linha = "";

		try {//tentando ler o link inserido

			URL url = new URL(link);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			linha = br.readLine();

			while (linha != null) {
				texto += linha;
				linha = br.readLine();
			}

			br.close();
		} catch (MalformedURLException excecao) {
			excecao.printStackTrace();
		} catch (IOException excecao) {
			excecao.printStackTrace();
		}
	
		return texto;

	}

	public static void verificarPadroes(String texto,int numPadroes[])
	{

		for(int i = 0; i < 25 ; i++) 
		{//iniciando todas as posicoes do array

			numPadroes[i] = 0;

		}

		for(int i = 0; i < texto.length(); i++)
		{//Contando todos os padroes presentes no link

			if( texto.charAt(i) == 'a' )
			{
				numPadroes[0]++;
			}
			else if( texto.charAt(i) == 'e' )
			{
				numPadroes[1]++;
			}
			else if( texto.charAt(i) == 'i' )
			{
				numPadroes[2]++;
			}
			else if( texto.charAt(i) == 'o' )
			{
				numPadroes[3]++;
			}
			else if( texto.charAt(i) == 'u' )
			{
				numPadroes[4]++;
			}
			else if( texto.charAt(i) == 'á' )
			{
				numPadroes[5]++;
			}
			else if( texto.charAt(i) == 'é' )
			{
				numPadroes[6]++;
			}
			else if( texto.charAt(i) == 'í' )
			{
				numPadroes[7]++;
			}
			else if( texto.charAt(i) == 'ó' )
			{
				numPadroes[8]++;
			}
			else if( texto.charAt(i) == 'ú' )
			{
				numPadroes[9]++;
			}
			else if( texto.charAt(i) == 'à' )
			{
				numPadroes[10]++;
			}
			else if( texto.charAt(i) == 'è' )
			{
				numPadroes[11]++;
			}
			else if( texto.charAt(i) == 'ì' )
			{
				numPadroes[12]++;
			}
			else if( texto.charAt(i) == 'ò' )
			{
				numPadroes[13]++;
			}
			else if( texto.charAt(i) == 'ù' )
			{
				numPadroes[14]++;
			}
			else if( texto.charAt(i) == 'ã' )
			{
				numPadroes[15]++;
			}
			else if( texto.charAt(i) == 'õ' )
			{
				numPadroes[16]++;
			}
			else if( texto.charAt(i) == 'â' )
			{
				numPadroes[17]++;
			}
			else if( texto.charAt(i) == 'ê' )
			{
				numPadroes[18]++;
			}
			else if( texto.charAt(i) == 'î' )
			{
				numPadroes[19]++;
			}
			else if( texto.charAt(i) == 'ô' )
			{
				numPadroes[20]++;
			}
			else if( texto.charAt(i) == 'û' )
			{
				numPadroes[21]++;
			}
			else if ( ehConsoante(texto.charAt(i)) == true )
			{
				numPadroes[22]++;
			}
			if ( texto.charAt(i) == '<' && texto.charAt(i + 1) == 'b' && texto.charAt(i + 2) == 'r' && texto.charAt(i + 3) == '>' )
			{
				numPadroes[23]++;
				i += 3;
			}	
			if ( texto.charAt(i) == '<' && texto.charAt(i + 1) == 't' && texto.charAt(i + 2) == 'a' && texto.charAt(i + 3) == 'b' && texto.charAt(i + 4) == 'l' && texto.charAt(i + 5) == 'e' && texto.charAt(i + 6) == '>')
			{
				numPadroes[24]++;
				i += 6;
			}

		}

	}

	public static boolean ehConsoante(char entrada)
	{

		//Iniciando variavel
		boolean resultado = false;

		if ( entrada >= 'a' && entrada <= 'z' ) 
		{//Se o caractere lido for uma letra
			resultado = true;
		}
		return resultado;

	}
	

	public static void resultado(int numPadroes[], String titulo)
	{//Leitura do resultado

		System.out.print("a("+numPadroes[0]+") e("+numPadroes[1]+") i("+numPadroes[2]+") o("+numPadroes[3]+") u("+numPadroes[4]+") á("+numPadroes[5]+") é("+numPadroes[6]+") í("+numPadroes[7]+") ó("+numPadroes[8]+") ú("+numPadroes[9]+") à("+numPadroes[10]+") è("+numPadroes[11]+") ì("+numPadroes[12]+") ò("+numPadroes[13]+") ù("+numPadroes[14]+") ã("+numPadroes[15]+") õ("+numPadroes[16]+") â("+numPadroes[17]+") ê("+numPadroes[18]+") î("+numPadroes[19]+") ô("+numPadroes[20]+") û("+numPadroes[21]+") consoante("+numPadroes[22]+") <br>("+numPadroes[23]+") <table>("+numPadroes[24]+") "+titulo+"\n");

	}

}

		
			
		
					
				
