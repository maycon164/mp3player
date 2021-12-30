package maykeee.music.mp3;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import maykeee.music.entities.Music;
import maykeee.music.entities.Player;
import maykeee.music.utils.Utils;

public class FXMLController implements Initializable {

	private ObservableList<Music> musics = FXCollections.observableArrayList();

	@FXML
	private Label label;

	@FXML
	private TableView<Music> tableMusic = new TableView<>();

	@FXML
	private TableColumn<Music, String> colArtist;

	@FXML
	private TableColumn<Music, String> colMusic;

	@FXML
	private TableColumn<Music, String> colFormat;

	@FXML
	private void playMusic(ActionEvent event) {

		if (tableMusic.getSelectionModel().getSelectedItem() != null) {

			Player.play(tableMusic.getSelectionModel().getSelectedItem().getPath());

		}

	}
	
	@FXML
	private void stopMusic(ActionEvent event) {
		Player.pause();
	}

	@FXML
	private void resumeMusic(ActionEvent event) {
		Player.resume();
	}
	
	@FXML
	private void showLyrics(ActionEvent event) {
		System.out.println("OLÁ");
		Music music = tableMusic.getSelectionModel().getSelectedItem();
		String lyrics = Utils.findLyrics(music.getArtist(), music.getMusic());
		System.out.println(lyrics);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		findMusics();
		initializeColumns();
	}

	private void findMusics() {
		String path = "/Users/maycon.huanca/Music";
		File directory = new File(path);

		if (directory.isDirectory()) {
			for (File file : directory.listFiles()) {

				if (file.getName().contains(".mp3")) {

					try {
						Mp3File mp3 = new Mp3File(file);
						ID3v2 tags = mp3.getId3v2Tag();

						if (mp3.hasId3v2Tag()) {
							Music music = new Music();
							music.setArtist(tags.getArtist());
							music.setMusic(tags.getTitle());
							music.setPath(file.getAbsolutePath());
							music.setFormat("mp3");
							musics.add(music);
						}

					} catch (UnsupportedTagException e) {
						e.printStackTrace();
					} catch (InvalidDataException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

				}

			}
		}

	}

	private void initializeColumns() {
		colArtist.setCellValueFactory(new PropertyValueFactory<>("artist"));
		colMusic.setCellValueFactory(new PropertyValueFactory<>("music"));
		colFormat.setCellValueFactory(new PropertyValueFactory<>("format"));

		tableMusic.setItems(musics);
	}
}
