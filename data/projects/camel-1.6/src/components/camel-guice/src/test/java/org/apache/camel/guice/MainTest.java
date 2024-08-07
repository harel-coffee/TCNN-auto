/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.guice;

import java.util.List;

import junit.framework.TestCase;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;

/**
 * @version $Revision: 708078 $
 */
public class MainTest extends TestCase {
    protected Main main = new Main();
    protected String uri = "mock:results";
    protected Object expectedBody = "<hello>world!</hello>";

    public void testMain() throws Exception {
        main.start();
        List<CamelContext> contexts = main.getCamelContexts();
        assertEquals("Expected size : " + contexts, 1, contexts.size());
        CamelContext camelContext = contexts.get(0);

        ProducerTemplate template = main.getCamelTemplate();
        assertNotNull("should have a template!", template);
        MockEndpoint endpoint = camelContext.getEndpoint(uri, MockEndpoint.class);
        endpoint.expectedBodiesReceived(expectedBody);

        template.sendBody(uri, expectedBody);

        endpoint.assertIsSatisfied();
    }

    @Override
    protected void tearDown() throws Exception {
        if (main != null) {
            main.stop();
        }
    }
}
