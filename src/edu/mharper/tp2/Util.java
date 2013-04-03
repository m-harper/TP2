package edu.mharper.tp2;

import java.util.ArrayList;

public class Util {
	
	public static ArrayList<String> getTokens(String _command) {
		String command = _command;
		ArrayList<String> tokens = new ArrayList<String>();
		String token;
		while (command.indexOf(" ") != -1) {
			token = command.substring(0, command.indexOf(" "));
			tokens.add(token);
			command = command.substring(command.indexOf(" ") + 1);
		}
		tokens.add(command);
		return tokens;
	}
}