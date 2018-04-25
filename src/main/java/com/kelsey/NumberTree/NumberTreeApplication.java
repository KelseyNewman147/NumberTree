package com.kelsey.NumberTree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.swing.*;

@SpringBootApplication
public class NumberTreeApplication {

	@Bean
	public static JFrame frame() {
		JFrame frame = new JFrame("HeadlessExceptionDemo");
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return frame;
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(NumberTreeApplication.class, args);
		JFrame frame = ctx.getBean(JFrame.class);
		frame.setVisible(true);

	}
}
