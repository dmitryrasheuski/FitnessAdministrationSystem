package di;

import com.google.inject.AbstractModule;
import service.ClientService;
import service.GroupService;
import service.IClientService;
import service.IGroupService;

public class ServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IGroupService.class).to(GroupService.class);
        bind(IClientService.class).to(ClientService.class);
    }
}
