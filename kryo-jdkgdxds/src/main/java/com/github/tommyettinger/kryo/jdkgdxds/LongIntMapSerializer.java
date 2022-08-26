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
import com.github.tommyettinger.ds.LongIntMap;

/**
 * Kryo {@link Serializer} for jdkgdxds {@link LongIntMap}s.
 */
public class LongIntMapSerializer extends Serializer<LongIntMap> {

    public LongIntMapSerializer() {
        setAcceptsNull(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final LongIntMap data) {
        int length = data.size();
        output.writeInt(length, true);
        for(LongIntMap.EntryIterator it = new LongIntMap.EntryIterator(data); it.hasNext();) {
            LongIntMap.Entry ent = it.next();
            output.writeVarLong(ent.key, false);
            output.writeVarInt(ent.value, false);
        }
    }

    @Override
    public LongIntMap read(final Kryo kryo, final Input input, final Class<? extends LongIntMap> dataClass) {
        int length = input.readInt(true);
        LongIntMap data = new LongIntMap(length);
        for (int i = 0; i < length; i++)
            data.put(input.readVarLong(false), input.readVarInt(false));
        return data;
    }

    @Override
    public LongIntMap copy(Kryo kryo, LongIntMap original) {
        return new LongIntMap(original);
    }
}