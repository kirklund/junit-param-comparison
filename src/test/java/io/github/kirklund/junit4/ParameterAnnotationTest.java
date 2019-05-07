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
package io.github.kirklund.junit4;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import io.github.kirklund.math.MathUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ParameterAnnotationTest {

  @Parameter // implicitly (0)
  public int number;

  @Parameter(1)
  public boolean expectedResult;

  @Parameters(name = "Parameters({index}): number={0}, expectedResult={1}")
  public static Iterable<Object[]> values() {
    return Arrays.asList(new Object[][]{
        {2, true},
        {6, false},
        {19, true},
        {22, false},
        {23, true}
    });
  }

  @Test
  public void isPrimeReturnsTrueIfNumberIsPrime() {
    boolean result = MathUtils.isPrime(number);

    assertThat(result).isEqualTo(expectedResult);
  }
}