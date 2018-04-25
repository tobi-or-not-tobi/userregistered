package de.hybris.platform.userregistered.listeners;

import de.hybris.platform.commerceservices.event.RegisterEvent;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.userregistered.model.UserRegisteredEventModel;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;


public class UserRegisteredEventListener extends AbstractEventListener<RegisterEvent>
{

	static private Log LOG = LogFactory.getLog(UserRegisteredEventListener.class);
	private ModelService modelService;

	@Override
	protected void onEvent(final RegisterEvent event)
	{
		final UserRegisteredEventModel registeredEvent = modelService.create(UserRegisteredEventModel.class);
		registeredEvent.setUser(event.getCustomer());
		registeredEvent.setBaseSite(event.getSite());
		registeredEvent.setRegisteredDate(new Date());
		if (LOG.isDebugEnabled())
		{
			LOG.debug("User registered: " + registeredEvent.getUser().getUid());
		}
		modelService.save(registeredEvent);
	}

	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

}
