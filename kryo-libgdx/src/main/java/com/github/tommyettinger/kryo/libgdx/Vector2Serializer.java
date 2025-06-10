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

import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * Kryo {@link Serializer} for libGDX {@link Vector2}s.
 */
public class Vector2Serializer extends Serializer<Vector2> {

    public Vector2Serializer() {
        setImmutable(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final Vector2 data) {
        output.writeFloat(data.x);
        output.writeFloat(data.y);
    }

    @Override
    public Vector2 read(final Kryo kryo, final Input input, final Class<? extends Vector2> dataClass) {
        return new Vector2(input.readFloat(), input.readFloat());
    }

    @Override
    public Vector2 copy(Kryo kryo, Vector2 original) {
        return new Vector2(original);
    }
}