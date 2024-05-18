package view;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import alerts.Alerts;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.College;
import model.EnumBuildings;
import model.Name;
import model.Student;
import model.TextBook;

public class TextBookPane {

		private College college;
		private TextField bookTitleField;
		private TextField isbnField;
		private TextField firstNameField;
		private TextField lastNameField;
		private TextField priceField;
		
		private Button insertButton;
		private Button searchButton;
		private Button updateButton;
		private Button removeButton;
		
		private HBox buttonBox;
		private HBox textFieldBox;
		private VBox root;
		
		private Name[] name = new Name[4];
		private TextBook textBook;
		private String title;
		private String firstName;
		private String lastName;
		private double price;
		private String isbn;
		
		private TextArea textArea;
		private TextArea nameTextArea;
		
		public TextBookPane(College college) {
			buildTextFields();
			buildButtons();
			buildHboxes();
			buildVBox();
			handleEvent();
			this.college = college;
		}
		
		private void buildTextFields() {
			isbnField = new TextField();
			bookTitleField = new TextField();
			priceField = new TextField();
//			bookTitleField.setDisable(true);
			isbnField.setPromptText("ISBN");
			bookTitleField.setPromptText("BOOK TITLE");
			priceField.setPromptText("PRICE");
//			idField.setDisable(false);
		}

		private void buildButtons() {
			insertButton = new Button("insert");
			searchButton = new Button("SEARCH");
			updateButton = new Button("UPDATE");
			removeButton = new Button("REMOVE");
		}
		
		private void buildVBox() {
			root = new VBox(10);
			root.setAlignment(Pos.CENTER);
			textArea = new TextArea();
			textArea.setMaxWidth(500);
			textArea.setMaxHeight(300);
			textArea.setPromptText("TextBook Info Bag");
			root.getChildren().addAll(textFieldBox, buttonBox, textArea);
		}
		private void buildHboxes() {
			textFieldBox =  new HBox(10);
			buttonBox =  new HBox(10);
			textFieldBox.setAlignment(Pos.CENTER);
			buttonBox.setAlignment(Pos.CENTER);
			nameTextArea = new TextArea();
			nameTextArea.setMaxWidth(200);
			nameTextArea.setMaxHeight(100);
			nameTextArea.setPromptText("Authors: Enter First And Last Name By Line");
			textFieldBox.getChildren().addAll(bookTitleField, isbnField, nameTextArea, priceField);
			buttonBox.getChildren().addAll(insertButton, searchButton, updateButton, removeButton);
		}
		
		public void handleEvent() {
			insertButton.setOnAction(e -> {
//				nameTextArea.clear();
				 isbn = isbnField.getText();
				 
				String names = nameTextArea.getText();
				 Scanner scan = new Scanner(names);
				 int count = 0;
				 while(scan.hasNextLine()) {
				 firstName = scan.nextLine();
				 lastName = scan.nextLine();
				 name[count++]=new Name(firstName, lastName);
				 }
				 
				Name[] namess = Arrays.copyOf(name, count);
				
				 title = bookTitleField.getText();
				 bookTitleField.setText(title);
				 price = Double.parseDouble((priceField.getText()));
				
				 textBook = new TextBook(title, isbn, namess, price);
				college.getTextBookBag().insertTextBook(textBook);
				String info = "Book Title: " + title + "    ISBN: " + isbn + "     " + Arrays.toString(namess) + "     Price: " + price+ "\n";
				textArea.appendText(info);
				
				bookTitleField.clear();
				isbnField.clear();
				priceField.clear();
				nameTextArea.clear();
			});
			
			searchButton.setOnAction(s -> {
				try {
				 isbn = isbnField.getText();
				textBook = college.getTextBookBag().findTextBookByIsbn(isbn);
				if(textBook == null) {
					isbn = Alerts.getDialog("Enter the correct isbn");
					textBook = college.getTextBookBag().findTextBookByIsbn(isbn);
					isbnField.setText(isbn);
				}else {
				name = college.getTextBookBag().findTextBookByIsbn(isbn).getAuthor();
				String price2 = textBook.getPrice();
				
				nameTextArea.appendText(Arrays.toString(name)+"\n");
				bookTitleField.setText(title);
				isbnField.setText(isbn);
				priceField.setText(price2);
				}}catch(Exception e) {
					System.out.println(e + "\n file not found");
				}
			});
			
			updateButton.setOnAction(s -> {
				try {
				 isbn = isbnField.getText();
					textBook = college.getTextBookBag().findTextBookByIsbn(isbn);
					
					String names = nameTextArea.getText();
					 Scanner scan = new Scanner(names);
					 int count = 0;
					 while(scan.hasNextLine()) {
					 firstName = scan.nextLine();
					 lastName = scan.nextLine();
					 name[count++]=new Name(firstName, lastName);
					 }
					
					 Name[] namess = Arrays.copyOf(name, count);
					 
					if(textBook == null) {
						isbn = Alerts.getDialog("Enter the correct isbn");
						textBook = college.getTextBookBag().findTextBookByIsbn(isbn);
						isbnField.setText(isbn);
					}else {
						
				  price = Double.parseDouble(priceField.getText());
				 title = bookTitleField.getText();
				 
				textBook.setAuthor(namess);
				textBook.setBookTitle(title);
				textBook.setPrice(price);
				

				String info = "New: Book Title: " + title + "    ISBN: " + isbn + "     " + Arrays.toString(namess) + "     Price: " + price+ "\n";
				textArea.appendText(info);
				
				
				bookTitleField.clear();
				isbnField.clear();
				priceField.clear();
				nameTextArea.clear();
					}}catch(Exception e) {
			}
			});
			
			removeButton.setOnAction(s -> {
				try {
				 isbn = isbnField.getText();
				 college.getTextBookBag().removeTextBookByIsbn(isbn);
				 if(textBook == null) {
						isbn = Alerts.getDialog("Enter the correct isbn");
						textBook = college.getTextBookBag().removeTextBookByIsbn(isbn);
					}else {
						
						bookTitleField.clear();
						isbnField.clear();
						priceField.clear();
						nameTextArea.clear();
					}}catch(Exception e) {
			}
			});
		}
		public Pane getPane() {
			return root;
		}
}
