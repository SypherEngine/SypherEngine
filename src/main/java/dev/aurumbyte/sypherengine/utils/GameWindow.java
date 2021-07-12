package dev.aurumbyte.sypherengine.utils;

import dev.aurumbyte.sypherengine.SypherEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class GameWindow {
    private JFrame frame;
    private BufferedImage image;
    private Canvas canvas;
    private BufferStrategy bufferStrategy;
    private Graphics graphics;

    public GameWindow(SypherEngine engine){
        image = new BufferedImage(engine.getWidth(), engine.getHeight(), BufferedImage.TYPE_INT_RGB);
        canvas = new Canvas();
        Dimension dimension = new Dimension((int)(engine.getWidth() * engine.getScale()), (int)(engine.getHeight() * engine.getScale()));
        canvas.setPreferredSize(dimension);
        canvas.setMaximumSize(dimension);
        canvas.setMinimumSize(dimension);

        frame = new JFrame(engine.getTitle());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(canvas, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        graphics = bufferStrategy.getDrawGraphics();
    }

    public void update(){
        graphics.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
        bufferStrategy.show();

    }
}
