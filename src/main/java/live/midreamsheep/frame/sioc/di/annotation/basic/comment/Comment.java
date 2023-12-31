package live.midreamsheep.frame.sioc.di.annotation.basic.comment;

import live.midreamsheep.frame.sioc.scan.annotation.meta.SIocFlag;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 用于标记交由applicationContext管理的对象
 * */
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@SIocFlag
public @interface Comment {
    //beanName
    String value() default "";
    /**
     * 作用域
     * 包括：Single Prototype
     * */
    String scope() default "SINGLE";
}
