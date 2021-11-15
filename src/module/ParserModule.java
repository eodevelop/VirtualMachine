package module;

import java.util.List;

import enums.ArithmeticCommand;

public class ParserModule {
	private List<String> lines;
	private String currentLine;
	private CodeWriterModule codeModule = new CodeWriterModule();
	
	public ParserModule(List<String> lines) {
		this.lines = lines;
	}
	
	public String parse() {
		
		return "";
	}
	
	private ArithmeticCommand commandType() {
		
		return ArithmeticCommand.C_ARITHMETIC;
	}
	
	private String arg1() {
		
		return "";
	}
	
	private String arg2() {
		
		return "";
	}
}
