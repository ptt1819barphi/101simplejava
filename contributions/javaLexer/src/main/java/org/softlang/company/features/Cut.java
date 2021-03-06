package org.softlang.company.features;

import static org.softlang.company.features.parsing.Token.*;
import org.softlang.company.features.parsing.Recognizer;
import org.softlang.company.features.parsing.Token;

import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.IOException;

public class Cut {
	
	public Cut(String in, String out) throws IOException {
		Recognizer recognizer = new Recognizer(in);
		Writer writer = new OutputStreamWriter(new FileOutputStream(out));
		Token current = null;
		Token previous = null;
		String lexeme = null;
		while (recognizer.hasNext()) {
			
			current = recognizer.next();
			lexeme = recognizer.getLexeme();

			// Cut salary in half
			if (current == FLOAT && previous == SALARY)
				lexeme = Double.toString(
							(Double.parseDouble(recognizer.getLexeme())
								/ 2.0d));

			// Copy possibly modified lexeme
			writer.write(lexeme);

			if (current!=WS)
				previous = current;
		}
		writer.close();
	}
}
