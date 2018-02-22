package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;

public class Controller {
    @FXML
    private RadioButton selectMove, ellipse, rectangle, line;

    @FXML
    private ColorPicker pickColor;

    @FXML
    private Button delete, clone;

    @FXML
    private Canvas canvas;

    //0 -> Select/Move
    //1 -> Dessine des ellipses
    //2 -> Dessine des rectangles
    //3 -> Dessine des lignes
    int figure = 0;

    //-1 -> ni delete ni clone ne sont sélectionnés
    //0 -> delete est sélectionné
    //1 -> clone est sélectionné
    int selected = -1;

    public GraphicsContext drawing;

    //Couleur sélectionnée
    Color col;

    private ToggleGroup choices = new ToggleGroup();

    public Controller() {
        super();
    }

    @FXML
    public void initialize() {
        //Ajoute les radio button à un toggle group pour n'en avoir qu'un de sélectionné à chaque fois
        selectMove.setToggleGroup(choices);
        ellipse.setToggleGroup(choices);
        rectangle.setToggleGroup(choices);
        line.setToggleGroup(choices);

        //Sélectionne par défaut Select/Move
        selectMove.setSelected(true);

        //On initialise la variable pour la couleur
        col = pickColor.getValue();

        drawing = canvas.getGraphicsContext2D();
        drawing.setLineWidth(5);

        //Change la valeur de figure quand un radio button est sélectionné
        selectMove.setOnMouseClicked(event -> {
            figure = 0;
            delete.setDisable(false);
            clone.setDisable(false);
            selected = -1;
        });
        ellipse.setOnMouseClicked(event -> {
            figure = 1;
            delete.setDisable(true);
            clone.setDisable(true);
        });
        rectangle.setOnMouseClicked(event -> {
            figure = 2;
            delete.setDisable(true);
            clone.setDisable(true);
        });
        line.setOnMouseClicked(event -> {
            figure = 3;
            delete.setDisable(true);
            clone.setDisable(true);
        });

        delete.setOnMouseClicked(event -> {
            selected = 0;
        });

        clone.setOnMouseClicked(event -> {
            selected = 1;
        });

        //Change la couleur sélectionnée
        pickColor.setOnAction(event -> {
            col = pickColor.getValue();
        });

        //Dessine la figure sélectionnée, avec la couleur sélectionnée
        canvas.setOnMousePressed(event -> {
            double x = event.getX();
            double y = event.getY();
            drawing.setFill(col);
            if (figure == 1) { drawing.fillOval(x,y,100,25); }
            else if (figure == 2) { drawing.fillRect(x, y, 100, 50); }
            else if (figure == 3) { drawing.fillRect(x,y,100,5); }
        });
    }
}
