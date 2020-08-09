package com.seaghansmatrices;

import java.util.stream.Stream;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.utils.Align;

public class GUITextParagraph extends GUIContainerColumn {
  Skin skin;
  String font;

  public GUITextParagraph() { super(); }
  public GUITextParagraph(String text, GUILocation loc, Skin skin, String font) {
    super(loc);
    this.skin = skin;
    this.font = font;
    this.setContent(text);
  }

  public void setContent(String text) {
    this.contents.clear();
    String[] lines = text.split(System.lineSeparator());
    for (String l : lines) {
      System.out.print("l: ");
      System.out.println(l);
      this.contents.add(new GUITextArea(l, this.skin, this.font));
      this.contents.get(this.contents.size()-1).setPos(new Posn(0,0,100,30));
    }
    Posn newpos = this.getPos();
    if (this.contents.size() > 0) {
      Posn elempos = this.contents.get(0).getPos();
      newpos.h = elempos.h*this.contents.size();
      newpos.h += (this.contents.size()+1)*this.contents.get(0).getPaddingBottom();
    }
    else newpos.h = 0;
    newpos.w = 100;
    System.out.print("newpos.h: ");
    System.out.println(newpos.h);
    this.setPos(newpos);
  }

  public void setContent(String text, Stage stage) {
    this.setContent(text);
    this.addToStage(stage);
    //this.calibrateLocations();
  }
}