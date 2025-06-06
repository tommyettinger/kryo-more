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
import com.github.tommyettinger.ds.IntFloatMap;

/**
 * Kryo {@link Serializer} for jdkgdxds {@link IntFloatMap}s.
 */
public class IntFloatMapSerializer extends Serializer<IntFloatMap> {

    public IntFloatMapSerializer() {
        setAcceptsNull(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final IntFloatMap data) {
        int length = data.size();
        output.writeInt(length, true);
        output.writeFloat(data.getDefaultValue());
        for(IntFloatMap.EntryIterator it = new IntFloatMap.EntryIterator(data); it.hasNext();) {
            IntFloatMap.Entry ent = it.next();
            output.writeVarInt(ent.key, false);
            output.writeFloat(ent.value);
        }
    }

    @Override
    public IntFloatMap read(final Kryo kryo, final Input input, final Class<? extends IntFloatMap> dataClass) {
        int length = input.readInt(true);
        IntFloatMap data = new IntFloatMap(length);
        data.setDefaultValue(input.readFloat());
        for (int i = 0; i < length; i++)
            data.put(input.readVarInt(false), input.readFloat());
        return data;
    }

    @Override
    public IntFloatMap copy(Kryo kryo, IntFloatMap original) {
        return new IntFloatMap(original);
    }
}