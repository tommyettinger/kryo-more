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
import com.github.tommyettinger.ds.BooleanDeque;

/**
 * Kryo {@link Serializer} for jdkgdxds {@link BooleanDeque}s.
 */
public class BooleanDequeSerializer extends Serializer<BooleanDeque> {

    public BooleanDequeSerializer() {
        setAcceptsNull(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final BooleanDeque data) {
        int length = data.size();
        output.writeInt(length, true);
        output.writeBoolean(data.getDefaultValue());
        for (int i = 0; i < length; i++)
            output.writeBoolean(data.get(i));
    }

    @Override
    public BooleanDeque read(final Kryo kryo, final Input input, final Class<? extends BooleanDeque> dataClass) {
        int length = input.readInt(true);
        BooleanDeque data = new BooleanDeque(length);
        data.setDefaultValue(input.readBoolean());
        for (int i = 0; i < length; i++)
            data.add(input.readBoolean());
        return data;
    }

    @Override
    public BooleanDeque copy(Kryo kryo, BooleanDeque original) {
        return new BooleanDeque(original);
    }
}