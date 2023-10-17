import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
import javafx.geometry.Rectangle2D;
import java.io.*;
import javafx.util.Pair;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Contact extends HBox {

    private Label index;

    private Button picButton;
    private ImageView profilePic;
    private Stage imageStage;
    private FileChooser fileChooser = new FileChooser();

    private Button selectButton;
    private boolean selected;
    private VBox buttons;

    private HBox contactName;
    private HBox phoneNumber;
    private HBox emailAddress;
    private VBox contactInfo;

    private TextField contactNameText;
    private TextField phoneNumberText;
    private TextField emailAddressText;

    private ChoiceBox<String> categoryMenu;


    Contact() {
        this.setPrefSize(500, 50); // sets size of task
        this.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0; -fx-font-weight: bold;"); // sets background color of task
        this.setSpacing(20);
        selected = false;

        contactName = new HBox();
        phoneNumber = new HBox();
        emailAddress = new HBox();
        contactInfo = new VBox();
        buttons = new VBox();


        //index number
        index = new Label();
        index.setText(""); // create index label
        index.setPrefSize(80, 40); // set size of Index label
        index.setTextAlignment(TextAlignment.LEFT); // Set alignment of index label
        index.setPadding(new Insets(5, 0, 5, 0)); // adds some padding to the task
        this.getChildren().add(index); // add index label to task

        //add profile image
        profilePic = new ImageView();
        profilePic.setFitWidth(110);
        Rectangle2D viewportRect = new Rectangle2D(200, 200, 1200, 1200);
        profilePic.setViewport(viewportRect);
        profilePic.setPreserveRatio(true);
        this.getChildren().add(profilePic);

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

        //upload button
        picButton = new Button("Upload Image");
        picButton.setPrefSize(280, 55);
        picButton.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); 
        buttons.getChildren().add(picButton);

        //selection button
        selectButton = new Button("Select Contact");
        selectButton.setPrefSize(280, 55);
        selectButton.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); 
        buttons.getChildren().add(selectButton);

        //contact categorization
        categoryMenu = new ChoiceBox<>();
        categoryMenu.getItems().add("Default");
        categoryMenu.getItems().add("Friend");
        categoryMenu.getItems().add("Family");
        categoryMenu.getItems().add("School");
        categoryMenu.getItems().add("Work");
        categoryMenu.setValue("Default");
        categoryMenu.setPrefSize(280, 55);
        categoryMenu.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;");
        buttons.getChildren().add(categoryMenu);

        this.getChildren().add(buttons);
        buttons.setAlignment(Pos.CENTER);

        categoryMenu.setOnAction(e -> {
            String selectedCategory = categoryMenu.getValue();
            categoryMenu.setValue(selectedCategory);
        });
       
    }

    public void setTaskIndex(int num) {
        this.index.setText(String.valueOf(num)); // num to String
        this.contactNameText.setPromptText("Name");
        this.emailAddressText.setPromptText("Email address");
        this.phoneNumberText.setPromptText("Phone number");
    }

    public TextField getContactName() {
        return this.contactNameText;
    }

    public TextField getEmailAddress() {
        return this.emailAddressText;
    }

    public TextField getPhoneNumber() {
        return this.phoneNumberText;
    }

    public Button getPicButton() {
        return this.picButton;
    }

    public Button getSelectButton() {
        return this.selectButton;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void uploadPic() {
        imageStage = new Stage();

        fileChooser.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(imageStage);
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            profilePic.setImage(image);
        }
    }

    public void toggleSelect() {
        if (!selected) {
            selected = true;
            selectButton.setStyle("-fx-border-color: #000000; -fx-border-width: 0; -fx-font-weight: bold;"); 
            selectButton.setStyle("-fx-background-color: #BCE29E; -fx-border-width: 0;");
        }
        else {
            selected = false;
            selectButton.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); 
        }
    }

    public String getCategory() {
        return categoryMenu.getValue();
    }

    public void setCategory(String category) {
        categoryMenu.setValue(category);
    }
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

    public void removeSelectedContacts() {
        this.getChildren().removeIf(contact -> contact instanceof Contact && ((Contact) contact).isSelected());
        this.updateTaskIndices();
    }

    public void sortNames() {
        ArrayList<Contact> nameList = new ArrayList<Contact>();
        for (int i = 0; i < this.getChildren().size(); i++) {
            if (this.getChildren().get(i) instanceof Contact) {
                nameList.add((Contact) this.getChildren().get(i));
            }
        }
        Collections.sort(nameList, new Comparator<Contact>() { //sorted list of contacts
            public int compare(Contact a, Contact b) {
                return a.getContactName().getText().compareTo(b.getContactName().getText());
            }
        });
        this.getChildren().clear(); //empty list
        for (int i = 0; i < nameList.size(); i++) { //add all contacts back into official list
            Contact contact = (Contact)nameList.get(i);
            this.getChildren().add(contact);
            Button picButton = contact.getPicButton();
            picButton.setOnAction(e1 -> {
                contact.uploadPic();
            });
            Button selectButton = contact.getSelectButton();
            selectButton.setOnAction(e2 -> {
                contact.toggleSelect();
            });
            //contact.setCategory(contact.getCategory());
        }
        this.updateTaskIndices();
    }

    // public String convertToCSV(String[] data) {
    //     return Stream.of(data)
    //     .map(this::escapeSpecialCharacters)
    //     .collect(Collectors.joining(","));
    // }

    public void loadContacts() {
        //wip
        try {
            FileReader fr = new FileReader("src/contacts.csv");
            BufferedReader br = new BufferedReader(fr);
            while (br.ready()) {
                String str = br.readLine();
                Contact cur = new Contact();
                this.getChildren().add(cur);

                System.out.println(str);

                int count = 0;
                String name = "", email = "", phone = "", category = "";
                for (int i = 0; i < str.length(); i++) {
                    if (str.substring(i, i+1).equals(",")) {
                        count++;
                    }
                    else if (count == 0) {
                        name += str.substring(i, i+1);
                    }
                    else if (count == 1) {
                        email += str.substring(i, i+1);
                    }
                    else if (count == 2) {
                        phone += str.substring(i, i+1);
                    }
                    else if (count == 3) {
                        category += str.substring(i, i+1);
                    }
                    else {
                        System.out.println("too many commas!");
                    }
                }
                //System.out.println(name + " " + email + " " + phone + " " + category + "" + count + "\n");
                cur.getContactName().setText(name);
                cur.getEmailAddress().setText(email);
                cur.getPhoneNumber().setText(phone);
                cur.setCategory(category);

                Button picButton = cur.getPicButton();
                picButton.setOnAction(e1 -> {
                    cur.uploadPic();
                });
                Button selectButton = cur.getSelectButton();
                selectButton.setOnAction(e2 -> {
                    cur.toggleSelect();
                });
                
                this.updateTaskIndices();
            }
            fr.close();
            br.close();
        }
        catch (Exception e) {
            System.out.println("no 'contacts.csv' file found!");
        }
    }
    public void saveContacts() {
        try {
            FileWriter fw = new FileWriter("src/contacts.csv", false);
            for (int i = 0; i < this.getChildren().size(); i++) {
                if (this.getChildren().get(i) instanceof Contact) {
                    Contact cur = (Contact) this.getChildren().get(i);
                    String name = cur.getContactName().getText();
                    String email = cur.getEmailAddress().getText();
                    String phoneNum = cur.getPhoneNumber().getText();
                    String category = cur.getCategory();
                    fw.write(name + ',' + email + ',' + phoneNum + ',' + category + '\n'); 
                }
            }
            fw.close();
        }
        catch (Exception e) {
            System.out.println("unable to save to 'contacts.csv' file!");
        }
    }

    public void sortCategory() {
    Map<String, List<Contact>> contactsByCategory = new HashMap<>();

    for (int i = 0; i < this.getChildren().size(); i++) {
        if (this.getChildren().get(i) instanceof Contact) {
            Contact contact = (Contact) this.getChildren().get(i);
            String category = contact.getCategory();
            contactsByCategory.computeIfAbsent(category, k -> new ArrayList<>()).add(contact);
        }
    }

    // Sort contacts within each category by name
    for (List<Contact> contacts : contactsByCategory.values()) {
        Collections.sort(contacts, new Comparator<Contact>() {
            @Override
            public int compare(Contact contact1, Contact contact2) {
                String name1 = contact1.getContactName().getText();
                String name2 = contact2.getContactName().getText();
                return name1.compareTo(name2);
            }
        });
    }

    // Clear the list and add the sorted contacts back in
    this.getChildren().clear();
    for (List<Contact> contacts : contactsByCategory.values()) {
        for (int i = 0; i < contacts.size(); i++) {
            Contact contact = (Contact)contacts.get(i);
            this.getChildren().add(contact);
            Button picButton = contact.getPicButton();
            picButton.setOnAction(e1 -> {
                contact.uploadPic();
            });
            Button selectButton = contact.getSelectButton();
            selectButton.setOnAction(e2 -> {
                contact.toggleSelect();
            });
        }
    }

    this.updateTaskIndices();
}
}


class Footer extends HBox {

    private Button addButton;
    private Button clearButton;
    private Button loadButton;
    private Button saveButton;
    private Button sortButton;
    private Button sortCategoryButton;

    Footer() {
        this.setPrefSize(500, 60);
        this.setStyle("-fx-background-color: #F0F8FF;");
        this.setSpacing(15);

        String defaultButtonStyle = "-fx-font-style: italic; -fx-background-color: #FFFFFF;  -fx-font-weight: bold; -fx-font: 11 arial;";

        addButton = new Button("Add Contact"); 
        addButton.setStyle(defaultButtonStyle); 
        clearButton = new Button("Clear Selected"); 
        clearButton.setStyle(defaultButtonStyle);

        loadButton = new Button("Load");
        loadButton.setStyle(defaultButtonStyle);
        saveButton = new Button("Save");
        saveButton.setStyle(defaultButtonStyle);
        sortButton = new Button("Sort");
        sortButton.setStyle(defaultButtonStyle);

        sortCategoryButton = new Button("Sort By Category");
        sortCategoryButton.setStyle(defaultButtonStyle);

        this.getChildren().addAll(addButton, clearButton, loadButton, saveButton, sortButton, sortCategoryButton);
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
    public Button getSortCategoryButton() {
        return sortCategoryButton;
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

    private Button sortCategoryButton;

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
        sortCategoryButton = footer.getSortCategoryButton();

        addListeners();
    }

    public void addListeners()
    {
        addButton.setOnAction(e -> {
            Contact contact = new Contact();
            contactList.getChildren().add(contact);
            Button picButton = contact.getPicButton();
            picButton.setOnAction(e1 -> {
                contact.uploadPic();
            });
            Button selectButton = contact.getSelectButton();
            selectButton.setOnAction(e2 -> {
                contact.toggleSelect();
            });
            contactList.updateTaskIndices();
        });
        clearButton.setOnAction(e -> {
            contactList.removeSelectedContacts();
        });

        loadButton.setOnAction(e -> {
            contactList.loadContacts();
        });

        saveButton.setOnAction(e -> {
            contactList.saveContacts();
        });

        sortButton.setOnAction(e -> {
            contactList.sortNames();
        });
        sortCategoryButton.setOnAction(e -> {
            contactList.sortCategory();
        });
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