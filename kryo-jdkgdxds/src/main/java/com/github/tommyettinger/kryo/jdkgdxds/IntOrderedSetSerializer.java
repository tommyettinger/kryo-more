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
import com.github.tommyettinger.ds.IntDeque;
import com.github.tommyettinger.ds.IntOrderedSet;
import com.github.tommyettinger.ds.Utilities;

/**
 * Kryo {@link Serializer} for jdkgdxds {@link IntOrderedSet}s.
 */
public class IntOrderedSetSerializer extends Serializer<IntOrderedSet> {

    public IntOrderedSetSerializer() {
        setAcceptsNull(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final IntOrderedSet data) {
        int length = data.size();
        output.writeInt(length, true);
        output.writeBoolean(data.order() instanceof IntDeque);
        for(IntOrderedSet.IntOrderedSetIterator it = new IntOrderedSet.IntOrderedSetIterator(data); it.hasNext();)
            output.writeVarInt(it.nextInt(), false);
    }

    @Override
    public IntOrderedSet read(final Kryo kryo, final Input input, final Class<? extends IntOrderedSet> dataClass) {
        int length = input.readInt(true);
        IntOrderedSet data = new IntOrderedSet(length, Utilities.getDefaultLoadFactor(), input.readBoolean());
        for (int i = 0; i < length; i++)
            data.add(input.readVarInt(false));
        return data;
    }

    @Override
    public IntOrderedSet copy(Kryo kryo, IntOrderedSet original) {
        return new IntOrderedSet(original);
    }
}