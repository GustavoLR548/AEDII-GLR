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
			else if( texto.charAt(i) == '�' )
			{
				numPadroes[5]++;
			}
			else if( texto.charAt(i) == '�' )
			{
				numPadroes[6]++;
			}
			else if( texto.charAt(i) == '�' )
			{
				numPadroes[7]++;
			}
			else if( texto.charAt(i) == '�' )
			{
				numPadroes[8]++;
			}
			else if( texto.charAt(i) == '�' )
			{
				numPadroes[9]++;
			}
			else if( texto.charAt(i) == '�' )
			{
				numPadroes[10]++;
			}
			else if( texto.charAt(i) == '�' )
			{
				numPadroes[11]++;
			}
			else if( texto.charAt(i) == '�' )
			{
				numPadroes[12]++;
			}
			else if( texto.charAt(i) == '�' )
			{
				numPadroes[13]++;
			}
			else if( texto.charAt(i) == '�' )
			{
				numPadroes[14]++;
			}
			else if( texto.charAt(i) == '�' )
			{
				numPadroes[15]++;
			}
			else if( texto.charAt(i) == '�' )
			{
				numPadroes[16]++;
			}
			else if( texto.charAt(i) == '�' )
			{
				numPadroes[17]++;
			}
			else if( texto.charAt(i) == '�' )
			{
				numPadroes[18]++;
			}
			else if( texto.charAt(i) == '�' )
			{
				numPadroes[19]++;
			}
			else if( texto.charAt(i) == '�' )
			{
				numPadroes[20]++;
			}
			else if( texto.charAt(i) == '�' )
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

		System.out.print("a("+numPadroes[0]+") e("+numPadroes[1]+") i("+numPadroes[2]+") o("+numPadroes[3]+") u("+numPadroes[4]+") �("+numPadroes[5]+") �("+numPadroes[6]+") �("+numPadroes[7]+") �("+numPadroes[8]+") �("+numPadroes[9]+") �("+numPadroes[10]+") �("+numPadroes[11]+") �("+numPadroes[12]+") �("+numPadroes[13]+") �("+numPadroes[14]+") �("+numPadroes[15]+") �("+numPadroes[16]+") �("+numPadroes[17]+") �("+numPadroes[18]+") �("+numPadroes[19]+") �("+numPadroes[20]+") �("+numPadroes[21]+") consoante("+numPadroes[22]+") <br>("+numPadroes[23]+") <table>("+numPadroes[24]+") "+titulo+"\n");

	}

}

		
			
		
					
				
