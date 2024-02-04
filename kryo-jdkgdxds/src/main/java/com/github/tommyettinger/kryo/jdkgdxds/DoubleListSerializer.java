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
import com.github.tommyettinger.ds.DoubleList;

/**
 * Kryo {@link Serializer} for jdkgdxds {@link DoubleList}s.
 */
public class DoubleListSerializer extends Serializer<DoubleList> {

    public DoubleListSerializer() {
        setAcceptsNull(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final DoubleList data) {
        int length = data.size();
        output.writeInt(length, true);
        output.writeDoubles(data.items, 0, length);
    }

    @Override
    public DoubleList read(final Kryo kryo, final Input input, final Class<? extends DoubleList> dataClass) {
        int length = input.readInt(true);
        DoubleList data = new DoubleList(length);
        for (int i = 0; i < length; i++)
            data.add(input.readDouble());
        return data;
    }

    @Override
    public DoubleList copy(Kryo kryo, DoubleList original) {
        return new DoubleList(original);
    }
}