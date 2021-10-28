package com.lz.treebuilder.tree;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TreeSort {
    TreeSortRule rule() default TreeSortRule.ASC;

    TreeSortType type() default TreeSortType.LONG;
}
