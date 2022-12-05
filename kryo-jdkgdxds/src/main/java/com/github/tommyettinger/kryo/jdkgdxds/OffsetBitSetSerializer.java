/*
 * Copyright (c) 2022 See AUTHORS file.
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
import com.github.tommyettinger.ds.OffsetBitSet;

/**
 * Kryo {@link Serializer} for jdkgdxds {@link OffsetBitSet}s.
 */
public class OffsetBitSetSerializer extends Serializer<OffsetBitSet> {

    public OffsetBitSetSerializer() {
        setAcceptsNull(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final OffsetBitSet data) {
        long[] bits = data.getRawBits();
        int length = bits.length;
        int off = data.getOffset();
        output.writeInt(off);
        output.writeInt(length, true);
        output.writeLongs(bits, 0, length);
    }

    @Override
    public OffsetBitSet read(final Kryo kryo, final Input input, final Class<? extends OffsetBitSet> dataClass) {
        int off = input.readInt();
        int length = input.readInt(true);
        OffsetBitSet data = new OffsetBitSet();
        data.setRawBits(input.readLongs(length));
        data.setOffset(off);
        return data;
    }

    @Override
    public OffsetBitSet copy(Kryo kryo, OffsetBitSet original) {
        return new OffsetBitSet(original);
    }
}