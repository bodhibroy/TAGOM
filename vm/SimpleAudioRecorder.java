

/*
 *  *   SimpleAudioRecorder.java
 *   *
 *    * This file is part of jsresources.org
 *     */


/*
 * Copyright (c) 1999 - 2003 by Matthias Pfisterer
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * - Redistributions of source code must retain the above copyright notice,
 *   this list of conditions and the following disclaimer.
 * - Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * |<---            this code is formatted to fit into 80 columns             --->|
 * http://www.jsresources.org/examples/SimpleAudioRecorder.html
 * 
 * 
 * Modified by Bodhi
 * */


import java.io.IOException;
import java.io.File;

import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.AudioFileFormat;


public class SimpleAudioRecorder
extends Thread
{
	private TargetDataLine m_line;
	private AudioFileFormat.Type m_targetType;
	private AudioInputStream m_audioInputStream;
	private File m_outputFile;

	private SimpleAudioRecorder recorder;


	private SimpleAudioRecorder(TargetDataLine line,AudioFileFormat.Type targetType,File file)
	{
		m_line = line;
		m_audioInputStream = new AudioInputStream(line);
		m_targetType = targetType;
		m_outputFile = file;
	}

	public SimpleAudioRecorder()
	{
		String  strFilename = "./bin/recordedVoiceFromMIC.wav";
		File    outputFile = new File(strFilename);

		AudioFormat audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,44100.0F, 16, 2, 4, 44100.0F, false);
		DataLine.Info   info = new DataLine.Info(TargetDataLine.class, audioFormat);
		TargetDataLine  targetDataLine = null;
		try
		{
			targetDataLine = (TargetDataLine) AudioSystem.getLine(info);
			targetDataLine.open(audioFormat);
		}
		catch (LineUnavailableException e)
		{
			out("unable to get a recording line");
			e.printStackTrace();
			System.exit(1);
		}

		AudioFileFormat.Type    targetType = AudioFileFormat.Type.WAVE;

		recorder = new SimpleAudioRecorder(targetDataLine,targetType,outputFile);
	}

	public void start()
	{
		m_line.start();
		super.start();
	}


	public void stopRecording()
	{
		m_line.stop();
		m_line.close();
	}




	public void run()
	{
		try
		{
			AudioSystem.write(m_audioInputStream,m_targetType,m_outputFile);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void RunAudioRecorder()
	{
		out("Recording started.");
		recorder.start();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		recorder.stopRecording();
		out("Recording stopped.");
	}

	private void out(String strMessage)
	{
		System.out.println(strMessage);
	}
}



