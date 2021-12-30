package maykeee.music.mp3;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;

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
	private void handleButtonAction(ActionEvent event) {
		System.out.println("RONALDO");
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int value = fc.showOpenDialog(null);

		if (value == JFileChooser.APPROVE_OPTION) {
			System.out.println(fc.getSelectedFile().toString());
		}
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
