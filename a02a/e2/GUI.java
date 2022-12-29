package a02a.e2;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private Map<JButton,Pair<Integer, Integer>> buttons = new HashMap<>();
    private int pos = 0;
    private int size;
    private boolean reduce = true;
    
    public GUI(int size) {
        this.size=size;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 500);
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(BorderLayout.CENTER,panel);
        JButton south = new JButton(">");
        this.getContentPane().add(BorderLayout.SOUTH,south);
        south.addActionListener(e -> {
            click();
            updateView();
        });
        ActionListener al = (e)->{
            var p = buttons.get(e.getSource());
            if(getPositions().contains(p)) {
                System.exit(0);
            }
        };

        for (int i=0;i<size;i++){
            for (int j=0;j<size;j++){
                final JButton jb = new JButton();
                buttons.put(jb,new Pair<>(i,j));
                jb.addActionListener(al);
                panel.add(jb);
            }
        }   
        updateView();
        this.setVisible(true);
    }

    private void click() {
        if(pos==size/2) {
            reduce=false;
        } else if(pos==0) {
            reduce=true;
        }
        this.pos = this.pos + (this.reduce ? 1 : -1);
    }

    private void updateView() {
        buttons.keySet().forEach(v -> v.setText(""));
        buttons.forEach((k,v) -> {
            if(getPositions().contains(v)) {
                k.setText("X");
            }
        });
    }


    public Set<Pair<Integer, Integer>> getPositions() {
        return Stream.of(new Pair<>(pos,pos), new Pair<>(size/2,pos))
                .flatMap(p -> Stream.of(p, new Pair<>(size-1-p.getX(), size-1-p.getY())))
                .flatMap(p -> Stream.of(p, new Pair<>(size-1-p.getY(),p.getX())))
                .collect(Collectors.toSet());
    }
}
