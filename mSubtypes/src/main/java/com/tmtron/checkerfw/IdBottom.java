package com.tmtron.checkerfw;

import org.checkerframework.framework.qual.ImplicitFor;
import org.checkerframework.framework.qual.LiteralKind;
import org.checkerframework.framework.qual.SubtypeOf;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Denotes that the representation of an object is encrypted.
 */
@SubtypeOf({IdCustomer.class, IdDomainObject.class})
@ImplicitFor(literals = {LiteralKind.NULL})
@Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
public @interface IdBottom {
}
