package it.kamaladafrica.cdi.axonframework.quickstart;

import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.axonframework.eventhandling.saga.AssociationValue;
import org.axonframework.eventhandling.saga.AssociationValues;
import org.axonframework.eventhandling.saga.repository.SagaStore;
import org.axonframework.eventsourcing.eventstore.TrackingToken;

public class SagaStoreLogger<T> implements SagaStore<T> {

	private static final Logger LOGGER = Logger.getLogger(SagaStoreLogger.class.getName());

	private final SagaStore<T> original;

	public SagaStoreLogger(final SagaStore<T> original) {
		this.original = Objects.requireNonNull(original);
	}

	@Override
	public Set<String> findSagas(final Class<? extends T> sagaType, AssociationValue associationValue) {
		LOGGER.log(Level.INFO, "SagaStoreLogger findSagas ''{0}''", original);
		return original.findSagas(sagaType, associationValue);
	}

	@Override
	public <S extends T> org.axonframework.eventhandling.saga.repository.SagaStore.Entry<S> loadSaga(final Class<S> sagaType,
			final String sagaIdentifier) {
		LOGGER.log(Level.INFO, "SagaStoreLogger loadSaga ''{0}''", original);
		return original.loadSaga(sagaType, sagaIdentifier);
	}

	@Override
	public void deleteSaga(final Class<? extends T> sagaType, final String sagaIdentifier,
			final Set<AssociationValue> associationValues) {
		LOGGER.log(Level.INFO, "SagaStoreLogger deleteSaga ''{0}''", original);
		original.deleteSaga(sagaType, sagaIdentifier, associationValues);
	}

	@Override
	public void insertSaga(final Class<? extends T> sagaType, final String sagaIdentifier, final T saga, final TrackingToken token,
			final Set<AssociationValue> associationValues) {
		LOGGER.log(Level.INFO, "SagaStoreLogger insertSaga ''{0}''", original);
		original.insertSaga(sagaType, sagaIdentifier, saga, token, associationValues);
	}

	@Override
	public void updateSaga(final Class<? extends T> sagaType, final String sagaIdentifier, final T saga, final TrackingToken token,
			final AssociationValues associationValues) {
		LOGGER.log(Level.INFO, "SagaStoreLogger updateSaga ''{0}''", original);
		original.updateSaga(sagaType, sagaIdentifier, saga, token, associationValues);
	}

}
