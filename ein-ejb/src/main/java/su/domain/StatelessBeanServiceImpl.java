package su.domain;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;

@Stateless
public class StatelessBeanServiceImpl implements StatelessBean {
	private static final Logger log = Logger
			.getLogger(StatelessBeanServiceImpl.class);

	public StatelessBeanServiceImpl() {
		log.info("++StatelessBeanServiceImpl constructed." + " " + this);
	}

	static {
		log.debug("--------------------------------WAGH2!----------------------------------");
	}

	public void doSmth() {
		log.debug("WAGH!");
	}

}