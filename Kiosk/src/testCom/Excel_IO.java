package testCom;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Excel_IO {
	static String wpath = "C:\\Users\\Limgiwon\\Desktop\\Menu.csv";
	static String rpath = "C:\\Users\\Limgiwon\\Desktop\\Menu.csv";
}

class Write {
	BufferedWriter bw = null;

	public Write() {
	}
	public void write() {
		try {
			bw = Files.newBufferedWriter(Paths.get(Excel_IO.wpath));
			final String line[] = {""};
			
			for(String str : line) {
				bw.write(str);;
				bw.newLine();
			}
		} catch (FileNotFoundException fnfe) {
			System.out.println("파일을 찾을 수 없음");
		} catch (IOException ioe) {
			System.out.println("파일 입출력 오류");
		} finally {
			try {
				bw.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		  }
	}
}

class Read {
	BufferedReader br = null;
	public Read() {};
	
	public void read() {
		try {
			
			br = Files.newBufferedReader(Paths.get(Excel_IO.rpath));
			Charset.forName("UTF-8");
			String line = "";
		} catch (FileNotFoundException fnfe) {
			System.out.println("파일을 찾을 수 없음");
		} catch (IOException ioe) {
			System.out.println("파일 입출력 오류");
		}
		
	}
}