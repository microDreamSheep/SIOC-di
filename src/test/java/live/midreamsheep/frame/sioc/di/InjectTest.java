package live.midreamsheep.frame.sioc.di;

import live.midreamsheep.frame.sioc.api.builder.application.ApplicationContextBuilder;
import live.midreamsheep.frame.sioc.api.context.application.ApplicationContext;
import live.midreamsheep.frame.sioc.core.context.CoreBeanHandlerInjector;
import live.midreamsheep.frame.sioc.core.context.application.CoreApplicationContext;
import live.midreamsheep.frame.sioc.core.context.factory.CoreBeanFactory;
import live.midreamsheep.frame.sioc.di.pojo.BTest;
import live.midreamsheep.frame.sioc.scan.PackageBeanDefinitionsFactory;
import live.midreamsheep.frame.sioc.scan.parse.CoreClassParserToDefinition;
import live.midreamsheep.frame.sioc.scan.scanner.HutoolScannerAdaptor;
import org.junit.Test;

public class InjectTest {
    @Test
    public void test(){
        ApplicationContextBuilder applicationContextBuilder = new ApplicationContextBuilder();
        applicationContextBuilder.setClassbeanHandlerFactory(new PackageBeanDefinitionsFactory(new HutoolScannerAdaptor(""),new CoreClassParserToDefinition()));
        applicationContextBuilder.setApplicationContext(new CoreApplicationContext(new CoreBeanFactory()));
        applicationContextBuilder.setBeanHandlerInjector(new CoreBeanHandlerInjector());
        ApplicationContext build = applicationContextBuilder.build();
        BTest bean = build.getBean(BTest.class);
        bean.sayHello();

    }
}
