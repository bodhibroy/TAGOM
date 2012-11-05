import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Analysis {

	/**
	 * @param args
	 * @author Bodhi
	 */

/*	private static void out(String strMessage)
	{
		System.out.println(strMessage);
	}*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		1. DONE randomly play a file from src/wav_data folder
		2. DONE record an audio, asking the user to sing in the same pitch
		3. DONE calculate the pitch of the recorded tone
		4. DONE match the pitch with the corresponding kv-pair
		5. DONE Give the user feedback if s/he needs to sing higher/lower or if
		   the singing is perfect


		 DONE :: write a script to generate HM put data 
		 */

		HashMap<String, Float>hm= new HashMap<String, Float>();
		// notes and their fundamental frequencies in HashMap
		hm.put("C3",233.0819f);
		hm.put("C#3",220.0001f);
		hm.put("D3",466.164f);
		hm.put("D#3",440.0f);
		hm.put("E3",932.328f);
		hm.put("F3",880.0003f);
		hm.put("F#3",123.4709f);
		hm.put("G3",246.9417f);
		hm.put("G#3",493.8835f);
		hm.put("A3",987.7669f);
		hm.put("A#3",138.5914f);
		hm.put("B3",130.8128f);
		hm.put("C4",277.1826f);
		hm.put("C#4",261.6256f);
		hm.put("D4",554.3654f);
		hm.put("D#4",523.2512f);
		hm.put("E4",155.5635f);
		hm.put("F4",146.8324f);
		hm.put("F#4",311.1271f);
		hm.put("G4",293.6649f);
		hm.put("G#4",622.2542f);
		hm.put("A4",587.3297f);
		hm.put("A#4",164.8138f);
		hm.put("B4",329.6277f);
		hm.put("C5",659.2552f);
		hm.put("C#5",184.9973f);
		hm.put("D5",174.6142f);
		hm.put("D#5",369.9945f);
		hm.put("E5",349.2283f);
		hm.put("F5",739.9891f);
		hm.put("F#5",698.4565f);
		hm.put("G5",207.6524f);
		hm.put("G#5",195.9978f);
		hm.put("A5",415.3048f);
		hm.put("A#5",391.9955f);
		hm.put("B5",830.6097f);

		String[] listOfFiles = {"A#3","A3","A#4","A4","A#5","A5","B2","B3","B4","B5","C#3","C3","C#4","C4","C#5","C5","D#3","D3","D#4","D4","D#5","D5","E3","E4","E5","F#3","F3","F#4","F4","F#5","F5","G#3","G3","G#4","G4","G#5","G5"};
		int lengthList= listOfFiles.length;
		Random rnd=new Random();
		int index=rnd.nextInt(lengthList);

		// Display 
		ProgressBar pb=new ProgressBar();

		// playing a random tone from audio_samples using SimpleAudioPlayer 
		SimpleAudioPlayer sap = new SimpleAudioPlayer();
		pb.modifyDisplay("Playing "+listOfFiles[index]+ " tone.");
		sap.RandomAudio(index);
		pb.playProgressAnimation();

		// recording user voice using SimpleAudioRecorder 
		pb.modifyDisplay("Please get ready to record");
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		pb.modifyDisplay("Recording now");
		SimpleAudioRecorder sar = new SimpleAudioRecorder();
		sar.RunAudioRecorder();

		/*try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		// analysing the audio using Targos jar
		//use the modified JAR in VM
		pb.modifyDisplay("Recording stopped. Analysing Recorded Audio now...");
		try {
			String recordedVoicePath = "./bin/recordedVoiceFromMIC.wav";
			Runtime.getRuntime().exec("java -jar ./lib/Tarsos-0.9-Bodhi.jar detect_pitch " + recordedVoicePath);
			// The Targos JAR file was obtained from https://github.com/JorenSix/Tarsos/ 
			//and modified to get the desired output
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// retrieve the pitch of the recorded tone
		float pitchFromRecording=0; 
		try
		{
			String FrequencyFromTargosJAR = "./bin/frequencyCalculatedFromTargos";
			FileInputStream fin = new FileInputStream(FrequencyFromTargosJAR);
			DataInputStream din = new DataInputStream(fin);
			pitchFromRecording = din.readFloat();
			din.close();
		}
		catch(FileNotFoundException fe)
		{
			System.out.println("FileNotFoundException : " + fe);
		}
		catch(IOException ioe)
		{
			System.out.println("IOException : " + ioe);
		}
		
		// Check if the tone is 80% accurate
		if(Float.isNaN(pitchFromRecording)) // if value is NaN, then set to 0
			pitchFromRecording=0;
		String fname = sap.musicfilename;
		float differenceOfFrequency = (pitchFromRecording - hm.get(fname))/hm.get(fname);
		differenceOfFrequency=Rounding.round(differenceOfFrequency,2);
		//System.out.println("tone:"+fname + " "+ hm.get(fname)+ "  "+pitchFromRecording + " "+differenceOfFrequency);
		if(Math.abs(differenceOfFrequency)>0.1)		
		{
			String s= new String();
			if(differenceOfFrequency<0)
				s="You need to sing in a slightly higher pitch.";
			else
				s="You need to sing in a slightly lower pitch.";
			pb.modifyDisplay("Your accuracy was "+ Math.abs(100*(1-Math.abs(differenceOfFrequency)))+"%. " + s + " Please try again.");
			// tell user if s/he needs to sing higher or lower
			
		}
			
		else
			pb.modifyDisplay("Success!");
	}

	 /**
     * Round a float value to a specified number of decimal 
     * places.
     *
     * @param val the value to be rounded.
     * @param places the number of decimal places to round to.
     * @return val rounded to places decimal places.
     */
	
}
