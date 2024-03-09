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
import javafx.scene.layout.AnchorPane;
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
    public AnchorPane mainPane;
    @FXML
    private ScrollBar scrollBar;

    @FXML
    MenuItem btn_open;
    Stage secondStage = new Stage();
    Image image;
    @FXML
    ImageView imageView;
    Stage curStage;
    ImageView newImg = new ImageView();


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
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setWidth(810);
        primaryStage.setHeight(510);
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
//        if(mainPane.lookup("img") == null)
//        {
//            mainPane.getChildren().add(newImg);
//            newImg.setId("img");
//        }
        image = new Image(file.toURI().toString());
        newImg.setImage(image);
        newImg.setPreserveRatio(true);
        newImg.setFitHeight(200);
        newImg.setLayoutX((370-200.0*image.getWidth()/image.getHeight())/2);
        System.out.println(newImg.getImage().getWidth());
        newImg.setY(120);

//        imageView.setPreserveRatio(true);
//        imageView.setImage(image);
//        if(imageView.getImage().getWidth()+26 <= 333)
//        {
//            imageView.setLayoutX(333 - (imageView.getImage().getWidth()+4*26));
//        }
//        imageView.setSmooth(true);
//        imageView.setCache(true);

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
        mainPane.getChildren().add(newImg);
        scrollBar.setValue(scrollBar.getMax());
    }
}
