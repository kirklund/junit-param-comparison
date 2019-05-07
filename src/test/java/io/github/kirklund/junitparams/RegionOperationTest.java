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

import java.util.function.Consumer;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.apache.geode.cache.Cache;
import org.apache.geode.cache.CacheFactory;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.RegionShortcut;

@RunWith(JUnitParamsRunner.class)
public class RegionOperationTest {

  private Cache cache;
  private Region<String, String> region;

  @Before
  public void setUp() {
    cache = new CacheFactory().create();
    region = cache.<String, String>createRegionFactory(RegionShortcut.LOCAL).create("region");
    region.put("key", "value");
  }

  @After
  public void tearDown() {
    cache.close();
  }

  @Test
  @Parameters({"NOTHING", "INVALIDATE", "DESTROY", "CLEAR"})
  @TestCaseName("{method}({params})")
  public void regionOperation(RegionOperation regionOperation) {
    regionOperation.operate(region);

    assertThat(region.containsKey("key")).isEqualTo(regionOperation.keyExistsAfterwards());
  }

  private enum RegionOperation {
    NOTHING(region -> {}, true),
    INVALIDATE(region -> region.invalidate("key"), true),
    DESTROY(region -> region.destroy("key"), false),
    CLEAR(region -> region.clear(), false);

    private final Consumer<Region<String, String>> operation;
    private final boolean keyExistsAfterwards;

    RegionOperation(Consumer<Region<String, String>> operation, boolean keyExistsAfterwards) {
      this.operation = operation;
      this.keyExistsAfterwards = keyExistsAfterwards;
    }

    void operate(Region<String, String> region) {
      operation.accept(region);
    }

    boolean keyExistsAfterwards() {
      return keyExistsAfterwards;
    }
  }
  @Test
  @Parameters({"NOTHING", "INVALIDATE", "DESTROY", "CLEAR"})
  @TestCaseName("{method}({params})")
  public void regionOperation(RegionOperationValidation regionOperationValidation) {
    regionOperationValidation.operate(region);

    regionOperationValidation.validate(region);
  }

  private enum RegionOperationValidation {
    NOTHING(region -> {}, region -> assertThat(region.containsKey("key")).isTrue()),
    INVALIDATE(region -> region.invalidate("key"), region -> assertThat(region.containsKey("key")).isTrue()),
    DESTROY(region -> region.destroy("key"), region -> assertThat(region.containsKey("key")).isFalse()),
    CLEAR(region -> region.clear(), region -> assertThat(region.containsKey("key")).isFalse());

    private final Consumer<Region<String, String>> operation;
    private final Consumer<Region<String, String>> validation;

    RegionOperationValidation(Consumer<Region<String, String>> operation, Consumer<Region<String, String>> validation) {
      this.operation = operation;
      this.validation = validation;
    }

    void operate(Region<String, String> region) {
      operation.accept(region);
    }

    void validate(Region<String, String> region) {
      validation.accept(region);
    }
  }
}
