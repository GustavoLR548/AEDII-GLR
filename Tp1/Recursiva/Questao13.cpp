//Questao13
//Fazendo alteracoes aleatorias em uma string em C
//Aluno: Gustavo Lopes Rodrigues
//Matricula: 655264

//Incluindo bibliotecas
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<stdbool.h>

//Declarando funções
int abs(int entrada);
bool isFim(char* entrada);
void aleatorio(char* entrada, int letra,char rand1, char rand2);

int main() 
{
	
	//Declarando variaveis
	char* entrada = (char*)malloc(1000 * sizeof(char*));
	bool pararLeitura = false;
	char rand1;
	char rand2;

	//Configurando o gerador de numeros aleatorios
	srand(4);	

	do
	{//Looping da leitura da entrada

		fgets(entrada,1000,stdin);

		if (isFim(entrada) == true) 
		{//Se "FIM" for lido

			pararLeitura = true;	

		}
		else 
		{// Caso contrario

		//Geracao dos numeros aleatorios
		rand1 = (char)('a' + (rand() % 26));
		rand2 = (char)('a' + (rand() % 26));

		aleatorio(entrada,0,rand1,rand2);//Gerando a nova string
		printf("%s",entrada);//Imprimindo a nova string

		}	


	} while ( pararLeitura == false );

	free(entrada);//liberando memoria que tinha no char*

}

void aleatorio(char* entrada, int letra,char rand1,char rand2) 
{

	//Entrada do laço recursivo
	if ( letra < strlen(entrada) ) 
	{

		if(entrada[letra] == rand1)
		{//Se o caractere na posicao entrada for igual a 'rand1'
		
			entrada[letra] = rand2;//mude o caractere para outro

		}

			aleatorio(entrada,++letra,rand1,rand2);//chamada recursiva

	}


}

int abs(int entrada)
{//Converte um numero para o seu valor absoluto

	if(entrada < 0)
		return -entrada;
	else
		return entrada;
}

bool isFim(char* entrada) 
{//Verifica se a entrada de uma string for igual a "FIM"

	return ( strlen(entrada) >= 3 && entrada[0] == 'F' && entrada[1] == 'I' && entrada[2] == 'M' );

}



			
