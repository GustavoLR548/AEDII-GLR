/*

Questao5
Leh uma expressao de algebra booleana e retorno com o seu resultado
Aluno: Gustavo Lopes Rodrigues
Matricula: 655264

*/
public class Questao14
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

		entrada = resolverAexpressao(entrada);
	
		if ( entrada.charAt(0) == '0' ) //Se o valor na string for igual a '0'
			resp = false;
		
		return resp;

	}

	public static int[] recuperarValores(String entrada, int numExpr)
	{

	
		entrada = ajustarValores(entrada);
		int[] vetor = new int[numExpr];
		return recuperarValores(entrada,vetor,0);

	}

	public static String ajustarValores(String entrada)
	{

		return ajustarValores(entrada,2);

	}

	public static String ajustarValores(String entrada, int i)
	{

		String saida = "";

		if ( not(entrada,i) == false && and(entrada,i) == false && or(entrada,i) == false)
		{

			if(entrada.charAt(i) != ' ' )
				saida += entrada.charAt(i);
			
			saida += ajustarValores(entrada,++i);	
		
		}

		return saida;

	}
	
	public static int[] recuperarValores(String entrada, int[] vetor, int i)
	{

		//Declaracao das variaveis
		if ( i < vetor.length )
		{

			vetor[i] = entrada.charAt(i) - 48;

			vetor = recuperarValores(entrada,vetor,++i);

		}
		
		return vetor;//retorne o array

	}

	public static String ajustarExpressaoEsubstituirValores(String entrada, int[] expr)
	{
	
		return ajustarExpressaoEsubstituirValores(entrada,expr,posInicioExpressao(entrada,0));		

	}

	public static String ajustarExpressaoEsubstituirValores(String entrada, int[] expr,int i)
	{

		//Declaracao da variavel
		String novaExpressao = "";
		
		
		if ( i < entrada.length() )
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

			novaExpressao += ajustarExpressaoEsubstituirValores(entrada,expr,++i);

		}

		return novaExpressao;//retorne a nova string
	
	}


	public static String substituirNandsEnors(String entrada)
	{

		return substituirNandsEnors(entrada,0);
	
	}


	public static String substituirNandsEnors(String entrada, int i) 
	{

		//Declaracao das variaveis
		String novaExpressao = "";
		
		if ( i < entrada.length() )
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
			
			novaExpressao += substituirNandsEnors(entrada,++i);
		
		}

		return novaExpressao;

	}

	public static String substituirAndsEors(String entrada)
	{

		return substituirAndsEors(entrada,0);

	}

	public static String substituirAndsEors(String entrada,int i)
	{ 

		//Declaracao da variavel
		String novaExpressao = "";

		if( i < entrada.length() )
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

			novaExpressao += substituirAndsEors(entrada,++i);

		}
	
		return novaExpressao;
	
	}

	public static String resolverAexpressao(String entrada)
	{	
		
		entrada = substituirAndsEors(entrada);		

		if (entrada.length() != 1)
		{
			
			entrada = resolverAexpressao(entrada);		
			
		}

		return entrada;		

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

		String expressao = resgatarExpressao(entrada,pos);

		int[] vet = new int[expressao.length()];

		return recuperarValores(expressao,vet,0);
	
	}

	public static String resgatarExpressao(String entrada, int i)
	{

		String resposta = "";
		
		if ( entrada.charAt(i) != '(' && entrada.charAt(i) != ')' )
		{
			
			if ( entrada.charAt(i) != ',' ) 
				resposta += entrada.charAt(i);

			resposta += resgatarExpressao(entrada,++i);			
	
		}

		return resposta;
	
	}

	public static int operacaoAnd(int[] valores)
	{

		return operacaoAnd(valores,0);

	}

	public static int operacaoAnd(int[] valores,int i)
	{//Realizar operacao 'and'
		
		int saida = 1;

		if ( i < valores.length )
		{

			if (valores[i] == 0) 
			{

				saida = 0;	
				i = valores.length;

			}
		
			if ( saida != 0 )
				saida = operacaoAnd(valores,++i);

		}		

		return saida;		
	
	}

	public static int operacaoOr(int[] valores)
	{

		return operacaoOr(valores,0);

	}

	public static int operacaoOr(int[] valores,int i)
	{//Realizar operacao 'or'
		
		int saida = 0;
	
		if ( i < valores.length )
		{

			if (valores[i] == 1)
			{

				saida = 1;	
				i = valores.length;

			}			
			
			if ( saida != 1 )
				saida = operacaoOr(valores,++i);			
			
		}
		
		return saida;	
	
	}

	public static boolean ehVariavel(String entrada,int pos)
	{//Verificar a presenca de uma variavel, seja ela uma letra ou os numeros '0' e '1'

		return ((entrada.charAt(pos) >= 'A' && entrada.charAt(pos) <= 'Z') || 
			entrada.charAt(pos) == '0' || entrada.charAt(pos) == '1' );	

	}

	public static int posInicioExpressao(String entrada, int i)
	{//Calcular o inicio da expressao geral, pulando o numero de variaveis inserido e seus valores
		
		
		if ( not(entrada,i) == false && and(entrada,i) == false && or(entrada,i) == false)
		{

			i = posInicioExpressao(entrada,++i);			
			
		}
	
		return i;
	
	}

	public static boolean temExpressao(String entrada, int pos)
	{//Verificando se tem uma expressao, dentro de outra expressao
		
		boolean resultado = false;
		if ( pos < entrada.length() )
		{			
			
			if ( entrada.charAt(pos) == '(' ) 
			{

				resultado = true;
				pos = entrada.length();
	
			}	
			
			if( resultado != true )
				resultado = temExpressao(entrada,++pos);		

		}

		return resultado;
	
	}

	public static int posFimExpressao(String entrada, int pos)
	{//Verificando onde eh que esta o fim da expressao, o ')'
		
		int saida = 0;		

		if ( entrada.charAt(pos+1) != ')' )
		{

			saida++;
			saida += posFimExpressao(entrada,++pos);

		}
	
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
