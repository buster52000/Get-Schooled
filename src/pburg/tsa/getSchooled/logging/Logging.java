package pburg.tsa.getSchooled.logging;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Calendar;

import javax.swing.JTextArea;

public class Logging extends PrintStream {

	private static String logFileName = "Logging.#.log";
	private static String logPrefix = "Logging";

	private static Logging logOut, logErr;
	private static PrintStream printErr;

	private PrintStream printOut;
	private boolean err, printinfo;

	private static LoggingOutputStream los;

	private Logging(LoggingOutputStream los, boolean err)
			throws FileNotFoundException {
		super(los);
		this.err = err;
		printinfo = true;
		printErr = System.err;
		printOut = System.out;
	}

	public static void setUpOutput(JTextArea area, String logPrefix) {
		try {
			logFileName = logPrefix + ".#.log";
			Logging.logPrefix = logPrefix;
			los = new LoggingOutputStream(area, System.out, logFileName);
			logOut = new Logging(los, false);
			logErr = new Logging(los, true);
			PrintStream out = logOut;
			PrintStream err = logErr;
			System.setOut(out);
			System.setErr(err);
		} catch (IOException e) {
			e.printStackTrace(printErr);
		}
	}

	public static void shutdownLogger() {
		try {
			if (logOut != null) {
				logOut.close();
				logOut = null;
			}
			if (logErr != null) {
				logErr.close();
				logErr = null;
			}
			if (los != null) {
				los.close();
				los = null;
			}
		} catch (IOException e) {
			e.printStackTrace(printErr);
		}
	}

	private void write(String s) {
		if (printinfo) {
			Calendar cal = Calendar.getInstance();
			String info = logPrefix + "_";
			info += (cal.get(Calendar.MONTH) + 1) + "-"
					+ cal.get(Calendar.DAY_OF_MONTH) + "-"
					+ cal.get(Calendar.YEAR) + "_";
			info += cal.get(Calendar.HOUR_OF_DAY) + ":"
					+ (cal.get(Calendar.MINUTE) < 10 ? "0" : "")
					+ cal.get(Calendar.MINUTE) + ":"
					+ (cal.get(Calendar.SECOND) < 10 ? "0" : "")
					+ cal.get(Calendar.SECOND) + "_";
			if (err)
				info += "[STDERR] ";
			else
				info += "[STDOUT] ";
			s = info + s;
			printinfo = false;
		}
		try {
			los.write(s.getBytes());
		} catch (IOException e) {
			e.printStackTrace(printErr);
		}
	}

	private void newLine() {
		write("\n");
		try {
			los.flush();
		} catch (IOException e) {
			e.printStackTrace(printErr);
		}
		printinfo = true;
	}

	@Override
	public void print(boolean b) {
		write(b ? "true" : "false");
	}

	@Override
	public void print(char c) {
		write(String.valueOf(c));
	}

	@Override
	public void print(int i) {
		write(String.valueOf(i));
	}

	@Override
	public void print(long l) {
		write(String.valueOf(l));
	}

	@Override
	public void print(float f) {
		write(String.valueOf(f));
	}

	@Override
	public void print(double d) {
		write(String.valueOf(d));
	}

	@Override
	public void print(char s[]) {
		write(String.valueOf(s));
	}

	@Override
	public void print(String s) {
		if (s == null) {
			s = "null";
		}
		write(s);
	}

	@Override
	public void print(Object obj) {
		write(String.valueOf(obj));
	}

	@Override
	public void println() {
		newLine();
	}

	@Override
	public void println(boolean x) {
		print(x);
		newLine();
	}

	@Override
	public void println(char x) {
		print(x);
		newLine();
	}

	@Override
	public void println(int x) {
		print(x);
		newLine();
	}

	@Override
	public void println(long x) {
		print(x);
		newLine();
	}

	@Override
	public void println(float x) {
		print(x);
		newLine();
	}

	@Override
	public void println(double x) {
		print(x);
		newLine();
	}

	@Override
	public void println(char x[]) {
		print(x);
		newLine();
	}

	@Override
	public void println(String x) {
		print(x);
		newLine();
	}

	@Override
	public void println(Object x) {
		print(x);
		newLine();
	}

	@Override
	public void close() {
		if (err)
			System.setErr(printErr);
		else
			System.setOut(printOut);
	}

}
