package View.NakedObject;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NakedObjDisplayer {
	
	private VBox dialogBox;
	private Stage dialog = new Stage();
	
	public void display (NakedObject obj)
	{
		dialog.initModality(Modality.APPLICATION_MODAL);
		List<StringProperty> textPropertyList = new ArrayList<>();
		Field[] fields = obj.getClass().getFields();
		this.dialogBox = new VBox(fields.length*10);
		dialogBox.setPadding(new Insets(40, 40, 40, 40));
		
		for (int i=0;i<fields.length;i++) 
			textPropertyList.add(createDisplayForField(fields[i].getName(), obj.getFieldValue(fields[i].getName())));

		Button saveButton = new Button();
		saveButton.setText("Save");
		saveButton.setOnAction(value-> {
			for (int i=0;i<fields.length;i++) {
				try {
					fields[i].set(obj,textPropertyList.get(i).getValue());
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			this.dialog.close();
		});
		this.dialogBox.getChildren().add(saveButton);
		showDialog(this.dialog, this.dialogBox);
	}
	
	public void display (NakedMsg msg) {
		
	}
	
	private StringProperty createDisplayForField (String fieldName, String fieldValue) {
		Text caption = new Text(fieldName);
		this.dialogBox.getChildren().add(caption);
		TextField textBox = new TextField(fieldValue);
		this.dialogBox.getChildren().add(textBox);
		return textBox.textProperty();
	}

    private void showDialog(Stage dialog, VBox dialogBox) {
        Scene dialogScene = new Scene(dialogBox);
        dialog.setScene(dialogScene);
        dialog.setAlwaysOnTop(true);
        dialog.setResizable(false);
        dialog.show();
    }
}
