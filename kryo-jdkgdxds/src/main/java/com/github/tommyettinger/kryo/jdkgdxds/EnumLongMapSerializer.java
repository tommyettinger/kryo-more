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
import com.github.tommyettinger.ds.EnumLongMap;

/**
 * Kryo {@link Serializer} for jdkgdxds {@link EnumLongMap}s.
 * Requires the type of any enum keys that are contained in an EnumLongMap to also be registered.
 */
public class EnumLongMapSerializer extends Serializer<EnumLongMap> {

    public EnumLongMapSerializer() {
    }

    @Override
    public void write(final Kryo kryo, final Output output, final EnumLongMap data) {
        int length = data.size();
        output.writeInt(length, true);
        output.writeLong(data.getDefaultValue());
        for(EnumLongMap.Entry ent : data) {
            kryo.writeClassAndObject(output, ent.getKey());
            output.writeVarLong(ent.getValue(), false);
        }
    }

    @Override
    public EnumLongMap read(final Kryo kryo, final Input input, final Class<? extends EnumLongMap> dataClass) {
        int length = input.readInt(true);
        EnumLongMap data = new EnumLongMap();
        data.setDefaultValue(input.readLong());
        for (int i = 0; i < length; i++)
            data.put((Enum<?>)kryo.readClassAndObject(input), input.readVarLong(false));
        return data;
    }

    @Override
    public EnumLongMap copy(Kryo kryo, EnumLongMap original) {
        EnumLongMap map = new EnumLongMap(original);
        kryo.reference(map);
        map.clear();
        for(EnumLongMap.Entry ent : original) {
            map.put(ent.getKey(), ent.getValue());
        }
        return map;
    }
}