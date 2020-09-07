

/**
 * Celestial Body class for NBody
 * @author Kadriye Sude Almus
 *
 */
public class CelestialBody {

	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;

	/**
	 * Create a Body from parameters	
	 * @param xp initial x position
	 * @param yp initial y position
	 * @param xv initial x velocity
	 * @param yv initial y velocity
	 * @param mass of object
	 * @param filename of image for object animation
	 */
	public CelestialBody(double xp, double yp, double xv,
			             double yv, double mass, String filename){
		myXPos = xp;
		myYPos = yp;
		myXVel = xv;
		myYVel = yv;
		myMass = mass;
		myFileName = filename;

	}

	/**
	 * Copy constructor: copy instance variables from one
	 * body to this body
	 * @param b used to initialize this body
	 */
	public CelestialBody(CelestialBody b){
		myXPos = b.getX();
		myYPos = b.getY();
		myXVel = b.getXVel();
		myYVel = b.getYVel();
		myMass = b.getMass();
		myFileName = b.getName();
	}

	public double getX() {
		// TODO: complete method
		return myXPos;
	}
	public double getY() {
		// TODO: complete method
		return myYPos;
	}
	public double getXVel() {
		// TODO: complete method
		return myXVel;
	}
	/**
	 * Return y-velocity of this Body.
	 * @return value of y-velocity.
	 */
	public double getYVel() {
		// TODO: complete method
		return myYVel;
	}
	/**
	 * Return mass of this Body.
	 * @return value of mass.
	 */

	public double getMass() {
		// TODO: complete method
		return myMass;
	}

	/**
	 * Return file name of this Body.
	 * @return value of file name.
	 */

	public String getName() {
		// TODO: complete method
		return myFileName;
	}

	/**
	 * Return the distance between this body and another
	 * @param b the other body to which distance is calculated
	 * @return distance between this body and b
	 */
	public double calcDistance(CelestialBody b) {
		double dx = myXPos - b.getX();
		double dy = myYPos - b.getY();
		double rsq = (dx * dx) + (dy * dy);
		return Math.sqrt(rsq);
	}

	/**
	 * Return the force exerted by another body on this body
	 * @param b the other body that exerts force
	 * @return force exerted by b on this body
	 */

	public double calcForceExertedBy(CelestialBody b) {
		double g = 6.67*1e-11;
		double rsq = calcDistance(b) * calcDistance(b);
		return (g * myMass * b.getMass()) / rsq;
	}

	/**
	 * Return the force exerted in the x direction by another body on this body
	 * @param b the other body that exerts force
	 * @return force exerted by b on this body in the x direction
	 */

	public double calcForceExertedByX(CelestialBody b) {
		double dx = b.getX() - myXPos;
		double f = calcForceExertedBy(b);
		double r = calcDistance(b);
		return f * dx / r;
	}

	/**
	 * Return the force exerted in the y direction by another body on this body
	 * @param b the other body that exerts force
	 * @return force exerted by b on this body in the y direction
	 */

	public double calcForceExertedByY(CelestialBody b) {
		double dy = b.getY() - myYPos;
		double f = calcForceExertedBy(b);
		double r = calcDistance(b);
		return f * dy / r;
	}

	/**
	 * Return the net force exerted in the x direction by all other bodies on this body
	 * @param bodies array of all celestial bodies in the data
	 * @return force exerted by bodies on this body in the x direction
	 */

	public double calcNetForceExertedByX(CelestialBody[] bodies) {
		double sum = 0.0;
		for (CelestialBody b: bodies) {
			if (!b.equals(this)) {
				sum += this.calcForceExertedByX(b);
			}
		}
		return sum;
	}

	/**
	 * Return the net force exerted in the y direction by all other bodies on this body
	 * @param bodies array of all celestial bodies in the data
	 * @return force exerted by bodies on this body in the y direction
	 */

	public double calcNetForceExertedByY(CelestialBody[] bodies) {
		double sum = 0.0;
		for (CelestialBody b: bodies) {
			if (!b.equals(this)) {
				sum += this.calcForceExertedByY(b);
			}
		}
		return sum;
	}

	/**
	 * Updates the x-position, y-position, x-velocity, and y-velocity of the body
	 * @param deltaT change in time
	 * @param xforce force exerted in the x direction on the body
	 * @param yforce force exerted in the y direction on the body
	 */


	public void update(double deltaT, 
			           double xforce, double yforce) {
		// TODO: complete method
		double ax = xforce / myMass;
		double ay = yforce / myMass;
		double nvx = myXVel + deltaT*ax;
		double nvy = myYVel + deltaT*ay;
		double nx = myXPos + nvx * deltaT;
		double ny = myYPos + nvy * deltaT;
		myXPos = nx;
		myYPos = ny;
		myXVel = nvx;
		myYVel = nvy;

	}

	/**
	 * Draws this planet's image at its current position
	 */
	public void draw() {
		StdDraw.picture(myXPos,myYPos,"images/"+myFileName);
	}
}
