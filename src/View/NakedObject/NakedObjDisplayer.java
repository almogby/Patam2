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
		
		 for (String fieldName : obj.getFieldNames()) {
	            String fieldValue = obj.getFieldValue(fieldName);
	            textPropertyList.add(createDisplayForField(fieldName, fieldValue));
	        }
		
		Button saveButton = new Button();
		saveButton.setText("Save");
		saveButton.setOnAction(value-> {
			for (int i=0;i<obj.getFieldNames().size();i++) {
				obj.setFieldNewValue(obj.getFieldNames().get(i), textPropertyList.get(i).getValue());
			}
			this.dialog.close();
		});
		this.dialogBox.getChildren().add(saveButton);
		showDialog(this.dialog, this.dialogBox);
	}
	
	public void display (NakedMsg msg) {
		dialog.initModality(Modality.APPLICATION_MODAL);
		this.dialogBox = new VBox(msg.getFieldNames().size()*10);
		dialogBox.setPadding(new Insets(40, 40, 40, 40));
		
		for (String message : msg.getFieldNames()) {
			Text caption = new Text(message);
			this.dialogBox.getChildren().add(caption);
		}
		
		Button okButton = new Button();
		okButton.setText("OK");
		okButton.setOnAction(value -> dialog.close());

		this.dialogBox.getChildren().add(okButton);
		
		showDialog(this.dialog, this.dialogBox);
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
