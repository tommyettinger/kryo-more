/*
 * Copyright (c) 2022-2023 See AUTHORS file.
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
import com.github.tommyettinger.random.LaserRandom;

/**
 * Kryo {@link Serializer} for juniper {@link LaserRandom}s.
 */
public class LaserRandomSerializer extends Serializer<LaserRandom> {

    public LaserRandomSerializer() {
        setImmutable(false);
        setAcceptsNull(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final LaserRandom data) {
        output.writeVarLong(data.getStateA(), false);
        output.writeVarLong(data.getStateB(), false);
    }

    @Override
    public LaserRandom read(final Kryo kryo, final Input input, final Class<? extends LaserRandom> dataClass) {
        return new LaserRandom(input.readVarLong(false), input.readVarLong(false));
    }

    @Override
    public LaserRandom copy(Kryo kryo, LaserRandom original) {
        return original.copy();
    }
}