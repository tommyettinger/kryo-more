/*
 * Copyright (c) 2025 See AUTHORS file.
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

package com.github.tommyettinger.kryo.libgdx;

import com.badlogic.gdx.math.Affine2;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * Kryo {@link Serializer} for libGDX {@link Affine2}s.
 */
public class Affine2Serializer extends Serializer<Affine2> {

    public Affine2Serializer() {
        setImmutable(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final Affine2 data) {
        output.writeFloat(data.m00);
        output.writeFloat(data.m01);
        output.writeFloat(data.m02);
        output.writeFloat(data.m10);
        output.writeFloat(data.m11);
        output.writeFloat(data.m12);
    }

    @Override
    public Affine2 read(final Kryo kryo, final Input input, final Class<? extends Affine2> dataClass) {
        Affine2 data = new Affine2();
        data.m00 = input.readFloat();
        data.m01 = input.readFloat();
        data.m02 = input.readFloat();
        data.m10 = input.readFloat();
        data.m11 = input.readFloat();
        data.m12 = input.readFloat();
        return data;
    }

    @Override
    public Affine2 copy(Kryo kryo, Affine2 original) {
        return new Affine2(original);
    }
}