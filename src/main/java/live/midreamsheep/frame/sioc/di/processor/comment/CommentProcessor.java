package live.midreamsheep.frame.sioc.di.processor.comment;

import live.midreamsheep.frame.sioc.api.handle.ContextHandler;
import live.midreamsheep.frame.sioc.di.annotation.basic.comment.Comment;
import live.midreamsheep.frame.sioc.di.annotation.basic.comment.Injector;
import live.midreamsheep.frame.sioc.di.handler.comment.BeanDefinition;
import live.midreamsheep.frame.sioc.di.handler.comment.BeanDefinitionHandler;
import live.midreamsheep.frame.sioc.di.handler.comment.BeanScope;
import live.midreamsheep.frame.sioc.di.handler.comment.CoreBeanDefinition;
import live.midreamsheep.frame.sioc.di.handler.comment.field.FieldInjectHandler;
import live.midreamsheep.frame.sioc.scan.annotation.meta.ProcessorFlag;
import live.midreamsheep.frame.sioc.scan.clazz.ClassMetaDefinition;
import live.midreamsheep.frame.sioc.scan.clazz.field.FieldInter;
import live.midreamsheep.frame.sioc.scan.clazz.method.MethodInter;
import live.midreamsheep.frame.sioc.scan.processor.core.register.RegisterProcessor;

import java.lang.reflect.Constructor;
import java.util.LinkedList;
import java.util.List;

import static live.midreamsheep.frame.sioc.di.tool.BeanNameTool.getBeanName;

@ProcessorFlag({Comment.class})
public class CommentProcessor extends RegisterProcessor {
    @Override
    public void process(ClassMetaDefinition classMetaDefinition, List<ContextHandler> contextHandlerList) {
        //处理构造方法的注入
        contextHandlerList.add(constructorInject(classMetaDefinition.getMethodInfo().getConstructorList(),classMetaDefinition));
        //处理字段的注入
        contextHandlerList.addAll(FieldInject(classMetaDefinition));
    }

    private List<ContextHandler> FieldInject(ClassMetaDefinition classMetaDefinition) {
        List<ContextHandler> contextHandlerList = new LinkedList<>();
        for (FieldInter fieldInter : classMetaDefinition.getFieldInfo().getFieldInterList()) {
            Injector annotation = fieldInter.getAnnotationInfo().getAnnotation(Injector.class);
            if (annotation==null){
                continue;
            }
            contextHandlerList.add(new FieldInjectHandler(classMetaDefinition.getId(),fieldInter));
        }
        return contextHandlerList;
    }

    private ContextHandler constructorInject(List<MethodInter<Constructor<?>>> constructorList, ClassMetaDefinition classMetaDefinition){
        Comment annotation = classMetaDefinition.getAnnotationInfo().getAnnotation(Comment.class);
        //构建BeanDefinition
        BeanDefinition beanDefinition = new CoreBeanDefinition();
        beanDefinition.setBeanId(classMetaDefinition.getId());
        beanDefinition.setBeanName(getBeanName(annotation.value(),classMetaDefinition.getOwnClass()));
        beanDefinition.setScope(BeanScope.getScope(annotation.scope()));
        beanDefinition.setBeanClass(classMetaDefinition.getOwnClass());

        MethodInter<Constructor<?>> target = null;
        for (MethodInter<Constructor<?>> methodInter : constructorList) {
            if (methodInter.getAnnotationInfo().getAnnotation(Injector.class)!=null){
                target=methodInter;
                break;
            }
            if(methodInter.getParameterTypes().length==0){
                target=methodInter;
            }
        }
        assert target != null:"not found suitable constructor method.please ensure a null constructor or a @Injector annotation tag";
        return new BeanDefinitionHandler(beanDefinition,target);
    }


}