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
import com.github.tommyettinger.ds.ByteDeque;

/**
 * Kryo {@link Serializer} for jdkgdxds {@link ByteDeque}s.
 */
public class ByteDequeSerializer extends Serializer<ByteDeque> {

    public ByteDequeSerializer() {
        setAcceptsNull(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final ByteDeque data) {
        int length = data.size();
        output.writeInt(length, true);
        output.writeByte(data.getDefaultValue());
        for (int i = 0; i < length; i++)
            output.writeByte(data.get(i));
    }

    @Override
    public ByteDeque read(final Kryo kryo, final Input input, final Class<? extends ByteDeque> dataClass) {
        int length = input.readInt(true);
        ByteDeque data = new ByteDeque(length);
        data.setDefaultValue(input.readByte());
        for (int i = 0; i < length; i++)
            data.add(input.readByte());
        return data;
    }

    @Override
    public ByteDeque copy(Kryo kryo, ByteDeque original) {
        return new ByteDeque(original);
    }
}