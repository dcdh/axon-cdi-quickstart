package it.kamaladafrica.cdi.axonframework.quickstart;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.common.Registration;
import org.axonframework.messaging.MessageHandler;

public class CommandBusLogger implements CommandBus {

	private static final Logger LOGGER = Logger.getLogger(CommandBusLogger.class.getName());

	private final CommandBus original;

	public CommandBusLogger(final CommandBus original) {
		this.original = Objects.requireNonNull(original);
	}

	@Override
	public <C, R> void dispatch(final CommandMessage<C> command, final CommandCallback<? super C, R> callback) {
		LOGGER.log(Level.INFO, "CommandBus dispatch ''{0}''", original);
		original.dispatch(command, callback);
	}

	@Override
	public Registration subscribe(final String commandName, final MessageHandler<? super CommandMessage<?>> handler) {
		LOGGER.log(Level.INFO, "CommandBus subscribe '{0}'", original);
		return original.subscribe(commandName, handler);
	}

}
