/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dromara.soul.admin.listener.nacos;

import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;

import java.util.HashMap;
import java.util.Map;

public class NacosMockConfigService implements ConfigService {

    private static final Map<String, String> EMPTY = new HashMap<>();

    private Map<String, Map<String, String>> store = new HashMap<>();

    @Override
    public String getConfig(final String s, final String s1, final long l) throws NacosException {
        return store.getOrDefault(s, EMPTY).getOrDefault(s1, "{}");
    }

    @Override
    public String getConfigAndSignListener(
            final String s, final String s1, final long l, final Listener listener)
            throws NacosException {

        return null;
    }

    @Override
    public void addListener(final String s, final String s1, final Listener listener)
            throws NacosException {
    }

    @Override
    public boolean publishConfig(final String key, final String group, final String result)
            throws NacosException {
        Map<String, String> row = store.getOrDefault(key, new HashMap<>());
        row.put(group, result);
        store.put(key, row);
        return true;
    }

    @Override
    public boolean removeConfig(final String s, final String s1) throws NacosException {
        return false;
    }

    @Override
    public void removeListener(final String s, final String s1, final Listener listener) {
    }

    @Override
    public String getServerStatus() {
        return null;
    }
}