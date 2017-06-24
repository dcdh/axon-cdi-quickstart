package it.kamaladafrica.cdi.axonframework.quickstart;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;

public class CommandGatewayLogger implements CommandGateway {

	private static final Logger LOGGER = Logger.getLogger(CommandGatewayLogger.class.getName());

	private final CommandGateway original;

	public CommandGatewayLogger(final CommandGateway original) {
		this.original = Objects.requireNonNull(original);
	}

	@Override
	public <C, R> void send(final C command, final CommandCallback<? super C, R> callback) {
		LOGGER.log(Level.INFO, "CommandGatewayLogger send ''{0}''", original);
		original.send(command, callback);
	}

	@Override
	public <R> R sendAndWait(final Object command) {
		LOGGER.log(Level.INFO, "CommandGatewayLogger sendAndWait ''{0}''", original);
		return original.sendAndWait(command);
	}

	@Override
	public <R> R sendAndWait(final Object command, final long timeout, final TimeUnit unit) {
		LOGGER.log(Level.INFO, "CommandGatewayLogger sendAndWait ''{0}''", original);
		return original.sendAndWait(command, timeout, unit);
	}

	@Override
	public <R> CompletableFuture<R> send(final Object command) {
		LOGGER.log(Level.INFO, "CommandGatewayLogger send ''{0}''", original);
		return original.send(command);
	}

}
