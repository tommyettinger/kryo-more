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
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.CollectionSerializer;
import com.github.tommyettinger.ds.EnumSet;
import com.github.tommyettinger.ds.EnumSet;

import java.util.Iterator;

public class EnumSetSerializer extends CollectionSerializer<EnumSet> {

    public EnumSetSerializer() {
        setAcceptsNull(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final EnumSet data) {
        int length = data.size();
        output.writeInt(length, true);
        for (Iterator<Enum<?>> it = new EnumSet.ESetIterator(data); it.hasNext(); )
            kryo.writeClassAndObject(output, it.next());
    }

    @Override
    public EnumSet read(final Kryo kryo, final Input input, final Class<? extends EnumSet> dataClass) {
        int length = input.readInt(true);
        EnumSet data;
        data = new EnumSet();
        for (int i = 0; i < length; i++)
            data.add((Enum<?>)kryo.readClassAndObject(input));
        return data;
    }

    @Override
    public EnumSet copy(Kryo kryo, EnumSet original) {
        return new EnumSet(original);
    }
}
