#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

#define bool      short
#define true      1
#define false     0

//Declarando constante MAXTAM
const int MAXTAM = 50;

int numComparacoes = 0,numMovimentacoes = 0;

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

typedef struct Lista
{
	int n;
	StarWars* array[MAXTAM];

}Lista;

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

//Lista
Lista* new_Lista();
void inserirInicio(Lista* x,StarWars* y);
void inserirFim(Lista* x,StarWars* y);
void inserir(Lista* x,StarWars* y, int pos);
StarWars* removerInicio(Lista* x);
StarWars* removerFim(Lista* x);
StarWars* remover(Lista* x,int pos);
void mostrar(Lista* x);
bool pesquisar(Lista* y,char* x);
void quicksort(Lista* x);
void quicksortNome(Lista* x,int esq, int dir);
void quicksortCabelo(Lista* x,int esq, int dir);
void swap(int i, int j,Lista* x);


int main()
{

	String* entrada = new_String();
	scanf("%s", entrada->string);
	StarWars* Personagens;
	bool resp;

	Lista* lista = new_Lista();
	
	while (!isFim(entrada->string)) 
	{

		Personagens = new_StarWars();
		setPersonagem(entrada->string,Personagens);
		inserirFim(lista,Personagens);
		scanf("\n%s", entrada->string);

	}
    
    clock_t tempo = clock();

    quicksort(lista);

    tempo = clock() - tempo;

	mostrar(lista);

    FILE *arq = fopen("matricula_quicksort.txt","w");
    fprintf(arq,"655264 %d %d %lf\n",numComparacoes,numMovimentacoes,((double)tempo)/((CLOCKS_PER_SEC/1000)));

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

	printf("## %s ## %d ## %g ## %s ## %s ## %s ## %s ## %s ## %s ##", temp->nome->string,temp->altura,temp->peso,temp->corDoCabelo->string,temp->corDaPele->string,temp->corDosOlhos->string,temp->anoNascimento->string,temp->genero->string,temp->homeworld->string);

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
	novo->n = 0;
	return novo;
}
 
 
/**
 * Insere um elemento na primeira posicao da lista e move os demais
 * elementos para o fim da 
 * @param x int elemento a ser inserido.
 */
void inserirInicio(Lista* x, StarWars* y) {
   int i;
 
   //validar insercao
   if(x->n >= MAXTAM){
      printf("Erro ao inserir!");
      exit(1);
   } 
 
   //levar elementos para o fim do array
   for(i = x->n; i > 0; i--){
      x->array[i] = clone(x->array[i-1]);
   }
 
   x->array[0] = clone(y);
   x->n++;
}
 
 
/**
 * Insere um elemento na ultima posicao da 
 * @param x int elemento a ser inserido.
 */
void inserirFim(Lista* x,StarWars* y) {
 
   //validar insercao
   if(x->n >= MAXTAM){
      printf("Erro ao inserir!");
      exit(1);
   }
 
   x->array[x->n] = clone(y);
   x->n++;
}
 
 
/**
 * Insere um elemento em uma posicao especifica e move os demais
 * elementos para o fim da 
 * @param x int elemento a ser inserido.
 * @param pos Posicao de insercao.
 */
void inserir(Lista* x, StarWars* y, int pos) {
   int i;
 
   //validar insercao
   if(x->n >= MAXTAM || pos < 0 || pos > x->n){
      printf("Erro ao inserir!");
      exit(1);
   }
 
   //levar elementos para o fim do array
   for(i = x->n; i > pos; i--){
      x->array[i] = clone(x->array[i-1]);
   }
 
   x->array[pos] = clone(y);
   x->n++;
}
 
 
/**
 * Remove um elemento da primeira posicao da lista e movimenta 
 * os demais elementos para o inicio da mesma.
 * @return resp int elemento a ser removido.
 */
StarWars* removerInicio(Lista* x) {
   int i;
   StarWars* resp = (StarWars*)malloc(sizeof(StarWars));
 
   //validar remocao
   if (x->n == 0) {
      printf("Erro ao remover!");
      exit(1);
   }
 
   resp = clone(x->array[0]);
   x->n--;
 
   for(i = 0; i < x->n; i++){
      x->array[i] = clone(x->array[i+1]);
   }
 
   return resp;
}
 
 
/**
 * Remove um elemento da ultima posicao da 
 * @return resp int elemento a ser removido.
 */
StarWars* removerFim(Lista* x) {
 
   //validar remocao
   if (x->n == 0) {
      printf("Erro ao remover!");
      exit(1);
   }
 
   return x->array[--x->n];
}
 
 
/**
 * Remove um elemento de uma posicao especifica da lista e 
 * movimenta os demais elementos para o inicio da mesma.
 * @param pos Posicao de remocao.
 * @return resp int elemento a ser removido.
 */
StarWars* remover(Lista* x,int pos) {
   int i;
   StarWars* resp = (StarWars*)malloc(sizeof(StarWars));
 
   //validar remocao
   if (x->n == 0 || pos < 0 || pos >= x->n) {
      printf("Erro ao remover!");
      exit(1);
   }
 
   resp = clone(x->array[pos]);
   x->n--;
 
   for(i = pos; i < x->n; i++){
      x->array[i] = clone(x->array[i+1]);
   }
 
   return resp;
}
 
 
/**
 * Mostra os array separados por espacos.
 */
void mostrar(Lista* x) {
   int i;
 
   for(i = 0; i < x->n; i++){
	  printf(" ");
      imprimir(x->array[i]);	
	  printf("\n");
   }

}
 
void quicksort(Lista* x)
{
		
	int i = 0, j = 1;

	quicksortCabelo(x,0, x->n-1);

	while ( i != (x->n-1) && j != (x->n-1) )
    {
		while (strcmp(x->array[j]->corDoCabelo->string,x->array[i]->corDoCabelo->string) == 0 ) 
		{

	    	j++;
		
	    }	
	
	    if ( j - i != 0 )
			quicksortNome(x,i,j-1);
	
		i = j;		
	
	  }

}

void quicksortCabelo(Lista* x,int esq, int dir)
{
        int i = esq, j = dir;
        StarWars* pivo = clone(x->array[(dir+esq)/2]);

        while (i <= j) {
            while (strcmp(x->array[i]->corDoCabelo->string,pivo->corDoCabelo->string) < 0 ) {
				i++;
				numComparacoes++;
            } 

			while (strcmp(x->array[j]->corDoCabelo->string,pivo->corDoCabelo->string) > 0 ) {
				j--;
				numComparacoes++;
            } 

			if (i <= j) {
				numMovimentacoes++;
                swap(i, j, x);
                i++;
                j--;
            }
        }
        if (esq < j) 
		     quicksortCabelo(x,esq, j);
        if (i < dir) 
             quicksortCabelo(x,i, dir);
}

void quicksortNome(Lista* x,int esq, int dir)
{
        int i = esq, j = dir;

        StarWars* pivo = clone(x->array[(dir+esq)/2]);

        while (i <= j) {
            while (strcmp(x->array[i]->nome->string,pivo->nome->string) < 0 ) {
				i++;
				numComparacoes++;
            } 

			while (strcmp(x->array[j]->nome->string,pivo->nome->string) > 0 ) {
				j--;
				numComparacoes++;
            } 

			if (i <= j) {
				numMovimentacoes++;
                swap(i, j, x);
                i++;
                j--;
            }
        }
        if (esq < j) 
		     quicksortNome(x,esq, j);
        if (i < dir) 
             quicksortNome(x,i, dir);
}

void swap(int i, int j,Lista* x) {
   StarWars* temp = clone(x->array[i]);
   x->array[i] = clone(x->array[j]);
   x->array[j] = clone(temp);
}

/**
 * Procura um array e retorna se ele existe.
 * @param x int elemento a ser pesquisado.
 * @return <code>true</code> se o array existir,
 * <code>false</code> em caso contrario.
 */
bool pesquisar(Lista* y,char* x) {

   bool resp = false;
   int dir = y->n -1, esq = 0, meio;

   while ( esq <= dir) {
	numComparacoes++;
	meio = (esq + dir) / 2;
	if (strcmp(y->array[meio]->nome->string,x) == 0) {
		numComparacoes++;
	    resp = true;
	    esq = y->n;
	}
	else if (strcmp(y->array[meio]->nome->string,x) < 0) {
		numComparacoes++;
	    esq = meio + 1;
	} 
	else {
		numComparacoes++;
	    dir = meio - 1;
	}
   }

   return resp;
}

