package model;

import java.io.Serializable;

public class TextBookBag implements Serializable{

	private TextBook[] textBooks;
	private int nElems;

	public TextBookBag(int maxTextBooks) {
		textBooks = new TextBook[maxTextBooks];
		nElems = 0;
	}

	public void insertTextBook(TextBook textBook) {
		textBooks[nElems++] = textBook;
	}

	public String getTextBookBag() {
		String str = "";
		for(int i = 0; i < nElems; i++) {
			str += textBooks[i] + "\n";
		}
		return str;
	}
	
	public TextBook findTextBookByIsbn(String isbn) {
		for (int i = 0; i < nElems; i++) {
			if(isbn.equals(textBooks[i].getIsbn())){
			return textBooks[i];
			}
		}
		return null;
	}

	public TextBook removeTextBookByIsbn(String isbn) {
		int i;
		for (i = 0; i < nElems; i++) {
			if (isbn.contentEquals(textBooks[i].getIsbn())) {
				break;
			}
		}
		TextBook temp = textBooks[i];
		if (i == nElems) {
			return null;
		} else {
			for (int j = i; j < nElems - 1; j++) {
				textBooks[j] = textBooks[j + 1];
			}
		}
		nElems--;
		return temp;
	}

	public void displayTextBooks() {
		for (int i = 0; i < nElems; i++) {
			System.out.println(textBooks[i]);
		}
	}
}
