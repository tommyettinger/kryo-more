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

import com.badlogic.gdx.math.Rectangle;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * Kryo {@link Serializer} for libGDX {@link Rectangle}s.
 */
public class RectangleSerializer extends Serializer<Rectangle> {

    public RectangleSerializer() {
        setImmutable(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final Rectangle data) {
        output.writeFloat(data.x);
        output.writeFloat(data.y);
        output.writeFloat(data.width);
        output.writeFloat(data.height);
    }

    @Override
    public Rectangle read(final Kryo kryo, final Input input, final Class<? extends Rectangle> dataClass) {
        return new Rectangle(input.readFloat(), input.readFloat(), input.readFloat(), input.readFloat());
    }

    @Override
    public Rectangle copy(Kryo kryo, Rectangle original) {
        return new Rectangle(original);
    }
}