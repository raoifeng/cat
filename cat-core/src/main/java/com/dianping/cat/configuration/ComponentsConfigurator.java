package com.dianping.cat.configuration;

import java.util.ArrayList;
import java.util.List;

import com.dianping.cat.message.broker.DefaultMessageBroker;
import com.dianping.cat.message.broker.MessageBroker;
import com.dianping.cat.message.handler.MessageDispatcher;
import com.dianping.cat.message.handler.MessageHandler;
import com.dianping.cat.message.io.InMemoryQueue;
import com.dianping.cat.message.io.InMemoryReceiver;
import com.dianping.cat.message.io.InMemorySender;
import com.dianping.cat.message.io.MessageReceiver;
import com.dianping.cat.message.io.MessageSender;
import com.site.lookup.configuration.AbstractResourceConfigurator;
import com.site.lookup.configuration.Component;

public class ComponentsConfigurator extends AbstractResourceConfigurator {
	@Override
	public List<Component> defineComponents() {
		List<Component> all = new ArrayList<Component>();

		String inMemory = "in-memory";

		all.add(C(InMemoryQueue.class));
		all.add(C(MessageSender.class, inMemory, InMemorySender.class) //
		      .req(InMemoryQueue.class));
		all.add(C(MessageReceiver.class, inMemory, InMemoryReceiver.class) //
		      .req(InMemoryQueue.class));
		all.add(C(MessageHandler.class, MessageDispatcher.class) //
		      .req(MessageReceiver.class, inMemory));

		all.add(C(MessageBroker.class, inMemory, DefaultMessageBroker.class) //
		      .req(MessageSender.class, inMemory) //
		      .req(MessageReceiver.class, inMemory));

		return all;
	}

	public static void main(String[] args) {
		generatePlexusComponentsXmlFile(new ComponentsConfigurator());
	}
}
