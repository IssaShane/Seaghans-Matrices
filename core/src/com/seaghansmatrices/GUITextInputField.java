package com.seaghansmatrices;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class GUITextInputField extends GUIElement {
  protected String content;
  protected TextField field;
  protected Skin skin;

  public GUITextInputField() {
    super();
    this.init();
  }

  public GUITextInputField(Posn pos) {
    super();
    this.pos = pos;
    this.init();
  }

  public GUITextInputField(Posn pos, String skinfilename) {
		super();
		this.pos = pos;
		this.skin = new Skin(Gdx.files.internal(skinfilename));
		this.init();
  }
  
  public GUITextInputField(Posn pos, Skin skin) {
    super();
    this.pos = pos;
    this.skin = skin;
    this.init();
  }

  public void init() {
    this.field = new TextField("", skin);
    this.field.setX(this.pos.x);
    this.field.setY(this.pos.y);
    // check for proper width
    if (this.pos.w != 0 && this.pos.h != 0) {
			this.field.setWidth(this.pos.w);
			this.field.setHeight(this.pos.h);
		}
		else {
			this.field.setWidth(200);
			this.field.setWidth(30);
		}
  }

  @Override
  protected void addToStage(Stage stage) {
    stage.addActor(this.field);
  }

  @Override
	public void setPos(Posn pos) {
		super.setPos(pos);
		this.field.setX(this.pos.x);
		this.field.setY(this.pos.y);
		this.field.setWidth(this.pos.w);
		this.field.setHeight(this.pos.h);
  }
  
  public String getContent() {
    this.content = new String(this.field.getText());
    return this.content;
  }

  public void setText(String text) {
    this.field.setText(text);
  }

  public boolean isEmpty() {
		System.out.println("isempty");
		if (this.field.getText() == null) {
			System.out.println("empty");
			return true;
		}
		else {
			System.out.println("not empty");
			return false;
		}
  }
  
  public TextField getField() { return this.field; }

  @Override
  public void clear() {
    this.setText("");
  }
}