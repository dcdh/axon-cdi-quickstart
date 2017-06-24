package it.kamaladafrica.cdi.axonframework.quickstart;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.axonframework.eventsourcing.SnapshotTrigger;
import org.axonframework.eventsourcing.SnapshotTriggerDefinition;

public class SnapshotTriggerDefinitionLogger implements SnapshotTriggerDefinition {

	private static final Logger LOGGER = Logger.getLogger(SnapshotTriggerDefinitionLogger.class.getName());

	private final SnapshotTriggerDefinition original;

	public SnapshotTriggerDefinitionLogger(final SnapshotTriggerDefinition original) {
		this.original = Objects.requireNonNull(original);
	}

	@Override
	public SnapshotTrigger prepareTrigger(final Class<?> aggregateType) {
		LOGGER.log(Level.INFO, "SnapshotTriggerDefinitionLogger prepareTrigger ''{0}''", original);
		return original.prepareTrigger(aggregateType);
	}

}
