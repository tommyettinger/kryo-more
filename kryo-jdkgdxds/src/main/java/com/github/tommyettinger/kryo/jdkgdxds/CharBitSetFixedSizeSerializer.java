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
import com.github.tommyettinger.ds.CharBitSetFixedSize;

/**
 * Kryo {@link Serializer} for jdkgdxds {@link CharBitSetFixedSize}s.
 */
public class CharBitSetFixedSizeSerializer extends Serializer<CharBitSetFixedSize> {

    public CharBitSetFixedSizeSerializer() {
        setAcceptsNull(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final CharBitSetFixedSize data) {
        int[] bits = data.getRawBits();
        output.writeInts(bits, 0, 2048);
    }

    @Override
    public CharBitSetFixedSize read(final Kryo kryo, final Input input, final Class<? extends CharBitSetFixedSize> dataClass) {
        CharBitSetFixedSize cbsr = new CharBitSetFixedSize();
        cbsr.setRawBits(input.readInts(2048));
        return cbsr;
    }

    @Override
    public CharBitSetFixedSize copy(Kryo kryo, CharBitSetFixedSize original) {
        return new CharBitSetFixedSize(original);
    }
}