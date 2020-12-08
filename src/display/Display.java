package display;

import game.Game;
import game.state.State;
import input.Input;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Display extends JFrame {

    private Canvas canvas;
    private Renderer renderer;
    public Display(int width, int height, Input input){
        setTitle("Covid Hunter");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        this.renderer = new Renderer();
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setFocusable(false);

        add(canvas);
        addKeyListener(input);
        pack();

        canvas.createBufferStrategy(3);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void  render(State state){
        BufferStrategy bs = canvas.getBufferStrategy();
        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0,0, canvas.getWidth(), canvas.getHeight());

        renderer.render(state,g);

        g.dispose();
        bs.show();
    }
}
