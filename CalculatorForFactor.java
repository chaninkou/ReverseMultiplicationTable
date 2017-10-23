package application;

import java.util.ArrayList;
import java.util.List;

public class CalculatorForFactor {
	// Make a list
	public List<Integer> listOfInteger = new ArrayList<Integer>();

	public List<Integer> findFactor(int factor) {
		int b = 1;

		if (factor % 2 != 0) {
			b = 2;
		}

		while (b <= factor) {
			if (factor % b == 0) {
				listOfInteger.add(b);
			}
			b++;
		}
		return listOfInteger;
	}
}