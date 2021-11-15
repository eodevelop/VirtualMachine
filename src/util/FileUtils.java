package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtils {
	public List<String> getLine(String strPath) {
		Path path = Paths.get(strPath);

		try {
			Stream<String> linesStream = Files.lines(path);
			List<String> lines = linesStream.collect(Collectors.toList());

			return lines;
		} catch (IOException e) {

			return null;
		}
	}

	public void writeFile(String fileName, String writeStr) {
		try {
			File file = new File(fileName);
			file.delete();
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(writeStr);
			file.createNewFile();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
