package su.domain.beans.impl;

import java.util.Date;

import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.log4j.Logger;

@MessageDriven(mappedName="jms/FailQueue")
public class FailMdb implements MessageListener{
	

private Logger logger = Logger.getLogger(FailMdb.class);
	
	public void onMessage(Message msg){
		logger.debug("Fail " + new Date());
	}


}
