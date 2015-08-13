package mihir.connect;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.MalformedURLException;

import mihir.connect.URLConstants;

import java.lang.Exception;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

import lombok.val;
import lombok.extern.java.Log;
import lombok.extern.log4j.*;
/**
 * 
 * @author Mihir Kakrambe
 *
 */
@Log4j
public class HttpURLConnect {
	
	public static void main(String[] args){
		
		
		try{
			
			//extracting the basic url
			StringBuilder urlBuilder = new StringBuilder(URLConstants.BASIC_URL);
			//building the url with query
			String testString = "what is your name?";
//			urlBuilder.append(testString);
			String encodedQuery = URLEncoder.encode(testString,"UTF-8");
//			String encodedURL = URLEncoder.encode(urlBuilder.toString()+encodedQuery,"UTF-8");
			
			//opening a new https connection
			URL url = new URL(urlBuilder.toString()+encodedQuery);
			
			System.out.println(url.toString());
			HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Chrome");
			con.setDoInput(true);
			con.setDoOutput(true);
			
/*			OutputStream outputStream = con.getOutputStream();
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
			bw.write(url);
			bw.flush();
			bw.close();*/
			
			con.connect();
			
			InputStream inputStream = con.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
//			int v;
//			while((v=br.read())!=-1)
			String line;
			String filename = "/home/mihir/testFile";
			File file = new File(filename);
			BufferedWriter fileBW = new BufferedWriter(new FileWriter(file));
			while((line=br.readLine())!=null)
			{
				System.out.println(line);
				fileBW.write(line);
				fileBW.newLine();
			}
			
			fileBW.close();
			br.close();
			
			System.out.println("Done");
		
		}catch(MalformedURLException e){
//			Log.error("MalformedURLException spotted"+ e.getMessage());
			e.printStackTrace();
			
		}catch (UnsupportedEncodingException e){
//			log.error("UnsupportedEncodingException "+ e.getMessage());
			e.printStackTrace();
		} catch(IOException ioe){
			ioe.printStackTrace();
		}
		
	}
	

}