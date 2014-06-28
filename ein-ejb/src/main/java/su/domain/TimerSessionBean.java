package su.domain;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.TimerService;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.log4j.Logger;

@Singleton
@Startup
public class TimerSessionBean implements TimerSession {
	@Resource
	TimerService timerService;

	@EJB
	StatelessBean ssb;

	@Resource(lookup = "jms/ConnFactXA")
	ConnectionFactory cf;

	@Resource(lookup = "jms/CuckooQueue")
	Destination cuckooQueue;

	private int i = 0;

	private Date lastProgrammaticTimeout;
	private Date lastAutomaticTimeout;

	private static final Logger logger = Logger
			.getLogger(StatelessBeanServiceImpl.class);

	public TimerSessionBean() {
		logger.info("++timer constructed." + i + " " + this);
		i += 1000;
	}

	@PostConstruct
	public void init() {
		timerService.createTimer(0, 100000, "programmatic timer crtd");
		ssb.doSmth();
	}

	@Timeout
	public void programmaticTimeout() {
//		logger.info("Programmatic timeout occurred." + i++ + " " + this);
		Connection connection = null;
		try {
			// logger.debug("creating conn");
			connection = cf.createConnection();
			// logger.debug("creating sess");
			Session sess = connection.createSession(true,
					Session.AUTO_ACKNOWLEDGE);
			// logger.debug("creating msg");
			Message msg = sess.createMessage();
			// logger.debug("creating producer");
			MessageProducer producer = sess.createProducer(cuckooQueue);
//			 logger.debug("sending msg");
			producer.send(msg);
			 logger.debug("msg sent");
		} catch (Exception e) {
			logger.error(e);
		} finally {
			try {
				if (connection != null)
//					logger.debug("closing connection");
				connection.close();
			} catch (JMSException e) {
				logger.error(e);
			}
		}

	}

	

}