package com.compression.compression_svd;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.transform.Scale;


/**
 * Class that applies Scalable behavior
 *
 */
public class Scaler implements Scalable
{
    private final Scene scene;
    private final Parent contentNode;

    public Scaler(Scene scene, Parent contentNode)
    {
        this.scene = scene;
        this.contentNode = contentNode;
    }

    /**
     * Scales all visible objects to maintain consistent proportions across
     * screens with varying resolutions, ensuring a uniform and high-quality
     * picture for all users.
     */
    @Override
    public void addResizeListener()
    {
        scene.widthProperty().addListener((observableValue, oldValue, newValue) -> scale());
        scene.heightProperty().addListener((observableValue, oldValue, newValue) -> scale());
    }

    /**
     * Scales the content node based on the current size of the scene.
     */
    private void scale()
    {
        Scale scale = new Scale(scene.getWidth()/605, scene.getHeight()/375);
        scale.setPivotX(0);
        scale.setPivotY(0);
        contentNode.getTransforms().setAll(scale);
    }
}
