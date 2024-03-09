package com.compression.compression_svd;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CompressionController extends Application implements Initializable
{
    @FXML
    private ScrollBar scrollBar;

    @FXML
    MenuItem btn_open;
    Stage secondStage = new Stage();
    Image image;
    @FXML
    ImageView imageView;
    Stage curStage;


    @Override
    public void start(Stage primaryStage) throws Exception
    {

        curStage = primaryStage;
        /// Loads first View -> Intro
        Parent mainPage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("svd.fxml")));

        Scene scene = new Scene(mainPage, Screen.getPrimary().getBounds().getMaxX(), Screen.getPrimary().getBounds().getMaxY());
        (new Scaler(scene, mainPage)).addResizeListener();

        /// Configures initial settings of the game for the primary stage.
        primaryStage.setTitle("SVD Compression");
        primaryStage.setScene(scene);
        primaryStage.setWidth(610);
        primaryStage.setHeight(410);
        primaryStage.setMinWidth(610);
        primaryStage.setMinHeight(410);
        primaryStage.setFullScreenExitHint("");

        primaryStage.show();


        /////
    }

    private void setExtFilters(FileChooser chooser)
    {
        chooser.getExtensionFilters().addAll
                (
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }

    private void openNewImageWindow(File file)
    {

        image = new Image(file.toURI().toString());

//        imageView.setPreserveRatio(true);
        imageView.setImage(image);
        imageView.setSmooth(true);
        imageView.setCache(true);

    }

    public static void main(String[] args)
    {
        launch();
    }

    public void open(ActionEvent actionEvent)
    {
        final FileChooser fileChooser = new FileChooser();
        setExtFilters(fileChooser);
        File file = fileChooser.showOpenDialog(curStage);
        if (file != null)
        {
            openNewImageWindow(file);
        }
    }

    public void save(ActionEvent actionEvent)
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image");

        File file = fileChooser.showSaveDialog(secondStage);
        if (file != null) {
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(imageView.getImage(),
                        null), "png", file);
            } catch (IOException ex) {
                Logger.getLogger(
                        CompressionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     *                  the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scrollBar.setValue(scrollBar.getMax());
    }
}
