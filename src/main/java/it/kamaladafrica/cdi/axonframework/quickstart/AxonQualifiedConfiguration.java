package it.kamaladafrica.cdi.axonframework.quickstart;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.eventhandling.saga.repository.SagaStore;
import org.axonframework.eventhandling.saga.repository.inmemory.InMemorySagaStore;
import org.axonframework.eventsourcing.EventCountSnapshotTriggerDefinition;
import org.axonframework.eventsourcing.SnapshotTriggerDefinition;
import org.axonframework.eventsourcing.Snapshotter;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;

@ApplicationScoped
public class AxonQualifiedConfiguration {

	@Produces
	@ApplicationScoped
	@Qualified
	public EventStore eventStore() {
		return new EventStoreLogger(
				new EmbeddedEventStore(
					new InMemoryEventStorageEngine()));
	}

	@Produces
	@ApplicationScoped
	@Qualified
	public SagaStore<Object> sagaRepository() {
		return new SagaStoreLogger<>(
				new InMemorySagaStore());
	}

	@Produces
	@ApplicationScoped
	@Qualified
	public CommandBus commandBus() {
		return new CommandBusLogger(
				new SimpleCommandBus());
	}

	// Not mandatory: Bean will we created if necessary
	// cf. CommandGatewayBeanCreation
	@Produces
	@ApplicationScoped
	@Qualified
	public CommandGateway commandGateway(@Qualified final CommandBus commandBus) {
		return new CommandGatewayLogger(
				new DefaultCommandGateway(commandBus));
	}

	// Snapshots
	// The Snapshotter is dynamically created in axon-cdi
	@Produces
	@ApplicationScoped
	@Qualified
	public SnapshotTriggerDefinition snapshotterTrigger(@Qualified final Snapshotter snapshotter) {
		return new SnapshotTriggerDefinitionLogger(
				new EventCountSnapshotTriggerDefinition(snapshotter, 10));
	}

}
