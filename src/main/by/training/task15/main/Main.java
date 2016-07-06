package by.training.task15.main;

import java.io.IOException;

import by.training.task15.controller.Controller;

public class Main {

		public static void main(String[] args) throws IOException, InterruptedException{
			Controller controller =new Controller();
			controller.count(DIRECTORY_NAME);
		}
}
