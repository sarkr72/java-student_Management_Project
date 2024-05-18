package menuAndItems;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import app.AppDemo;
import borderPane.ButtonPane;
import fileChoooserSample.FileChooserFinal;
import helpers.Helper;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import listView.ListViewDemo;
import model.College;
import saveAndRestoreFile.FileRestorer;
import saveAndRestoreFile.FileSaver;
import view.ClassRoomPane;
import view.CoursePane;
import view.FacultyCourseInfoPane;
import view.FacultyPane;
import view.StudentCourseInfoPane;
import view.StudentPane;
import view.TextBookPane;

public class MenuAndItems {
//	ListView listView = new ListView();
//	private BorderPane root;

//	@SuppressWarnings("unchecked")
	public static void getMenu(AppDemo app, Stage primaryStage, College college) throws IOException {
//		AppDemo app = new AppDemo();
		ListView listView = new ListView();
		 BorderPane root;
		root = new BorderPane();

		MenuBar menuBar = new MenuBar();
		Menu fileMenu = new Menu("File");
		MenuItem exitItem = new MenuItem("Exit");

		exitItem.setOnAction(e -> {
			Platform.exit();
		});

		Button colleges = new Button("Colleges");
		colleges.setOnAction(e -> {
			Pane pane = ListViewDemo.getCollges(primaryStage, app, college);
			Scene scene = new Scene(pane, 400, 450);
			primaryStage.setScene(scene);
			primaryStage.show();
		});
//		root.getChildren().add(colleges);
		root.setCenter(colleges);

		Menu importMenu = new Menu("Import");

		MenuItem studentImportItem = new MenuItem("Student");
		MenuItem facultyImportItem = new MenuItem("Faculty");
		MenuItem studentCourseInfoImport = new MenuItem("StudentCourseInfo");
		MenuItem facultyCourseInfoImportItem = new MenuItem("FacultyCourseInfo");
		MenuItem textBookImportItem = new MenuItem("Textbook");
		MenuItem classRoomImportItem = new MenuItem("Classroom");
		MenuItem courseImportItem = new MenuItem("Course");

		importMenu.getItems().addAll(studentImportItem, studentCourseInfoImport, facultyImportItem,
				facultyCourseInfoImportItem, courseImportItem, textBookImportItem, classRoomImportItem);

		Menu exportMenu = new Menu("Export");
		MenuItem studentExportItem = new MenuItem("Student");
		MenuItem facultyExportItem = new MenuItem("Faculty");
		MenuItem courseExportItem = new MenuItem("Course");
		MenuItem textBookExportItem = new MenuItem("Textbook");
		MenuItem classRoomExportItem = new MenuItem("Classroom");
		MenuItem facultyCourseInfoExport = new MenuItem("Faculty CourseInfo");
		MenuItem studentCourseInfoExport = new MenuItem("Student CourseInfo");

		exportMenu.getItems().addAll(studentExportItem, studentCourseInfoExport, facultyExportItem,
				facultyCourseInfoExport, courseExportItem, textBookExportItem, classRoomExportItem);

		MenuItem backUpItem = new MenuItem("Backup");
		MenuItem restoreItem = new MenuItem("Restore");

		fileMenu.getItems().addAll(importMenu, exportMenu,
//									new Separator(),
				backUpItem, restoreItem,
//									new Separator(),
				exitItem);
		Menu editMenu = new Menu("Edit");
		MenuItem studentCourseInfoItem = new MenuItem("StudentCourseInfo");
		MenuItem studentItem = new MenuItem("Student");
		MenuItem facultyItem = new MenuItem("Faculty");
		MenuItem facultyCourseInfoItem = new MenuItem("FacultyCourseInfo");
		MenuItem courseItem = new MenuItem("Course");
		MenuItem textBookItem = new MenuItem("Textbook");
		MenuItem classRoomItem = new MenuItem("Classroom");

		editMenu.getItems().addAll(studentItem, studentCourseInfoItem, facultyItem, facultyCourseInfoItem,
//									new Separator(),
				courseItem, textBookItem,
//									new Separator(),
				classRoomItem);

		menuBar.getMenus().addAll(fileMenu, editMenu);

		Pane buttonBox = new ButtonPane().getButtonBox();

		facultyItem.setOnAction(e -> {
			Pane facultyPane = new FacultyPane(college).getPane();
			VBox pane = new VBox(10);
			pane.setAlignment(Pos.CENTER);
			Button bt = new Button("Home Page");
			bt.setAlignment(Pos.BOTTOM_CENTER);
			pane.getChildren().addAll(facultyPane, bt);
			primaryStage.setScene(new Scene(pane, 800, 500));
			primaryStage.show();
			bt.setOnAction(e2 -> {

				try {
					app.start(primaryStage);
				} catch (IOException e1) {
					System.out.println(e1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
		});

		studentItem.setOnAction(e -> {
			StudentPane sp = new StudentPane(college, primaryStage, app);
			Pane studentPane = sp.getVbox();
			VBox pane = new VBox(10);
			pane.setAlignment(Pos.CENTER);
			Button bt = new Button("Home Page");
			bt.setAlignment(Pos.BOTTOM_CENTER);
			pane.getChildren().addAll(studentPane, bt);
			primaryStage.setScene(new Scene(pane, 800, 500));
			primaryStage.show();
			bt.setOnAction(e2 -> {

//				try {
//					app.start(primaryStage);
//				} catch (Exception e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
			});
		});

		courseItem.setOnAction(e -> {
			Pane coursePane = new CoursePane(college).getPane();
			VBox pane = new VBox(10);
			pane.setAlignment(Pos.CENTER);
			Button bt = new Button("Home Page");
			bt.setAlignment(Pos.BOTTOM_CENTER);
			pane.getChildren().addAll(coursePane, bt);
			primaryStage.setScene(new Scene(pane, 800, 500));
			primaryStage.show();
			bt.setOnAction(e2 -> {

				try {
					app.start(primaryStage);
				} catch (IOException e1) {
					System.out.println(e1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
		});

		textBookItem.setOnAction(e -> {
			Pane textBookPane = new TextBookPane(college).getPane();
			VBox pane = new VBox(10);
			pane.setAlignment(Pos.CENTER);
			Button bt = new Button("Home Page");
			bt.setAlignment(Pos.BOTTOM_CENTER);
			pane.getChildren().addAll(textBookPane, bt);
			primaryStage.setScene(new Scene(pane, 800, 500));
			primaryStage.show();
			bt.setOnAction(e2 -> {

				try {
					app.mainPage(app,primaryStage, college);
				} catch (IOException e1) {
					System.out.println(e1);
				}
			});
		});
//
//		classRoomItem.setOnAction(e -> {
//			Pane classRoomPane = new ClassRoomPane(college).getPane();
//			VBox pane = new VBox(10);
//			pane.setAlignment(Pos.CENTER);
//			Button bt = new Button("Home Page");
//			bt.setAlignment(Pos.BOTTOM_CENTER);
//			pane.getChildren().addAll(classRoomPane, bt);
//			primaryStage.setScene(new Scene(pane, 800, 500));
//			primaryStage.show();
//			bt.setOnAction(e2 -> {
//
//				try {
//					app.mainPage(primaryStage);
//				} catch (IOException e1) {
//					System.out.println(e1);
//				}
//			});
//		});
//
//		studentCourseInfoItem.setOnAction(e -> {
//			Pane studentCourseInfoPane = new StudentCourseInfoPane(college).getPane();
//			VBox pane = new VBox(10);
//			pane.setAlignment(Pos.CENTER);
//			Button bt = new Button("Home Page");
//			bt.setAlignment(Pos.BOTTOM_CENTER);
//			pane.getChildren().addAll(studentCourseInfoPane, bt);
//			primaryStage.setScene(new Scene(pane, 800, 500));
//			primaryStage.show();
//			bt.setOnAction(e2 -> {
//
//				try {
//					app.mainPage(primaryStage);
//				} catch (IOException e1) {
//					System.out.println(e1);
//				}
//			});
//		});
//
//		facultyCourseInfoItem.setOnAction(e -> {
//			Pane facultyCourseInfoPane = new FacultyCourseInfoPane(college).getPane();
//			VBox pane = new VBox(10);
//			pane.setAlignment(Pos.CENTER);
//			Button bt = new Button("Home Page");
//			bt.setAlignment(Pos.BOTTOM_CENTER);
//			pane.getChildren().addAll(facultyCourseInfoPane, bt);
//			primaryStage.setScene(new Scene(pane, 800, 500));
//			primaryStage.show();
//			bt.setOnAction(e2 -> {
//
//				try {
//					app.mainPage(primaryStage);
//				} catch (IOException e1) {
//					System.out.println(e1);
//				}
//			});
//		});

		studentImportItem.setOnAction(e -> {
			TextArea textArea = new TextArea();
			FileChooser fileChooser = new FileChooser();
			String str = new String();

			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
			fileChooser.getExtensionFilters().add(extFilter);

			File file = fileChooser.showOpenDialog(primaryStage);
			if (file != null) {
				try {
					str = FileChooserFinal.readFile(file, college);
				} catch (FileNotFoundException e1) {
					System.out.println(e);
				}
			}

			VBox pane = new VBox(10);
			pane.setAlignment(Pos.CENTER);
			Button bt = new Button("Home Page");
			bt.setAlignment(Pos.BOTTOM_CENTER);
			textArea.appendText(str);
			pane.getChildren().addAll(textArea, bt);
			primaryStage.setScene(new Scene(pane, 450, 500));
			primaryStage.show();

			bt.setOnAction(e2 -> {

				try {
					app.mainPage(app, primaryStage, college);
				} catch (IOException e1) {
					System.out.println(e1);
				}
			});

		});

		studentCourseInfoImport.setOnAction(e -> {
			TextArea textArea = new TextArea();
			textArea.setMaxHeight(500);
			textArea.setMaxWidth(600);

			FileChooser fileChooser = new FileChooser();

			String str = new String();

			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
			fileChooser.getExtensionFilters().add(extFilter);

			File file = fileChooser.showOpenDialog(primaryStage);
			if (file != null) {
				try {
					str = FileChooserFinal.readStudentCourseInfoFile(file, college);
				} catch (FileNotFoundException e1) {
					System.out.println(e);
				}
			}
			VBox pane = new VBox(10);
			pane.setAlignment(Pos.CENTER);
			Button bt = new Button("Home Page");
			bt.setAlignment(Pos.BOTTOM_CENTER);
			textArea.appendText(str);
			pane.getChildren().addAll(textArea, bt);
			primaryStage.setScene(new Scene(pane, 600, 350));
			primaryStage.show();
			bt.setOnAction(e2 -> {
				try {
					app.mainPage(app, primaryStage, college);
				} catch (IOException e1) {
					System.out.println(e1);
				}
			});
		});

		facultyImportItem.setOnAction(e -> {
			TextArea textArea = new TextArea();
			textArea.setMaxHeight(500);
			textArea.setMaxWidth(600);

			FileChooser fileChooser = new FileChooser();

			String str = new String();

			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
			fileChooser.getExtensionFilters().add(extFilter);

			File file = fileChooser.showOpenDialog(primaryStage);
			if (file != null) {
				try {
					str = FileChooserFinal.readFacultyFile(file, college);
				} catch (FileNotFoundException e1) {
					System.out.println(e);
				}
			}
			VBox pane = new VBox(10);
			pane.setAlignment(Pos.CENTER);
			Button bt = new Button("Home Page");
			bt.setAlignment(Pos.BOTTOM_CENTER);
			textArea.appendText(str);
			pane.getChildren().addAll(textArea, bt);
			primaryStage.setScene(new Scene(pane, 600, 350));
			primaryStage.show();
			bt.setOnAction(e2 -> {
//				AppDemo app = new AppDemo();
				try {
					app.mainPage(app, primaryStage, college);
				} catch (IOException e1) {
					System.out.println(e1);
				}
			});
		});

		facultyCourseInfoImportItem.setOnAction(e -> {
			TextArea textArea = new TextArea();
			textArea.setMaxHeight(500);
			textArea.setMaxWidth(600);

			FileChooser fileChooser = new FileChooser();

			String str = new String();

			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
			fileChooser.getExtensionFilters().add(extFilter);

			File file = fileChooser.showOpenDialog(primaryStage);
			if (file != null) {
				try {
					str = FileChooserFinal.readFacultyCourseInfoFile(file, college);
				} catch (FileNotFoundException e1) {
					System.out.println(e);
				}
			}
			VBox pane = new VBox(10);
			pane.setAlignment(Pos.CENTER);
			Button bt = new Button("Home Page");
			bt.setAlignment(Pos.BOTTOM_CENTER);
			textArea.appendText(str);
			pane.getChildren().addAll(textArea, bt);
			primaryStage.setScene(new Scene(pane, 600, 350));
			primaryStage.show();
			bt.setOnAction(e2 -> {
//				AppDemo app = new AppDemo();
				try {
					app.mainPage(app, primaryStage, college);
				} catch (IOException e1) {
					System.out.println(e1);
				}
			});
		});

		textBookImportItem.setOnAction(e -> {
			TextArea textArea = new TextArea();
			FileChooser fileChooser = new FileChooser();

			String str = new String();

			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
			fileChooser.getExtensionFilters().add(extFilter);

			List<File> list = fileChooser.showOpenMultipleDialog(null);

//			File file = fileChooser.showOpenDialog(primaryStage);
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					listView.getItems().add(list.get(i).getAbsolutePath());
				}
				try {
					str = FileChooserFinal.readTextbookFile(list, college);
				} catch (FileNotFoundException e1) {
				}
			}
			VBox pane = new VBox(10);
			pane.setMaxHeight(450);
			pane.setMaxWidth(550);
			pane.setAlignment(Pos.CENTER);
			Button bt = new Button("Home Page");
			bt.setAlignment(Pos.BOTTOM_CENTER);
			textArea.appendText(str);
			pane.getChildren().addAll(textArea, bt);
			primaryStage.setScene(new Scene(pane, 600, 600));
			primaryStage.show();
			bt.setOnAction(e2 -> {
//				AppDemo app = new AppDemo();
				try {
					app.mainPage(app, primaryStage, college);
				} catch (IOException e1) {
					System.out.println(e1);
				}
			});
		});

		classRoomImportItem.setOnAction(e -> {
			TextArea textArea = new TextArea();
			FileChooser fileChooser = new FileChooser();

			String str = new String();

			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
			fileChooser.getExtensionFilters().add(extFilter);

			File file = fileChooser.showOpenDialog(primaryStage);
			if (file != null) {
				try {
					str = FileChooserFinal.readClassRoomFile(file, college);
				} catch (FileNotFoundException e1) {
					System.out.println(e);
				}
			}

			VBox pane = new VBox(10);
			pane.setAlignment(Pos.CENTER);
			Button bt = new Button("Home Page");
			bt.setAlignment(Pos.BOTTOM_CENTER);
			textArea.appendText(str);
			pane.getChildren().addAll(textArea, bt);
			primaryStage.setScene(new Scene(pane, 600, 350));
			primaryStage.show();
			bt.setOnAction(e2 -> {
//				AppDemo app = new AppDemo();
				try {
					app.mainPage(app, primaryStage, college);
				} catch (IOException e1) {
					System.out.println(e1);
				}
			});
		});

		courseImportItem.setOnAction(e -> {
			TextArea textArea = new TextArea();
			FileChooser fileChooser = new FileChooser();

			String str = new String();

			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
			fileChooser.getExtensionFilters().add(extFilter);

			File file = fileChooser.showOpenDialog(primaryStage);
			if (file != null) {
				try {
					str = FileChooserFinal.readCourseFile(file, college);
				} catch (FileNotFoundException e1) {
					System.out.println(e);
				}
			}
			VBox pane = new VBox(10);
			pane.setAlignment(Pos.CENTER);
			Button bt = new Button("Home Page");
			bt.setAlignment(Pos.BOTTOM_CENTER);
			textArea.appendText(str);
			pane.getChildren().addAll(textArea, bt);
			primaryStage.setScene(new Scene(pane, 600, 350));
			primaryStage.show();
			bt.setOnAction(e2 -> {
//				AppDemo app = new AppDemo();
				try {
					app.mainPage(app, primaryStage, college);
				} catch (IOException e1) {
					System.out.println(e1);
				}
			});
		});

		studentExportItem.setOnAction(e -> {
			try {
				Helper.students(college.getPersonBag(), college);
			} catch (FileNotFoundException e1) {
				System.out.println(e);
			}
			Button btn = new Button("Successfully Exported");
			btn.setMaxWidth(200);
			btn.setMaxHeight(40);

			FileChooser fileChooser = new FileChooser();

			String str = college.getPersonBag().getPersonBagArray();

			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
			fileChooser.getExtensionFilters().add(extFilter);

			File file = fileChooser.showSaveDialog(primaryStage);

			if (file != null) {
				FileChooserFinal.exportFile(college.getPersonBag().getPersonBagArray(), file);
			}
			VBox pane = new VBox(10);
			pane.setAlignment(Pos.CENTER);
			Button bt = new Button("Home Page");
			bt.setAlignment(Pos.BOTTOM_CENTER);
			pane.getChildren().addAll(btn, bt);
			primaryStage.setScene(new Scene(pane, 600, 350));
			primaryStage.show();
			bt.setOnAction(e2 -> {
//				AppDemo app = new AppDemo();
				try {
					app.mainPage(app, primaryStage, college);
				} catch (IOException e1) {
					System.out.println(e1);
				}
			});
		});

		studentCourseInfoExport.setOnAction(e -> {
			try {
				Helper.courses(college.getMasterCourseBag());
				Helper.students(college.getPersonBag(), college);
			} catch (FileNotFoundException e1) {
				System.out.println(e);
			}
			Button btn = new Button("Successfully Exported");
			btn.setMaxWidth(200);
			btn.setMaxHeight(40);

			FileChooser fileChooser = new FileChooser();

			String str = college.getPersonBag().getPersonBagArray();

			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
			fileChooser.getExtensionFilters().add(extFilter);

			File file = fileChooser.showSaveDialog(primaryStage);

			if (file != null) {
				FileChooserFinal.exportStudentCourseInfoFile(college.getStudentCourseInfoBag().studntCourseInfoBag(),
						file);
			}
			VBox pane = new VBox(10);
			pane.setAlignment(Pos.CENTER);
			Button bt = new Button("Home Page");
			bt.setAlignment(Pos.BOTTOM_CENTER);
			pane.getChildren().addAll(btn, bt);
			primaryStage.setScene(new Scene(pane, 600, 350));
			primaryStage.show();
			bt.setOnAction(e2 -> {
//				AppDemo app = new AppDemo();
				try {
					app.mainPage(app, primaryStage, college);
				} catch (IOException e1) {
					System.out.println(e1);
				}
			});
		});

		facultyExportItem.setOnAction(e -> {
			try {
				Helper.courses(college.getMasterCourseBag());
				Helper.faculty(college.getFacultyCourseBag(), college);
			} catch (FileNotFoundException e1) {
				System.out.println(e1);
			}
			Button btn = new Button("Successfully Exported");
			btn.setMaxWidth(200);
			btn.setMaxHeight(40);

			FileChooser fileChooser = new FileChooser();
			TextArea textArea2 = new TextArea();
			String str = college.getPersonBag().getPersonBagArray();
//			System.out.println(str);
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
			fileChooser.getExtensionFilters().add(extFilter);

			File file = fileChooser.showSaveDialog(primaryStage);

			if (file != null) {
				FileChooserFinal.exportFacultyFile(college.getPersonBag().getFacultyBagArray(), file);
			}
			VBox pane = new VBox(10);
			pane.setAlignment(Pos.CENTER);
			Button bt = new Button("Home Page");
			bt.setAlignment(Pos.BOTTOM_CENTER);
			pane.getChildren().addAll(btn, bt);
			primaryStage.setScene(new Scene(pane, 600, 350));
			primaryStage.show();
			bt.setOnAction(e2 -> {
//				AppDemo app = new AppDemo();
				try {
					app.mainPage(app, primaryStage, college);
				} catch (IOException e1) {
					System.out.println(e1);
				}
			});
		});

		facultyCourseInfoExport.setOnAction(e -> {
			try {
				Helper.courses(college.getMasterCourseBag());
				Helper.faculty(college.getFacultyCourseBag(), college);
			} catch (FileNotFoundException e1) {
				System.out.println(e1);
			}
			Button btn = new Button("Successfully Exported");
			btn.setMaxWidth(200);
			btn.setMaxHeight(40);

			FileChooser fileChooser = new FileChooser();
			TextArea textArea2 = new TextArea();
			String str = college.getPersonBag().getPersonBagArray();
//			System.out.println(str);
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
			fileChooser.getExtensionFilters().add(extFilter);

			File file = fileChooser.showSaveDialog(primaryStage);

			if (file != null) {
				FileChooserFinal.exportFacultyFile(college.getFacultyCourseBag().getFacutyCourseInfoBag(), file);
			}
			VBox pane = new VBox(10);
			pane.setAlignment(Pos.CENTER);
			Button bt = new Button("Home Page");
			bt.setAlignment(Pos.BOTTOM_CENTER);
			pane.getChildren().addAll(btn, bt);
			primaryStage.setScene(new Scene(pane, 600, 350));
			primaryStage.show();
			bt.setOnAction(e2 -> {
			});
		});

		courseExportItem.setOnAction(e -> {
			try {
				Helper.courses(college.getMasterCourseBag());
			} catch (FileNotFoundException e1) {
				System.out.println(e1);
			}
			Button btn = new Button("Successfully Exported");
			btn.setMaxWidth(200);
			btn.setMaxHeight(40);

			FileChooser fileChooser = new FileChooser();
			TextArea textArea2 = new TextArea();
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
			fileChooser.getExtensionFilters().add(extFilter);

			File file = fileChooser.showSaveDialog(primaryStage);

			if (file != null) {
				FileChooserFinal.exportCourseFile(college.getMasterCourseBag().getMasterCourseBag(), file);
			}
			VBox pane = new VBox(10);
			pane.setAlignment(Pos.CENTER);
			Button bt = new Button("Home Page");
			bt.setAlignment(Pos.BOTTOM_CENTER);
			pane.getChildren().addAll(btn, bt);
			primaryStage.setScene(new Scene(pane, 600, 350));
			primaryStage.show();
			bt.setOnAction(e2 -> {
				try {
					app.mainPage(app, primaryStage, college);
				} catch (IOException e1) {
					System.out.println(e1);
				}
			});
		});

		textBookExportItem.setOnAction(e -> {
			Button btn = new Button("Successfully Exported");
			btn.setMaxWidth(200);
			btn.setMaxHeight(40);

			FileChooser fileChooser = new FileChooser();
			TextArea textArea2 = new TextArea();

			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
			fileChooser.getExtensionFilters().add(extFilter);

			File file = fileChooser.showSaveDialog(primaryStage);
			if (file != null) {
				try {
					FileChooserFinal.exportTextBookFile(college, file);
				} catch (FileNotFoundException e1) {
				}
			}
			VBox pane = new VBox(10);
			pane.setAlignment(Pos.CENTER);
			Button bt = new Button("Home Page");
			bt.setAlignment(Pos.BOTTOM_CENTER);
			pane.getChildren().addAll(btn, bt);
			primaryStage.setScene(new Scene(pane, 600, 350));
			primaryStage.show();
			bt.setOnAction(e2 -> {
				try {
					app.mainPage(app, primaryStage, college);
				} catch (IOException e1) {
					System.out.println(e1);
				}
			});
		});

		classRoomExportItem.setOnAction(e -> {
			try {
				Helper.classRooms(college.getClassRoomBag());
			} catch (FileNotFoundException e1) {
				System.out.println(e1);
			}
			Button btn = new Button("Successfully Exported");
			btn.setMaxWidth(200);
			btn.setMaxHeight(40);

			FileChooser fileChooser = new FileChooser();
			TextArea textArea2 = new TextArea();

			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
			fileChooser.getExtensionFilters().add(extFilter);

			File file = fileChooser.showSaveDialog(primaryStage);

			if (file != null) {
				FileChooserFinal.exportClassRoomFile(college.getClassRoomBag().getClassroomBag(), file);
			}
			VBox pane = new VBox(10);
			pane.setAlignment(Pos.CENTER);
			Button bt = new Button("Home Page");
			bt.setAlignment(Pos.BOTTOM_CENTER);
			pane.getChildren().addAll(btn, bt);
			primaryStage.setScene(new Scene(pane, 600, 350));
			primaryStage.show();
			bt.setOnAction(e2 -> {
				try {
					app.mainPage(app, primaryStage, college);
				} catch (IOException e1) {
					System.out.println(e1);
				}
			});
		});

		backUpItem.setOnAction(e -> {
			Button btn = new Button("Successfully Saved BinaryFile");
			btn.setMaxWidth(200);
			btn.setMaxHeight(40);

			FileChooser fc = new FileChooser();
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("BINARY Files (*.dat)", "*.dat");
			fc.getExtensionFilters().add(extFilter);

			File file = fc.showSaveDialog(primaryStage);

			if (file != null) {
				try {
					FileSaver.saveBinaryFile(file, college);
				} catch (IOException e1) {
					System.out.println(e1);
				}
			}
			VBox pane = new VBox(10);
			pane.setAlignment(Pos.CENTER);
			Button bt = new Button("Home Page");
			bt.setAlignment(Pos.BOTTOM_CENTER);
			pane.getChildren().addAll(btn, bt);
			primaryStage.setScene(new Scene(pane, 600, 350));
			primaryStage.show();
			bt.setOnAction(e2 -> {
				try {
					app.mainPage(app, primaryStage, college);
				} catch (IOException e1) {
					System.out.println(e1);
				}
			});
		});

		restoreItem.setOnAction(e -> {
			TextArea textArea = new TextArea();
			textArea.setMaxHeight(500);
			textArea.setMaxWidth(500);

			FileChooser fc = new FileChooser();
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("BINARY Files (*.dat)", "*.dat");
			fc.getExtensionFilters().add(extFilter);

			String str = "";
			File file = fc.showOpenDialog(primaryStage);

			if (file != null) {
				try {
					try {
						str = FileRestorer.restoreFile(file);
					} catch (ClassNotFoundException e1) {
						System.out.println(e1);
					}
				} catch (IOException e1) {
					System.out.println(e1);
				}
			}
			textArea.appendText(str);
			VBox pane = new VBox(10);
			pane.setMaxHeight(550);
			pane.setMaxWidth(550);
			pane.setAlignment(Pos.CENTER);
			Button bt = new Button("Home Page");
			bt.setAlignment(Pos.BOTTOM_CENTER);
			pane.getChildren().addAll(textArea, bt);
			primaryStage.setScene(new Scene(pane, 600, 600));
			primaryStage.show();
			bt.setOnAction(e2 -> {
				try {
					app.mainPage(app, primaryStage, college);
				} catch (IOException e1) {
					System.out.println(e1);
				}
			});
		});
		root.setTop(menuBar);
		Scene scene = new Scene(root, 600, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
//		return root;
	}
}
