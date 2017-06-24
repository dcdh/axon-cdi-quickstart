package it.kamaladafrica.cdi.axonframework.quickstart;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.enterprise.inject.Stereotype;

import it.kamaladafrica.cdi.axonframework.AggregateConfiguration;

@Stereotype
@AggregateConfiguration(value = QualifiedAggregateConfiguration.QualifiedQualifierMeme.class)
@Retention(RUNTIME)
@Target(TYPE)
public @interface QualifiedAggregateConfiguration {

	@Qualified
	public static interface QualifiedQualifierMeme {}

}
