#include<stdio.h>
#include<stdlib.h>
#include<string.h>

//struct String
typedef struct String
{

	char string[50];

}String;

typedef struct Celula {
	int elemento;        // Elemento inserido na celula.
	struct Celula* prox; // Aponta a celula prox.
} Celula;

String* new_String();
Celula* new_Celula(int elemento);

int main() {
	return 0;
}

String* new_String(){

	return (String*) malloc(sizeof(String));

}

Celula* new_Celula(int elemento) {
   Celula* nova = (Celula*) malloc(sizeof(Celula));
   nova->elemento = elemento;
   nova->prox = NULL;
   return nova;
}
