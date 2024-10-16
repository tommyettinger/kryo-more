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

package com.github.tommyettinger.kryo.cringe;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.github.tommyettinger.cringe.PointSequence.*;

/**
 * Outer class containing nothing of its own except for many inner Kryo Serializer classes.
 */
public final class PointSequenceSerializers {

    private PointSequenceSerializers() {
    }

    /**
     * Kryo {@link Serializer} for cringe {@link Halton2}s.
     */
    public static class Halton2Serializer extends Serializer<Halton2> {

        public Halton2Serializer() {
            setImmutable(false);
            setAcceptsNull(false);
        }

        @Override
        public void write(final Kryo kryo, final Output output, final Halton2 data) {
            output.writeInt(data.baseX, true);
            output.writeInt(data.baseY, true);
            output.writeInt(data.index, false);
        }

        @Override
        public Halton2 read(final Kryo kryo, final Input input, final Class<? extends Halton2> dataClass) {
            return new Halton2(input.readInt(true), input.readInt(true), input.readInt(false));
        }

        @Override
        public Halton2 copy(Kryo kryo, Halton2 original) {
            return original.copy();
        }
    }

    /**
     * Kryo {@link Serializer} for cringe {@link Halton3}s.
     */
    public static class Halton3Serializer extends Serializer<Halton3> {

        public Halton3Serializer() {
            setImmutable(false);
            setAcceptsNull(false);
        }

        @Override
        public void write(final Kryo kryo, final Output output, final Halton3 data) {
            output.writeInt(data.baseX, true);
            output.writeInt(data.baseY, true);
            output.writeInt(data.baseZ, true);
            output.writeInt(data.index, false);
        }

        @Override
        public Halton3 read(final Kryo kryo, final Input input, final Class<? extends Halton3> dataClass) {
            return new Halton3(input.readInt(true), input.readInt(true), input.readInt(true), input.readInt(false));
        }

        @Override
        public Halton3 copy(Kryo kryo, Halton3 original) {
            return original.copy();
        }
    }
}