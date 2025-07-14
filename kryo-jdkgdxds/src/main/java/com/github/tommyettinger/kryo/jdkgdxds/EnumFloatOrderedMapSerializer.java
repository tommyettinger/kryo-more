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
import com.github.tommyettinger.ds.EnumFloatOrderedMap;
import com.github.tommyettinger.ds.OrderType;

/**
 * Kryo {@link Serializer} for jdkgdxds {@link EnumFloatOrderedMap}s.
 * Requires the type of any enum keys that are contained in an EnumFloatOrderedMap to also be registered.
 */
public class EnumFloatOrderedMapSerializer extends Serializer<EnumFloatOrderedMap> {

    private static final OrderType[] ORDER_TYPES = OrderType.values();

    public EnumFloatOrderedMapSerializer() {
    }

    @Override
    public void write(final Kryo kryo, final Output output, final EnumFloatOrderedMap data) {
        int length = data.size();
        output.writeInt(length, true);
        output.writeVarInt(data.getOrderType().ordinal(), true);
        output.writeFloat(data.getDefaultValue());
        for(EnumFloatOrderedMap.Entry ent : data) {
            kryo.writeClassAndObject(output, ent.getKey());
            output.writeFloat(ent.getValue());
        }
    }

    @Override
    public EnumFloatOrderedMap read(final Kryo kryo, final Input input, final Class<? extends EnumFloatOrderedMap> dataClass) {
        int length = input.readInt(true);
        EnumFloatOrderedMap data = new EnumFloatOrderedMap(ORDER_TYPES[input.readVarInt(true)]);
        data.setDefaultValue(input.readFloat());
        for (int i = 0; i < length; i++)
            data.put((Enum<?>)kryo.readClassAndObject(input), input.readFloat());
        return data;
    }

    @Override
    public EnumFloatOrderedMap copy(Kryo kryo, EnumFloatOrderedMap original) {
        EnumFloatOrderedMap map = new EnumFloatOrderedMap(original);
        kryo.reference(map);
        map.clear();
        for(EnumFloatOrderedMap.Entry ent : original) {
            map.put(ent.getKey(), ent.getValue());
        }
        return map;
    }
}