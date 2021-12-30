package maykeee.music.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Utils {

	public static String findLyrics(String artist, String music) {

		artist = artist.replace(" ", "%20");
		music = music.replace(" ", "%20");

		String url = "http://localhost:8080/letra?art=" + artist + "&music=" + music;

		try {
			URL urlConnection = new URL(url);

			HttpURLConnection httpConn = (HttpURLConnection) urlConnection.openConnection();

			if (httpConn.getResponseCode() != 200) {
				return null;
			}

			BufferedReader ans = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));

			StringBuilder lyrics = new StringBuilder();

			String linha = "";

			while ((linha = ans.readLine()) != null) {
				lyrics.append(linha + "\n");
			}

			return lyrics.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
