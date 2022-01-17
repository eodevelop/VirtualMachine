package module;

import enums.ArithmeticCommand;

public class CodeWriterModule {
	private String fileName;
	private int ifCount = 0;
	
	public CodeWriterModule(String fileName) {
		this.fileName = fileName;
	}
	
	public String getCode(String currentLine, ArithmeticCommand command, String segment, String index) {
		if(command.equals(ArithmeticCommand.C_PUSH) || command.equals(ArithmeticCommand.C_POP)) {
			return writePushPop(command, segment, Integer.parseInt(index));
		} else {
			return writerArithmetic(currentLine);
		}
	}
	
	private String writerArithmetic(String arithmetic) {
		StringBuilder resStr = new StringBuilder();		
		if(!arithmetic.equals("neg") && !arithmetic.equals("not")) {
			resStr.append(popStack());
		}
		resStr.append(decrementSp());
		resStr.append(setA());
		
		if(arithmetic.equals("add")) {
			resStr.append("//writerArithmetic : add").append(System.getProperty("line.separator"));
			resStr.append("M=M+D").append(System.getProperty("line.separator"));
		} else if(arithmetic.equals("sub")) {
			resStr.append("//writerArithmetic : sub").append(System.getProperty("line.separator"));
			resStr.append("M=M-D").append(System.getProperty("line.separator"));
		} else if(arithmetic.equals("and")) {
			resStr.append("//writerArithmetic : and").append(System.getProperty("line.separator"));
			resStr.append("M=M&D").append(System.getProperty("line.separator"));
		} else if(arithmetic.equals("or")) {
			resStr.append("//writerArithmetic : or").append(System.getProperty("line.separator"));
			resStr.append("M=M|D").append(System.getProperty("line.separator"));
		} else if(arithmetic.equals("neg")) {
			resStr.append("//writerArithmetic : neg").append(System.getProperty("line.separator"));
			resStr.append("M=-M").append(System.getProperty("line.separator"));
		} else if(arithmetic.equals("not")) {
			resStr.append("//writerArithmetic : not").append(System.getProperty("line.separator"));
			resStr.append("M=!M").append(System.getProperty("line.separator"));
		} else if(arithmetic.equals("eq") || arithmetic.equals("gt") || arithmetic.equals("lt")) {
			resStr.append("//writerArithmetic : eq, gt, lt").append(System.getProperty("line.separator"));
			resStr.append("M=M-D").append(System.getProperty("line.separator"));
			resStr.append("@IF" + ifCount).append(System.getProperty("line.separator"));
			
			if(arithmetic.equals("eq")) {
				resStr.append("//writerArithmetic : eq").append(System.getProperty("line.separator"));
				resStr.append("D;JEQ").append(System.getProperty("line.separator"));
			} else if(arithmetic.equals("gt")) {
				resStr.append("//writerArithmetic : gt").append(System.getProperty("line.separator"));
				resStr.append("D;JGT").append(System.getProperty("line.separator"));
			} else if(arithmetic.equals("lt")) {
				resStr.append("//writerArithmetic : lt").append(System.getProperty("line.separator"));
				resStr.append("D;JLT").append(System.getProperty("line.separator"));
			}
			
			resStr.append(setA());
			resStr.append("M=0").append(System.getProperty("line.separator"));
			resStr.append("@ENDIF" + ifCount).append(System.getProperty("line.separator"));
			resStr.append("0;JMP").append(System.getProperty("line.separator"));
			
			resStr.append("(IF" + ifCount + ")").append(System.getProperty("line.separator"));
			resStr.append(setA());
			resStr.append("M=-1").append(System.getProperty("line.separator"));
			
			resStr.append("(ENDIF" + ifCount + ")").append(System.getProperty("line.separator"));
			ifCount++;
		}
		
		resStr.append(incrementSp());
		return resStr.toString();
	}

	private String writePushPop(ArithmeticCommand command, String segment, int index) {
		StringBuilder resStr = new StringBuilder();
		// 세그먼트에 따른 주소값 대입 
		String address = getAddress(segment);
		if(segment.equals("constant")) {
			resStr.append("//writePushPop : constant").append(System.getProperty("line.separator"));
			resStr.append("@" + index).append(System.getProperty("line.separator"));
		} else if(segment.equals("static")) {
			resStr.append("//writePushPop : static").append(System.getProperty("line.separator"));
			resStr.append("@" + this.fileName + "." + index).append(System.getProperty("line.separator"));
		} else if(segment.equals("pointer") || segment.equals("temp")) {
			resStr.append("//writePushPop : pointer, temp").append(System.getProperty("line.separator"));
			resStr.append("@R" + address + index).append(System.getProperty("line.separator"));
		} else if(segment.equals("local") || segment.equals("argument") || segment.equals("this") || segment.equals("that")) {
			resStr.append("//writePushPop : local, argument, this, that").append(System.getProperty("line.separator"));
			resStr.append("@" + address).append(System.getProperty("line.separator"));
			resStr.append("D=M").append(System.getProperty("line.separator"));
			resStr.append("@" + index).append(System.getProperty("line.separator"));
			resStr.append("A=D+A").append(System.getProperty("line.separator"));
		}
		
		// 주소 설정 후 PUSH, POP 작업
		if(command.equals(ArithmeticCommand.C_PUSH)) {
			resStr.append("//writePushPop : push").append(System.getProperty("line.separator"));
			if(segment.equals("constant")) {
				resStr.append("//writePushPop : push constant").append(System.getProperty("line.separator"));
				resStr.append("D=A").append(System.getProperty("line.separator"));
			} else {
				resStr.append("//writePushPop : push !constant").append(System.getProperty("line.separator"));
				resStr.append("D=M").append(System.getProperty("line.separator"));
			}
            resStr.append(pushStack());
		} else {
			resStr.append("//writePushPop : pop").append(System.getProperty("line.separator"));
            resStr.append("D=A").append(System.getProperty("line.separator"));
            resStr.append("@R13").append(System.getProperty("line.separator"));
            resStr.append("M=D").append(System.getProperty("line.separator"));
            resStr.append(popStack());
            resStr.append("@R13").append(System.getProperty("line.separator"));
            resStr.append("A=M").append(System.getProperty("line.separator"));
            resStr.append("M=D").append(System.getProperty("line.separator"));
		}
		
		return resStr.toString();
	}
	
	private String pushStack() {
		StringBuilder resStr = new StringBuilder();
		resStr.append("//pushStack").append(System.getProperty("line.separator"));
		resStr.append("@SP").append(System.getProperty("line.separator"));
		resStr.append("A=M").append(System.getProperty("line.separator"));
		resStr.append("M=D").append(System.getProperty("line.separator"));
		resStr.append("@SP").append(System.getProperty("line.separator"));
		resStr.append("M=M+1").append(System.getProperty("line.separator"));
		return resStr.toString();
	}
	
	private String popStack() {
		StringBuilder resStr = new StringBuilder();
		resStr.append("//popStack").append(System.getProperty("line.separator"));
		resStr.append("@SP").append(System.getProperty("line.separator"));
		resStr.append("M=M-1").append(System.getProperty("line.separator"));
		resStr.append("A=M").append(System.getProperty("line.separator"));
		resStr.append("D=M").append(System.getProperty("line.separator"));
		return resStr.toString();
	}
	
	private String decrementSp() {
		StringBuilder resStr = new StringBuilder();
		resStr.append("//decrementSp").append(System.getProperty("line.separator"));
		resStr.append("@SP").append(System.getProperty("line.separator"));
		resStr.append("M=M-1").append(System.getProperty("line.separator"));
		return resStr.toString();
	}
	
	private String incrementSp() {
		StringBuilder resStr = new StringBuilder();
		resStr.append("//incrementSp").append(System.getProperty("line.separator"));
		resStr.append("@SP").append(System.getProperty("line.separator"));
		resStr.append("M=M+1").append(System.getProperty("line.separator"));
		return resStr.toString();
	}
	
	private String setA() {
		StringBuilder resStr = new StringBuilder();
		resStr.append("//setA").append(System.getProperty("line.separator"));
		resStr.append("@SP").append(System.getProperty("line.separator"));
		resStr.append("A=M").append(System.getProperty("line.separator"));
		return resStr.toString();
	}
	
	private String getAddress(String segment) {
		switch(segment) {
	        case "local":
	            return "LCL";
	        case "argument":
	            return "ARG";
	        case "this":
	            return "THIS";
	        case "that":
	            return "THAT";
	        case "pointer":
	            return "3";
	        case "temp":
	            return "5";
	        case "static":
	            return "16";
		}
		
		return "";
	}
}
