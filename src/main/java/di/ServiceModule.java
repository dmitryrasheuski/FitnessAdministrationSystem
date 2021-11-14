package di;

import com.google.inject.AbstractModule;
import service.GroupService;
import service.IGroupService;

public class ServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IGroupService.class).to(GroupService.class);
    }
}
