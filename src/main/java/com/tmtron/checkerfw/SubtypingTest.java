package com.tmtron.checkerfw;

public class SubtypingTest {

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

    public void test() {
        Long along = 1L;
        @IdUser Long userId;
        @IdCustomer Long customerId1 = toCustomerId(1);
        @IdCustomer Long customerId2;

        customerId2 = noop(customerId1);
        genericTest(customerId1);

        // when activated this fails with the expected message: required: @IdUser Long
        // userId = customerId;
    }

}
