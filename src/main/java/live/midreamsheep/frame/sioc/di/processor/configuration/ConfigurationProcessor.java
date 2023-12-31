package live.midreamsheep.frame.sioc.di.processor.configuration;

import live.midreamsheep.frame.sioc.api.handle.ContextHandler;
import live.midreamsheep.frame.sioc.di.annotation.basic.configurarion.Bean;
import live.midreamsheep.frame.sioc.di.annotation.basic.configurarion.Configuration;
import live.midreamsheep.frame.sioc.di.handler.configuration.ConfigurationHandler;
import live.midreamsheep.frame.sioc.di.tool.BeanNameTool;
import live.midreamsheep.frame.sioc.scan.annotation.meta.ProcessorFlag;
import live.midreamsheep.frame.sioc.scan.clazz.ClassMetaDefinition;
import live.midreamsheep.frame.sioc.scan.clazz.method.MethodInter;
import live.midreamsheep.frame.sioc.scan.processor.core.register.RegisterProcessor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

@ProcessorFlag({Configuration.class})
public class ConfigurationProcessor extends RegisterProcessor {
    @Override
    public void process(ClassMetaDefinition classMetaDefinition, List<ContextHandler> list) {
        try {
            Object o = classMetaDefinition.getOwnClass().getDeclaredConstructor().newInstance();
            for (MethodInter<Method> methodMethodInter : classMetaDefinition.getMethodInfo().getMethodInterList()) {
                Bean annotation = methodMethodInter.getAnnotationInfo().getAnnotation(Bean.class);
                if (annotation==null){
                    continue;
                }
                list.add(new ConfigurationHandler(methodMethodInter.getMethod(),o, BeanNameTool.getBeanName(annotation.value(),methodMethodInter.getMethod().getReturnType())));
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
