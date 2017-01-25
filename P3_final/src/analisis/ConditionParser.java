package analisis;

import conditionals.Equal;
import conditionals.Less;
import conditionals.LessEq;
import conditionals.NotEqual;

public class ConditionParser {
	private final static Condition[] conditions = {new Less(), new LessEq(),
				new Equal(), new NotEqual()};
				
	public static Condition parse(String t1, String op, String t2, LexicalParser lexParser){
		Condition cd;
		for (Condition d:conditions) {
		cd = d.parse(t1, op, t2, lexParser);
		if (cd!=null) return cd;
		}
		return null;
	} 
}
