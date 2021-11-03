#include<stdio.h>
#include<stdlib.h>
#include<string.h>

#define bool      short
#define true      1
#define false     0


typedef struct String {

	char string[50];

}String;

typedef struct Assassino {

	String* nome;
	int contador;

}Assassino;

typedef struct Lista {

	Assassino* array[50];
	int tam;	

}Lista;

String* new_String();
void setAssassino(String* entrada,Lista* lista);
Lista* new_Lista();
Assassino* new_Assassino(String* nome);
Assassino* new_Vitima(String* nome);
void mostrar(Lista* lista);
bool pesquisa(String* entrada,Lista* lista);
void adicionarMorte(String* entrada,Lista* lista);
void morreu(String* entrada,Lista* lista);
bool isFim(String* entrada);
bool pesquisaMorte(String* entrada,Lista* lista);

int main() {

	String* entrada = new_String();
	Lista* lista = new_Lista();
	
	fgets(entrada->string,50,stdin);

	while ( isFim(entrada) == false) {

		setAssassino(entrada,lista);
		fgets(entrada->string,50,stdin);

	}
	
	if ( lista->tam != 0 ) 
		mostrar(lista);

}

void setAssassino(String* entrada,Lista* lista) {

	String* assassino = new_String();
	String* vitima = new_String();

	int i;
	for (i = 0 ; i < strlen(entrada->string)-1 && entrada->string[i] != ' '; i++);

	strncpy(assassino->string,entrada->string,i);

	memmove(&entrada->string[0], &entrada->string[i+1], strlen(entrada->string));

	strncpy(vitima->string,entrada->string,strlen(entrada->string)-1);

	if( pesquisa(assassino,lista) == true) {
		adicionarMorte(assassino,lista);

	}
	else if ( pesquisa(assassino,lista) == false ) {
		lista->array[lista->tam] = new_Assassino(assassino);
		lista->tam++;
	}

	if ( pesquisa(vitima,lista) == true ) {
		morreu(vitima,lista);

	}
	else if ( pesquisa(vitima,lista) == false) {
		lista->array[lista->tam] = new_Vitima(vitima);
		lista->tam++;
	}

}

void mostrar(Lista* lista) {

	printf("HALL OF MURDERERS\n");

	for ( int i = 0 ; i < lista->tam ; i++ ) {
		
		if ( lista->array[i]->contador > 0 ) {
			printf("%s %d\n", lista->array[i]->nome->string,lista->array[i]->contador);
		}

	}

}

bool pesquisa(String* entrada,Lista* lista) {

	bool resp = false;

	for ( int i = 0; i < lista->tam ; i++ ) {
		if ( strcmp(entrada->string,lista->array[i]->nome->string) == 0) {
			resp = true;
			i = lista->tam;
		}
	}

	return resp;
}

bool pesquisaMorte(String* entrada,Lista* lista) {

	bool resp = false;

	for ( int i = 0; i < lista->tam ; i++ ) {
		if ( strcmp(entrada->string,lista->array[i]->nome->string) == 0 && lista->array[i]->contador < 0) {
			resp = true;
			i = lista->tam;
		}
	}

	return resp;
}


void morreu(String* entrada,Lista* lista) {

	for ( int i = 0; i < lista->tam ; i++ ) {
		if ( strcmp(entrada->string,lista->array[i]->nome->string) == 0) {
			lista->array[i]->contador = -100;
		}
	}
}

void adicionarMorte(String* entrada,Lista* lista) {

	for ( int i = 0; i < lista->tam ; i++ ) {
		if ( strcmp(entrada->string,lista->array[i]->nome->string) == 0) {
			lista->array[i]->contador++;
			i = lista->tam;
		}
	}
}

String* new_String(){

	return (String*)malloc(sizeof(String));

}

Assassino* new_Assassino(String* nome){

	Assassino* resp = (Assassino*)malloc(sizeof(Assassino));
	resp->nome = new_String();
	strcpy(resp->nome->string,nome->string);
	resp->contador = 1;

	return resp;

}

Assassino* new_Vitima(String* nome){

	Assassino* resp = (Assassino*)malloc(sizeof(Assassino));
	resp->nome = new_String();
	strcpy(resp->nome->string,nome->string);
	resp->contador = -100;

	return resp;

}

Lista* new_Lista(){

	Lista* resp = (Lista*)malloc(sizeof(Lista));
	resp->tam = 0;
	return resp;

}

bool isFim(String* entrada) 
{

	return ((strlen(entrada->string)-1) == 3 && entrada->string[0] == 'F' && entrada->string[1] == 'I' && entrada->string[2] == 'M');  

}
