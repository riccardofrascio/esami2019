package a02b.e2;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private Map<JButton, Pair<Integer, Integer>> buttons = new HashMap<>();    
    private Set<Pair<Integer, Integer>> clickedPos = new HashSet<>();
    private int size;
    private int count=1;
    
    public GUI(int size) {
        this.size=size;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 500);
        JPanel panel = new JPanel(new GridLayout(size,size));
        final JButton south = new JButton(">");
        south.addActionListener(e -> {
            System.out.println(clickedPos);
            click();
            updateView();
        });
        this.getContentPane().add(BorderLayout.CENTER,panel);
        this.getContentPane().add(BorderLayout.SOUTH,south);
        ActionListener al = (e)->{
            final JButton bt = (JButton)e.getSource();
            if(!clickedPos.contains((buttons.get(bt)))) {
                clickedPos.add(buttons.get(bt));
            }
            bt.setText("X");
        };
        for (int i=0;i<size;i++){
            for (int j = 0; j < size; j++) {
            final JButton jb = new JButton("");
            buttons.put(jb,new Pair<>(j, i));
            jb.addActionListener(al);
            panel.add(jb);
            }
        } 
        this.setVisible(true);
    }

    private void click() {
        int lastRow = (int)clickedPos.stream()
                .filter(v -> v.getY()==size-1)
                .count();
        int firstRow = (int)clickedPos.stream()
        .filter(v -> v.getY()== 0)
        .count();
        if(lastRow > 0) {
            count=-1;
        } else if(firstRow > 0) {
            count=1;
        }
        if(lastRow>0 && firstRow>0) {
            count=0;
        }
    }

    private Set<Pair<Integer, Integer>> compute() {
        return clickedPos.stream()
                .map(v -> v=new Pair<>(v.getX(),v.getY()+count))
                .collect(Collectors.toSet());
    }

    private void updateView() {
        clickedPos=compute();
        buttons.forEach((k,v) ->k.setText(clickedPos.contains(v) ? "X" : ""));
    }



}
