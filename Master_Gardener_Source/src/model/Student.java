package model;

import java.util.Random;

//TODO: define parameters
public class Student {
	private String name = null;
	private int computer_mark = 0;
	private int mathematics_mark = 0;
	private int geography_mark = 0;
	private int history_mark = 0;
	private int literature_mark = 0;

	public Student(){
		this.name = name;
		this.computer_mark = computer_mark;
		this.mathematics_mark = mathematics_mark;
		this.geography_mark = geography_mark;
		this.history_mark = history_mark;
		this.literature_mark = literature_mark;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getComputer_mark() {
		return computer_mark;
	}

	public void setComputer_mark(int computer_mark) {
		this.computer_mark = computer_mark;
	}

	public int getMathematics_mark() {
		return mathematics_mark;
	}

	public void setMathematics_mark(int mathematics_mark) {
		this.mathematics_mark = mathematics_mark;
	}

	public int getGeography_mark() {
		return geography_mark;
	}

	public void setGeography_mark(int geography_mark) {
		this.geography_mark = geography_mark;
	}

	public int getHistory_mark() {
		return history_mark;
	}

	public void setHistory_mark(int history_mark) {
		this.history_mark = history_mark;
	}

	public int getLiterature_mark() {
		return literature_mark;
	}

	public void setLiterature_mark(int literature_mark) {
		this.literature_mark = literature_mark;
	}
}