package main;

import java.util.List;
import java.util.Scanner;

import module.ParserModule;
import util.FileUtils;

public class Main {

	public static void main(String[] args) {
		while(true) {
			Scanner scan = new Scanner(System.in);

			System.out.print("파일명 : ");
			String fileNm = scan.nextLine();
			
			if("exit".equals(fileNm)) {
				return;
			}
			
			FileUtils fileUtils = new FileUtils();
			
			List<String> lines = fileUtils.getLine(fileNm);
			
//			ParserModule parserModule = new ParserModule(lines);
//			String hackCode = parserModule.assemble();
//			fileUtils.writeFile(fileNm.split("[.]")[0] + ".asm" , hackCode);
		}
	}
}
