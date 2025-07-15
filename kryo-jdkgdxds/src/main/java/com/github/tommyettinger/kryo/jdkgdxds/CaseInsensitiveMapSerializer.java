/*
 * Copyright (c) 2023-2024 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.github.tommyettinger.kryo.jdkgdxds;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.MapSerializer;
import com.github.tommyettinger.ds.CaseInsensitiveMap;

public class CaseInsensitiveMapSerializer extends MapSerializer<CaseInsensitiveMap<?>> {
    public CaseInsensitiveMapSerializer() {
        super();
        setKeysCanBeNull(false);
    }

    @Override
    protected void writeHeader(Kryo kryo, Output output, CaseInsensitiveMap<?> data) {
        kryo.writeClassAndObject(output, data.getDefaultValue());
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    protected CaseInsensitiveMap<?> create(Kryo kryo, Input input, Class<? extends CaseInsensitiveMap<?>> type, int size) {
        CaseInsensitiveMap data = new CaseInsensitiveMap(size);
        data.setDefaultValue(kryo.readClassAndObject(input));
        return data;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    protected CaseInsensitiveMap<?> createCopy(Kryo kryo, CaseInsensitiveMap<?> original) {
        CaseInsensitiveMap data = new CaseInsensitiveMap(original.size(), original.getLoadFactor());
        data.setDefaultValue(original.getDefaultValue());
        return data;
    }
}
