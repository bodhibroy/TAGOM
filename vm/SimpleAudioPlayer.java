import java.io.*;
import sun.audio.*;

/**
 * A simple Java sound file example (i.e."," Java code to play a sound file).
 * AudioStream and AudioPlayer code comes from a javaworld.com example.
 * @author alvin alexander"," devdaily.com.
 * 
 * Modified by Bodhi
 */

@SuppressWarnings("restriction")


public class SimpleAudioPlayer
{
	public String musicfilename;
	/*public static void main(String[] args) throws IOException 
	{
		// This segment can be uncommented if you want to run the player as a stand-alone application
		// open the sound file as a Java input stream
		String[] listOfFiles = {"A#3","A3","A#4","A4","A#5","A5","B2","B3","B4","B5","C#3","C3","C#4","C4","C#5","C5","D#3","D3","D#4","D4","D#5","D5","E3","E4","E5","F#3","F3","F#4","F4","F#5","F5","G#3","G3","G#4","G4","G#5","G5"};
		int lengthList= listOfFiles.length;
		Random rnd=new Random();
		int index=rnd.nextInt(lengthList); // generates a value between 1 and lengthList 
	    String gongFile = "./src/wavDataSet/"+listOfFiles[index]+".wav"; // choose a random file
	    InputStream in = new FileInputStream(gongFile);
	    // create an audiostream from the inputstream
	    AudioStream audioStream = new AudioStream(in);
	    // play the audio clip with the audioplayer class
	    AudioPlayer.player.start(audioStream);
	}*/
	public void RandomAudio(int index)
	{
		String[] listOfFiles = {"A#3","A3","A#4","A4","A#5","A5","B2","B3","B4","B5","C#3","C3","C#4","C4","C#5","C5","D#3","D3","D#4","D4","D#5","D5","E3","E4","E5","F#3","F3","F#4","F4","F#5","F5","G#3","G3","G#4","G4","G#5","G5"};
		String toneFile = "./src/wavDataSet/"+listOfFiles[index]+".wav"; 
		System.out.println("Playing "+ listOfFiles[index]+ " tone.");
		musicfilename = listOfFiles[index];
		InputStream in = null;
		try {
			in = new FileInputStream(toneFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		AudioStream audioStream = null;
		try {
			audioStream = new AudioStream(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AudioPlayer.player.start(audioStream);
	}
}
