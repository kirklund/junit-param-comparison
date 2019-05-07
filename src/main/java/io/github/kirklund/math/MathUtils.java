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
package io.github.kirklund.math;

public class MathUtils {

  public static boolean isPrime(int number) {
    for (int i = 2; i < number / 2; i++) {
      if (number % i == 0) {
        return false;
      }
    }
    return true;
  }

  public static int fibonacci(int indexInSequence) {
    if (indexInSequence <= 1) {
      return indexInSequence;
    }
    return fibonacci(indexInSequence - 1) + fibonacci(indexInSequence - 2);
  }

  public static boolean isPositive(int number) {
    return number > 0;
  }

  public static boolean isZero(int number) {
    return number == 0;
  }
}
