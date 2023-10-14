package es.bluesolution.pokedex.framework.cqrs;

import reactor.core.CorePublisher;

public interface Handler<T> {

  CorePublisher<T> handle(CommandQuery commandQuery);

}
