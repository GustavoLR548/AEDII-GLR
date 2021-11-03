//Questao2
//Conferindo se a palavra eh palindromo em C
//Aluno: Gustavo Lopes Rodrigues
//Matricula: 655264

//Incluindo bibliotecas
#include<stdio.h>
#include<string.h>
#include<stdlib.h>

//Configurando booleano usando #define
#define bool      short
#define true      1
#define false     0

//declarando funcoes
bool verfFrase(char* frase);
void saida(bool resposta[], int contador);
bool isFim(char *entrada);

int main() 
{ 

	//Iniciando variaveis
	char entrada[1000];
	bool resposta[1000], pararLeitura = false;
	int contador = 0;

	do 
	{//Iniciando repeticao da leitura da string
		fgets(entrada, 1000, stdin);
		if (isFim(entrada) == true) 
		{ //Se a palavra lida for igual a "FIM"
			pararLeitura = true;//Sinalize para parar a leitura
		}
		else {//Senao
			resposta[contador] = verfFrase(entrada);//Verifique se eh palindromo
			contador++;
		}
	} while ( pararLeitura == false );
	saida(resposta, contador);//Leitura do resultado da verificacao
}

bool verfFrase(char* frase)
{

	//Iniciando variavel
	bool palindromo = true;

	//Verificacao
	for (int i = 0 ; i < (strlen(frase)) / 2; i++ ) {
		if (frase[i] != frase[((strlen(frase)) -2) - i]) {	
			palindromo = false;
		}
	}
	return palindromo;//retornando o resultado da verificacao pra funcao principal

}

void saida(bool resposta[], int contador) 
{

	//Iniciando leitura
	for (int i = 0; i < contador; i++) {
		if (resposta[i] == true ) {
			printf("SIM\n");
		}
		else {
			printf("NAO\n");
		}
	}

}

bool isFim(char *entrada) 
{

	return ((strlen(entrada) - 1) <= 3 && entrada[0] == 'F' && entrada[1] == 'I' && entrada[2] == 'M'); 

}

