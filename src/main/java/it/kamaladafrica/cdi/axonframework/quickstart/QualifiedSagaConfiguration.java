package it.kamaladafrica.cdi.axonframework.quickstart;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.enterprise.inject.Stereotype;

import it.kamaladafrica.cdi.axonframework.SagaConfiguration;

@Stereotype
@SagaConfiguration(value = QualifiedSagaConfiguration.QualifiedQualifierMeme.class)
@Retention(RUNTIME)
@Target(TYPE)
public @interface QualifiedSagaConfiguration {

	@Qualified
	public static interface QualifiedQualifierMeme {}

}
