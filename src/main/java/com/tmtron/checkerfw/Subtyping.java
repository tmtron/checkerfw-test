package com.tmtron.checkerfw;

import java.util.concurrent.Callable;

public class Subtyping {

    @SuppressWarnings({"RedundantCast", "cast.unsafe"})
    public static @IdCustomer long toCustomerId(long id) {
        return (@IdCustomer long) id;
    }

    @SuppressWarnings({"RedundantCast", "cast.unsafe"})
    public static @IdUser long toUserId(long id) {
        return (@IdUser long) id;
    }

    public static @IdCustomer long noop(final @IdCustomer long input) {

        return input;
    }

    /* this will only compile when
     * - we have a bottom type that is a subtype of all other types and is implicitly used for null
     * - the top type is the default for LOWER_BOUND and UPPER_BOUND
     *
     * see 29.4.7 https://checkerframework.org/manual/#alint
     * | Bottom qualifier
     * | Your type hierarchy must have a bottom qualifier — a qualifier that is a (direct or indirect) subtype of
     * every other qualifier.
     * | Your type system must give null the bottom type...
     *
     */
    public static <T> T genericTest(final T input) {
        return input;
    }

    <T> T f() {
        return null;
    }

    // RxJava2 example
    public static <T> GenericHolder<T> fromCallable(final Callable<? extends T> callable) {
        GenericHolder<T> result = new GenericHolder<T>();
        try {
            result.field = callable.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void testFromCallable1() {
        GenericHolder<@IdCustomer Long> tmp = fromCallable(new Callable<@IdCustomer Long>() {
            @Override
            public @IdCustomer Long call() throws Exception {
                final @IdCustomer Long customerId = toCustomerId(1);
                return customerId;
            }
        });
    }

    /* this FAILS with this error-message:
     *
     *        GenericHolder<@IdCustomer Long> tmp2 = fromCallable(() -> {
     *   required: @IdDomainObject GenericHolder<@IdCustomer Long>
     */
    public void testFromCallable2() {
        GenericHolder<@IdCustomer Long> tmp2 = fromCallable(() -> {
                    final @IdCustomer Long customerId = toCustomerId(1);
                    return customerId;
                }
        );
    }

    public void test() {
        Long along = 1L;
        @IdUser Long userId = toUserId(1);
        @IdCustomer Long customerId1 = toCustomerId(1);
        @IdCustomer Long customerId2;

        customerId2 = noop(customerId1);
        genericTest(customerId1);

        new CustomerIdHolder().field = customerId1;
        // the next line must not compile
        // new CustomerIdHolder().field = userId;

        // when activated this fails with the expected message: required: @IdUser Long
        // userId = customerId;
    }

    static class GenericHolder<T> {
        public T field;
    }

    static class CustomerIdHolder extends GenericHolder<@IdCustomer Long> {

        public CustomerIdHolder() {
            super();
        }
    }

}
