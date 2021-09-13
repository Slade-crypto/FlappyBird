package com.cursoandroid.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

import javax.xml.soap.Text;

public class Jogo extends ApplicationAdapter {

    //Texturas
    private SpriteBatch batch;
    private Texture[] passaros;
    private Texture fundo;
    private Texture canoBaixo;
    private Texture canoTopo;

    //Atributos de configurações
    private float larguraDispositivo;
    private float alturaDispositivo;
    private float variacao = 0;
    private float gravidade = 0;
    private float posicaoInicialVerticalPassaro = 0;
    private float posicaoCanoHorizontal;
    private float posicaoCanoVertical;
    private float espacoEntreCanos;
    private Random random;
    private int pontos=0;
    private boolean passouCano = false;

    //Exibição de fontes
    BitmapFont textoPontuacao;

    @Override
    public void create() {
//		Gdx.app.log("create", "jogo iniciado");
        inicializarTexturas();
        inicializaObjetos();
    }

    @Override
    public void render() {
//		Gdx.app.log("render", "jogo renderizado");
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        verificaEstadoJogo();
        validarPontos();
        desenharTexturas();

//		gravidade = gravidade + 2;

    }

    private void verificaEstadoJogo() {
//      Movimentar o cano
        posicaoCanoHorizontal -= Gdx.graphics.getDeltaTime() * 200;
        if(posicaoCanoHorizontal < -canoTopo.getWidth()){
            posicaoCanoHorizontal = larguraDispositivo;
            posicaoCanoVertical = random.nextInt(400) - 200;
            passouCano = false;
        }

//		Aplica evento de clique na tela
        boolean toqueTela = Gdx.input.justTouched();
        if (toqueTela) {
            gravidade = -20;
        }

//		Aplica gravidade no passaro
        if (posicaoInicialVerticalPassaro > 0 || toqueTela)
            posicaoInicialVerticalPassaro = posicaoInicialVerticalPassaro - gravidade;


        variacao += Gdx.graphics.getDeltaTime() * 10;
        //		Veifica variação para bater asas do pássaro
        if (variacao > 3)
            variacao = 0;
        gravidade++;

    }

    private void desenharTexturas() {
        batch.begin();
        batch.draw(fundo, 0, 0, larguraDispositivo, alturaDispositivo);
        batch.draw(passaros[(int) variacao], 50, posicaoInicialVerticalPassaro);
        batch.draw(canoBaixo, posicaoCanoHorizontal, alturaDispositivo / 2 - canoBaixo.getHeight() - espacoEntreCanos/2 + posicaoCanoVertical);
        batch.draw(canoTopo, posicaoCanoHorizontal, alturaDispositivo / 2 + espacoEntreCanos/2 + posicaoCanoVertical);
        textoPontuacao.draw(batch, String.valueOf(pontos), larguraDispositivo/2, alturaDispositivo - 110);
        batch.end();
    }

    public void validarPontos(){
        if(posicaoCanoHorizontal < 50-passaros[0].getWidth()){//Passou do posicao passaro
            if(!passouCano){
                pontos++;
                passouCano = true;
            }
        }
    }

    private void inicializarTexturas() {
        passaros = new Texture[3];
        passaros[0] = new Texture("passaro1.png");
        passaros[1] = new Texture("passaro2.png");
        passaros[2] = new Texture("passaro3.png");

        fundo = new Texture("fundo.png");
        canoBaixo = new Texture("cano_baixo_maior.png");
        canoTopo = new Texture("cano_topo_maior.png");
    }

    private void inicializaObjetos() {
        batch = new SpriteBatch();
        random = new Random();

        larguraDispositivo = Gdx.graphics.getWidth();
        alturaDispositivo = Gdx.graphics.getHeight();
        posicaoInicialVerticalPassaro = alturaDispositivo / 2;
        posicaoCanoHorizontal = larguraDispositivo;
        espacoEntreCanos = 150;

        //Configurações dos textos
        textoPontuacao = new BitmapFont();
        textoPontuacao.setColor(Color.WHITE);
        textoPontuacao.getData().setScale(10);
    }

    @Override
    public void dispose() {
    }
}
