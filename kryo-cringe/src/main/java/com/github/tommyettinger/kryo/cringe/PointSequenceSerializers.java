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
    
    /**
     * Kryo {@link Serializer} for cringe {@link Halton4}s.
     */
    public static class Halton4Serializer extends Serializer<Halton4> {

        public Halton4Serializer() {
            setImmutable(false);
            setAcceptsNull(false);
        }

        @Override
        public void write(final Kryo kryo, final Output output, final Halton4 data) {
            output.writeInt(data.baseX, true);
            output.writeInt(data.baseY, true);
            output.writeInt(data.baseZ, true);
            output.writeInt(data.baseW, true);
            output.writeInt(data.index, false);
        }

        @Override
        public Halton4 read(final Kryo kryo, final Input input, final Class<? extends Halton4> dataClass) {
            return new Halton4(input.readInt(true), input.readInt(true), input.readInt(true),
                    input.readInt(true), input.readInt(false));
        }

        @Override
        public Halton4 copy(Kryo kryo, Halton4 original) {
            return original.copy();
        }
    }
    
    /**
     * Kryo {@link Serializer} for cringe {@link Halton5}s.
     */
    public static class Halton5Serializer extends Serializer<Halton5> {

        public Halton5Serializer() {
            setImmutable(false);
            setAcceptsNull(false);
        }

        @Override
        public void write(final Kryo kryo, final Output output, final Halton5 data) {
            output.writeInt(data.baseX, true);
            output.writeInt(data.baseY, true);
            output.writeInt(data.baseZ, true);
            output.writeInt(data.baseW, true);
            output.writeInt(data.baseU, true);
            output.writeInt(data.index, false);
        }

        @Override
        public Halton5 read(final Kryo kryo, final Input input, final Class<? extends Halton5> dataClass) {
            return new Halton5(input.readInt(true), input.readInt(true), input.readInt(true),
                    input.readInt(true), input.readInt(true), input.readInt(false));
        }

        @Override
        public Halton5 copy(Kryo kryo, Halton5 original) {
            return original.copy();
        }
    }
    
    /**
     * Kryo {@link Serializer} for cringe {@link Halton6}s.
     */
    public static class Halton6Serializer extends Serializer<Halton6> {

        public Halton6Serializer() {
            setImmutable(false);
            setAcceptsNull(false);
        }

        @Override
        public void write(final Kryo kryo, final Output output, final Halton6 data) {
            output.writeInt(data.baseX, true);
            output.writeInt(data.baseY, true);
            output.writeInt(data.baseZ, true);
            output.writeInt(data.baseW, true);
            output.writeInt(data.baseU, true);
            output.writeInt(data.baseV, true);
            output.writeInt(data.index, false);
        }

        @Override
        public Halton6 read(final Kryo kryo, final Input input, final Class<? extends Halton6> dataClass) {
            return new Halton6(input.readInt(true), input.readInt(true), input.readInt(true),
                    input.readInt(true), input.readInt(true), input.readInt(true), input.readInt(false));
        }

        @Override
        public Halton6 copy(Kryo kryo, Halton6 original) {
            return original.copy();
        }
    }
    
    /**
     * Kryo {@link Serializer} for cringe {@link R2}s.
     */
    public static class R2Serializer extends Serializer<R2> {

        public R2Serializer() {
            setImmutable(false);
            setAcceptsNull(false);
        }

        @Override
        public void write(final Kryo kryo, final Output output, final R2 data) {
            output.writeFloat(data.x);
            output.writeFloat(data.y);
        }

        @Override
        public R2 read(final Kryo kryo, final Input input, final Class<? extends R2> dataClass) {
            return new R2(input.readFloat(), input.readFloat());
        }

        @Override
        public R2 copy(Kryo kryo, R2 original) {
            return original.copy();
        }
    }

    /**
     * Kryo {@link Serializer} for cringe {@link R3}s.
     */
    public static class R3Serializer extends Serializer<R3> {

        public R3Serializer() {
            setImmutable(false);
            setAcceptsNull(false);
        }

        @Override
        public void write(final Kryo kryo, final Output output, final R3 data) {
            output.writeFloat(data.x);
            output.writeFloat(data.y);
            output.writeFloat(data.z);
        }

        @Override
        public R3 read(final Kryo kryo, final Input input, final Class<? extends R3> dataClass) {
            return new R3(input.readFloat(), input.readFloat(), input.readFloat());
        }

        @Override
        public R3 copy(Kryo kryo, R3 original) {
            return original.copy();
        }
    }
}