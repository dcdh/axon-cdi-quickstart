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

package it.kamaladafrica.cdi.axonframework.quickstart.saga;

import java.time.Duration;

import javax.inject.Inject;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.saga.EndSaga;
import org.axonframework.eventhandling.saga.SagaEventHandler;
import org.axonframework.eventhandling.saga.StartSaga;
import org.axonframework.eventhandling.scheduling.EventScheduler;
import org.axonframework.eventhandling.scheduling.ScheduleToken;

import it.kamaladafrica.cdi.axonframework.quickstart.api.MarkToDoItemOverdueCommand;
import it.kamaladafrica.cdi.axonframework.quickstart.api.ToDoItemCompletedEvent;
import it.kamaladafrica.cdi.axonframework.quickstart.api.ToDoItemCreatedEvent;
import it.kamaladafrica.cdi.axonframework.quickstart.api.ToDoItemDeadlineExpiredEvent;

/**
 * @author Allard Buijze
 */
public class ToDoSaga {

	@Inject
	private transient CommandGateway commandGateway;

	@Inject
	private transient EventScheduler eventScheduler;

	private ScheduleToken deadline;

	@StartSaga
	@SagaEventHandler(associationProperty = "todoId")
	public void onToDoItemCreated(ToDoItemCreatedEvent event) {
		deadline = eventScheduler.schedule(Duration.ofSeconds(2),
				new ToDoItemDeadlineExpiredEvent(event.getTodoId()));
	}

	@SagaEventHandler(associationProperty = "todoId")
	public void onDeadlineExpired(ToDoItemDeadlineExpiredEvent event) {
		commandGateway.send(new MarkToDoItemOverdueCommand(event.getTodoId()));
	}

	@EndSaga
	@SagaEventHandler(associationProperty = "todoId")
	public void onToDoItemCompleted(ToDoItemCompletedEvent event) {
		if (deadline != null) {
			eventScheduler.cancelSchedule(deadline);
		}
	}

}
