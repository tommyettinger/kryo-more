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

import com.badlogic.gdx.math.Polygon;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * Kryo {@link Serializer} for libGDX {@link Polygon}s.
 */
public class PolygonSerializer extends Serializer<Polygon> {

    public PolygonSerializer() {
        setImmutable(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final Polygon data) {
        float[] vertices = data.getVertices();
        output.writeInt(vertices.length, true);
        output.writeFloats(vertices, 0, vertices.length);
        output.writeFloat(data.getOriginX());
        output.writeFloat(data.getOriginY());
        output.writeFloat(data.getX());
        output.writeFloat(data.getY());
        output.writeFloat(data.getRotation());
        output.writeFloat(data.getScaleX());
        output.writeFloat(data.getScaleY());
    }

    @Override
    public Polygon read(final Kryo kryo, final Input input, final Class<? extends Polygon> dataClass) {
        final int len = input.readInt(true);
        Polygon data = new Polygon(input.readFloats(len));
        data.setOrigin(input.readFloat(), input.readFloat());
        data.setPosition(input.readFloat(), input.readFloat());
        data.setRotation(input.readFloat());
        data.setScale(input.readFloat(), input.readFloat());
        return data;
    }

    @Override
    public Polygon copy(Kryo kryo, Polygon original) {
        Polygon data = new Polygon(original.getVertices());
        data.setOrigin(original.getOriginX(), original.getOriginY());
        data.setPosition(original.getX(), original.getY());
        data.setRotation(original.getRotation());
        data.setScale(original.getScaleX(), original.getScaleY());
        return data;
    }
}