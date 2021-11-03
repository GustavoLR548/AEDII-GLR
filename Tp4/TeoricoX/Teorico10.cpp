#include<iostream>
#include<string>
#include<cctype>
#include<cstring>
#include<climits>
#include<cstdlib>

using namespace std;

	class Contato {
	private:
		string nome;
		int telefone;
		string email;
		int CPF;
	public:
		Contato() {
			setNome("Unknown");
			setTelefone(0);
			setEmail("NULL");
			setCPF(0);
		}
		
		Contato(string nome,int telefone,string email,int CPF) {
			setNome(nome);
			setTelefone(telefone);
			setEmail(email);
			setCPF(CPF);
		}
		
		string getNome() {
			return this->nome;
		}
		int getTelefone() {
			return this->telefone;
		}
		string getEmail() {
			return this->email;
		}
		int getCPF() {
			return this->CPF;
		}
		void setNome(string nome) {
			this->nome = nome;
		}
		void setTelefone(int telefone) {
			this->telefone = telefone;
		}
		void setEmail(string email) {
			this->email = email;
		}
		void setCPF(int CPF) {
			this->CPF = CPF;
		}
		string imprimir() {
			string resp = "------------------\nNome: ";
			resp += this->nome;
			resp += "\nTelefone: ";
			resp += to_string(this->telefone);
			resp += "\nEmail: ";
			resp += this->email;
			resp += "\nCPF: ";
			resp += to_string(this->CPF);
			resp += "\n------------------\n";
			return resp;
		}
};

	class Celula {
	public:
		Celula* prox;
		Contato* elemento;

		Celula() {
			this->elemento = NULL;
			this->prox = NULL;	
		}
		Celula(Contato* elemento) {
			this->prox = NULL;
			this->elemento = elemento;
		}
};


	class Lista {
	public:
		Celula* primeiro;
		Celula* ultimo;

	Lista() {
		this->primeiro = new Celula();
		ultimo = primeiro;
	}

	void inserirLista(Contato* elemento) {
		Celula* tmp = new Celula(elemento);
		tmp->prox = this->primeiro->prox;
		this->primeiro->prox = tmp;
		if ( this->primeiro == this->ultimo ) {
			this->ultimo = tmp;
		}
		tmp = NULL;
	}

	Contato* remover(string chave) {
		Contato* resp;
		if ( pesquisarString(chave) == false) {
			cerr << "Impossível remover elemento\n";
		}
		else {
			Celula* j;
			bool parar = false;
			int contador = 0;
			for (j = this->primeiro->prox; j != NULL && parar != true; j = j->prox,contador++) { 
				if ( j->elemento->getNome().compare(chave) == 0 ) {
					parar = true;
				} 
			}
			j = this->primeiro;

			for (int i = 0; i < contador-1; i++, j = j->prox);
			
			Celula* tmp = j->prox;
			resp = tmp->elemento;
			j->prox = tmp->prox;
			tmp->prox = NULL;
			j = tmp = NULL;
			cout << "Contato \"" << chave << "\" removido com sucesso"; 
		}
		return resp;
	} 
	
	bool pesquisarString(string chave) {
		bool resp = false,stop = false;
		for ( Celula* i = this->primeiro->prox; i != NULL && stop != true; i = i->prox) {
			if ( i->elemento->getNome().compare(chave) == 0 ) {
				resp = true;
				stop = true;
			}
		}
		return resp;
	}

	bool pesquisarCPF(int chave) {
		bool resp = false,stop = false;
		for ( Celula* i = this->primeiro->prox; i != NULL && stop != true; i = i->prox) {
			if ( i->elemento->getCPF() == chave ) {
				resp = true;
				stop = true;
			}
		}
		return resp;
	}

	void mostrarPre() {
		Celula* i;
		for(i = this->primeiro; i != NULL ; i = i->prox) {
			if ( i->elemento != NULL ) {
				cout << i->elemento->imprimir() << "\n";
			}
		}
	}

};
	
	class No {
	public:
		char letra;
		No* esq;
		No* dir;
		Lista* elemento;

		No() {
			this->letra = '0';
			this->elemento = NULL;
			this->esq = NULL;
			this->dir = NULL;	
		}
		No(char elemento) {
			this->letra = elemento;
			this->elemento = new Lista();
			this->esq = NULL;
			this->dir = NULL;
		}
};

	class Agenda {
	private:
		No* raiz;
	public: 
		
		Agenda() {
			raiz = NULL;
			inserirChar('A');
			inserirChar('B');
			inserirChar('C');
		}

		Agenda(char seed[]) {
			raiz = NULL;
			for (int i = 0 ; i < strlen(seed); i++ ) {
				if ((int)toupper(seed[i]) >= 65 && (int)toupper(seed[i]) <= 90) {
					inserirChar(toupper(seed[i]));
				}
			}
		}

		No* getRaiz() {
			return this->raiz;
		}

		void setRaiz(No* raiz) {
			this->raiz = raiz;
		}
		
		void inserir(Contato* elemento) {
			this->raiz = inserirRec(elemento,this->raiz);
		}

		No* inserirRec(Contato* elemento, No* i) {
			if (i == NULL) {
         		cerr << "Não foi possível inserir\n";
			} else if (elemento->getNome().at(0) == i->letra ) {
				i->elemento->inserirLista(elemento);
			} else if (elemento->getNome().at(0) < i->letra ) {
         		i->esq = inserirRec(elemento, i->esq);
			} else {
         		i->dir = inserirRec(elemento, i->dir);
      		}
			return i;
		}

		void remover(string elemento) {
			this->raiz = remover(elemento, this->raiz);
		}

		No* remover(string elemento, No* i) {

		  if (i == NULL) {
		     cerr << "Erro ao remover!";
		  } else if (i->letra == elemento.at(0) ) {
		     i->elemento->remover(elemento);
		  } else if (i->letra > elemento.at(0)  ) {
		     i->esq = remover(elemento, i->esq);
		  } else {
		     i->dir = remover(elemento, i->dir);
		  }
		  return i;
		}

		void inserirChar(char elemento) {
			this->raiz = inserirCharRec(elemento,this->raiz);
		}

		No* inserirCharRec(char elemento, No* i) {
			if (i == NULL) {
         		i = new No(elemento);
			} else if (elemento < i->letra ) {
         		i->esq = inserirCharRec(elemento, i->esq);
			} else if (elemento > i->letra ) {
         		i->dir = inserirCharRec(elemento, i->dir);
			} else {
         		cerr << "Não foi possível inserir\n";
      		}
			return i;
		}

		void mostrarPre() {
			mostrarPreRec(this->raiz);
		}

		void mostrarPreRec(No* i) {
			if ( i != NULL ) {
				cout << i->letra << "\n";
				i->elemento->mostrarPre();
				mostrarPreRec(i->esq);
				mostrarPreRec(i->dir);
			}
		}

		bool pesquisarString() {
			string nome;
	
			cout << "\nDigite o nome da pessoa que você gostaria de buscar: ";

			cin.ignore(INT_MAX, '\n');
			getline(cin,nome);
			
			return pesquisarStringRec(nome,this->raiz);
		}

		bool pesquisarStringRec(string elemento, No* i) {
			
			bool resp = false;
			if (i == NULL) {
         		resp = false;
			} else if (elemento.at(0) == i->letra ) {
         		resp = i->elemento->pesquisarString(elemento);
			} else if (elemento.at(0) < i->letra ) {
         		resp = pesquisarStringRec(elemento, i->esq);
			} else {
         		resp = pesquisarStringRec(elemento, i->dir);
      		}
			return resp;
		}

		bool pesquisarCPF() {
			int cpf;
	
			cout << "\nDigite o CPF da pessoa que você gostaria de buscar: ";

			cin >> cpf;
			
			return pesquisarCPFRec(cpf,this->raiz);
		}

		bool pesquisarCPFRec(int cpf, No* i) {
			bool resp = false;
			if (i != NULL) {
				resp = i->elemento->pesquisarCPF(cpf);
				if ( resp == false ) {
					resp = pesquisarCPFRec(cpf,i->esq); 
				} if ( resp == false ) {
					resp = pesquisarCPFRec(cpf,i->dir); 
				}
			}
			return resp;
		}

};

char menu();
Contato* configurarContato();

int main() {

	int n;

	cout << "\nDigite o número de letras que a lista terá:\n";

	cin >> n;
	while ( n < 0 || n > 26 ) {
		cin.ignore();
		if ( n < 0 || n > 26) {
			cout << "\nO número digitado é invalido! Tente novamente: ";
		}
		cin >> n;
	}

	char seed[n];

	cout << "\nAgora digite todas as letras:\n";
	for ( int i = 0 ; i < n+1 ; i++ ) {
		seed[i] = (char)cin.get();
		fflush(stdin);
	}
	cout << "\nSeed de letras configurada com sucesso!";
	
	cout << "\nConfigurando Agenda...";

	Agenda* agenda = new Agenda(seed);

	cout << "\nAgenda configurada!... indo para o menu principal...\n\nSeja bem vindo a Agenda!";
	char opcao = '0';

	while ( opcao != 'S' ) {
		Contato* tmp;
		string chave;	
		bool resp;	

		opcao = toupper(menu());
		if ( opcao == 'I' ) {
			tmp = configurarContato();
			agenda->inserir(tmp);
			cout << "\nContato inserido com sucesso!\n";
		}
		else if ( opcao == 'R' ) {
			cout << "\nInsira o contato a ser removido\nObs: para remover um contato, insira o nome do tal contato\n-> ";
			cin.ignore(INT_MAX, '\n');
			getline(cin,chave);
			cout << "\n";
			agenda->remover(chave);
			cout << "\nVoltando ao menu principal...\n";
		}
		else if ( opcao == 'M' ) {
			cout << "\nMostrando agenda...\n";
			agenda->mostrarPre();
			cout << "\nVoltando ao menu principal...\n";
		}
		else if ( opcao == 'P' ){
			cout << "\nModo pesquisa ativado\nEscolha seu método de pesquisa:\n1 - pelo nome\n2 - pelo CPf\n->";
			while ( opcao != '1' && opcao != '2' ) {
				cin >> opcao;
				if ( opcao != '1' && opcao != '2' ) {
					cout << "\nOpção inválida! Tente novamente:\n->";
				}
			}
			if ( opcao == '1' ) {
				resp = agenda->pesquisarString();
			}
			else {
				resp = agenda->pesquisarCPF();
			}

			if ( resp == true ) {
				cout << "\nO contato pesquisado está presente na agenda!\n";
			}
			else {
				cout << "\nO contato pesquisado não está presente na agenda!\n";
			}
			cout << "\nVoltando ao menu principal...\n";
		}
		else {
			cout << "\nObrigado por usar o programa!\nSaindo...\n";
		}
	}
	return 0;
}

char menu() {

	char resp = '0';

	cout << "\nPor favor, digite:\ni/I - para inserir um novo contato na agenda.\nm/M - para mostrar os contatos da agenda\nr/R - para remover um contato da agenda\ns/S - para sair do programa\np/P - para pesquisar na agenda\nDigite sua opção: ";

	while ( resp != 'I' && resp != 'R' && resp != 'M' && resp != 'S' && resp != 'P' ) {

		cin >> resp;
		resp = toupper(resp);
		if ( resp != 'I' && resp != 'R' && resp != 'M' && resp != 'S' && resp != 'P') {
			cout << "\nResposta inválida! Tente novamente...\nDigite sua opção: ";
		}
	}
	return resp;
}

Contato* configurarContato() {
	cout << "\nDigite o nome,telefone,email e CPF, respectivamente\n";
	string nome,email;
	int telefone,cpf;

	cin.ignore(INT_MAX, '\n');
	getline(cin,nome);
	cin >> telefone;
	cin >> email;
	cin >> cpf;

	Contato* resp = new Contato(nome,telefone,email,cpf);
	return resp;
}

