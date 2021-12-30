package maykeee.music.entities;

import java.nio.file.Paths;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Player {

	private static MediaPlayer media;

	public static void play(String path) {
		if(media != null)
				media.stop();
		
		media = new MediaPlayer(new Media(Paths.get(path).toUri().toString()));
		media.play();
	}
	
	public static void pause() {
		if(media != null) {
			media.pause();
		}
	}
	
	public static void resume() {
		if(media != null) {
			media.play();
		}
	}

}
