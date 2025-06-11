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
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.math.collision.OrientedBoundingBox;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * Kryo {@link Serializer} for libGDX {@link OrientedBoundingBox}s.
 */
public class OrientedBoundingBoxSerializer extends Serializer<OrientedBoundingBox> {

    public OrientedBoundingBoxSerializer() {
        setImmutable(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final OrientedBoundingBox data) {
        final BoundingBox bb = data.getBounds();
        output.writeFloat(bb.min.x);
        output.writeFloat(bb.min.y);
        output.writeFloat(bb.min.z);
        output.writeFloat(bb.max.x);
        output.writeFloat(bb.max.y);
        output.writeFloat(bb.max.z);
        float[] val = data.transform.val;
        for (int i = 0; i < 16; i++) {
            output.writeFloat(val[i]);
        }
    }

    @Override
    public OrientedBoundingBox read(final Kryo kryo, final Input input, final Class<? extends OrientedBoundingBox> dataClass) {
        BoundingBox bb = new BoundingBox(
                new Vector3(input.readFloat(), input.readFloat(), input.readFloat()),
                new Vector3(input.readFloat(), input.readFloat(), input.readFloat()));
        final Matrix4 tr = new Matrix4();
        final float[] val = tr.val;
        for (int i = 0; i < 16; i++) {
            val[i] = input.readFloat();
        }

        return new OrientedBoundingBox(bb, tr);
    }

    @Override
    public OrientedBoundingBox copy(Kryo kryo, OrientedBoundingBox original) {
        return new OrientedBoundingBox(original.getBounds(), original.getTransform());
    }
}