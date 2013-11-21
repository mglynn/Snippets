package snippets;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import snippets.model.Snippet;

public class NewSnippetViewController {
    @FXML protected static Button saveButton;
    @FXML private Button cancelButton;
    @FXML protected static TextField nameTextField;
    @FXML protected static TextArea codeTextArea;

    @FXML private void handleSaveButtonAction(ActionEvent event) {
        if (saveButton.getText().equalsIgnoreCase("Update")) {
            Snippet temp = (Snippet) SnippetsViewController.snippetListView.getSelectionModel().getSelectedItem();
            SQLiteJDBC.update(temp.getId(), nameTextField.getText(), codeTextArea.getText());
            SnippetsViewController.codeTextArea.setText(codeTextArea.getText());
        } else {
            SQLiteJDBC.insert(nameTextField.getText(), codeTextArea.getText());
        }
        SnippetsViewController.populateListView();
        
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }

    @FXML private void handleCancelButtonAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    
    public static void initData(Snippet snippet) {
        nameTextField.setText(snippet.getName());
        codeTextArea.setText(snippet.getCode());
        saveButton.setText("Update");
    }
}
