package it.unibo.es1;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;


public class GUI extends JFrame{
	
	final List<JButton> jbs = new ArrayList<>();

	public GUI(int size){
		final Logics logics = new LogicsImpl(size);
		this.setSize(500, 200);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new FlowLayout());
		
		ActionListener ac = e -> {
			final JButton buttonClicked = (JButton)e.getSource();
			final int buttonPosition = jbs.indexOf(buttonClicked);
			buttonClicked.setText(String.valueOf(logics.hit(buttonPosition)));
			buttonClicked.setEnabled(logics.enablings().get(buttonPosition));
			if (logics.toQuit()) {
				System.exit(0);
			}
		};
		logics.values().forEach(v -> {
			JButton jb = new JButton(String.valueOf(v));
			jbs.add(jb);
			jb.addActionListener(ac);
			this.getContentPane().add(jb);
		});
		final JButton ok = new JButton("Print");
		this.getContentPane().add(ok);
		ok.addActionListener(e -> System.out.println(logics.result()));
		
		this.setVisible(true);
	}

}
