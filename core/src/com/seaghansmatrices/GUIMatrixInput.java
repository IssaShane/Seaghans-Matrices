package com.seaghansmatrices;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class GUIMatrixInput extends GUIElement {
  int cellh;
  int cellw;
  Skin skin;
  GUIContainerColumn contents; // column of rows

  public GUIMatrixInput(Skin skin, int cellw, int cellh) {
    this.skin = skin;
    this.cellw = cellw;
    this.cellh = cellh;
    System.out.println("cellw, cellh: " + Integer.toString(this.cellw) + "," + Integer.toString(this.cellh));
    this.contents = new GUIContainerColumn(new GUILocation(Alignment.CENTRE, Alignment.CENTRE));
  }

  public void calibrateLocations() {
    this.contents.calibrateLocations();
  }

  // input is the matrix in [a b; c d] form
  public void setContent(String input, Stage stage) {
    this.contents.clear();
    BashWrapper libvec = new BashWrapper("frac", "./libvecwrap.sh");
    String dim = new String(libvec.runCommand("matrixdim " + input));
    dim = dim.replace("\n", "");
    String firstrow = new String(libvec.runCommand("matrixgetrow " + input + " 0"));
    firstrow += ";";
    String rowdim = new String(libvec.runCommand("vecdim " + firstrow));
    rowdim = rowdim.replace("\n", "");
    System.out.println("rowdim: " + rowdim);
    for (int i = 0; i < Integer.parseInt(dim); i++) {
      GUIContainer row = new GUIContainerRow(new GUILocation(Alignment.CENTRE, Alignment.CENTRE));
      for (int j = 0; j < Integer.parseInt(rowdim); j++) {
        System.out.println("(i,j): " + Integer.toString(i) + "," + Integer.toString(j));
        String value = new String(libvec.runCommand("matrixgetelem " + input + " " + Integer.toString(i) + " " + Integer.toString(j)));
        value = value.replace("\n", "");
        //System.out.println("value: " + value);
        row.addElement(new TextLabel(value, this.skin, new Posn(0,0,this.cellw, this.cellh)));
      }
      row.adjustToFit();
      this.contents.addElement(row);
    }
    this.contents.adjustToFit();
    this.calibrateLocations();
    this.contents.addToStage(stage);
  }

  @Override
  public void setPos(Posn pos) {
    this.contents.setPos(pos);
  }

  @Override
  public Posn getPos() {
    return this.contents.getPos();
  }

  @Override
  public void addToStage(Stage stage) {
    this.contents.addToStage(stage);
  }

  @Override 
  public void clear() {
    this.contents.clear();
  }
}