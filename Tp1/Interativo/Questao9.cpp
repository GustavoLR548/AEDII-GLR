/*
Questao9
Le um link html e confere a quantidade de padroes presentes
Aluno: Gustavo Lopes Rodrigues
Matricula: 655264
*/

//Importando biblioteca
#include <stdio.h>

//Declarando as funcoes que sera utilizada
void leituraNums(FILE *arq, int nNumeros);
void lerArq(FILE *arq,int n);

int main() 
{

	FILE *arq;//Criando ponteiro para o arquivo
	int nNumeros;//Criando variavel para armazenar a quantidade de numeros a ler
	scanf("%d" , &nNumeros);
	leituraNums(arq, nNumeros);//fazendo a leitura dos numeros e colocando no arquivo
	lerArq(arq, nNumeros);//leitura do arquivo em reverso
	return 0;

}

void leituraNums(FILE *arq, int nNumeros) 
{

	//Iniciando a variavel para armazenar o numero e abrindo o arquivo
	float input;
	arq = fopen("teste.txt","w");
	for (int i = 1; i <= nNumeros; i++) 
	{ //repeticao leitura do numero e armazenamento do mesmo no arquivo
		scanf("%g", &input);
		fprintf(arq,"%g\n",input);
	}
	fclose(arq); //fechando arquivo

}

void lerArq(FILE *arq,int n) 
{

	//Iniciando a variavel para armazenar o numero e abrindo o arquivo
	float numLido;
	arq = fopen("teste.txt","r");
	//Leitura dos numeros: o programa ele faz a leitura dos numeros do inicio ao fim, porem, apenas imprimi o ultimo numero lido
	for (int j = 0 ; j < n ; j++)
	{
		fseek(arq, 0, SEEK_SET);//colocando a posicao para leitura no inicio do arquivo
		for (int i = 1; i <= n-j ; i++) 
		{
			fscanf(arq,"%g\n", &numLido); //leitura do numero
			if ( i == (n-j) ) //Ao chegar na ultima posicao do arquivo 
			{
				printf("%g\n", numLido);//Escreva na tela o numero
			}
		}
	}
	fclose(arq);

}

