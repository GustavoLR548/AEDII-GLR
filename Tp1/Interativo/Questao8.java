/*
Questao8
Fazer a leitura de um arquivo de tras pra frente
Aluno: Gustavo Lopes Rodrigues
Matricula: 655264
*/

//Importando bibliotecas
import java.io.RandomAccessFile;
import java.io.IOException;

public class Questao8
{ 

	public static void main(String[] args) 
	{ 

		//Leitura de quantos numeros serao lidos
		int nNumeros = MyIO.readInt("");
		inserirFloats(nNumeros); // Insercao dos numeros no arquivo
		lehArquivo(nNumeros); // Leitura dos numeros dentro do arquivo de tras para frente

	} 
	
	public static void inserirFloats(int n)
	{ 

		double numero; //variavel que armazenara o numero lido
		try
		{ // Abrindo o arquivo e criando uma excecao caso de um erro na leitura

			RandomAccessFile arq = new RandomAccessFile("teste.txt","rw");
			for (int i = 1; i <= n ; i++) 
			{

				numero = MyIO.readDouble();
				arq.writeDouble(numero);// Escrevendo o numero lido dentro do arquivo em formato double

			}
			arq.close(); // fechando o arquivo e encerrando a leitura
		}

		catch (IOException excecao)
		{ // caso uma excecao tenha sido "capturada" avise ao usuario

			excecao.printStackTrace();

		}
	} 

	public static void lehArquivo(int n)
	{ 

		double numLido; // Variavel que armazenara o numero lido dentro do arquivo

		try
		{ 

			RandomAccessFile arq = new RandomAccessFile("teste.txt","r");
			for (int i = 1 ; i <= n ; i++) 
			{

				arq.seek(arq.length() - (i * 8)); // seek para encontrar a primeira posicao do numero do arquivo
				numLido = arq.readDouble(); // Leitura do numero na posicao calculada anteriormente

				if(numLido == (int)numLido)
				{ // caso o numero lido seja do tipo (int)

					MyIO.println((int)numLido);

				}
				else
				{// caso contrario

					MyIO.println(numLido);

				}
			}

			arq.close();

		}

		catch (IOException excecao)
		{ // caso tenha ocorrido alguma excecao

			excecao.printStackTrace();
		}
	} 

} 
		
