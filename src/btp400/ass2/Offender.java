package btp400.ass2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;

public class Offender {
	
	private int number;
	private String race;
	private String raceGrouping;
	private String gender;
	private int age;
	private String incarcerated;
	private String supervisorType;
	private String sentenceType;
	private int aggSentenceLength;
	private String province;
	private String maritalStatus;
	private String religion;
	/**
	 * Constructor sets the members of the offender object
	 */
	public Offender(int number, String race, String raceGrouping, String gender, int age, String incarcerated, 
			String supervisorType, String sentenceType, int aggSentenceLength, String province, String maritalStatus,
			String religion) {
		this.number = number;
		this.race = race;
		this.raceGrouping = raceGrouping;
		this.gender = gender;
		this.age = age;
		this.incarcerated = incarcerated;
		this.supervisorType = supervisorType;
		this.sentenceType = sentenceType;
		this.aggSentenceLength = aggSentenceLength;
		this.province = province;
		this.maritalStatus = maritalStatus;
		this.religion = religion;
	}
	
	/**
	 * creates an offender and sets the metadata to the member
	 * @param metadata array of strings
	 * @return a new offender object with the provided metadata
	 */
	public static Offender createOffender(String[] metadata) {
		
		int number = Integer.parseInt(metadata[0]);
		String race = metadata[1];
		String raceGrouping = metadata[2];
		String gender = metadata[3];
		int age = Integer.parseInt(metadata[4]);
		String incarcerated = metadata[5];
		String supervisorType = metadata[6];
		String sentenceType = metadata[7];
		int aggSentenceLength = Integer.parseInt(metadata[8]);
		String province = metadata[9];
		String maritalStatus = metadata[10];
		String religion;
		try {
			religion = metadata[11];
		} catch(Exception e) {
			religion = "NONE";
		}
		
		return new Offender(number, race, raceGrouping, gender, age, incarcerated, supervisorType, sentenceType,
				aggSentenceLength, province, maritalStatus, religion);
	}
	/**
	 * 
	 * @return number of the offender
	 */
	public int getNumber() {
		return number;
	}
	/**
	 * 
	 * @return race of the offender
	 */
	public String getRace() {
		return race;
	}
	/**
	 * 
	 * @return race grouping of the offender
	 */
	public String getRaceGrouping() {
		return raceGrouping;
	}
	/**
	 * 
	 * @return gender of the offender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * 
	 * @return of the offender
	 */
	public int getAge() {
		return age;
	}
	/**
	 * 
	 * @return incarceration type of the offender
	 */
	public String getIncarcerated() {
		return incarcerated;
	}
	/**
	 * 
	 * @return supervisor type of the offender
	 */
	public String getSupervisorType() {
		return supervisorType;
	}
	/**
	 * 
	 * @return sentence type of the offender
	 */
	public String getSentenceType() {
		return sentenceType;
	}
	/**
	 * 
	 * @return sentence length of the offender
	 */
	public int getAggSentenceLength() {
		return aggSentenceLength;
	}
	/**
	 * 
	 * @return province of the offender
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * 
	 * @return maritial status of the offender
	 */
	public String getMaritalStatus() {
		return maritalStatus;
	}
	/**
	 * 
	 * @return religion of the offender
	 */
	public String getReligion() {
		return religion;
	}
	/**
	 * Prints the offender information to console
	 */
	public void printOffender() {
		System.out.println("Number: " + getNumber());
		System.out.println("Race: " + getRace());
		System.out.println("Race Grouping: " + getRaceGrouping());
		System.out.println("Gender: " + getGender());
		System.out.println("Age: " + getAge());
		System.out.println("Incarcerated: " + getIncarcerated());
		System.out.println("Supervisor Type: " + getSupervisorType());
		System.out.println("Sentence Type: " + getSentenceType());
		System.out.println("Aggergate Sentence Length: " + getAggSentenceLength());
		System.out.println("Province: " + getProvince());
		System.out.println("Marital Status: " + getMaritalStatus());
		System.out.println("Religion: "+ getReligion());
		System.out.println();
	}
	/**
	 * reads from the csv file, creates the offenders and saves it into an array
	 * @return array of offenders
	 */
	public static Offender[] readArrayCSV() {
		Offender[] offenders = new Offender[22891];
		Path file = Paths.get("Data.csv");
		int count = 0;
		try (BufferedReader br = Files.newBufferedReader(file,
                StandardCharsets.US_ASCII)) {

            String line = br.readLine();

            while (line != null) {

                String[] attributes = line.split(",");
                
                Offender offender = Offender.createOffender(attributes);

                offenders[count] = offender;
                count++;

                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

		return offenders;
	}
	/**
	 * reads from the csv file, creates the offenders and saves it into an arrayList
	 * @return arrayList of offenders
	 */
	public static ArrayList<Offender> readArrayListCSV() {
		ArrayList<Offender> offenders = new ArrayList<Offender>();
		Path pathToFile = Paths.get("Data.csv");
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
			String line = br.readLine();
			
			while (line != null) {
				String[] attributes = line.split(",");
				
				Offender offender = Offender.createOffender(attributes);
				
				offenders.add(offender);
				
				line = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return offenders;
	}
	/**
	 * reads from the csv file, creates the offenders and saves it into an LinkedList
	 * @return LinkedList of offenders
	 */
	public static LinkedList<Offender> readLinkedListCSV() {
		LinkedList<Offender> offenders = new LinkedList<Offender>();
		Path pathToFile = Paths.get("Data.csv");
		
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
			String line = br.readLine();
			
			while (line != null) {
				String[] attributes = line.split(",");
				
				Offender offender = Offender.createOffender(attributes);
				
				offenders.add(offender);
				
				line = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return offenders;
	}

	/*
	@Override
	public int compareTo(Integer o) {
		return Integer.compare(this.getAge(), o);
	}*/
	
}
