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
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class PrimeNumberTest {

  @Test
  @Parameters({"2, true", "6, false", "19, true", "22, false", "23, true"})
  @TestCaseName("{method}({params})")
  public void isPrimeReturnsTrueIfNumberIsPrime(int number, boolean expectedResult) {
    boolean result = MathUtils.isPrime(number);

    assertThat(result).isEqualTo(expectedResult);
  }
}
