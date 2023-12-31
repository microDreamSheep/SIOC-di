package live.midreamsheep.frame.sioc.di.handler.configuration;

import live.midreamsheep.frame.sioc.api.context.application.ApplicationContext;
import live.midreamsheep.frame.sioc.api.handle.AbstractContextHandler;
import live.midreamsheep.frame.sioc.scan.clazz.ClassMetaDefinition;

import java.lang.reflect.Method;
import java.util.Arrays;

public class ConfigurationHandler extends AbstractContextHandler{

    private final Method method;
    private final Object source;
    private final String beanName;


    public ConfigurationHandler(Method method, Object source,  String beanName) {
        this.method = method;
        this.source = source;
        this.beanName = beanName;
        //设置依赖
        toGenerate.add(method.getReturnType());
        dependencies.addAll(Arrays.asList(method.getParameterTypes()));
    }

    @Override
    public void handle(ApplicationContext applicationContext) {
        //获取参数
        Object[] parameters = new Object[dependencies.size()];
        for (int i = 0; i < dependencies.size(); i++) {
            parameters[i] = applicationContext.getBean(dependencies.get(i));
        }
        try {
            Object o = method.invoke(source, parameters);
            applicationContext.registerBean(beanName, o);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
