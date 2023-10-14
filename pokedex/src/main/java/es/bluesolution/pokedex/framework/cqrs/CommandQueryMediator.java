package es.bluesolution.pokedex.framework.cqrs;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.CorePublisher;

public class CommandQueryMediator implements Mediator<CommandQuery> {

  private static final Logger log = LoggerFactory.getLogger(CommandQueryMediator.class);

  private final Map<String, Handler> dispatcher;

  public CommandQueryMediator(Map<String, Handler> dispatcher) {
    this.dispatcher = dispatcher;
  }

  public CorePublisher<Object> execute(CommandQuery commandQuery) {
    String concreteCommandQuery = commandQuery.getClass().getSimpleName();
    Handler concreteHandler = dispatcher.get(concreteCommandQuery);
    if (log.isInfoEnabled()) {
      log.trace(String.format("executed: %s", concreteCommandQuery));
    }
    return concreteHandler.handle(commandQuery);
  }


}
