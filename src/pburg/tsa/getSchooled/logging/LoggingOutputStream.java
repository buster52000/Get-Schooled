package pburg.tsa.getSchooled.logging;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JTextArea;

public class LoggingOutputStream extends OutputStream {

	private static PrintStream consoleWrite;
	private JTextArea debugWrite;
	private File logFile;
	private FileWriter fileWriter;

	public LoggingOutputStream(JTextArea area, PrintStream console, String logFileName) throws IOException {
		consoleWrite = console;
		debugWrite = area;
		arrangeLogFiles(logFileName);
		logFile = new File(logFileName.replaceFirst("#", Integer.toString(0)));
		fileWriter = new FileWriter(logFile, true);
	}

	@Override
	public void write(final int b) throws IOException {
		consoleWrite.write(b);
		debugWrite.append(String.valueOf((char) b));
		fileWriter.write(b);
	}

	@Override
	public void write(byte[] b) throws IOException {
		consoleWrite.write(b);
		final String s = new String(b);
		debugWrite.append(s);
		fileWriter.write(s);
	}

	@Override
	public void flush() throws IOException {
		fileWriter.flush();
	}

	@Override
	public void close() throws IOException {
		fileWriter.close();
	}

	private void arrangeLogFiles(String logFileName) {
		for (int i = 3; i >= 0; i--) {
			File file = new File(logFileName.replaceFirst("#", Integer.toString(i)));
			if (file.exists())
				if (i == 3)
					file.delete();
				else
					file.renameTo(new File(logFileName.replaceFirst("#", Integer.toString(++i))));
		}
	}

}
