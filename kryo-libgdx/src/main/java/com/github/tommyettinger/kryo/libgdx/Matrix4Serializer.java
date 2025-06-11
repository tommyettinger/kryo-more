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

import com.badlogic.gdx.math.Matrix4;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * Kryo {@link Serializer} for libGDX {@link Matrix4}s.
 */
public class Matrix4Serializer extends Serializer<Matrix4> {

    public Matrix4Serializer() {
        setImmutable(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final Matrix4 data) {
        for (int i = 0; i < 16; i++) {
            output.writeFloat(data.val[i]);
        }
    }

    @Override
    public Matrix4 read(final Kryo kryo, final Input input, final Class<? extends Matrix4> dataClass) {
        Matrix4 m = new Matrix4();
        for (int i = 0; i < 16; i++) {
            m.val[i] = input.readFloat();
        }
        return m;
    }

    @Override
    public Matrix4 copy(Kryo kryo, Matrix4 original) {
        return new Matrix4(original);
    }
}