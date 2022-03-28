package effective.java._3;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

@SuppressWarnings("unused")
public abstract class Pizza {

	public enum Topping {
		HAM, MUSHROOM, ONION, PEPPER, SAUSAGE
	}

	final Set<Topping> toppings;

	abstract static class Builder<T extends Builder<T>> {
		EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

		public T addTopping(Topping topping) {
			toppings.add(Objects.requireNonNull(topping));
			return self();
		}

		abstract Pizza build();

		protected abstract T self();
	}

	Pizza(Builder<?> builder) {
		this.toppings = builder.toppings.clone();
	}


}
