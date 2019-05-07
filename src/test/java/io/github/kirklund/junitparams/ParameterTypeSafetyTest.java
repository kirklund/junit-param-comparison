/*
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
package io.github.kirklund.junitparams;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.kirklund.math.MathUtils;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class ParameterTypeSafetyTest {

  @Test
  @Parameters({"0,0", "1,1", "2,1", "3,2", "4,3", "5,5", "6,8"})
  @TestCaseName("{method}({params})")
  public void fibonacciSequence(int indexInSequence, boolean expectedResult) {
    int result = MathUtils.fibonacci(indexInSequence);

    assertThat(result).isEqualTo(expectedResult);
  }
}
