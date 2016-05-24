package exception;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

//Exception handler to handle self-defined exception
public class AutoException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8025860214576773770L;

	private int errorNo;//error number

	private String errorMsg;//error message

	//Exception enumeration
	public enum ExceptionEnum {
		MissingPrice(1), MissingOptionSet(2), MissingOptionPrice(3), WrongFileName(4), MissingOption(5);
		private int errorNumber;

		ExceptionEnum(int errorNumber) {
			this.setErrorNumber(errorNumber);
		}

		public int getErrorNumber() {
			return errorNumber;
		}

		public void setErrorNumber(int errorNumber) {
			this.errorNumber = errorNumber;
		}

	}

	public AutoException(ExceptionEnum exceptionEnum) {
		this.errorNo = exceptionEnum.getErrorNumber();
		this.errorMsg = exceptionEnum.toString();
		log();
	}

	public int getErrorNo() {
		return errorNo;
	}

	public void setErrorNo(int errorNo) {
		this.errorNo = errorNo;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	//Log error message to file
	public void log() {
		File file = new File("log.txt");
		try {
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			Date date = new Date();
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(new Timestamp(date.getTime())).append(": ErrorNo[").append(errorNo).append("] [")
					.append(errorMsg).append("].\n");
			bw.append(stringBuilder.toString());
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//Fix different exceptions
	public void fix(int errno) {

		FixHelper fixHelper = new FixHelper();

		switch (errno) {

		case 1:
			fixHelper.fixMissingPrice();
			break;
		case 2:
			fixHelper.fixMissingOptionSet();
			break;
		case 3:
			fixHelper.fixMissingOptionPrice();
			break;
		case 4:
			fixHelper.fixWrongFileName();
			break;
		case 5:
			fixHelper.fixMissingOption();
			break;
		default:
			break;
		}
	}
}
