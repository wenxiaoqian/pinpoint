/*
 * *
 *  * Copyright 2014 NAVER Corp.
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.navercorp.pinpoint.plugin.spring.beans.interceptor;

import com.navercorp.pinpoint.bootstrap.context.SpanEventRecorder;
import com.navercorp.pinpoint.bootstrap.context.Trace;
import com.navercorp.pinpoint.test.mock.MockTraceContext;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;

/**
 * @author Woonduk Kang(emeroad)
 */
public class BeanMethodInterceptorTest {

    @Test
    public void testBefore() throws Exception {

    }

    @Test
    public void testAfter() throws Exception {
        final MockTraceContext traceContext = new MockTraceContext();
        final Trace trace = Mockito.mock(Trace.class);
        traceContext.setTrace(trace);
        final SpanEventRecorder recorder = Mockito.mock(SpanEventRecorder.class);
        Mockito.when(trace.currentSpanEventRecorder()).thenReturn(recorder);

        final BeanMethodInterceptor beanMethodInterceptor = new BeanMethodInterceptor(traceContext, true);

        Object thisObject = new Object();
        final Exception throwable = new Exception();
        beanMethodInterceptor.after(thisObject, 10, null, null, throwable);

        Mockito.verify(recorder, times(1)).recordException(true, throwable);
    }
}