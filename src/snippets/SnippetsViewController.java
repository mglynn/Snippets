package snippets;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import snippets.model.Snippet;

public class SnippetsViewController implements Initializable {
    @FXML private static Button newButton;
    @FXML private static Button editButton;
    @FXML private static Button deleteButton;
    @FXML protected static ListView snippetListView;
    @FXML protected static TextArea codeTextArea;
    
    protected static ObservableList<Snippet> snippetData = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SQLiteJDBC.createDB();
        populateListView();
        
        snippetListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            
            @Override
            public void handle(MouseEvent event) {
                handleMouseClick(event);
            }
        });
    }
    
    protected static void populateListView() {
        snippetData = SQLiteJDBC.getSnippetData();
        snippetListView.setItems(snippetData);
    }

    @FXML private void handleNewButtonAction(ActionEvent event) throws Exception {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Snippets.class.getResource("view/NewSnippet.fxml"));
        
        stage.setTitle("New Snippet");
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    @FXML private void handleEditButtonAction(ActionEvent event) throws IOException {  
        int selectedIndex = snippetListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            Snippet temp = (Snippet) snippetListView.getSelectionModel().getSelectedItem();
            
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("view/NewSnippet.fxml"));

            stage.setTitle("Edit Snippet");
            stage.setScene(new Scene(root));
            
            stage.show();
            NewSnippetViewController.initData(temp);
        }   
    }
    
    @FXML private void handleDeleteButtonAction(ActionEvent event) throws IOException {
        int selectedIndex = snippetListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            Snippet temp = (Snippet) snippetListView.getSelectionModel().getSelectedItem();
                        
            int newSelectedIndex = (selectedIndex == snippetListView.getItems().size() - 1) ? selectedIndex - 1: selectedIndex;
            
            SQLiteJDBC.delete(temp.getId());
            snippetData.remove(selectedIndex);
            codeTextArea.clear();
            
            snippetListView.getSelectionModel().select(newSelectedIndex);
        }
    }
    
    @FXML public void handleMouseClick(MouseEvent event) {
        showSnippetDetails((Snippet) snippetListView.getSelectionModel().getSelectedItem());
    }

    private void showSnippetDetails(Snippet snippet) {
        if (snippet != null) {
            codeTextArea.setText(snippet.getCode());
        } else {
            codeTextArea.setText("");
        }
    }
}