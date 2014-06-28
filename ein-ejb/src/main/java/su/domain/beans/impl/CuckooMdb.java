package su.domain.beans.impl;

import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.log4j.Logger;

@MessageDriven(mappedName = "jms/CuckooQueue", name = "CuckooMdb")
public class CuckooMdb implements MessageListener {
	@Resource
	MessageDrivenContext mdc;

	private static int i = 0;
	private Logger logger = Logger.getLogger(CuckooMdb.class);

	public void onMessage(Message msg) {
		/*int j = i;
		logger.debug(j + ":Cuckoo" + new Date());
		try {
			Thread.sleep(60);
		} catch (InterruptedException e) {
			logger.error(e);
		}
		i++;
		if (i % 3 == 2) {*/
			logger.warn(":Rollback context");
			mdc.setRollbackOnly();
		/*} else
			logger.debug(j + ":All is OK");*/
	}

}
