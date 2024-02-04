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

package com.github.tommyettinger.kryo.juniper;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.github.tommyettinger.random.LongSequence;

/**
 * Kryo {@link Serializer} for jdkgdxds {@link LongSequence}s.
 */
public class LongSequenceSerializer extends Serializer<LongSequence> {

    public LongSequenceSerializer() {
        setAcceptsNull(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final LongSequence data) {
        int length = data.size();
        output.writeInt(length, true);
        for (int i = 0; i < length; i++)
            output.writeVarLong(data.get(i), false);
    }

    @Override
    public LongSequence read(final Kryo kryo, final Input input, final Class<? extends LongSequence> dataClass) {
        int length = input.readInt(true);
        LongSequence data = new LongSequence(length);
        for (int i = 0; i < length; i++)
            data.add(input.readVarLong(false));
        return data;
    }
    
    @Override
    public LongSequence copy(Kryo kryo, LongSequence original) {
        return original.copy();
    }
}