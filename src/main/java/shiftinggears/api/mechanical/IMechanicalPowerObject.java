package shiftinggears.api.mechanical;

/**
 * @author shadowfacts
 */
public interface IMechanicalPowerObject {

	double getSpeed();

	void setSpeed(double speed);

	default void apply(IMechanicalPowerObject other) {
		double avg = (getSpeed() + other.getSpeed()) / 2;
		setSpeed(avg);
		other.setSpeed(avg);
	}

}
