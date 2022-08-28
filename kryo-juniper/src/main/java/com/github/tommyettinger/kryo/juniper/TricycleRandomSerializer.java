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

package com.github.tommyettinger.kryo.juniper;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.github.tommyettinger.random.TricycleRandom;

/**
 * Kryo {@link Serializer} for juniper {@link TricycleRandom}s.
 */
public class TricycleRandomSerializer extends Serializer<TricycleRandom> {

    public TricycleRandomSerializer() {
        setImmutable(false);
        setAcceptsNull(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final TricycleRandom data) {
        output.writeVarLong(data.getStateA(), false);
        output.writeVarLong(data.getStateB(), false);
        output.writeVarLong(data.getStateC(), false);
    }

    @Override
    public TricycleRandom read(final Kryo kryo, final Input input, final Class<? extends TricycleRandom> dataClass) {
        return new TricycleRandom(input.readVarLong(false), input.readVarLong(false), input.readVarLong(false));
    }

    @Override
    public TricycleRandom copy(Kryo kryo, TricycleRandom original) {
        return original.copy();
    }
}