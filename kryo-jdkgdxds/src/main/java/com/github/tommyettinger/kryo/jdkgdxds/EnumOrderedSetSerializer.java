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
import com.github.tommyettinger.ds.EnumOrderedSet;
import com.github.tommyettinger.ds.OrderType;

import java.util.Iterator;

/**
 * Kryo {@link Serializer} for jdkgdxds {@link EnumOrderedSet}s.
 * Requires the type of any enum values that are contained in an EnumOrderedSet to also be registered.
 */
public class EnumOrderedSetSerializer extends Serializer<EnumOrderedSet> {

    private static final OrderType[] ORDER_TYPES = OrderType.values();

    public EnumOrderedSetSerializer() {
        setAcceptsNull(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final EnumOrderedSet data) {
        int length = data.size();
        output.writeInt(length, true);
        output.writeVarInt(data.getOrderType().ordinal(), true);
        for (Iterator<Enum<?>> it = new EnumOrderedSet.EnumOrderedSetIterator(data); it.hasNext(); )
            kryo.writeClassAndObject(output, it.next());
    }

    @Override
    public EnumOrderedSet read(final Kryo kryo, final Input input, final Class<? extends EnumOrderedSet> dataClass) {
        int length = input.readInt(true);
        EnumOrderedSet data;
        data = new EnumOrderedSet(ORDER_TYPES[input.readVarInt(true)]);
        for (int i = 0; i < length; i++)
            data.add((Enum<?>)kryo.readClassAndObject(input));
        return data;
    }

    @Override
    public EnumOrderedSet copy(Kryo kryo, EnumOrderedSet original) {
        return new EnumOrderedSet(original);
    }
}
