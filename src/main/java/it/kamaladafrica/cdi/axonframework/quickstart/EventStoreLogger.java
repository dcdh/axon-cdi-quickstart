package it.kamaladafrica.cdi.axonframework.quickstart;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.axonframework.common.Registration;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventsourcing.DomainEventMessage;
import org.axonframework.eventsourcing.eventstore.DomainEventStream;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.TrackingEventStream;
import org.axonframework.eventsourcing.eventstore.TrackingToken;
import org.axonframework.messaging.MessageDispatchInterceptor;

public class EventStoreLogger implements EventStore {

	private static final Logger LOGGER = Logger.getLogger(EventStoreLogger.class.getName());

	private final EventStore original;

	public EventStoreLogger(final EventStore original) {
		this.original = Objects.requireNonNull(original);
	}

	@Override
	public TrackingEventStream openStream(final TrackingToken trackingToken) {
		LOGGER.log(Level.INFO, "EventStoreLogger openStream ''{0}''", original);
		return original.openStream(trackingToken);
	}

	@Override
	public void publish(final List<? extends EventMessage<?>> events) {
		LOGGER.log(Level.INFO, "EventStoreLogger publish ''{0}''", original);
		original.publish(events);
	}

	@Override
	public Registration registerDispatchInterceptor(final MessageDispatchInterceptor<EventMessage<?>> dispatchInterceptor) {
		LOGGER.log(Level.INFO, "EventStoreLogger registerDispatchInterceptor ''{0}''", original);
		return original.registerDispatchInterceptor(dispatchInterceptor);
	}

	@Override
	public Registration subscribe(final Consumer<List<? extends EventMessage<?>>> messageProcessor) {
		LOGGER.log(Level.INFO, "EventStoreLogger subscribe ''{0}''", original);
		return original.subscribe(messageProcessor);
	}

	@Override
	public DomainEventStream readEvents(final String aggregateIdentifier) {
		LOGGER.log(Level.INFO, "EventStoreLogger readEvents ''{0}''", original);
		return original.readEvents(aggregateIdentifier);
	}

	@Override
	public void storeSnapshot(final DomainEventMessage<?> snapshot) {
		LOGGER.log(Level.INFO, "EventStoreLogger storeSnapshot ''{0}''", original);
		original.storeSnapshot(snapshot);	
	}

}
