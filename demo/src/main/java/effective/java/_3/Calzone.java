package effective.java._3;

@SuppressWarnings("ALL")
public class Calzone extends Pizza {

	private final boolean sauceInside;

	Calzone(Builder builder) {
		super(builder);
		sauceInside = builder.sauceInside;
	}

	public static class Builder extends Pizza.Builder<Builder> {

		private boolean sauceInside = false;

		public Builder sauceInside() {
			this.sauceInside = true;
			return this;
		}

		@Override
		Pizza build() {
			return new Calzone(this);
		}

		@Override
		protected Builder self() {
			return this;
		}
	}
}
