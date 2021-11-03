#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

#define bool      short
#define true      1
#define false     0

//Declarando constante MAXTAM
const int MAXTAM = 50;
const int TAM = 6;

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

typedef struct Fila
{
	int primeiro,ultimo;
	StarWars* array[TAM+1];

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
void inserir(StarWars* x,Fila* y);
Fila* new_Fila();
StarWars* remover(Fila* x);
bool isVazia(Fila* x);
void mostrar(Fila* x);
void imprimirMediaAltura(Fila* x);

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

		if (((fila->ultimo + 1) % TAM ) == fila->primeiro)
		{

			tmp = remover(fila);

		}

		inserir(Personagens,fila);
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

			if (((fila->ultimo + 1) % TAM ) == fila->primeiro)
			{

				tmp = remover(fila);

			}

			endereco = strstr(entrada->string,"/tmp");
			setPersonagem(endereco,Personagens);
			inserir(Personagens,fila);
			imprimirMediaAltura(fila);

		}
		else if ( entrada->string[0] == 'R' )
		{

			Personagens = remover(fila);
			printf("(R) %s\n", Personagens->nome->string); 

		}

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
	novo->primeiro = novo->ultimo = 0;
	return novo;
}
 
 
void inserir(StarWars* x,Fila* y) {

   //validar insercao

   if (((y->ultimo + 1) % TAM ) == y->primeiro) {
      printf("Erro ao inserir!");
      exit(1);
   }

   y->array[y->ultimo] = clone(x);
   y->ultimo = (y->ultimo + 1) % TAM;
	
}


/**
 * Remove um elemento da primeira posicao da fila e movimenta 
 * os demais elementos para o primeiro da mesma.
 * @return resp int elemento a ser removido.
 * @Se a fila estiver vazia.
 */
StarWars* remover(Fila* x) {

   //validar remocao
   if (x->primeiro == x->ultimo) {
      printf("Erro ao remover!");
      exit(1);
   }

   StarWars* resp = new_StarWars();

   resp = clone(x->array[x->primeiro]);

   x->primeiro = (x->primeiro + 1) % TAM;

   return resp;
}

/**
 * Retorna um bool indicando se a fila esta vazia
 * @return bool indicando se a fila esta vazia
 */
bool isVazia(Fila* x) {
   return (x->primeiro == x->ultimo); 
}

void imprimirMediaAltura(Fila* x)
{

	float acumulador = 0;
	int n = 0;

    for(int i = x->primeiro; i != x->ultimo; i = ((i + 1) % TAM)){

    	acumulador += x->array[i]->altura;	
		n++;

    }

	float resp = acumulador/n;

	printf("%g\n", round(resp));

}
 
/**
 * Mostra os array separados por espacos.
 */
void mostrar(Fila* x) {
   int i;
 
   for(i = x->primeiro; i != x->ultimo; i = ((i + 1) % TAM)){
	  printf("[%d] ", i);
      imprimir(x->array[i]);	
	  printf("\n");
   }

}

