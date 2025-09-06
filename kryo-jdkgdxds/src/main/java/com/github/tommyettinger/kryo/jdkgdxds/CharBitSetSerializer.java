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
import com.github.tommyettinger.ds.CharBitSet;

/**
 * Kryo {@link Serializer} for jdkgdxds {@link CharBitSet}s.
 */
public class CharBitSetSerializer extends Serializer<CharBitSet> {

    public CharBitSetSerializer() {
        setAcceptsNull(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final CharBitSet data) {
        int[] bits = data.getRawBits();
        int length = bits.length;
        output.writeInt(length, true);
        output.writeInts(bits, 0, length);
    }

    @Override
    public CharBitSet read(final Kryo kryo, final Input input, final Class<? extends CharBitSet> dataClass) {
        int length = input.readInt(true);
        return new CharBitSet(input.readInts(length), true);
    }

    @Override
    public CharBitSet copy(Kryo kryo, CharBitSet original) {
        return new CharBitSet(original);
    }
}