package model;

public class Loan {

	private double principal;
	private double interest;
	private double period;
	public static final double fixedInterest = 5;
	public static final double gracePeriod = 6;

	public Loan() {

	};

	public Loan(Double a, Double i, Double p) {
		principal = a;
		interest = i;
		period = p;
	}

	public double computePayment(Double p, Double a, Double i, String g, Double gp, Double fi) throws Exception {

		interest = i;

		principal = a;

		period = p;

		double graceInterest;

		double resultGrace;

		if (fi != 5)
			throw new Exception("Fixed interest is not correct");
		if (i <= 0)
			throw new Exception("Interest must be greater than 0!");
		if (a <= 0)
			throw new Exception("Principal must be greater than 0!");
		if (p <= 0)
			throw new Exception("Period must be greater than 0!");

		double rate = interest / 100; // user supply

		double totalInterest = rate + (fixedInterest / 100);

		double result = (totalInterest / 12) * principal / (1 - Math.pow((1 + (totalInterest / 12)), -period));
		if (gp != 6) {

			graceInterest = principal * (totalInterest / 12) * gp;

			resultGrace = result + (graceInterest / gp);
		} else {

			graceInterest = principal * (totalInterest / 12) * gracePeriod;

			resultGrace = result + (graceInterest / gracePeriod);
		}

		if (g == null)
			return result;
		else
			return resultGrace;

	}

	public double computeGraceInterest(Double a, Double gp, Double i, Double fi) throws Exception {

		interest = i;

		principal = a;

		double graceInterest;

		if (fi != 5)
			throw new Exception("Fixed interest is not correct");
		if (i <= 0)
			throw new Exception("Interest must be greater than 0!");
		if (a <= 0)
			throw new Exception("Principal must be greater than 0!");

		double rate = interest / 100; // user supply

		double totalInterest = rate + (fixedInterest / 100);

		if (gp != 6) {
			graceInterest = principal * (totalInterest / 12) * gp;
		} else {
			graceInterest = principal * (totalInterest / 12) * gracePeriod;
		}

		return graceInterest;

	}

	public void checkUsername(String name) throws Exception {
		if ("".equals(name))
			throw new Exception("Name should not be empty!");
		else {
			if (!("".equals(name)) && name.length() > 34)
				throw new Exception("Name should be less than 35 characters!");
		}

	}
}
