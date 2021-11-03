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

typedef struct No {
	StarWars* elemento;       
	struct No* esq; 
	struct No* dir;
	int nivel;
} No;

typedef struct AVL
{

	No* raiz;

}AVL;


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

//Arvore AVL
int max(int num1, int num2);
int abs(int num);
AVL* new_AVL();
No* new_No(StarWars* elemento);
int getNivel(No* no);
No* setNivel(No* no);
bool pesquisar(String* x,AVL* a);
bool pesquisarRec(String* x, No* i);
void inserir(StarWars* x,AVL* a);
No* inserirRec(No* no, StarWars* x);
No* rotacionarDir(No* no);
No* rotacionarEsq(No* no);
No* balancear(No* no);

int main()
{

	String* entrada = new_String();
	scanf("%s", entrada->string);
	StarWars* Personagens;
	bool resp;

	AVL* a = new_AVL();
	
	while (!isFim(entrada->string)) 
	{

		Personagens = new_StarWars();
		setPersonagem(entrada->string,Personagens);

		inserir(Personagens,a);
		scanf("\n%s", entrada->string);

	}
	
	fgets(entrada->string, sizeof(entrada->string), stdin);
	fgets(entrada->string, sizeof(entrada->string), stdin);
	entrada->string[strlen(entrada->string)-1] = '\0';
	while (!isFim(entrada->string)) 
	{

		printf("%s", entrada->string);
		printf(" raiz ");
		resp = pesquisar(entrada,a);
		if ( resp == true )
			printf("SIM\n");
		else
			printf("NÃO\n");

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

int max(int num1, int num2)
{
    return (num1 > num2 ) ? num1 : num2;
}

int abs(int num)
{
    if (num > 0 )
		return num;
	else
		return -num;
}

/**
 * Inicializacoes
 */
AVL* new_AVL()
{
	AVL* novo = (AVL*)malloc(sizeof(AVL));
    novo->raiz = NULL;
	return novo;
}

No* new_No(StarWars* elemento) {
   No* nova = (No*) malloc(sizeof(No));
   nova->elemento = clone(elemento);
   nova->dir = NULL;
   nova->esq = NULL;
   nova->nivel = 1;
   return nova;
}

No* setNivel(No* no) {
   no->nivel = 1 + max(getNivel(no->esq),getNivel(no->dir));
   return no;
}

int getNivel(No* no) {
   return (no == NULL) ? 0 : no->nivel;
}

bool pesquisar(String* x,AVL* a) {
	return pesquisarRec(x, a->raiz);
}

bool pesquisarRec(String* x, No* i) {
	bool resp;
    if (i == NULL) {
       resp = false;
    } else if (strcmp(x->string,i->elemento->nome->string) == 0 ) {
       resp = true;
    } else if (strcmp(x->string,i->elemento->nome->string) < 0) {
		 printf("esq ");
         resp = pesquisarRec(x, i->esq);
    } else{
		 printf("dir ");
         resp = pesquisarRec(x, i->dir);
    }
    return resp;
}

void inserir(StarWars* x,AVL* a) {
	a->raiz = inserirRec(a->raiz, x);
}

	/**
	 * Metodo privado recursivo para inserir elemento.
	 * @param no No em analise.
	 * @param x Elemento a ser inserido.
	 * @return No em analise, alterado ou nao.
	 * @throws Exception Se o elemento existir.
	 */

No* inserirRec(No* no, StarWars* x) {
	if (no == NULL) { 
        no = new_No(x); 
      
    } else if (strcmp(x->nome->string,no->elemento->nome->string) < 0) { 
        no->esq = inserirRec(no->esq, x); 

    } else if (strcmp(x->nome->string,no->elemento->nome->string) > 0) { 
        no->dir = inserirRec(no->dir, x); 
      
    } else { 
    	printf("Erro ao inserir elemento!\n");
		exit(0); 
    }

    no = balancear(no);
	return no;
}

No* balancear(No* no) {
	if(no != NULL){
    int fator = getNivel(no->dir) - getNivel(no->esq);

         //Se balanceada
    if (abs(fator) <= 1){
       no = setNivel(no);

    //Se desbalanceada para a direita
    }else if (fator == 2){

    	int fatorFilhoDir = getNivel(no->dir->dir) - getNivel(no->dir->esq);

        //Se o filho a direita tambem estiver desbalanceado
        if (fatorFilhoDir == -1) {
   	        no->dir = rotacionarDir(no->dir);
        }
        no = rotacionarEsq(no);

        //Se desbalanceada para a esquerda
        }else if (fator == -2){

            int fatorFilhoEsq = getNivel(no->esq->dir) - getNivel(no->esq->esq);

            //Se o filho a esquerda tambem estiver desbalanceado
            if (fatorFilhoEsq == 1) {
               no->esq = rotacionarEsq(no->esq);
            }
            no = rotacionarDir(no);

         }else{
            printf("Erro fator de balanceamento, fator invalido!\n"); 
			exit(0);
         }
      }

  	return no;
}
No* rotacionarDir(No* no) {
	No* noEsq = no->esq;
    No* noEsqDir = noEsq->dir;

    noEsq->dir = no;
    no->esq = noEsqDir;

    no = setNivel(no);
    noEsq = setNivel(noEsq);

    return noEsq;
}

No* rotacionarEsq(No* no) {
    No* noDir = no->dir;
    No* noDirEsq = noDir->esq;

    noDir->esq = no;
    no->dir = noDirEsq;

    no = setNivel(no);
    noDir = setNivel(noDir);

    return noDir;
}
