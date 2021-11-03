import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class ExemploURL {
	public static void main(String[] args) throws Exception {
		String address = "http://maratona.crc.pucminas.br/series/Jessica_Jones.html";
		String line = "";
		String resp = "";
		
		try {
			URL url = new URL(address);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			line = br.readLine();
			while (line != null) {
				resp += line;
				line = br.readLine();
			}
			br.close();
		} catch (MalformedURLException excecao) {
			excecao.printStackTrace();
		} catch (IOException excecao) {
			excecao.printStackTrace();
		}
		
		System.out.println(resp);
	}
}
