//NOME:ALEXANDRE FONSECA NOVAES
//MATRÍCULA:655337

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define bool int
#define true 1
#define false 0

typedef struct {

    char* nome;
    int altura;
    double peso;
    char* corDoCabelo;
    char* corDaPele;
    char* corDosOlhos;
    char* anoNascimento;
    char* genero;
    char* homeworld;

} Personagem;

char* ChangeAll(char* s, char c, char c2)
	{
		char* resp = (char*)malloc(sizeof(char)*700);

		for(int i=0;i<strlen(s);i++)
        {
            if(s[i]==c)
                resp[i]= c2;
            else
                resp[i] = s[i];

            if(i==strlen(s)-1)
                resp[i+1]='\0';
        }


		return resp;
}
char* MiniString(char* s,int start,int end)
{
    char* resp = (char*)malloc(sizeof(char)*700);

    for(int i=start,j=0;i<end;i++,j++)
    {
        resp[j] = s[i];
        if(i==end-1)
            resp[j+1]='\0';
    }


    return resp;
}

int NumberIndex(char* s,char* comp)
	{
		int index=0;
		//MyIO.println(comp);
		for(int i=0;i<strlen(s)-strlen(comp);i++)
		{
			index=0;
			for(int j=0;j<strlen(comp);j++)
			{
				if(index!=-1)
					index=i;
				if(s[i+j]!=comp[j])
				{
					index=-1;
				}

				if(j==strlen(comp)-1 && index!=-1)
				{
					i=strlen(s)-strlen(comp);
				}
			}
		}

		return index;
	}

bool isFim(char *s)
{
   return (s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
}
void Imprimir(Personagem p)
{
    printf(" ## %s ## %d",p.nome,p.altura);

    if(p.peso == (int)p.peso)
        printf(" ## %d",(int)p.peso);
    else
        printf(" ## %f",p.peso);

    printf(" ## %s ## %s ## %s ## %s ## %s ## %s ##\n",p.corDoCabelo,p.corDaPele,p.corDosOlhos,p.anoNascimento,p.genero,p.homeworld);
}

void Ler(char* s,Personagem p)
{
    FILE *arq;
    arq = fopen(s,"r");
    char str700[700];
    //char auxstr600[600];
    char *str = NULL;
    char *auxstr = NULL;

    //Aloca a memória para utilizar a string que usa menos memória
    fgets(str700, 700, arq);
    str = (char*)malloc(sizeof(char)*700);
    auxstr = (char*)malloc(sizeof(char)*700);
    strcpy(str,str700);
    //printf("%s\n",str);
	fclose(arq);


    //Get nome
    str = MiniString(str,NumberIndex(str,(char*)"name")+8,strlen(str));
    p.nome = (char*)malloc(strlen(str)+1);
    strcpy(p.nome,str);
    p.nome = MiniString(p.nome,0,NumberIndex(p.nome,(char*)"',"));


    //Get altura
    str = MiniString(str,NumberIndex(str,(char*)"height")+10,strlen(str));
    strcpy(auxstr,str);
    auxstr = MiniString(auxstr,0,NumberIndex(auxstr,(char*)"',"));
    auxstr=ChangeAll(auxstr,',','.');
    if(strcmp(auxstr,"unknown")==0)
			p.altura = 0;
		else
			p.altura = atoi(auxstr);

    //Get peso
    str = MiniString(str,NumberIndex(str,(char*)"mass")+8,strlen(str));
    auxstr = (char*)realloc(auxstr,strlen(str));
    strcpy(auxstr,str);
    auxstr = MiniString(auxstr,0,NumberIndex(auxstr,(char*)"',"));
    auxstr=ChangeAll(auxstr,',','.');
    if(strcmp(auxstr,"unknown")==0)
			p.peso = 0;
    else
        p.peso = atof(auxstr);

    //printf("Peguei altura e peso\n");
    //Get corDoCabelo
    str = MiniString(str,NumberIndex(str,(char*)"hair_color")+14,strlen(str));
    p.corDoCabelo = (char*)malloc(strlen(str)+1);
    strcpy(p.corDoCabelo,str);
    p.corDoCabelo = MiniString(p.corDoCabelo,0,NumberIndex(p.corDoCabelo,(char*)"',"));

    //Get corDaPele
    str = MiniString(str,NumberIndex(str,(char*)"skin_color")+14,strlen(str));
    p.corDaPele = (char*)malloc(strlen(str)+1);
    strcpy(p.corDaPele,str);
    p.corDaPele = MiniString(p.corDaPele,0,NumberIndex(p.corDaPele,(char*)"',"));

    //Get corDosOlhos
    str = MiniString(str,NumberIndex(str,(char*)"eye_color")+13,strlen(str));
    p.corDosOlhos = (char*)malloc(strlen(str)+1);
    strcpy(p.corDosOlhos,str);
    p.corDosOlhos = MiniString(p.corDosOlhos,0,NumberIndex(p.corDosOlhos,(char*)"',"));

    //Get anoNascimento
    str = MiniString(str,NumberIndex(str,(char*)"birth_year")+14,strlen(str));
    p.anoNascimento = (char*)malloc(strlen(str)+1);
    strcpy(p.anoNascimento,str);
    p.anoNascimento = MiniString(p.anoNascimento,0,NumberIndex(p.anoNascimento,(char*)"',"));

    //Get genero
    str = MiniString(str,NumberIndex(str,(char*)"gender")+10,strlen(str));
    p.genero = (char*)malloc(strlen(str)+1);
    strcpy(p.genero,str);
    p.genero = MiniString(p.genero,0,NumberIndex(p.genero,(char*)"',"));

    //Get homeworld
    str = MiniString(str,NumberIndex(str,(char*)"homeworld")+13,strlen(str));
    p.homeworld = (char*)malloc(strlen(str)+1);
    strcpy(p.homeworld,str);
    p.homeworld = MiniString(p.homeworld,0,NumberIndex(p.homeworld,(char*)"',"));


    Imprimir(p);


}

int main()
{
    char* entrada = (char*)malloc(sizeof(char)*120);
    Personagem p;

    do
    {
        scanf("%s",entrada);

        if(!isFim(entrada))
        {
            Ler(entrada,p);

        }

    }while(!isFim(entrada));


    return 0;
}
