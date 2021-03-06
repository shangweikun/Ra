package effective.java._3;

@SuppressWarnings("ALL")
public class NyPizza extends Pizza {

	public enum Size {SMALL, MEDIUM, LARGE}

	private final Size size;

	NyPizza(Builder builder) {
		super(builder);
		size = builder.size;
	}

	public static class Builder extends Pizza.Builder<Builder> {

		private final Size size;

		public Builder(Size size) {
			this.size = size;
		}

		@Override
		Pizza build() {
			return new NyPizza(this);
		}

		@Override
		protected Builder self() {
			return this;
		}
	}
}
