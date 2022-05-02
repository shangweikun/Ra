package effective.java._3;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<Pizza> pizzas = new ArrayList<>();

		pizzas.add(
				new NyPizza.Builder(NyPizza.Size.SMALL)
						.addTopping(Pizza.Topping.SAUSAGE)
						.addTopping(Pizza.Topping.ONION)
						.build()
		);

		pizzas.add(
				new Calzone.Builder()
						.addTopping(Pizza.Topping.HAM)
						.sauceInside()
						.build()
		);

		pizzas.forEach(System.out::println);
	}
}
