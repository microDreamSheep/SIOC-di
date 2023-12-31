package live.midreamsheep.frame.sioc.di.handler.comment;

import live.midreamsheep.frame.sioc.api.bean.Bean;
import live.midreamsheep.frame.sioc.api.context.application.ApplicationContext;
import live.midreamsheep.frame.sioc.api.handle.AbstractContextHandler;
import live.midreamsheep.frame.sioc.core.bean.CoreBean;
import live.midreamsheep.frame.sioc.core.bean.proto.PrototypeBean;
import live.midreamsheep.frame.sioc.core.bean.single.SingleBean;
import live.midreamsheep.frame.sioc.scan.clazz.method.MethodInter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class BeanDefinitionHandler extends AbstractContextHandler {

    private final BeanDefinition beanDefinition;
    private final MethodInter<Constructor<?>> constructor;

    public BeanDefinitionHandler(BeanDefinition beanDefinition, MethodInter<Constructor<?>> constructor) {
        toGenerate.add(beanDefinition.getBeanClass());
        dependencies.addAll(Arrays.asList(constructor.getParameterTypes()));
        this.beanDefinition = beanDefinition;
        this.constructor = constructor;
    }

    @Override
    public void handle(ApplicationContext context) {
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        //分别获取参数
        Object[] parameters = new Object[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            parameters[i] = context.getBean(parameterTypes[i]);
        }
        try {
            Object o = constructor.getMethod().newInstance(parameters);
            Bean.BeanMetaData beanMetaData = new Bean.BeanMetaData();
            beanMetaData.setAClass(beanDefinition.getBeanClass());
            beanMetaData.setId(beanDefinition.getBeanId());
            beanMetaData.setName(beanDefinition.getBeanName());

            Bean bean = null;
            if (beanDefinition.getScope()==BeanScope.PROTOTYPE){
                bean = new PrototypeBean(new CoreBean(o, beanMetaData));
            }
            if (beanDefinition.getScope()==BeanScope.SINGLE){
                bean = new SingleBean(new CoreBean(o, beanMetaData));
            }
            context.registerBean(beanDefinition.getBeanName(), bean);
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
}
