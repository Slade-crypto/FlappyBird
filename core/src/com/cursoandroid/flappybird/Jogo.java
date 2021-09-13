package com.cursoandroid.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import javax.xml.soap.Text;

public class Jogo extends ApplicationAdapter {

	private SpriteBatch batch;
	private Texture[] passaros;
	private Texture fundo;
	private int movimentoX = 0;
	private int movimentoY = 0;

	//Atributos de configurações
	private float larguraDispositivo;
	private float alturaDispositivo;
	private float variacao = 0;

	@Override
	public void create () {
//		Gdx.app.log("create", "jogo iniciado");
		batch = new SpriteBatch();
		passaros = new Texture[3];
		passaros[0] = new Texture("passaro1.png");
		passaros[1] = new Texture("passaro2.png");
		passaros[2] = new Texture("passaro3.png");

		fundo = new Texture("fundo.png");

		larguraDispositivo = Gdx.graphics.getWidth();
		alturaDispositivo =  Gdx.graphics.getHeight();
	}

	@Override
	public void render () {
//		Gdx.app.log("render", "jogo renderizado");
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		if(variacao > 3)
			variacao = 0;

		batch.draw(fundo, 0, 0, larguraDispositivo, alturaDispositivo);
		batch.draw(passaros[(int) variacao], 30, alturaDispositivo / 2);

		variacao += Gdx.graphics.getDeltaTime() * 10;
		movimentoX ++;
		movimentoY ++;
		batch.end();

	}
	
	@Override
	public void dispose () {
	}
}
