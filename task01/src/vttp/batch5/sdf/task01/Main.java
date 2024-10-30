package vttp.batch5.sdf.task01;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import vttp.batch5.sdf.task01.models.BikeEntry;

// Use this class as the entry point of your program

public class Main {

	public static <BikeEntry> void main(String[] args, String line) throws IOException, FileNotFoundException{
			List<BikeEntry> bikeEntries = new ArrayList<>();
			try (BufferedReader br = new BufferedReader(new FileReader("day.csv"))){

				//skipping the header
				String header = br.readLine();

				//formatting the date
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-DD");

				//while loop is to read each line in the csv file
				while ((line = br.readLine()) !=null) {
					String[] data = line.split(",");

					if (data.length ==8) {
						try{
							LocalDate date = LocalDate.parse(data[0], formatter);

							int season = Integer.parseInt(data[1]);
							int month = Integer.parseInt(data[2]);
							int weekday = Integer.parseInt(data[3]);
							int total = Integer.parseInt(data[4]);
							float temperature = Float.parseFloat(data[5]);
							int humidity = Integer.parseInt(data[6]);

							bikeEntries.add(new BikeEntry(
								season,
								date.toString(),
								month,
								weekday,
								total,
								temperature,
								humidity
							));
						} catch (NumberFormatException e) {
							System.err.println("invalid data in line" + line);
						}
					} else {
						System.err.println("Line skipped due to error in number of field" + line);
					}
					
				} 
			}catch (IOException e){
					e.printStackTrace();
				}

				

			
		}

}
