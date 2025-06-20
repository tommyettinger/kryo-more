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

import com.badlogic.gdx.graphics.Color;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * Kryo {@link Serializer} for libGDX {@link Color}s.
 */
public class ColorSerializer extends Serializer<Color> {

    public ColorSerializer() {
        setImmutable(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final Color data) {
        output.writeFloat(data.r);
        output.writeFloat(data.g);
        output.writeFloat(data.b);
        output.writeFloat(data.a);
    }

    @Override
    public Color read(final Kryo kryo, final Input input, final Class<? extends Color> dataClass) {
        return new Color(input.readFloat(), input.readFloat(), input.readFloat(), input.readFloat());
    }

    @Override
    public Color copy(Kryo kryo, Color original) {
        return new Color(original);
    }
}