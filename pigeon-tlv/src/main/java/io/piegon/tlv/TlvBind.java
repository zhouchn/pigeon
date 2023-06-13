package io.piegon.tlv;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/5/29
 **/
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TlvBind {
    int number() default 0;

    boolean signed() default false;
}
