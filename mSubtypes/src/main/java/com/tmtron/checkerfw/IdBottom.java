package com.tmtron.checkerfw;

import org.checkerframework.framework.qual.DefaultFor;
import org.checkerframework.framework.qual.ImplicitFor;
import org.checkerframework.framework.qual.InvisibleQualifier;
import org.checkerframework.framework.qual.LiteralKind;
import org.checkerframework.framework.qual.SubtypeOf;
import org.checkerframework.framework.qual.TypeUseLocation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Denotes that the representation of an object is encrypted.
 */
@SubtypeOf({IdCustomer.class, IdDomainObject.class})
@ImplicitFor(literals = {LiteralKind.NULL}, typeNames = java.lang.Void.class)
@DefaultFor(TypeUseLocation.LOWER_BOUND)
@Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
@InvisibleQualifier
public @interface IdBottom {
}
