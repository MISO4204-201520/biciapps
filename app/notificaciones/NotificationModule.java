package notificaciones;

import play.Configuration;
import play.Environment;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class NotificationModule extends AbstractModule{

	private final Environment environment;
    private final Configuration configuration;

    public NotificationModule(
          Environment environment,
          Configuration configuration) {
        this.environment = environment;
        this.configuration = configuration;
    }
	
	
	@Override
	protected void configure() {
//		bind(INotificationManager.class)
//        	.annotatedWith(Names.named("mock"))
//        	.to(NotificationManagerMock.class);
		
		String mock = configuration.getString("notificaciones.enabled");
		if(mock != null && mock.equals("TRUE")){
			bind(INotificationManager.class)
				.to(NotificationManager.class);
		}
		else{
			bind(INotificationManager.class)
			.to(NotificationManagerDisabled.class);
		}
	}

}
