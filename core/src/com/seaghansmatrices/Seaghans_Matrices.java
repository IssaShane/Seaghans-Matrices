package com.seaghansmatrices;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Seaghans_Matrices extends ApplicationAdapter {
	Stage stage;
	Skin skin;
	GUITextInputField m1;
	GUITextInputField m2;
	GUIContainer matrices;

	GUIButton calculate;

	GUIMatrixInput result;

	GUIContainer totalview;

	BashWrapper libvec;
	
	@Override
	public void create () {
		libvec = new BashWrapper("frac", "./libvecwrap.sh");

		stage = new Stage();
		skin = new Skin(Gdx.files.internal("uiskin.json"));
		m1 = new GUITextInputField(new Posn(0,0,100,100), skin);
		m2 = new GUITextInputField(new Posn(0,0,100,100), skin);
		m1.setText("[1 2; 2 1]");
		m2.setText("[1 0; 0 1]");
		matrices = new GUIContainerRow(new GUILocation(Alignment.CENTRE, Alignment.CENTRE));
		matrices.addElement(m1);
		matrices.addElement(m2);
		matrices.setPos(new Posn(0,0,1024,100));

		calculate = new GUIButton("Calculate", skin, "default");
		calculate.setPos(new Posn(0,0,100,40));
		calculate.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				String solution = libvec.runCommand("matrixmult " + 
					m1.getContent() + " " + m2.getContent());
				//convert into proper format
				solution = solution.replace("\n", "; ");
				solution = "[" + solution + "]";
				result.setContent(solution, stage);
				totalview.calibrateLocations();
				return true;
			}
		});

		result = new GUIMatrixInput(skin, 30, 30);
		result.setContent("[1 0; 0 1]", stage);
		result.setPos(new Posn(0,0,200,200));

		totalview = new GUIContainerColumn(new GUILocation(Alignment.CENTRE, Alignment.CENTRE));
		totalview.setPos(new Posn(0,0,1024,768));
		totalview.addElement(matrices);
		totalview.addElement(calculate);
		totalview.addElement(result);
		totalview.calibrateLocations();
		totalview.addToStage(stage);
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render () {
		drawRGB(82,82,82);
		stage.act();
		stage.draw();
		
	}
	
	@Override
	public void dispose () {
	}

	public void drawRGB(int r, int g, int b) {
		float R = r, B = b, G = g;
		R /= 255;
		G /= 255;
		B /= 255;
		Gdx.gl.glClearColor(R, G, B, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
}
