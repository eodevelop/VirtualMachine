package module;

import enums.ArithmeticCommand;

public class CodeWriterModule {
	
	public String getCode(ArithmeticCommand command, String segment, String index) {
		if(command.equals(ArithmeticCommand.C_PUSH) || command.equals(ArithmeticCommand.C_POP)) {
			return writePushPop(command, segment, Integer.parseInt(index));
		} else {
			return writerArithmetic(command.name());
		}
	}
	
	private String writerArithmetic(String command) {
		
		return "";
	}

	private String writePushPop(ArithmeticCommand command, String segment, int index) {
		StringBuilder resStr = new StringBuilder();
		
		if(command.equals(ArithmeticCommand.C_PUSH)) {
			if(segment.equals("constant")) {
				resStr.append("D=A").append(System.getProperty("line.separator"));
			} else {
				resStr.append("D=M").append(System.getProperty("line.separator"));
			}
			resStr.append("@SP").append(System.getProperty("line.separator"));
			resStr.append("A=M").append(System.getProperty("line.separator"));
			resStr.append("M=D").append(System.getProperty("line.separator"));
			resStr.append("@SP").append(System.getProperty("line.separator"));
			resStr.append("M=M+1").append(System.getProperty("line.separator"));
		} else {
            resStr.append("D=A").append(System.getProperty("line.separator"));
            resStr.append("@R13").append(System.getProperty("line.separator"));
            resStr.append("M=D").append(System.getProperty("line.separator"));
            resStr.append("@SP").append(System.getProperty("line.separator"));
            resStr.append("M=M-1").append(System.getProperty("line.separator"));
            resStr.append("A=M").append(System.getProperty("line.separator"));
            resStr.append("D=M").append(System.getProperty("line.separator"));
            resStr.append("@R13").append(System.getProperty("line.separator"));
            resStr.append("A=M").append(System.getProperty("line.separator"));
            resStr.append("M=D").append(System.getProperty("line.separator"));
		}
		
		return resStr.toString();
	}
}
