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
   int m;

   public Hash (){
      this(13);
   }

   public Hash (int m){
      this.m = m;
      this.tabela = new StarWars [this.m];
      for(int i = 0; i < m; i++){
         tabela[i] = null;
      }
   }

   public int h(StarWars elemento){
      return elemento.getAltura() % m;
   }

   public int reh(StarWars elemento){
      return (elemento.getAltura() + 1) % m;
   }

   public void inserir (StarWars elemento){

      if(elemento != null){

         int pos = h(elemento);

         if(tabela[pos] == null){
            tabela[pos] = elemento.clone();

         } else{

            pos = reh(elemento);
			MyIO.println(pos);
            if(tabela[pos] == null){
               tabela[pos] = elemento.clone();
            }
         }
      }

   }

   public boolean pesquisar (String elemento){

      boolean resp = false;

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
			MyIO.print(i + " ");
			if ( tabela[i] != null ) 
				MyIO.println(tabela[i].imprimir());
		}

	}

}

public class Questao6 {

	public static void main(String[] args) throws Exception{

		Hash h = new Hash(25);
		StarWars tmp = new StarWars();
		boolean resp;

		String entrada = MyIO.readLine();

		while( entrada.contains("FIM") != true )
		{

			MyIO.println(entrada);
	
			tmp = new StarWars(ISO88591toUTF8(entrada));
			h.inserir(tmp);

			entrada = MyIO.readLine();

		}

		h.mostrar();

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
