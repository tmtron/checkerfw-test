package com.tmtron.checkerfw;

import org.junit.Assert;
import org.junit.Test;

import static com.tmtron.checkerfw.Subtyping.toCustomerId;
import static com.tmtron.checkerfw.Subtyping.toUserId;

public class SubtypingTest {

    @Test
    public void test() {
        Long along = 1L;
        @IdUser Long userId1 = toUserId(1L);
        @IdUser Long userId2 = toUserId(2L);
        @IdCustomer Long customerId1 = toCustomerId(1);

        Assert.assertNotEquals(userId1, userId2);
        // TODO: is there a way to make this fail??
        Assert.assertEquals(userId1, customerId1);
    }

}