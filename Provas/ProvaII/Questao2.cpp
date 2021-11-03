#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<err.h>

//struct String
typedef struct String
{

	char string[50];

}String;

typedef struct Celula {
	String* elemento;        // Elemento inserido na celula.
	struct Celula* prox; // Aponta a celula prox.
} Celula;

typedef struct Pilha
{
	Celula* topo;

}Pilha;

String* new_String();
Celula* new_Celula(String* elemento);
Pilha* new_Pilha();
void inserir(Pilha* p,String* x);
String* remover(Pilha* p);
void mostrar(Pilha* p);
String* processar(String* entrada);

int main() {
	int n;
	String* entrada = new_String();
	Pilha* p = new_Pilha();
	scanf("%d", &n);
	while ( n != 0 ) {
		scanf("%s\n", entrada->string);
		String* saida = processar(entrada);
		printf("%s\n",saida->string);
		n--;
	}
}

String* processar(String* entrada) {

	String* saida = new_String();	
	char op,op2 ='\0';

	for (int i = 0; i < strlen(entrada->string) - 1; i += 2) {

		if ( entrada->string[i] == '*' || entrada->string[i] == (char)92 || entrada->string[i] == '+' || entrada->string[i] == '-') {
			op = entrada->string[i];
			if ( entrada->string[i+1] == '(' ) {
				for ( i = i + 2; entrada->string[i] != ')'; i++ ) {
					if ( !(entrada->string[i] == '*' || entrada->string[i] == (char)92 || entrada->string[i] == '+' || entrada->string[i] == '-') ) {
						saida->string[i] = entrada->string[i];
					}
					else {
						op2 = entrada->string[i];
					}
				}
				i++;
				saida->string[i] = op2;
				i++;
				saida->string[i] = op;
			}
			else {
				saida->string[i-1] = entrada->string[i-1];
				saida->string[i] = entrada->string[i+1];
				i += 2;
			}
		}
		else {
			saida->string[i] = entrada->string[i];
		}

	}

	return saida;

}

String* new_String(){

	return (String*) malloc(sizeof(String));

}

Celula* new_Celula(String* elemento) {
   Celula* nova = (Celula*) malloc(sizeof(Celula));
   nova->elemento = elemento;
   nova->prox = NULL;
   return nova;
}

Pilha* new_Pilha()
{
	Pilha* novo = (Pilha*)malloc(sizeof(Pilha));
    novo->topo = NULL;
	return novo;
}

void inserir(Pilha* p,String* x) {
   Celula* tmp = new_Celula(x);
   tmp->prox = p->topo;
   p->topo = tmp;
   tmp = NULL;
}

String* remover(Pilha* p) {
   if (p->topo == NULL) {
      errx(1, "Erro ao remover!");
   }

   String* resp = p->topo->elemento;
   Celula* tmp = p->topo;
   p->topo = p->topo->prox;
   tmp->prox = NULL;
   free(tmp);
   tmp = NULL;
   return resp;
}

void mostrar(Pilha* p) {
   for ( Celula* i = p->topo; i != NULL; i = i->prox) {
		printf("%s\n", i->elemento->string);
   }
}

