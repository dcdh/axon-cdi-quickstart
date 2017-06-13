/*
 * Copyright (c) 2010-2014. Axon Framework
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package it.kamaladafrica.cdi.axonframework.quickstart.annotated;

import java.time.Instant;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;

import it.kamaladafrica.cdi.axonframework.quickstart.Qualified;
import it.kamaladafrica.cdi.axonframework.quickstart.api.ToDoItemCompletedEvent;
import it.kamaladafrica.cdi.axonframework.quickstart.api.ToDoItemCreatedEvent;

/**
 * Event handler that listens to both events and prints a message to the system output stream.
 *
 * @author Jettro Coenradie
 */
@Qualified
public class QualifiedToDoEventHandler {

	@EventHandler
	public void handle(ToDoItemCreatedEvent event, @Timestamp Instant time) {
		System.out.println(">> Receiving event: " + event);
		System.out.println(String.format("We've got something qualified to do: %s (%s, created at %s)",
				event.getDescription(),
				event.getTodoId(),
				time.toString()));
	}

	@EventHandler
	public void handle(ToDoItemCompletedEvent event) {
		System.out.println(">> Receiving event: " + event);
		System.out.println(String.format("We've completed the qualified task with id %s", event.getTodoId()));
	}
}
