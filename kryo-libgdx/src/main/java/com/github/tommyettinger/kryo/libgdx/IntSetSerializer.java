/*
 * Copyright (c) 2025 See AUTHORS file.
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

package com.github.tommyettinger.kryo.libgdx;

import com.badlogic.gdx.utils.IntSet;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * Kryo {@link Serializer} for libGDX {@link IntSet}s.
 */
public class IntSetSerializer extends Serializer<IntSet> {

    public IntSetSerializer() {
        setImmutable(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final IntSet data) {
        output.writeVarInt(data.size, true);
        IntSet.IntSetIterator it = data.iterator();
        for (int item; it.hasNext;) {
            item = it.next();
            output.writeInt(item);
        }
    }

    @Override
    public IntSet read(final Kryo kryo, final Input input, final Class<? extends IntSet> dataClass) {
        final int len = input.readVarInt(true);
        IntSet data = new IntSet(len);
        for (int i = 0; i < len; i++) {
            data.add(input.readInt());
        }
        return data;
    }

    @Override
    public IntSet copy(Kryo kryo, IntSet original) {
        return new IntSet(original);
    }
}