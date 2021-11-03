#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define bool      short
#define true      1
#define false     0
#define equals(a, b) (((strcmp(a, b) == 0) ? true : false))
#define NUMENTRADA 1000
#define TAMLINHA   1000

typedef struct string
{
  char texto[1000];
}String;


typedef struct personagem
{
  String* nome;
  int altura;
  double peso;
  String* corDoCabelo;
  String* codDaPele;
  String* corDosOlhos;
  String* anoNascimento;
  String* genero;
  String* homeworld;
}Personagem;


bool isFim(String* s)
{
  return ( s->texto[0]  == 'F' && s->texto[1] == 'I' && s->texto[2] == 'M');
}

void imprimir(Personagem* personagem)
{
  printf("## %s ##",personagem -> nome -> texto);
  /*printf(" %i ##",Personagem -> altura);
  printf(" %g ##",Personagem -> peso);
  printf(" %s ##",Personagem -> corDoCabelo);
  printf(" %s ##",Personagem -> codDaPele);
  printf(" %s ##",Personagem -> corDosOlhos);
  printf(" %s ##",Personagem -> anoNascimento);
  printf(" %s ##",Personagem -> genero);
  printf(" %s ##",Personagem -> homeworld);*/
}

Personagem* novo_personagem()
{
  return (Personagem*)malloc(sizeof(Personagem));
}

String* criaString()
{
  return (String*)malloc(sizeof(String));
}

int tamExpressao(char* string)
{
  int i = 0;
  while (string[i] != '\'')
  i++;
  return i;
}
 
void setnome (String* nome, Personagem* personagem)
{

}

void InserindoInformacoes(String* arquivo , Personagem* personagem)
{
  String* nome = criaString();
  String* altura = criaString();
  String* peso = criaString();
  String* corDoCabelo = criaString();
  String* codDaPele = criaString();
  String* corDosOlhos = criaString();
  String* anoNascimento = criaString();
  String* genero = criaString();
  String* homeworld = criaString();

  printf("%s",arquivo -> texto);

  char* linha = (char*)malloc(sizeof(char)*100);

  linha = strstr(arquivo->texto,"name");

  linha = strstr(linha,"\'");
 
  memmove(&linha[0],&linha[4],strlen(linha));

  strncpy(personagem->nome->texto,linha,tamExpressao(linha));

}


String* lerArquivo(String* path){  //Funcao para extrair o texto de um arquivo

		//printf("Arquivo: %s\n", path->texto);
    FILE* arq = fopen(path->texto, "r");
    String* saida = criaString();

		if (arq == NULL) {
			printf("Erro ao ler arquivo\n");
			exit(1);
		} else {
    	fgets(saida -> texto, NUMENTRADA, arq);
		}
    
    //printf("%s\n",saida -> texto);

    return saida;
    
}

String* consertarFgets(String* entrada){
	int pos = 0;
	String* saida = criaString();

	while(entrada->texto[pos] != '\n' && entrada->texto[pos] != '\0'){
		saida->texto[pos] = entrada->texto[pos];
		pos++;	
	}
	saida->texto[pos] = '\0';
		 
	return saida;
}

int main()
{
  String* entrada = criaString();
  scanf("%s", entrada->texto);
  String* entradaCorrigida = consertarFgets(entrada);
  String* arquivo = lerArquivo(entradaCorrigida);
  while (!isFim(entradaCorrigida))
  {
    scanf("%s", entrada->texto);
    if(!isFim(entrada)) {
	  	Personagem* personagem = novo_personagem();
      entradaCorrigida = consertarFgets(entrada);
			arquivo = lerArquivo(entradaCorrigida);
      //printf("%s\n",arquivo -> texto);
      InserindoInformacoes(arquivo, personagem);
      //imprimir(personagem);
		}
  }

}
