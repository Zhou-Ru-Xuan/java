package com.zhouruxuan.unitTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MockitoTest {
    @Test
    public void spy_test() {
        List<String> list = new LinkedList<>();
        List<String> spy = spy(list);
        when(spy.size()).thenReturn(100);

        spy.add("one");
        spy.add("two");
        assertEquals(100, spy.size());
        assertEquals("one", spy.get(0));
    }

    @Test
    public void mock_test() {
        List<String> mock = mock(LinkedList.class);
        when(mock.size()).thenReturn(100);
        mock.add("one");
        mock.add("two");
        assertEquals(100, mock.size());
        assertNull(mock.get(0));
    }
}
