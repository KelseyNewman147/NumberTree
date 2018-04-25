package com.kelsey.NumberTree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;

@SpringBootApplication
public class NumberTreeApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(NumberTreeApplication.class, args);
		JFrame frame = ctx.getBean(JFrame.class);
		frame.setVisible(true);

	}
}
