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
import com.github.tommyettinger.ds.IntLongMap;

/**
 * Kryo {@link Serializer} for jdkgdxds {@link IntLongMap}s.
 */
public class IntLongMapSerializer extends Serializer<IntLongMap> {

    public IntLongMapSerializer() {
        setAcceptsNull(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final IntLongMap data) {
        int length = data.size();
        output.writeInt(length, true);
        output.writeVarLong(data.getDefaultValue(), false);
        for(IntLongMap.EntryIterator it = new IntLongMap.EntryIterator(data); it.hasNext();) {
            IntLongMap.Entry ent = it.next();
            output.writeVarInt(ent.key, false);
            output.writeVarLong(ent.value, false);
        }
    }

    @Override
    public IntLongMap read(final Kryo kryo, final Input input, final Class<? extends IntLongMap> dataClass) {
        int length = input.readInt(true);
        IntLongMap data = new IntLongMap(length);
        data.setDefaultValue(input.readVarLong(false));
        for (int i = 0; i < length; i++)
            data.put(input.readVarInt(false), input.readVarLong(false));
        return data;
    }

    @Override
    public IntLongMap copy(Kryo kryo, IntLongMap original) {
        return new IntLongMap(original);
    }
}