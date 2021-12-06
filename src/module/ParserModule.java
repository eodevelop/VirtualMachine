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
		for (String line : this.lines) {
			currentLine = line.trim();
		
		}
		
		return "";
	}
	
	private ArithmeticCommand commandType(String commandStr) {
		if(commandStr.length() == 0) {
			return ArithmeticCommand.C_EMPTY;
		}
		
		if (commandStr.length() > 1 && "//".equals(commandStr.substring(0, 2))) {
			return ArithmeticCommand.C_EMPTY;
		}
		
		String command = commandStr.split(" ")[0];
		
		switch (command) {
		case "push":
			return ArithmeticCommand.C_PUSH;
		case "pop":
			return ArithmeticCommand.C_POP;
		case "label":
			return ArithmeticCommand.C_LABEL;
		case "goto":
			return ArithmeticCommand.C_GOTO;
		case "if":
			return ArithmeticCommand.C_IF;
		case "function":
			return ArithmeticCommand.C_FUNCTION;
		case "return":
			return ArithmeticCommand.C_RETURN;
		case "call":
			return ArithmeticCommand.C_CALL;
		default:
			return ArithmeticCommand.C_ARITHMETIC;
		}		
	}
	
	private String arg1(String commandStr) {
		String command = commandStr.split(" ").length > 1 ? commandStr.split(" ")[1] : "";
		return command;
	}
	
	private String arg2(String commandStr) {
		String command = commandStr.split(" ").length > 2 ? commandStr.split(" ")[2] : "";
		return command;
	}
}
