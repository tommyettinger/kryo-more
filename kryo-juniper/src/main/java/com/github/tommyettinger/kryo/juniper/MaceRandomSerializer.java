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
import com.github.tommyettinger.random.MaceRandom;

/**
 * Kryo {@link Serializer} for juniper {@link MaceRandom}s.
 */
public class MaceRandomSerializer extends Serializer<MaceRandom> {

    public MaceRandomSerializer() {
        setImmutable(false);
        setAcceptsNull(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final MaceRandom data) {
        output.writeVarInt(data.getStreamIdentifier(), true);
        output.writeVarLong(data.getStateA(), false);
        output.writeVarLong(data.getStateB(), false);
        output.writeVarLong(data.getStateC(), false);
        output.writeVarLong(data.getStateD(), false);
        output.writeVarLong(data.getStateE(), false);
    }

    @Override
    public MaceRandom read(final Kryo kryo, final Input input, final Class<? extends MaceRandom> dataClass) {
        return new MaceRandom(input.readVarInt(true), input.readVarLong(false), input.readVarLong(false),
                input.readVarLong(false), input.readVarLong(false), input.readVarLong(false));
    }

    @Override
    public MaceRandom copy(Kryo kryo, MaceRandom original) {
        return original.copy();
    }
}