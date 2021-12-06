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
				file.getName().contains(".vm");
				List<String> lines = fileUtils.getLine(directoryNm);
				
				ParserModule parserModule = new ParserModule(lines);
//			String hackCode = parserModule.assemble();
//			fileUtils.writeFile(fileNm.split("[.]")[0] + ".asm" , hackCode);				
			}
			
			
		}
	}
}
