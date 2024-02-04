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
import com.github.tommyettinger.ds.IntBag;

/**
 * Kryo {@link Serializer} for jdkgdxds {@link IntBag}s.
 */
public class IntBagSerializer extends Serializer<IntBag> {

    public IntBagSerializer() {
        setAcceptsNull(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final IntBag data) {
        int length = data.size();
        output.writeInt(length, true);
        for (int i = 0; i < length; i++)
            output.writeVarInt(data.get(i), false);
    }

    @Override
    public IntBag read(final Kryo kryo, final Input input, final Class<? extends IntBag> dataClass) {
        int length = input.readInt(true);
        IntBag data = new IntBag(length);
        for (int i = 0; i < length; i++)
            data.add(input.readVarInt(false));
        return data;
    }

    @Override
    public IntBag copy(Kryo kryo, IntBag original) {
        return new IntBag(original);
    }
}