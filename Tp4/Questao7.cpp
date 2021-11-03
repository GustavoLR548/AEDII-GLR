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

int contador;

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

typedef struct Lista
{
	Celula* primeiro;
	Celula* ultimo;

}Lista;

typedef struct Hash
{
	Lista* tabela[25];
	int tam;

}Hash;


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

//Lista e Hash
Lista* new_Lista();
Celula* new_Celula(StarWars* elemento);
Hash* new_Hash();
void inserirLista(Lista* l,StarWars* x);
void inserirHash(Hash* hash,StarWars* x);
bool pesquisarLista(Lista* l,String* x);
bool pesquisarHash(Hash* h,String* x);
int h(StarWars* elemento,Hash* h);



int main()
{

	String* entrada = new_String();
	scanf("%s", entrada->string);
	StarWars* Personagens;
	bool resp;


	Hash* hash = new_Hash();
	
	while (!isFim(entrada->string)) 
	{

		Personagens = new_StarWars();
		setPersonagem(entrada->string,Personagens);

		inserirHash(hash,Personagens);
		scanf("\n%s", entrada->string);

	}

	fgets(entrada->string, sizeof(entrada->string), stdin);
	entrada->string[strlen(entrada->string)-1] = '\0';

	while (!isFim(entrada->string)) 
	{

		if (strcmp(entrada->string,"") != 0)
		{
			printf("%s",entrada->string);
			resp = pesquisarHash(hash,entrada);
			if ( resp == true )
				printf(" SIM\n");
			else 
				printf(" NÃO\n");

		}

		fgets(entrada->string, sizeof(entrada->string), stdin);
		entrada->string[strlen(entrada->string)-1] = '\0';

	}

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

	printf(" ## %s ## %d ## 0 ## %s ## %s ## %s ## %s ## %s ## %s ##", temp->nome->string,temp->altura,temp->corDoCabelo->string,temp->corDaPele->string,temp->corDosOlhos->string,temp->anoNascimento->string,temp->genero->string,temp->homeworld->string);

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
Lista* new_Lista()
{
	Lista* novo = (Lista*)malloc(sizeof(Lista));
    novo->primeiro = novo->ultimo = new_Celula(NULL);
	return novo;
}

Celula* new_Celula(StarWars* elemento) {
   Celula* nova = (Celula*) malloc(sizeof(Celula));
   nova->elemento = clone(elemento);
   nova->prox = NULL;
   return nova;
}

Hash* new_Hash()
{
	Hash* novo = (Hash*)malloc(sizeof(Hash));
	novo->tam = 25;
	for ( int i = 0; i < novo->tam ; i++ ) {
		novo->tabela[i] = new_Lista();
	}
	return novo;
}

int h(StarWars* elemento,Hash* h) {
	return elemento->altura % h->tam;
}

bool pesquisarHash(Hash* h, String* elemento) {
	int i = 0;
	bool resp = false;
	while ( resp == false && i < 25 ) {
		resp = pesquisarLista(h->tabela[i],elemento);
		i++;
	}
	return resp;
}

void inserirHash(Hash* hash, StarWars* elemento) {
	int pos = h(elemento,hash);
	inserirLista(hash->tabela[pos],elemento);
}

bool pesquisarLista(Lista* l, String* elemento) {
	bool resp;
	for ( Celula* i = l->primeiro->prox; i != NULL; i = i->prox) {
		if(strcmp(i->elemento->nome->string,elemento->string) == 0 ) {
			resp = true;
			i = l->ultimo;
		}
	}
	return resp;
}

void inserirLista(Lista* l,StarWars* elemento) {
	Celula* tmp = new_Celula(elemento);
	tmp->prox = l->primeiro->prox;
	l->primeiro->prox = tmp;
	if ( l->primeiro == l->ultimo ) {
		l->ultimo = tmp;
	}
	tmp = NULL;
}


