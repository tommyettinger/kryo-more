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
import com.github.tommyettinger.ds.EnumFloatMap;

/**
 * Kryo {@link Serializer} for jdkgdxds {@link EnumFloatMap}s.
 * Requires the type of any enum keys that are contained in an EnumFloatMap to also be registered.
 */
public class EnumFloatMapSerializer extends Serializer<EnumFloatMap> {

    public EnumFloatMapSerializer() {
    }

    @Override
    public void write(final Kryo kryo, final Output output, final EnumFloatMap data) {
        int length = data.size();
        output.writeInt(length, true);
        for(EnumFloatMap.Entry ent : data) {
            kryo.writeClassAndObject(output, ent.getKey());
            output.writeFloat(ent.getValue());
        }
    }

    @Override
    public EnumFloatMap read(final Kryo kryo, final Input input, final Class<? extends EnumFloatMap> dataClass) {
        int length = input.readInt(true);
        EnumFloatMap data = new EnumFloatMap();
        for (int i = 0; i < length; i++)
            data.put((Enum<?>)kryo.readClassAndObject(input), input.readFloat());
        return data;
    }

    @Override
    public EnumFloatMap copy(Kryo kryo, EnumFloatMap original) {
        EnumFloatMap map = new EnumFloatMap(original);
        kryo.reference(map);
        map.clear();
        for(EnumFloatMap.Entry ent : original) {
            map.put(ent.getKey(), ent.getValue());
        }
        return map;
    }
}