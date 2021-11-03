#include<stdio.h>
#include<stdlib.h>
#include<string.h>

typedef struct String {

	char string[50];

}string;

typedef struct Lista {
	
	String* array[51];
	int n;

}Lista;

String* new_String();
Lista* new_Lista(int n);
int getListaTam(String* entrada);
void setLista(Lista* l, String* entrada);
void mostrar(Lista* l);
void swap(Lista* l,int i, int j);
void selectionSort(Lista* l);

int main() 
{
	int n;
	String* entrada = new_String();
	scanf("%d\n", &n);
		
	for (int i = 0; i < n ; i++) {

		fgets(entrada->string,50,stdin);
		Lista* l = new_Lista(getListaTam(entrada));
		setLista(l,entrada);
		selectionSort(l);
		mostrar(l);

	}

}


String* new_String(){

	return (String*)malloc(sizeof(String));

}

Lista* new_Lista(int n) {

	Lista* resp = (Lista*)malloc(sizeof(Lista));
	resp->n = n;
	return resp;

}

int getListaTam(String* entrada) {

	int tam = 1;
	for (int i = 0 ; i < strlen(entrada->string) - 1; i++) {

		if ( entrada->string[i] == ' ' ) {

			tam++;

		}

	}
	return tam;

}

void setLista(Lista* l, String* entrada) {

	int j = 0;

	for (int i = 0 ; i < l->n; i++ ) {

		String* tmp = new_String();

		int j;		

		for (j = 0; entrada->string[j] != ' ' && j < strlen(entrada->string)-1 ; j++ );

		strncpy(tmp->string,entrada->string,j);

		l->array[i] = tmp;

		memmove(&entrada->string[0], &entrada->string[j], strlen(entrada->string));

	}

}

void mostrar(Lista* l) {

	for ( int i = 0; i < l->n; i++ ) {
		printf("%s ", l->array[i]->string);
	}

	printf("\n");

}

void selectionSort(Lista* l) {
   for (int i = 0; i < (l->n - 1); i++) {
      int indice = i;
      for (int j = (i + 1); j < l->n; j++){
         if (strlen(l->array[indice]->string) < strlen(l->array[j]->string)){
            indice = j;
         }
      }
      swap(l,indice, i);
   }
}

void swap(Lista* l,int i, int j) {
   String* temp = l->array[i];
   l->array[i] = l->array[j];
   l->array[j] = temp;
}


