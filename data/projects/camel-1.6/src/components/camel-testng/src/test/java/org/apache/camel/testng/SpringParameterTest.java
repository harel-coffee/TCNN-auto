/**
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.testng;

import java.util.Properties;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @version $Revision: 630591 $
 */
public class SpringParameterTest extends SpringRunner {
    @DataProvider(name = "appContextAndProperties")
    public Object[][] createData() {

        return new Object[][]{{
                "spring.xml", createProperties("foo", "param1")
        }, {
                "spring.xml", createProperties("foo", "param2")
        }};
    }

    @Test(dataProvider = "appContextAndProperties")
    public void assertApplicationContextStarts(String applicationContextLocations, Properties properties) throws Exception {
        super.assertApplicationContextStarts(applicationContextLocations, properties);
    }
}
