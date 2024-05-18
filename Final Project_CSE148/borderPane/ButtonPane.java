package borderPane;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class ButtonPane {

	private HBox buttonBox;
	private Button inerstBtn;
	private Button searchBtn;
	private Button updateBtn;
	private Button removeBtn;
	
	public ButtonPane() {
		buttonBox = new HBox(50);
		inerstBtn = new Button("INSERT");
		searchBtn = new Button("SEARCH");
		updateBtn = new Button("UPDATE");
		removeBtn = new Button("REMOVE");
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.getChildren().addAll(inerstBtn, searchBtn, updateBtn, removeBtn);
	}
	
	public HBox getButtonBox() {
		return buttonBox;
	}

	public Button getInerstBtn() {
		return inerstBtn;
	}

	public void setInerstBtn(Button inerstBtn) {
		this.inerstBtn = inerstBtn;
	}

	public Button getSearchBtn() {
		return searchBtn;
	}

	public void setSearchBtn(Button searchBtn) {
		this.searchBtn = searchBtn;
	}

	public Button getUpdateBtn() {
		return updateBtn;
	}

	public void setUpdateBtn(Button updateBtn) {
		this.updateBtn = updateBtn;
	}

	public Button getRemoveBtn() {
		return removeBtn;
	}

	public void setRemoveBtn(Button removeBtn) {
		this.removeBtn = removeBtn;
	}

	public void setButtonBox(HBox buttonBox) {
		this.buttonBox = buttonBox;
	}
	
}
