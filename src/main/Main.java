package main;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import module.ParserModule;
import util.FileUtils;

public class Main {

	public static void main(String[] args) {
		while(true) {
			Scanner scan = new Scanner(System.in);

			System.out.print("폴더명 : ");
			String directoryNm = scan.nextLine();
			
			if("exit".equals(directoryNm)) {
				return;
			}
			
			FileUtils fileUtils = new FileUtils();
			
			File directory = new File(directoryNm);
			
			for(File file : directory.listFiles()) {
				if(file.getName().contains(".vm")) {
					List<String> lines = fileUtils.getLine(file.getName());
					String fileName = file.getName().split("[.]")[0];
					ParserModule parserModule = new ParserModule(lines, fileName);
					String asm = parserModule.parse();
					fileUtils.writeFile(fileName + ".asm" , asm);
				}
			}	
		}
	}
}
