/**
 * 
 */
package le2lejosev3.tests;

import java.util.logging.Level;
import java.util.logging.Logger;

import le2lejosev3.logging.Setup;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;

/**
 * @author roland
 *
 */
public class MotorSyncTest {

	private static Class<?> clazz = MotorSyncTest.class;
	private static final Logger log = Logger.getLogger(clazz.getName());

	static final Port[] motorPorts = new Port[] { MotorPort.A, MotorPort.D };

	/**
	 * Main program entry point.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// setup logging to file for all levels
		Setup.log2File(clazz, Level.ALL);
		log.info("Starting ...");

		// instantiate the regulated motors
		BaseRegulatedMotor master = new EV3LargeRegulatedMotor(motorPorts[0]);
		BaseRegulatedMotor slave = new EV3LargeRegulatedMotor(motorPorts[1]);
		// synchronize them
		//master.synchronizeWith(new lejos.robotics.RegulatedMotor[] { slave });

		int mdegs = 0;
		int mdege = 0;
		int mdege2 = 0;
		int sdegs = 0;
		int sdege = 0;
		int sdege2 = 0;

		// ---------------------------------
		// motors on for a period
		mdegs = master.getTachoCount();
		sdegs = slave.getTachoCount();

		// run backward for 0.2 sec
		log.info("Motor On For Period, max speed: " + master.getMaxSpeed());
		// master.startSynchronization();
		// slave.startSynchronization();
		master.setSpeed(40F * master.getMaxSpeed() / 100F);
		slave.setSpeed(40F * master.getMaxSpeed() / 100F);
		master.backward();
		slave.backward();
		try {
			Thread.sleep(200L);
		} catch (InterruptedException e) {
			// ignore
		}
		master.stop(true);
		slave.stop(true);
		// master.endSynchronization();
		// slave.endSynchronization();
		log.info("Motor stop");

		mdege = master.getTachoCount();
		sdege = slave.getTachoCount();

		// run forward for 0.2 sec
		log.info("Motor On For Period");
		// master.startSynchronization();
		// slave.startSynchronization();
		master.setSpeed(40F * master.getMaxSpeed() / 100F);
		slave.setSpeed(40F * master.getMaxSpeed() / 100F);
		master.forward();
		slave.forward();
		try {
			Thread.sleep(200L);
		} catch (InterruptedException e) {
			// ignore
		}
		master.stop(true);
		slave.stop(true);
		// master.endSynchronization();
		// slave.endSynchronization();
		log.info("Motor stop");

		mdege2 = master.getTachoCount();
		sdege2 = slave.getTachoCount();

		log.info("Motor started at " + mdegs + " degr., " + sdegs + " degr.");
		log.info("Motor stopped at " + mdege + " degr., " + sdege + " degr.");
		log.info("Motor stopped at " + mdege2 + " degr., " + sdege2 + " degr.");
		log.info(" Total Rotation   : " + (mdege2 - mdegs) + " degr., " + (sdege2 - sdegs) + " degr.");

		// ---------------------------------
		// motors on for degrees
		mdegs = master.getTachoCount();
		sdegs = slave.getTachoCount();

		// run backward for -75 degrees
		log.info("Motor turn -75 degrees");
		// master.startSynchronization();
		// slave.startSynchronization();
		master.setSpeed(40F * master.getMaxSpeed() / 100F);
		slave.setSpeed(40F * master.getMaxSpeed() / 100F);
		master.rotate(-75, true);
		slave.rotate(-75, false);
		// master.endSynchronization();
		// slave.endSynchronization();

		log.info("Motor stop");

		mdege = master.getTachoCount();
		sdege = slave.getTachoCount();

		// run forward for +75 degrees
		log.info("Motor turn +75 degrees");
		// master.startSynchronization();
		// slave.startSynchronization();
		master.setSpeed(40F * master.getMaxSpeed() / 100F);
		slave.setSpeed(40F * master.getMaxSpeed() / 100F);
		master.rotate(75, true);
		slave.rotate(75, false);
		// master.endSynchronization();
		// slave.endSynchronization();

		log.info("Motor stop");

		mdege2 = master.getTachoCount();
		sdege2 = slave.getTachoCount();

		log.info("Motor started at " + mdegs + " degr., " + sdegs + " degr.");
		log.info("Motor stopped at " + mdege + " degr., " + sdege + " degr.");
		log.info("Motor stopped at " + mdege2 + " degr., " + sdege2 + " degr.");
		log.info(" Total Rotation   : " + (mdege2 - mdegs) + " degr., " + (sdege2 - sdegs) + " degr.");

		// ---------------------------------
		master.close();
		slave.close();

		log.info("The End");
	}
}
