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

import com.badlogic.gdx.math.Matrix3;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * Kryo {@link Serializer} for libGDX {@link Matrix3}s.
 */
public class Matrix3Serializer extends Serializer<Matrix3> {

    public Matrix3Serializer() {
        setImmutable(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final Matrix3 data) {
        for (int i = 0; i < 9; i++) {
            output.writeFloat(data.val[i]);
        }
    }

    @Override
    public Matrix3 read(final Kryo kryo, final Input input, final Class<? extends Matrix3> dataClass) {
        Matrix3 m = new Matrix3();
        for (int i = 0; i < 9; i++) {
            m.val[i] = input.readFloat();
        }
        return m;
    }

    @Override
    public Matrix3 copy(Kryo kryo, Matrix3 original) {
        return new Matrix3(original);
    }
}