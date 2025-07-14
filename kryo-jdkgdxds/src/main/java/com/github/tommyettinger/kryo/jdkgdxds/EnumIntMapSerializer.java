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
import com.github.tommyettinger.ds.EnumIntMap;

/**
 * Kryo {@link Serializer} for jdkgdxds {@link EnumIntMap}s.
 * Requires the type of any enum keys that are contained in an EnumIntMap to also be registered.
 */
public class EnumIntMapSerializer extends Serializer<EnumIntMap> {

    public EnumIntMapSerializer() {
    }

    @Override
    public void write(final Kryo kryo, final Output output, final EnumIntMap data) {
        int length = data.size();
        output.writeInt(length, true);
        output.writeInt(data.getDefaultValue());
        for(EnumIntMap.Entry ent : data) {
            kryo.writeClassAndObject(output, ent.getKey());
            output.writeVarInt(ent.getValue(), false);
        }
    }

    @Override
    public EnumIntMap read(final Kryo kryo, final Input input, final Class<? extends EnumIntMap> dataClass) {
        int length = input.readInt(true);
        EnumIntMap data = new EnumIntMap();
        data.setDefaultValue(input.readInt());
        for (int i = 0; i < length; i++)
            data.put((Enum<?>)kryo.readClassAndObject(input), input.readVarInt(false));
        return data;
    }

    @Override
    public EnumIntMap copy(Kryo kryo, EnumIntMap original) {
        EnumIntMap map = new EnumIntMap(original);
        kryo.reference(map);
        map.clear();
        for(EnumIntMap.Entry ent : original) {
            map.put(ent.getKey(), ent.getValue());
        }
        return map;
    }
}