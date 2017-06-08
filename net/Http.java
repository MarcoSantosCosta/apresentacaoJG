package net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author Marco
 */
public class Http {

    public void usingGet(String link) {

        //Cria um objeto da classe URL com o url passado por parametro;
        try {

            link = "http://" + link;
            URL url = new URL(link);

            //Abre uma conexao http usando o objeto url;
            //Retorna um objeto do tipo HttpURLConnection;
            HttpURLConnection http = (HttpURLConnection) url.openConnection();

            //Modifica o metodo de requisicao que sera usado;
            //Por padrao ja e get;
            http.setRequestMethod("GET");

            //Cria um arquivo para escrita
            FileWriter file = new FileWriter("page.html");
            BufferedWriter out = new BufferedWriter(file);

            //Cria um bufer de leitura para receber os dados da InputStream 
            //da conexao;
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(http.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                file.append(line);
            }
            System.out.println("Servidor diz: " + http.getResponseMessage());
            //Fecha buffer de leitura;
            in.close();
            //fecha buffer de escrita;
            out.close();

        } catch (MalformedURLException ex) {
            System.err.println("URL mal formulado. Motivo: " + ex.getMessage());
        } catch (IOException ex) {
            System.err.println("Erro de entra de saida de dados. Motivo: " + ex.getMessage());
        }
    }

    public void usingPost(String nome, String email) {
        try {
            //Cria um objeto da classe URL com o url passado por parametro;
            URL url = new URL("https://www.w3schools.com/php/welcome.php");

            //Abre uma conexao http usando o objeto url;
            //Retorna um objeto do tipo HttpURLConnection;
            HttpURLConnection http = (HttpURLConnection) url.openConnection();

            //Modifica o metodo de requisicao que sera usado;
            http.setRequestMethod("POST");

            //Cria uma string como os dados que serão enviados;
            String formData = "name=" + nome + "&email=" + email;

            //Habilita edição no corpo da mensagem;
            http.setDoOutput(true);

            //Cria uma stram de dados usando a stram da conexao;
            DataOutputStream writerData = new DataOutputStream(http.getOutputStream());

            //Escreve a as informacoes em Bytes;
            writerData.writeBytes(formData);
            writerData.flush();
            writerData.close();

            //Cria um arquivo para escrita
            FileWriter file = new FileWriter("post.html");
            BufferedWriter out = new BufferedWriter(file);

            //Cria um bufer de leitura para receber os dados da InputStream 
            //da conexao;
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(http.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                file.append(line);
            }
            System.out.println("Servidor diz: " + http.getResponseMessage());
            //Fecha buffer de leitura;
            in.close();
            //fecha buffer de escrita;
            out.close();
        } catch (MalformedURLException ex) {
            System.err.println("URL mal formulado. Motivo: " + ex.getMessage());
        } catch (IOException ex) {
            System.err.println("Erro de entra de saida de dados. Motivo: " + ex.getMessage());
        }
    }

    public void downloader(String link, String nome, String type) {
        try {
            //Cria um objeto da classe URL com o url passado por parametro;
            URL url = new URL(link);

            //Abre uma conexao http usando o objeto url;
            //Retorna um objeto do tipo HttpURLConnection;
            HttpURLConnection http = (HttpURLConnection) url.openConnection();

            //Cria um arquivo para escrita
            File file = new File(nome + "." + type);

            OutputStream out = new FileOutputStream(file);
            InputStream in = http.getInputStream();

            int n;
            int sizeFile = http.getContentLength();

            while ((n = in.read()) >= 0) {
                out.write(n);
            }
            System.out.println("Servidor diz: " + http.getResponseMessage());
            in.close();
            out.close();
        } catch (MalformedURLException ex) {
            System.err.println("URL mal formulado. Motivo: " + ex.getMessage());
        } catch (IOException ex) {
            System.err.println("Erro de entra de saida de dados. Motivo: " + ex.getMessage());
        }
    }

    public void headers(String link) {
        try {
            URL url = new URL(link);

            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("GET");
            System.out.println("Mensagem de resposta: " + http.getResponseMessage());
            System.out.println("Codigo de resposta: " + http.getResponseCode());
            System.out.println("Data da ultima modificação: " + http.getLastModified());
            System.out.println("Tamanho do corpo da mensagem: " + http.getContentLengthLong());
            System.out.println("Tipo do corpo da mensagem: " + http.getContentType());
            System.out.println("Tipo do corpo da mensagem: " + http.getHeaderFields());
        } catch (MalformedURLException ex) {
            System.err.println("URL mal formulado. Motivo: " + ex.getMessage());
        } catch (IOException ex) {
            System.err.println("Erro de entra de saida de dados. Motivo: " + ex.getMessage());
        }
    }

}
