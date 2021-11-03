import java.io.*;
import java.util.*;
import java.nio.charset.StandardCharsets;

class StarWars
{
	private String nome,corDoCabelo,corDaPele,corDosOlhos,anoNascimento,genero,homeworld;
	private int altura;
	private double peso;
	
	public StarWars ( ) 
	{

		nome = corDoCabelo = corDaPele = corDosOlhos = anoNascimento = genero = homeworld = "";
		altura = 0;
		peso = 0.0;

	}

	public StarWars (String diretorio) 
	{

		configPersonagem(diretorio);

	}

	public StarWars clone()
	{

		StarWars tmp = new StarWars();
		tmp.nome = this.nome;
		tmp.corDoCabelo = this.corDoCabelo;
		tmp.corDaPele = this.corDaPele;
		tmp.corDosOlhos = this.corDosOlhos;
		tmp.anoNascimento = this.anoNascimento;
		tmp.genero = this.genero;
		tmp.homeworld = this.homeworld;
		tmp.altura = this.altura;
		tmp.peso = this.peso;

		return tmp;	

	}

	public String getNome()
	{

		return nome;

	}

	public String getCorDoCabelo()
	{

		return corDoCabelo;

	}

	public String getCorDaPele()
	{

		return corDaPele;

	}

	public String getCorDosOlhos()
	{

		return corDosOlhos;

	}

	public String getAnoNascimento()
	{

		return anoNascimento;

	}

	public String getGenero()
	{

		return genero;

	}
	
	public String getHomeworld()
	{

		return homeworld;

	}

	public int getAltura()
	{	

		return altura;

	}

	public double getPeso()
	{	

		return peso;

	}

	public void setNome(String novoNome)
	{

		nome = novoNome;

	}

	public void setCorDoCabelo(String novaCorDoCabelo)
	{

		corDoCabelo = novaCorDoCabelo;

	}

	public void setCorDaPele(String novaCorDaPele)
	{

		corDaPele = novaCorDaPele;

	}
	
	public void setCorDosOlhos(String novaCorDosOlhos)
	{

		corDosOlhos = novaCorDosOlhos;

	}

	public void setAnoNascimento(String novoAnoNascimento)
	{

		anoNascimento = novoAnoNascimento;

	}

	public void setGenero(String novoGenero)
	{

		genero = novoGenero;

	}

	public void setHomeworld(String novoHomeworld)
	{

		homeworld = novoHomeworld;

	}

	public void setAltura(int novaAltura)
	{

		altura = novaAltura;

	}

	public void setPeso(double novoPeso)
	{

		peso = novoPeso;

	}
	
	public String imprimir()
	{

		
		String resp = " ## " + getNome() + " ## " + getAltura() + " ## ";

		if( getPeso() == (int)getPeso() )
			resp += (int)getPeso();
		else
			resp += getPeso();

		resp += " ## " + getCorDoCabelo() + " ## " +  getCorDaPele() + " ## " + getCorDosOlhos() + " ## " + getAnoNascimento() + " ## " + getGenero() + " ## " + getHomeworld() + " ## ";
		return resp;

	}

	public String pegarTexto(String diretorio)
	{
		String texto = "",linha = "";
		try {
			RandomAccessFile arq = new RandomAccessFile(diretorio,"r");
			linha = arq.readLine();

			while (linha != null) 
			{

				texto += linha;
				linha = arq.readLine();

			}

			arq.close();
		} 

		catch (IOException excecao) 
		{

			excecao.printStackTrace();

		}

		return texto;
	}

	public void configPersonagem(String diretorio)
	{

		int numInteiro;
		double numDouble;

		String texto = pegarTexto(diretorio),Split[];

		Split = texto.split("\'");

		setNome(Split[3]);
		
		if(Split[7].contains("unknown") == true)
		{

			setAltura(0);
		
		}		
		else
		{

			numInteiro = Integer.parseInt(Split[7]);
			setAltura(numInteiro);

		}
		if(Split[11].contains("unknown") == true)
		{

			setPeso(0);

		}
		else
		{
			
			numDouble = Double.parseDouble(Split[11]);
			setPeso(numDouble);

		}

		setCorDoCabelo(Split[15]);
		setCorDaPele(Split[19]);
		setCorDosOlhos(Split[23]);
		setAnoNascimento(Split[27]);
		setGenero(Split[31]);
		setHomeworld(Split[35]);

	}

} 



class Hash {

   StarWars tabela[];
   int m1, m2, m, reserva;

   public Hash (){
      this(21, 9);
   }

   public Hash (int m1, int m2){
      this.m1 = m1;
      this.m2 =  m2;
      this.m = m1 + m2;
      this.tabela = new StarWars [this.m];
      for(int i = 0; i < m; i++){
         tabela[i] = null;
      }
      reserva  = 0;
   }

   public int h(StarWars elemento){
      return elemento.getAltura() % this.m1;
   }

   public int h(String elemento){
	  String endereco;
	  if ( elemento.contains("Dorm") == true ) {
		  endereco = "/tmp/personagens/Dorm"+(char)233+".txt";
	  } else if ( elemento.contains("Amidala") == true ) {
		  endereco = "/tmp/personagens/Padm"+(char)233+"Amidala.txt";
	  } else if ( elemento.contains("Cord") == true ) {
		  endereco = "/tmp/personagens/Cord"+(char)233+".txt";
	  } else if ( elemento.contains("RicOli") == true ) {
		  endereco = "/tmp/personagens/RicOli"+(char)233+".txt";
	  } else {
	  	  endereco = "/tmp/personagens/" + elemento.replace(" ","") + ".txt";
	  }
	  StarWars tmp = new StarWars(endereco);
      return tmp.getAltura() % this.m1;
   }

   public void inserir (StarWars elemento) throws Exception {

      if(elemento != null){

         int pos = h(elemento);

         if(tabela[pos] == null){
            tabela[pos] = elemento.clone();

         } else if (reserva < m2){
            tabela[m1 + reserva] = elemento.clone();
            reserva++;
         }
		 else {

		 }
      } else {
		 throw new Exception ("Erro!");
	  }

   }


   public boolean pesquisar (String elemento){

      boolean resp = false;

      //int pos = h(elemento);


		     for(int i = 0; i < m; i++){
				if ( tabela[i] != null ) {
				    if(tabela[i].getNome().compareTo(elemento) == 0){
				       resp = true;
				       i = m;
				    }
				}
		     }

      return resp;
    }

	public void mostrar() {

		for ( int i = 0 ; i < this.m ; i++ ) {
			if ( tabela[i] != null ) 
				MyIO.println(tabela[i].imprimir());
		}

	}

}

public class Questao5 {

	public static void main(String[] args) throws Exception{

		Hash h = new Hash(21,9);
		StarWars tmp = new StarWars();
		boolean resp;

		String entrada = MyIO.readLine();

		while( entrada.contains("FIM") != true )
		{

			tmp = new StarWars(ISO88591toUTF8(entrada));
			h.inserir(tmp);

			entrada = MyIO.readLine();

		}

		entrada = MyIO.readLine();

		while( entrada.contains("FIM") != true )
		{			

			resp = h.pesquisar(ISO88591toUTF8(entrada));

			MyIO.print(entrada);

			if ( resp == true)
				MyIO.println(" SIM");
			else
				MyIO.println (" NÃO");	

			entrada = MyIO.readLine();	
		
		}

	}
	public static String ISO88591toUTF8(String strISO) throws Exception 
	{

		byte[] isoBytes = strISO.getBytes("ISO-8859-1");
		return new String(isoBytes, "UTF-8");

	}
}
