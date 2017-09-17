package com.tmtron.checkerfw;

public class SubtypingTest {

    @SuppressWarnings({"RedundantCast", "cast.unsafe"})
    public static @IdCustomer long toCustomerId(long id) {
        return (@IdCustomer long) id;
    }

    public static @IdCustomer long noop(final @IdCustomer long input) {
        return input;
    }

    public static <T> T genericTest(final T input) {
        return input;
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
