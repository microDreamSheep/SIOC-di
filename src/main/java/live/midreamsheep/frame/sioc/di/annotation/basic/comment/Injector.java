package live.midreamsheep.frame.sioc.di.annotation.basic.comment;

import live.midreamsheep.frame.sioc.scan.annotation.meta.SIocFlag;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于标记依赖注入
 * */
@Retention(RetentionPolicy.RUNTIME)
@SIocFlag
//目标：字段 构造方法参数
@Target({ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.PARAMETER})
public @interface Injector {
    String value() default "";
}
