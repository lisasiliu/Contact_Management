import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.geometry.Insets;
import javafx.scene.text.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

class Contact extends HBox {

    private Label index;
    private ImageView profilePic;
    private Button picButton;

    private HBox contactName;
    private HBox phoneNumber;
    private HBox emailAddress;
    private VBox contactInfo;

    private TextField contactNameText;
    private TextField phoneNumberText;
    private TextField emailAddressText;

    Contact() {
        this.setPrefSize(500, 50); // sets size of task
        this.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0; -fx-font-weight: bold;"); // sets background color of task

        contactName = new HBox();
        phoneNumber = new HBox();
        emailAddress = new HBox();
        contactInfo = new VBox();

        index = new Label();
        index.setText(""); // create index label
        index.setPrefSize(40, 20); // set size of Index label
        index.setTextAlignment(TextAlignment.CENTER); // Set alignment of index label
        index.setPadding(new Insets(10, 5, 10, 0)); // adds some padding to the task
        this.getChildren().add(index); // add index label to task

        //contact name
        contactNameText = new TextField(); // create task name text field
        contactNameText.setPrefSize(380, 20); // set size of text field
        contactNameText.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); // set background color of texfield
        index.setTextAlignment(TextAlignment.LEFT); // set alignment of text field
        contactNameText.setPadding(new Insets(10, 0, 10, 10)); // adds some padding to the text field
        contactName.getChildren().add(contactNameText); // add textlabel to task

        //contact email
        emailAddressText = new TextField();
        emailAddressText.setPrefSize(380, 20); 
        emailAddressText.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); 
        index.setTextAlignment(TextAlignment.RIGHT); 
        emailAddressText.setPadding(new Insets(10, 0, 10, 10)); 
        emailAddress.getChildren().add(emailAddressText); 

        //phone number
        phoneNumberText = new TextField();
        phoneNumberText.setPrefSize(380, 20); 
        phoneNumberText.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); 
        index.setTextAlignment(TextAlignment.RIGHT); 
        phoneNumberText.setPadding(new Insets(10, 0, 10, 10)); 
        phoneNumber.getChildren().add(phoneNumberText);
        
        contactInfo.getChildren().add(contactName);
        contactInfo.getChildren().add(emailAddress);
        contactInfo.getChildren().add(phoneNumber);
        this.getChildren().add(contactInfo);

    }

    public void setTaskIndex(int num) {
        this.index.setText(num + ""); // num to String
        this.contactNameText.setPromptText("Name");
        this.emailAddressText.setPromptText("Email address");
        this.phoneNumberText.setPromptText("Phone number");
    }

    public TextField getContactName() {
        return this.contactNameText;
    }

    public Button getPicButton() {
        return this.picButton;
    }

    // public void toggleDone() {
    //     if (!markedDone) {
    //         markedDone = true;
    //         this.setStyle("-fx-border-color: #000000; -fx-border-width: 0; -fx-font-weight: bold;"); // remove border of task
    //         for (int i = 0; i < this.getChildren().size(); i++) {
    //             this.getChildren().get(i).setStyle("-fx-background-color: #BCE29E; -fx-border-width: 0;"); // change color of task to green
    //         }
    //     }
    //     else {
    //         markedDone = false;
    //         for (int i = 0; i < this.getChildren().size(); i++) {
    //             this.getChildren().get(i).setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); // change color of task to green
    //         }
    //     }
    // }
}

class ContactList extends VBox {

    ContactList() {
        this.setSpacing(5); // sets spacing between tasks
        this.setPrefSize(500, 560);
        this.setStyle("-fx-background-color: #F0F8FF;");
    }

    public void updateTaskIndices() {
        int index = 1;
        for (int i = 0; i < this.getChildren().size(); i++) {
            if (this.getChildren().get(i) instanceof Contact) {
                ((Contact) this.getChildren().get(i)).setTaskIndex(index);
                index++;
            }
        }
    }
}


class Footer extends HBox {

    private Button addButton;
    private Button clearButton;
    private Button loadButton;
    private Button saveButton;
    private Button sortButton;

    Footer() {
        this.setPrefSize(500, 60);
        this.setStyle("-fx-background-color: #F0F8FF;");
        this.setSpacing(15);

        String defaultButtonStyle = "-fx-font-style: italic; -fx-background-color: #FFFFFF;  -fx-font-weight: bold; -fx-font: 11 arial;";

        addButton = new Button("Add Contact"); 
        addButton.setStyle(defaultButtonStyle); 
        clearButton = new Button("Clear finished"); 
        clearButton.setStyle(defaultButtonStyle);

        loadButton = new Button("Load");
        loadButton.setStyle(defaultButtonStyle);
        saveButton = new Button("Save");
        saveButton.setStyle(defaultButtonStyle);
        sortButton = new Button("Sort");
        sortButton.setStyle(defaultButtonStyle);

        this.getChildren().addAll(addButton, clearButton, loadButton, saveButton, sortButton);
        this.setAlignment(Pos.CENTER); 

    }

    public Button getAddButton() {
        return addButton;
    }

    public Button getClearButton() {
        return clearButton;
    }

    public Button getLoadButton() {
        return loadButton;
    }
    public Button getSaveButton() {
        return saveButton;
    }
    public Button getSortButton() {
        return sortButton;
    }
}

class Header extends HBox {
    Header() {
        this.setPrefSize(500, 60);
        this.setStyle("-fx-background-color: #F0F8FF;");

        Text titleText = new Text("Contacts");
        titleText.setStyle("-fx-font-weight: bold; -fx-font-size: 20;");
        this.getChildren().add(titleText);
        this.setAlignment(Pos.CENTER); 
    }
}

class AppFrame extends BorderPane{

    private Header header;
    private Footer footer;
    private ContactList contactList;

    private ScrollPane scrollPane;

    private Button addButton;
    private Button clearButton;

    
    private Button loadButton;
    private Button saveButton;
    private Button sortButton;

    //crud fix
    AppFrame()
    {
        header = new Header();
        contactList = new ContactList();
        footer = new Footer();

        scrollPane = new ScrollPane(contactList);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);

        this.setTop(header);
        this.setCenter(scrollPane);
        this.setBottom(footer);

        addButton = footer.getAddButton();
        clearButton = footer.getClearButton();
        loadButton = footer.getLoadButton();
        saveButton = footer.getSaveButton();
        sortButton = footer.getSortButton();

        addListeners();
    }

    public void addListeners()
    {
        addButton.setOnAction(e -> {
            Contact contact = new Contact();
            contactList.getChildren().add(contact);
            // Button picButton = contact.getPicButton();
            // picButton.setOnAction(e1 -> {
            //     contact.toggleDone();
            // });
            contactList.updateTaskIndices();
        });
        // clearButton.setOnAction(e -> {
        //     contactList.removeCompletedTasks();
        // });

        // loadButton.setOnAction(e -> {
        //     contactList.loadTasks();
        // });

        // saveButton.setOnAction(e -> {
        //     contactList.saveTasks();
        // });

        // sortButton.setOnAction(e -> {
        //     contactList.sortTasks();
        // });
    }
}

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        AppFrame root = new AppFrame();

        primaryStage.setTitle("Contact List");
        primaryStage.setScene(new Scene(root, 500, 600));
        primaryStage.setResizable(false);
        primaryStage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}