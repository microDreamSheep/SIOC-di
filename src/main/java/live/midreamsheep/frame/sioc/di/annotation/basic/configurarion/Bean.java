package live.midreamsheep.frame.sioc.di.annotation.basic.configurarion;

import live.midreamsheep.frame.sioc.scan.annotation.meta.SIocFlag;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@SIocFlag
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Bean {
    String value() default "";
}
