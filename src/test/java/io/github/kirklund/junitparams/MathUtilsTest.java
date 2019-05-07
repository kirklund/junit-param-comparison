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
public class MathUtilsTest {

  @Test
  @Parameters({"2, true", "6, false", "19, true", "22, false", "23, true"})
  @TestCaseName("{method}({params})")
  public void isPrimeReturnsTrueIfNumberIsPrime(int number, boolean expectedResult) {
    boolean result = MathUtils.isPrime(number);

    assertThat(result).isEqualTo(expectedResult);
  }

  @Test
  @Parameters({"0, 0", "1, 1", "2, 1", "3, 2", "4, 3", "5, 5", "6, 8"})
  @TestCaseName("{method}({params})")
  public void fibonacciReturnsNextIntegerInSequence(int indexInSequence, int expectedResult) {
    int result = MathUtils.fibonacci(indexInSequence);

    assertThat(result).isEqualTo(expectedResult);
  }

  @Test
  public void isPositiveReturnsFalseIfNumberIsLessThanZero() {
    boolean result = MathUtils.isPositive(-1);

    assertThat(result).isFalse();
  }

  @Test
  public void isPositiveReturnsFalseIfNumberIsZero() {
    boolean result = MathUtils.isPositive(0);

    assertThat(result).isFalse();
  }

  @Test
  public void isPositiveReturnsTrueIfNumberIsGreaterThanZero() {
    boolean result = MathUtils.isPositive(1);

    assertThat(result).isTrue();
  }

  @Test
  @Parameters({"-1, false", "0, false", "1, true"})
  @TestCaseName("{method}({params})")
  public void isPositiveReturnsTrueIfNumberIsGreaterThanZero(int number, boolean expectedResult) {
    boolean result = MathUtils.isPositive(number);

    assertThat(result).isEqualTo(expectedResult);
  }
}
