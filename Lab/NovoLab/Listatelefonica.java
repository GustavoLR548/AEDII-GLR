class CelulaDupla 
{
	public String elemento;
	public CelulaDupla ant;
	public CelulaDupla prox;

	public CelulaDupla() {
		this("");
	}

	public CelulaDupla(String elemento) 
    {
		this.elemento = elemento;
		this.ant = this.prox = null;
	}
}

class ListaDupla 
{
	private static CelulaDupla primeiro;
	private static CelulaDupla ultimo;

	public ListaDupla() 
    {
		primeiro = new CelulaDupla();
		ultimo = primeiro;
	}

	public void inserir(String x) 
    {
        ultimo.elemento = x;

        ultimo.prox = new CelulaDupla();
        ultimo.prox.ant = ultimo;
        ultimo = ultimo.prox;
        
	}

	public static int tamanho() 
    {
		int tamanho = 0; 
		for(CelulaDupla i = primeiro; i != ultimo; i = i.prox, tamanho++);
		return tamanho;
	}

    public static int economizar()
    {
        quicksort();

        int economia = 0;
        int contador; 
        String padrao;

        int n = tamanho();

        for(int i = 1; i <= primeiro.elemento.length() - 1; i++)
        {
            contador = 0;
            padrao = primeiro.elemento.substring(0,i);

            int c = 1;
            for(CelulaDupla aux = primeiro.prox; c < n; aux = aux.prox)
            {
                boolean igual = false;
                if(padrao.contains(aux.elemento.substring(0,i)))igual = true;

                if(padrao.contains(aux.elemento.substring(0,i)))contador++;
                
                else
                {
                    if(contador * i > economia)
                    {
                        economia = (contador * i);
                    }
                    contador = 0;
                    padrao = aux.elemento.substring(0,i);
                }

                if(c == n - 1)
                {
                    if(contador * i > economia)
                    {
                        economia = (contador * i);
                    }
                    contador = 0;
                    padrao = aux.elemento.substring(0,i);
                }
                c++;
            }
        }

        return economia;
    }

    public static CelulaDupla celulaPos(int x)
    {
        CelulaDupla aux;
        int i = 0;
        for(aux = primeiro; i < x; aux = aux.prox)i++;

        return aux;
    }

    public static void swap(int a, int b)
    {
        String tmp = celulaPos(a).elemento;
        celulaPos(a).elemento = celulaPos(b).elemento;
        celulaPos(b).elemento = tmp;
    }

    public static void quicksort() 
    {
        quicksort(0, tamanho() - 1);
    }

    private static void quicksort(int esq, int dir) 
    {
        int i = esq, j = dir;

        String pivo = celulaPos((int)dir+esq/2).elemento;


        while (i <= j) 
        {
            while (celulaPos(i).elemento.compareTo(pivo) < 0) i++;
            while (celulaPos(j).elemento.compareTo(pivo) > 0) j--;
            if (i <= j) 
            {
                swap(i, j);
                i++;
                j--;
            }
        }
        if (esq < j)quicksort(esq, j);
        if (i < dir)quicksort(i, dir);
    }
}


public class Listatelefonica
{
    public static boolean isFim(String s)
    {
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M'); //descobrir se � o FIM
    } 

    public static void main(String []agrs)throws Exception
    {
        ListaDupla lista = new ListaDupla();

        String[] saidas = new String[2];

        int contador = 0;
        
        int tam = Integer.parseInt(MyIO.readLine());
        for(; contador < tam; contador++)
        {
			System.out.println(contador);
            lista.inserir(MyIO.readLine());           //ler as linhas
        }
        
        saidas[0] = "";
        saidas[0] += lista.economizar();

        ListaDupla lista2 = new ListaDupla();

        contador = 0;
        tam = Integer.parseInt(MyIO.readLine());

        for(; contador < tam; contador++)
        {
            lista2.inserir(MyIO.readLine());           //ler as linhas
        }
        
        saidas[1] = "";
        saidas[1] += lista2.economizar();

        System.out.println(saidas[0]);
        System.out.println(saidas[1]);
    }
}
