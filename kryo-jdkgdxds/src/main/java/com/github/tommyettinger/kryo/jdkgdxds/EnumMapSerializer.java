/*
 * Copyright (c) 2022-2024 See AUTHORS file.
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
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.github.tommyettinger.ds.EnumMap;

import java.util.Iterator;
import java.util.Map;

/**
 * Kryo {@link Serializer} for jdkgdxds {@link EnumMap}s.
 * Requires the type of any enum keys that are contained in an EnumMap to also be registered.
 */
public class EnumMapSerializer extends Serializer<EnumMap<?>> {

    public EnumMapSerializer() {
    }

    @Override
    public void write(final Kryo kryo, final Output output, final EnumMap<?> data) {
        int length = data.size();
        output.writeInt(length, true);
        for(Iterator<? extends Map.Entry<Enum<?>, ?>> it = new EnumMap.Entries<>(data).iterator(); it.hasNext();) {
            Map.Entry<Enum<?>, ?> ent = it.next();
            kryo.writeClassAndObject(output, ent.getKey());
            kryo.writeClassAndObject(output, ent.getValue());
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked", "UnnecessaryLocalVariable"})
    @Override
    public EnumMap<?> read(final Kryo kryo, final Input input, final Class<? extends EnumMap<?>> dataClass) {
        int length = input.readInt(true);
        EnumMap<?> data = new EnumMap<>();
        EnumMap rawData = data;
        for (int i = 0; i < length; i++)
            rawData.put((Enum<?>)kryo.readClassAndObject(input), kryo.readClassAndObject(input));
        return data;
    }

    @SuppressWarnings({"rawtypes", "unchecked", "UnnecessaryLocalVariable"})
    @Override
    public EnumMap<?> copy(Kryo kryo, EnumMap<?> original) {
        EnumMap<?> map = new EnumMap<>(original);
        kryo.reference(map);
        map.clear();
        EnumMap rawMap = map;
        for(Map.Entry<Enum<?>, ?> ent : original) {
            rawMap.put(ent.getKey(), kryo.copy(ent.getValue()));
        }
        return map;
    }
}