package com.ga.superhero;

import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {


		static ArrayList<Superhero> supers = new ArrayList<>();
		Superhero superhero = new Superhero();
		public static void readFile(File file) throws IOException {

			BufferedReader bf = null;
			try {
				bf = new BufferedReader(new FileReader(file));

				String heroInfo = bf.readLine();

				while(heroInfo != null) {

					Superhero superhero = new Superhero();
					String[] data = heroInfo.split(",");
					superhero.setSuperName(data[0]);
					superhero.setAlterEgo(data[1]);
					superhero.setHomePlanet(data[2]);

					supers.add(superhero);

					heroInfo = bf.readLine();
				}

			}
			finally {
				bf.close();
			}

		}

		public static void writeFile (File file, String result) throws IOException{

			BufferedWriter bufferedWriter = null;

			try{
				bufferedWriter = new BufferedWriter(new FileWriter(file));
				bufferedWriter.write(result);

			} finally {
				bufferedWriter.close();
			}

		}
		public static String game(){
			int correct = 0, wrong =0;

			for(Superhero heroes: supers) {
				Scanner scanner = new Scanner(System.in);

				System.out.println("What is " + heroes.getSuperName() +"'s real name?");
				String name = scanner.nextLine();

				if(heroes.getAlterEgo().equalsIgnoreCase(name)){
					System.out.println("Correct!");
					correct++;
				}
				else{
					System.out.println("Wrong, it's : " + heroes.getAlterEgo());
					wrong++;
				}

				System.out.println("What is " + heroes.getSuperName() + "'s place of birth\"");
				String place = scanner.nextLine();

				if(heroes.getHomePlanet().equalsIgnoreCase(place)){
					System.out.println("Correct!");
					correct++;
				}
				else{
					System.out.println("Wrong, it's : " + heroes.getHomePlanet());
					wrong++;

				}
			}
			System.out.println("You got " + correct + " correct, and " +  wrong + " wrong");

			if(correct > wrong){
				return "You won the game";
			}
			else if(correct < wrong){
				return "You lost the game";
			}
			else{
				return "ehhhhh";
			}
		}
		public static void main(String[] args) {

			File input = new File("input.txt");
			try {
				readFile(input);

			} catch (IOException e) {
				System.out.println("Error while reading the file: " + e.getMessage());
			}

			String gameResult = game();
			File output = new File("output.txt");

			try{
				writeFile(output, gameResult);

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}



