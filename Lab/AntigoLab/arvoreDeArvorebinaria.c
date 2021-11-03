/**
 * Arvore binaria de pesquisa
 * @author Max do Val Machado
 */
#include <err.h>
#include <stdlib.h>
#include <stdio.h>

#define bool   short
#define true   1
#define false  0


/* Configuracao dos nos */
typedef struct No2 {
      int elemento;
	   struct No2 *esq, *dir;
} No2;

typedef struct No {
      int elemento;
	   struct No *esq, *dir;
	   struct No2 *outro;
} No;
/**
 * Criacao do novo no
 * @param elemento Conteudo do no.
 */
 No* novoNo(int elemento) {
   No* novo = (No*) malloc(sizeof(No));
   novo->elemento = elemento;
   novo->esq = NULL;
   novo->dir = NULL;
   novo->outro = NULL;
   return novo;
}

 No2* novoNo2(int elemento) {
   No2* novo = (No2*) malloc(sizeof(No2));
   novo->elemento = elemento;
   novo->esq = NULL;
   novo->dir = NULL;
   return novo;
}

/**/

bool pesquisarRec(int, No*);
bool pesquisarSegundaArvore(int, No2*);
void mostrarCentralRec(No*);
void mostrarPreRec(No*);
void mostrarPosRec(No*);
void inserirRec(int, No**);
void removerRec(int, No**);
void antecessor(No**, No**);

void start();
bool pesquisar(int);
void mostrarCentral();
void mostrarPre();
void mostrarPos();
void inserir(int);
void remover(int);

/*
 * Variavel global
 */
No* raiz;

/**
 * Criar arvore binaria.
 */
void start() {
   raiz = NULL;
}

/**
 * Metodo publico iterativo para pesquisar elemento.
 * @param x Elemento que sera procurado.
 * @return <code>true</code> se o elemento existir,
 * <code>false</code> em caso contrario.
 */
bool pesquisar(int x) {
   return pesquisarRec(x, raiz);
}

/**
 * Metodo privado recursivo para pesquisar elemento.
 * @param x Elemento que sera procurado.
 * @param i No em analise.
 * @return <code>true</code> se o elemento existir,
 * <code>false</code> em caso contrario.
 */
bool pesquisarRec(int x, No* i) {
   bool resp;
   if (i == NULL) {
      resp = false;

   } else if (x == i->elemento) {
      resp = pesquisarSegundaArvore(x,i->outro);

   } else if (x < i->elemento) {
      resp = pesquisarRec(x, i->esq);

   } else {
      resp = pesquisarRec(x, i->dir);
   }
   return resp;
}

bool pesquisarSegundaArvore(int x, No2* i) {
   bool resp;
   if (i == NULL) {
      resp = false;

   } else if (x == i->elemento) {
      resp = pesquisarSegundaArvore(x,i->outro);

   } else if (x < i->elemento) {
      resp = pesquisarRec(x, i->esq);

   } else {
      resp = pesquisarRec(x, i->dir);
   }
   return resp;
}

/**
 * Metodo publico iterativo para exibir elementos.
 */
void mostrarCentral() {
   printf("[ ");
   mostrarCentralRec(raiz);
   printf("]\n");
}

/**
 * Metodo privado recursivo para exibir elementos.
 * @param i No em analise.
 */
void mostrarCentralRec(No* i) {
   if (i != NULL) {
      mostrarCentralRec(i->esq);
      printf("%d ", i->elemento);
      mostrarCentralRec(i->dir);
   }
}

/**
 * Metodo publico iterativo para exibir elementos.
 */
void mostrarPre() {
   printf("[ ");
   mostrarPreRec(raiz);
   printf("]\n");
}

/**
 * Metodo privado recursivo para exibir elementos.
 * @param i No em analise.
 */
void mostrarPreRec(No* i) {
   if (i != NULL) {
      printf("%d ", i->elemento);
      mostrarPreRec(i->outro);
      mostrarPreRec(i->esq);
      mostrarPreRec(i->dir);
   }
}

/**
 * Metodo publico iterativo para exibir elementos.
 */
void mostrarPos() {
   printf("[ ");
   mostrarPosRec(raiz);
   printf("]\n");
}

/**
 * Metodo privado recursivo para exibir elementos.
 * @param i No em analise.
 */
void mostrarPosRec(No* i) {
   if (i != NULL) {
      mostrarPosRec(i->outro);
      mostrarPosRec(i->esq);
      mostrarPosRec(i->dir);
      printf("%d ", i->elemento);
   }
}

/**
 * Metodo publico iterativo para inserir elemento.
 * @param x Elemento a ser inserido.
 */
void inserir(int x) {
   inserirRec(x, &raiz);
}

/**
 * Metodo privado recursivo para inserir elemento.
 * @param x Elemento a ser inserido.
 * @param i No** endereco do ponteiro No
 */
void inserirRec(int x, No** i) {
   if (*i == NULL) {
      *i = novoNo(x);

   } else if (x < (*i)->elemento) {
      inserirRec(x, &((*i)->esq));

   } else if (x > (*i)->elemento) {
      inserirRec(x, &((*i)->dir));

   } else {
      errx(1, "Erro ao inserir!");
   }
}

/**
 * Metodo publico iterativo para remover elemento.
 * @param x Elemento a ser removido.
 */
void remover(int x) {
   removerRec(x, &raiz);
}

/**
 * Metodo privado recursivo para remover elemento.
 * @param x Elemento a ser removido.
 * @param i No** endereco do ponteiro No
 */
void removerRec(int x, No** i) {
   if (*i == NULL) {
      errx(1, "Erro ao remover!");

   } else if (x < (*i)->elemento) {
      removerRec(x, &((*i)->esq));

   } else if (x > (*i)->elemento) {
      removerRec(x, &((*i)->dir));

   } else if ((*i)->dir == NULL) {
      No* del = *i;
      *i = (*i)->esq;
      free(del);

   } else if ((*i)->esq == NULL) {
      No* del = *i;
      *i = (*i)->dir;
      free(del);

   } else {
      antecessor(i, &((*i)->esq));
   }
}

/**
 * Metodo para trocar no removido pelo antecessor.
 * @param i No** endereco do ponteiro No que contem o elemento removido.
 * @param j No** endereco do ponteiro No da subarvore esquerda.
 */
void antecessor(No** i, No** j) {
   if ((*j)->dir != NULL) {
      antecessor(i, &((*j)->dir));

   } else {
      No* del = *j;
      (*i)->elemento = (*j)->elemento;
      (*j) = (*j)->esq;
      free(del);
   }
}

int main(){
   return 1;
}

