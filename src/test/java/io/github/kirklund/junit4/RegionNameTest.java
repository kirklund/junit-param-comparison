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
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import org.apache.geode.cache.Cache;
import org.apache.geode.cache.CacheFactory;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.RegionShortcut;

@RunWith(Parameterized.class)
public class RegionNameTest {

  private Cache cache;
  private Region<String, String> region;

  @Parameter
  public int numberOfPuts;

  @Parameters(name = "Parameters-{index}_number-{0}")
  public static Collection<Integer> values() {
    return Arrays.asList(0, 1, 10);
  }

  @Rule
  public TestName testName = new TestName();

  @Before
  public void setUp() {
    cache = new CacheFactory().create();
    String regionName = "Region-" + testName.getMethodName();
    region = cache.<String, String>createRegionFactory(RegionShortcut.LOCAL).create(regionName);
  }

  @After
  public void tearDown() {
    cache.close();
  }

  @Test
  public void performPuts() {
    for (int i = 0; i < numberOfPuts; i++) {
      region.put("key-" + i, "value-" + i);
    }

    assertThat(region.size()).isEqualTo(numberOfPuts);
  }
}
