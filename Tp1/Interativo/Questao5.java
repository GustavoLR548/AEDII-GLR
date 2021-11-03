/*

Questao5
Leh uma expressao de algebra booleana e retorno com o seu resultado
Aluno: Gustavo Lopes Rodrigues
Matricula: 655264

*/
public class Questao5
{
	
	public static void main(String[] args)
       	{

		//Definindo entrada
      		String entrada;

		boolean resultado, pararLeitura = false;
		do 
		{//Repeticao da Leitura da entrada
					
			entrada = MyIO.readLine("");

			if ( entrada.length() == 1 && entrada.charAt(0) == '0' ) 
			{//Se a entrada for igual a '0'

				pararLeitura = true;

			}
			else 
			{//caso contrario

				resultado = verificarExpressao(entrada);
				lehResultado(resultado);

			}

		} while ( pararLeitura == false );

	}

	public static boolean verificarExpressao(String entrada) 
	{

		//Declaracao de variaveis
		boolean resp = true;
		int numExpr = entrada.charAt(0) - 48;
		int expr[] = new int[numExpr];
	
		
		expr = recuperarValores(entrada,numExpr);//recuperar os valores das variaveis e armazenar em um array
		entrada = ajustarExpressaoEsubstituirValores(entrada,expr);//substituir os valores das variaveis e ajustar caso tenha 'NOT'
		entrada = substituirNandsEnors(entrada);//Fazer as operacoes NANDs e NORs ( not(and(...)) e not(or(...)) )

		do 
		{ //looping para resolver as operacoes ANDs e ORs

			entrada = substituirAndsEors(entrada);
						
		} while(entrada.length() != 1);

		if ( entrada.charAt(0) == '0' ) //Se o valor na string for igual a '0'
			resp = false;
		
		return resp;

	}
	
	public static int[] recuperarValores(String entrada, int numExpr)
	{

		//Declaracao das variaveis
		int expr[] = new int[numExpr];

		for ( int i = 2, j = 0; i < (numExpr * 2) + 1 ; i += 2 , j++) 
		{//Leitura dos valores das variaveis

			expr[j] = (int)entrada.charAt(i) - 48;
		
		}
		
		return expr;//retorne o array

	}

	public static String ajustarExpressaoEsubstituirValores(String entrada, int[] expr)
	{

		//Declaracao da variavel
		String novaExpressao = "";
		
		
		for (int i = posInicioExpressao(entrada,expr.length); i < entrada.length(); i++)
		{//Inicio da leitura da expressaoInteira

			if ( ehVariavel(entrada,i) == true ) 
			{//Se o caractere lido for variavel

				novaExpressao += expr[(int)entrada.charAt(i) - 65];

			}
			else if(  not(entrada,i) == true && ehVariavel(entrada,i + 4) == true )
			{//Se o caractere lido for variavel com presenca de not

				i += 4;//'pular' o 'not'
				
				//Inserir na nova expressao o valor contido na variavel com o valor trocado
				novaExpressao += operacaoNot(expr[(int)entrada.charAt(i) - 65]); 

				i += 1;//'pular' o parentese
			}
			else if (entrada.charAt(i) != ' ')
			{//Se o caractere nao for nenhum dos dois e nao for um espaco vazio

				novaExpressao += entrada.charAt(i);

			}

		}

		return novaExpressao;//retorne a nova string
	
	}

	public static String substituirNandsEnors(String entrada) 
	{

		//Declaracao das variaveis
		String novaExpressao = "";
		int expr[];
		
		for ( int i = 0; i < entrada.length() ; i++ )
		{//Leitura da expressao inteira

			if ( not(entrada,i) == true )
			{//Caso tenha sido lido um not

				i += 4;//'pular' o 'not'

				if ( and(entrada,i) == true )
				{//se for lido um 'and'

					i += 4;//'pular' o 'and'

					//Inserir nova String o resultado da operacao 'not(and(...))'
					novaExpressao += operacaoNot(operacaoAnd(organizarValores(entrada,i)));

					i += posFimExpressao(entrada,i) + 2 ;//'pular' até o '))'
				
				}
				else if ( or(entrada,i) == true )
				{//Se for lido um 'or'
				
					i += 3;//'pular' o 'or'

					//Inserir na nova String o resultado da operacao 'not(or(...))'
					novaExpressao += operacaoNot(operacaoOr(organizarValores(entrada,i)));

					i += posFimExpressao(entrada,i) + 2 ;//'pular' até o '))'
				
				}	
			
			}
			else 
			{//Caso nao tenha sido lido a expressao 'not(and(...))' ou 'not(or(...))'

				novaExpressao += entrada.charAt(i);
			
			}
		
		}

		return novaExpressao;

	}

	public static String substituirAndsEors(String entrada)
	{ 

		//Declaracao da variavel
		String novaExpressao = "";

		for ( int i = 0 ; i < entrada.length(); i++)
		{//leitura da expressao completa

			if ( and(entrada,i) == true && temExpressao(entrada,i + 4) == false )
			{//Se for lido a expressao 'and' e essa expressao nao contem nenhum 'and' ou 'or' dentro dela
		
				i += 4;//'pular' o 'and'

				//Inserir na nova String o resultado da operacao 'and(...)'
				novaExpressao += operacaoAnd(organizarValores(entrada,i));

				i += posFimExpressao(entrada,i) + 1 ;//'pular' até o ')'
			
			}
			else if ( or(entrada,i) == true && temExpressao(entrada,i + 3) == false )
			{//Se for lido a expressao 'or' e essa expressao nao contem nenhum 'and' ou 'or' dentro dela
				
				i += 3;//'pular' o 'or'

				//Inserir na nova String o resultado da operacao 'or(...)'
				novaExpressao += operacaoOr(organizarValores(entrada,i));

				i += posFimExpressao(entrada,i) + 1 ;//'pular' até o ')'
				
			}	
			else 
			{//Caso nao tenha sido lido a expressao 'and(...)' ou 'or(...)'

				novaExpressao += entrada.charAt(i);
			
			}
		}
	
		return novaExpressao;
	
	}

		

	public static boolean not(String entrada,int pos)
	{//Verificar a presenca da expressao 'not'

		return (entrada.charAt(pos) == 'n' && entrada.charAt(pos + 1) == 'o' && entrada.charAt(pos + 2) == 't');	

	}

	public static boolean and(String entrada,int pos)
	{//Verificar a presenca da expressao 'and'

		return (entrada.charAt(pos) == 'a' && entrada.charAt(pos + 1) == 'n' && entrada.charAt(pos + 2) == 'd');	

	}

	public static boolean or(String entrada,int pos)
	{//Verificar a presenca da expressao 'or'

		return (entrada.charAt(pos) == 'o' && entrada.charAt(pos + 1) == 'r');	

	}

	public static int operacaoNot(int entrada)
	{//Realizar operacao 'not'
		
		int saida;

		if( entrada == 0)
			saida = 1;
		else
			saida = 0;
		
		return saida;			
	
	}

	public static int[] organizarValores(String entrada, int pos)
	{//Organizar os valores dentro de uma expressao 'and' ou 'or'
		
		//Declaracao das variaveis
		int[] vet;
		int fim = posFimExpressao(entrada,pos)+pos + 1,tamVetor = 0;

		for ( int i = pos ; i < fim; i++)
		{//leitura da expressao para determinar a quantidade de valores 
			
			if ( ehVariavel(entrada,i) == true )//Se for encontrado variavel
				tamVetor++; 		

		} 

		vet = new int[tamVetor];//Crie um vetor com o numero de variaveis presentes

		for ( int i = 0 ; pos < fim; pos++)
		{//leitura da expressao para armazenar os valores das variaveis

			if ( ehVariavel(entrada,pos) == true )
			{//Se for encontrado variavel
	
				vet[i] = (int)entrada.charAt(pos) - 48;
				i++;			
				
			}						

		} 	
		
		return vet;//Retornar o vetor com os valores
	
	}

	public static int operacaoAnd(int[] valores)
	{//Realizar operacao 'and'
		
		int saida = 1;

		for ( int i = 0; i < valores.length ; i++ )
		{

			if (valores[i] == 0)
			{
	
				saida = 0;
				i = valores.length;	
			
			}
		}

		return saida;
			
	
	}

	public static int operacaoOr(int[] valores)
	{//Realizar operacao 'or'
		
		int saida = 1, contadorDe0 = 0;
	
		for ( int i = 0; i < valores.length ; i++ )
		{

			if (valores[i] == 0)
			{
	
				contadorDe0++;
			
			}
		}

		if ( contadorDe0 == valores.length ) 
			saida = 0;
		
		return saida;	
	
	}

	public static boolean ehVariavel(String entrada,int pos)
	{//Verificar a presenca de uma variavel, seja ela uma letra ou os numeros '0' e '1'

		return ((entrada.charAt(pos) >= 'A' && entrada.charAt(pos) <= 'Z') || 
			entrada.charAt(pos) == '0' || entrada.charAt(pos) == '1' );	

	}

	public static int posInicioExpressao(String entrada, int numExpr)
	{//Calcular o inicio da expressao geral, pulando o numero de variaveis inserido e seus valores
		
		int pos = 0,whitespaces = 0;
		while( whitespaces != (numExpr + 1) )
		{
			
			if ( entrada.charAt(pos) == ' ' ) 
				whitespaces++;
	
			pos++;			
			
		}
	
		return pos;
	
	}

	public static boolean temExpressao(String entrada, int pos)
	{//Verificando se tem uma expressao, dentro de outra expressao
		
		boolean resultado = false;
		for ( /**/; pos < entrada.length() ; pos++)
		{			
			
			if ( entrada.charAt(pos) == '(' ) 
			{

				resultado = true;
				pos = entrada.length();
	
			}			

		}

		return resultado;
	
	}

	public static int posFimExpressao(String entrada, int pos)
	{//Verificando onde eh que esta o fim da expressao, o ')'
		
		int saida;		

		for ( saida = 0 ; entrada.charAt(pos+1) != ')' ; saida++,pos++);
	
		return saida;
	
	}

	public static void lehResultado(boolean saida) 
	{//ler o resultado a expressao booleana calculada

		if (saida == true) 
		{
			MyIO.println("1");
		}
		else 
		{
			MyIO.println("0");
		}

	}	

}				
