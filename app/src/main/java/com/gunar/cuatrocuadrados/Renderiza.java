package com.gunar.cuatrocuadrados;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.view.MotionEvent;

import java.util.Calendar;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class Renderiza extends GLSurfaceView implements GLSurfaceView.Renderer {
    private Rectangulo rectangulo;
    private Rectangulo rectangulo1;
    private Rectangulo rectangulo2;
    private Rectangulo rectangulo3;

    private float angulo;
    private float angulo1;
    private float angulo2;
    private float angulo3;

    private float incAngulo;
    private float incAngulo1;
    private float incAngulo2;
    private float incAngulo3;


    private long inicio, fin, duracion;
    private float tiempo_real;
    private float tiempoRotacion;
    private final float PERIODO_DE_LA_ROTACION = 1f; // en segundos

    private long inicio1, fin1, duracion1;
    private float tiempo_real1;
    private float tiempoRotacion1;
    private final float PERIODO_DE_LA_ROTACION1 = 2f; // en segundos

    private long inicio2, fin2, duracion2;
    private float tiempo_real2;
    private float tiempoRotacion2;
    private final float PERIODO_DE_LA_ROTACION2 = 3f; // en segundos

    private long inicio3, fin3, duracion3;
    private float tiempo_real3;
    private float tiempoRotacion3;
    private final float PERIODO_DE_LA_ROTACION3 = 5f; // en segundos

    private final MainActivity mActivity;

    private int hora, minutos, segundos;
    private Calendar calendario;

    public Renderiza(Context contexto, MainActivity activity) {
        super(contexto);
        /* Actividad Principal */
        this.mActivity = activity;
        /* Inicia el renderizado */
        this.setRenderer(this);
        /* La ventana solicita recibir una entrada */
        this.requestFocus();
        /* Establece que la ventana pueda detectar el modo t�ctil.*/
        this.setFocusableInTouchMode(true);
        /* El renderizado se llama continuamente para renderizar la escena. */
        this.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig arg1) {
        rectangulo = new Rectangulo();
        rectangulo1 = new Rectangulo();
        rectangulo2 = new Rectangulo();
        rectangulo3 = new Rectangulo();

        angulo = incAngulo = 4;
        angulo1 = incAngulo1 = 4;
        angulo2 = incAngulo2 = 4;
        angulo3 = incAngulo3 = 4;

        inicio = System.currentTimeMillis();
        tiempoRotacion = PERIODO_DE_LA_ROTACION;

        inicio1 = System.currentTimeMillis();
        tiempoRotacion1 = PERIODO_DE_LA_ROTACION1;

        inicio2 = System.currentTimeMillis();
        tiempoRotacion2 = PERIODO_DE_LA_ROTACION2;

        inicio3 = System.currentTimeMillis();
        tiempoRotacion3 = PERIODO_DE_LA_ROTACION3;

        calendario = Calendar.getInstance();
        hora = calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND);
        mActivity.actualizaTexto(hora, minutos, segundos);

        gl.glClearColor(1, 1, 1, 0);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        gl.glLoadIdentity();
        gl.glTranslatef(2, 3, 0);
        gl.glRotatef(angulo1, 0, 0, 1);
        rectangulo.dibuja(gl, 1, 0, 0);

        gl.glLoadIdentity();
        gl.glTranslatef(-2, 3, 0);
        gl.glRotatef(angulo, 0, 0, 1);
        rectangulo1.dibuja(gl, 0, 1, 0);

        gl.glLoadIdentity();
        gl.glTranslatef(-2, -3, 0);
        gl.glRotatef(angulo2, 0, 0, 1);
        rectangulo2.dibuja(gl, 0, 0, 1);

        gl.glLoadIdentity();
        gl.glTranslatef(2, -3, 0);
        gl.glRotatef(angulo3, 0, 0, 1);
        rectangulo3.dibuja(gl, 0, 1, 1);

        gl.glFlush();


        fin = System.currentTimeMillis();
        duracion = fin - inicio;
        tiempo_real = duracion / 1000f;
        inicio = fin;
        /* Incrementa y verifica el l�mite del tiempo */
        tiempoRotacion = tiempoRotacion - tiempo_real;

        fin1 = System.currentTimeMillis();
        duracion1 = fin1 - inicio1;
        tiempo_real1 = duracion1 / 1000f;
        inicio1 = fin1;
        /* Incrementa y verifica el l�mite del tiempo */
        tiempoRotacion1 = tiempoRotacion1 - tiempo_real1;

        fin2 = System.currentTimeMillis();
        duracion2 = fin2 - inicio2;
        tiempo_real2 = duracion2 / 1000f;
        inicio2 = fin2;
        /* Incrementa y verifica el l�mite del tiempo */
        tiempoRotacion2 = tiempoRotacion2 - tiempo_real2;

        fin3 = System.currentTimeMillis();
        duracion3 = fin3 - inicio3;
        tiempo_real3 = duracion3 / 1000f;
        inicio3 = fin3;
        /* Incrementa y verifica el l�mite del tiempo */
        tiempoRotacion3 = tiempoRotacion3 - tiempo_real3;


        if (tiempoRotacion < 0.001) {
            tiempoRotacion = PERIODO_DE_LA_ROTACION;
            angulo = angulo + incAngulo;
            if (angulo > 360)
                angulo = angulo - 360;
        }

        if (tiempoRotacion1 < 0.001) {
            tiempoRotacion1 = PERIODO_DE_LA_ROTACION1;
            angulo1 = angulo1 + incAngulo1;
            if (angulo1 > 360)
                angulo1 = angulo1 - 360;
        }
        if (tiempoRotacion2 < 0.001) {
            tiempoRotacion2 = PERIODO_DE_LA_ROTACION2;
            angulo2 = angulo2 + incAngulo2;
            if (angulo2 > 360)
                angulo2 = angulo2 - 360;
        }
        if (tiempoRotacion3 < 0.001) {
            tiempoRotacion3 = PERIODO_DE_LA_ROTACION3;
            angulo3 = angulo3 + incAngulo3;
            if (angulo3 > 360)
                angulo3 = angulo3 - 360;
        }

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int w, int h) {
        gl.glViewport(0, 0, w, h);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        GLU.gluOrtho2D(gl, -4, 4, -6, 6);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    /**
     * Maneja los eventos de la pantalla táctil.
     */
    @Override
    public boolean onTouchEvent(MotionEvent e) {
   /*     if (e.getAction() == MotionEvent.ACTION_DOWN) {
            incAngulo = incAngulo == 0 ? 8 : 0;
            incAngulo1 = incAngulo1 == 0 ? -10 : 0;
        }*/
        return true;
    }
}

