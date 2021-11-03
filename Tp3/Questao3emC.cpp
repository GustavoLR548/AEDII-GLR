#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <err.h>

#define bool      short
#define true      1
#define false     0

//Declarando constante MAXTAM
const int MAXTAM = 50;

//struct String
typedef struct String
{

	char string[MAXTAM];

}String;

//struct longString
typedef struct longString
{

	char string[MAXTAM * 20];

}longString;

//struct dos personagens
typedef struct StarWars
{

	String* nome;
	String* corDoCabelo;
	String* corDaPele;
	String* corDosOlhos;
	String* anoNascimento;
	String* genero;
	String* homeworld;
	int altura;
	double peso;

}StarWars;

typedef struct Celula {
	StarWars* elemento;        // Elemento inserido na celula.
	struct Celula* prox; // Aponta a celula prox.
} Celula;

typedef struct Fila
{
	int tam;
	Celula* primeiro;
	Celula* ultimo;

}Fila;


//Funcoes da classe PERSONAGENS
StarWars* new_StarWars();
String* new_String();
longString* new_longString();
void setPersonagem(char* diretorio, StarWars* Personagens);
bool isFim(char* entrada);
StarWars* clone(StarWars* x);
void imprimir(StarWars* temp);
int tamExpressao(char* string);
int charToInt(char* string);

//Fila
Fila* new_Fila();
Celula* novaCelula(StarWars* elemento);
void inserir(Fila* f,StarWars* x);
StarWars* remover(Fila* f);
void mostrar(Fila* f);
void imprimirMediaAltura(Fila* f);

int main()
{

	String* entrada = new_String();
	scanf("%s", entrada->string);
	StarWars* Personagens;
	StarWars* tmp;

	Fila* fila = new_Fila();
	
	while (!isFim(entrada->string)) 
	{

		Personagens = new_StarWars();
		tmp = new_StarWars();
		setPersonagem(entrada->string,Personagens);

		if (fila->tam == 5)
		{

			tmp = remover(fila);

		}

		inserir(fila,Personagens);
		imprimirMediaAltura(fila);
		scanf("\n%s", entrada->string);

	}

	int n,pos;
	scanf("%d", &n);

	for (int i = 0; i <= n; i++)
	{

		tmp = new_StarWars();
		Personagens = new_StarWars();
		char* endereco = (char*)malloc(sizeof(char)*100);
		fgets(entrada->string, sizeof(entrada->string), stdin);
		entrada->string[strlen(entrada->string)-1] = '\0';

		if ( entrada->string[0] == 'I' )
		{

			if (fila->tam == 5)
			{

				tmp = remover(fila);

			}

			endereco = strstr(entrada->string,"/tmp");
			setPersonagem(endereco,Personagens);
			inserir(fila,Personagens);
			imprimirMediaAltura(fila);

		}
		else if ( entrada->string[0] == 'R' )
		{

			Personagens = remover(fila);
			printf("(R) %s\n", Personagens->nome->string); 

		}

	}

	mostrar(fila);

	return 0;

}

void setPersonagem(char* diretorio, StarWars* Personagens)
{

	String* altura = new_String();
	String* peso = new_String();
	longString* texto = new_longString();
	Personagens->nome = new_String();
	Personagens->genero = new_String();
	Personagens->homeworld = new_String();
	Personagens->corDoCabelo = new_String();
	Personagens->corDaPele = new_String();
	Personagens->corDosOlhos = new_String();
	Personagens->anoNascimento = new_String();

	char* linha = (char*)malloc(sizeof(char)*100);

	FILE* arq=fopen(diretorio, "r");

	fgets(texto->string,1000,arq);
	texto->string[strlen(texto->string)-1]='\0';

	//Pegando o nome do personagem
	linha = strstr(texto->string, "name");

	linha = strstr(linha, "\'");

	memmove(&linha[0], &linha[4], strlen(linha));

	strncpy(Personagens->nome->string,linha,tamExpressao(linha));

	//Pegando a altura do personagem
	linha = strstr(texto->string, "height");

	linha = strstr(linha, "\'");

	memmove(&linha[0], &linha[4], strlen(linha));

	strncpy(altura->string,linha,tamExpressao(linha));

	if ( strcmp(altura->string,"unknown") == 0 )
		Personagens->altura = 0;
	else
		Personagens->altura = atoi(altura->string);

	//Pegando a massa do personagem
	linha = strstr(texto->string, "mass");

	linha = strstr(linha, "\'");

	memmove(&linha[0], &linha[4], strlen(linha));

	strncpy(peso->string,linha,tamExpressao(linha));

	if ( strcmp(peso->string,"unknown") == 0 )
		Personagens->peso = 0;
	else
		Personagens->peso = atof(peso->string);

	//Pegando a cor do cabelo do personagem
	linha = strstr(texto->string, "hair_color");

	linha = strstr(linha, "\'");

	memmove(&linha[0], &linha[4], strlen(linha));

	strncpy(Personagens->corDoCabelo->string,linha,tamExpressao(linha));


	//Pegando a cor de pele do personagem
	linha = strstr(texto->string, "skin_color");

	linha = strstr(linha, "\'");

	memmove(&linha[0], &linha[4], strlen(linha));
	
	strncpy(Personagens->corDaPele->string,linha,tamExpressao(linha));	


	//Pegando a cor dos olhos do personagem
	linha = strstr(texto->string, "eye_color");

	linha = strstr(linha, "\'");

	memmove(&linha[0], &linha[4], strlen(linha));

	strncpy(Personagens->corDosOlhos->string,linha,tamExpressao(linha));	


	//Pegando o ano de nascimento do personagem
	linha = strstr(texto->string, "birth_year");

	linha = strstr(linha, "\'");

	memmove(&linha[0], &linha[4], strlen(linha));

	strncpy(Personagens->anoNascimento->string,linha,tamExpressao(linha));


	//Pegando o genero do personagem
	linha = strstr(texto->string, "gender");

	linha = strstr(linha, "\'");

	memmove(&linha[0], &linha[4], strlen(linha));

	strncpy(Personagens->genero->string,linha,tamExpressao(linha));


	//Pegando o planeta natal do personagem
	linha = strstr(texto->string, "homeworld");

	linha = strstr(linha, "\'");

	memmove(&linha[0], &linha[4], strlen(linha));

	strncpy(Personagens->homeworld->string,linha,tamExpressao(linha));

	fclose(arq);

}
StarWars* clone(StarWars* x)
{

	StarWars *temp;
	temp = x;

	return temp;

}
	
void imprimir(StarWars* temp)
{

	printf(" ## %s ## %d ## %g ## %s ## %s ## %s ## %s ## %s ## %s ##", temp->nome->string,temp->altura,temp->peso,temp->corDoCabelo->string,temp->corDaPele->string,temp->corDosOlhos->string,temp->anoNascimento->string,temp->genero->string,temp->homeworld->string);

}

bool isFim(char* entrada)
{

	return ( entrada[0] == 'F' && entrada[1] == 'I' && entrada[2] == 'M' );

}

int tamExpressao(char* string)
{
	int i = 0;
	while (string[i] != '\'' )
		i++;	
		
	return i;
}

int charToInt(char* string)
{
	int i = 3,resultado;

	if (string[i + 1] != ' ')
		resultado = ((int)string[i] - 48) * 10 + ((int)string[i + 1] - 48);
	else 
		resultado = ((int)string[i] - 48);

	return resultado;

}

String* new_String(){

	return (String*) malloc(sizeof(String));

}

longString* new_longString(){

	return (longString*) malloc(sizeof(longString));

}

StarWars* new_StarWars(){

	return (StarWars*) malloc(sizeof(StarWars));

}

/**
 * Inicializacoes
 */
Fila* new_Fila()
{
	Fila* novo = (Fila*)malloc(sizeof(Fila));
    novo->primeiro = novaCelula(NULL);
    novo->ultimo = novo->primeiro;
	novo->tam = 0;
	return novo;
}

Celula* novaCelula(StarWars* elemento) {
   Celula* nova = (Celula*) malloc(sizeof(Celula));
   nova->elemento = clone(elemento);
   nova->prox = NULL;
   return nova;
}

/**
 * Insere elemento na fila (politica FIFO).
 * @param x int Elemento a inserir.
 */
void inserir(Fila* f,StarWars* x) {
   f->tam++;
   f->ultimo->prox = novaCelula(x);
   f->ultimo = f->ultimo->prox;
}


/**
 * Remove elemento da fila (politica FIFO).
 * @return Elemento removido.
 */
StarWars* remover(Fila* f) {
   if (f->primeiro == f->ultimo) {
      errx(1, "Erro ao remover!");
   }
   Celula* tmp = f->primeiro;
   f->primeiro = f->primeiro->prox;
   StarWars* resp = clone(f->primeiro->elemento);
   tmp->prox = NULL;
   free(tmp);
   tmp = NULL;
   f->tam--;
   return resp;
}


/**
 * Mostra os elementos separados por espacos.
 */
void mostrar(Fila* f) {
   Celula* i;
   int contador = 0;
   for (i = f->primeiro->prox; i != NULL; i = i->prox) {
	  printf("[%d] ", contador++);
      imprimir(i->elemento);	
	  printf("\n");
   }
}

void imprimirMediaAltura(Fila* f)
{

	float acumulador = 0;
	int n = 0;

    Celula* i;
    for (i = f->primeiro->prox; i != NULL; i = i->prox) {

    	acumulador += i->elemento->altura;	
		n++;

    }

	float resp = acumulador/n;

	printf("%g\n", round(resp));

}

