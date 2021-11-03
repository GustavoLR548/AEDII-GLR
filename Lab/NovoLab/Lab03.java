/*
Lab03
Fazer uma sequência espelho a partir de duas entradas
Aluno: Gustavo Lopes Rodrigues
Matricula: 655264
*/

public class Lab03
{ 

	public static void main(String[] args) 
	{ 

		//Declarando variaveis
		int entrada1,entrada2;
		boolean pararLeitura = false;		

		do 
		{//Leitura da entrada
			
			entrada1 = MyIO.readInt();
			
			if ( isFim(entrada1) == true ) 
			{// Se for lido "FIM"

				pararLeitura = true;

			}
			else 
			{// Caso contrario

				entrada2 = MyIO.readInt();
				seqEspelhada(entrada1,entrada2);				
				MyIO.println("");
			}
			
		} while ( pararLeitura == false );
		
	
	} 

	public static void seqEspelhada(int entrada1,int entrada2)
	{
		
		leituraCrescente(entrada1,entrada2);//Fazer a leitura crescente dos numeros
		leituraDecrescente(entrada2,entrada1);//Fazer a leitura decrescente dos numeros

	}

	public static void leituraCrescente(int entrada1,int entrada2)
	{

		for( int i = entrada1; i <= entrada2; i++)
			MyIO.print(i);//Leitura crescente dos numeros
		
	}

	public static void leituraDecrescente(int entrada1,int entrada2)
	{

		

		for( int i = entrada1; i >= entrada2; i--)
		{
			String saida = "";//Declarando uma string vazia
			saida += i;//Adicionando um numero a string

			for ( int j = saida.length() - 1; j >= 0; j-- )//leitura inversa do numero
				MyIO.print(saida.charAt(j));//Le do ultimo char ate o primeiro 

		}
	}

	public static boolean isFim(int entrada) 
	{
		//verificando se foi lido 'FIM'
		return ( entrada == -1 );

	}

}
				
